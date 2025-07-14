package ru.start.bank.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.dto.RecommendationResponse;
import ru.start.bank.entity.DynamicRecommendationQueryEntity;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;
import ru.start.bank.repository.DynamicQueryRepository;
import ru.start.bank.repository.DynamicRuleRepository;
import ru.start.bank.rule.DynamicRuleSet;
import ru.start.bank.rule.RecommendationRuleSet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DynamicRuleService {

    public DynamicRuleService(List<RecommendationRuleSet> ruleSets, DynamicRuleRepository dynamicRuleRepository, DynamicQueryRepository dynamicQueryRepository, DynamicRuleSet dynamicRuleSet, RuleStatsService ruleStatsService) {
        this.dynamicRuleRepository = dynamicRuleRepository;
        this.dynamicQueryRepository = dynamicQueryRepository;
        this.dynamicRuleSet = dynamicRuleSet;
        this.ruleStatsService = ruleStatsService;
        this.ruleSets = ruleSets;
    }

    private final DynamicQueryRepository dynamicQueryRepository;
    private final DynamicRuleRepository dynamicRuleRepository;
    private final DynamicRuleSet dynamicRuleSet;
    private final RuleStatsService ruleStatsService;
    private final List<RecommendationRuleSet> ruleSets;

    public List<DynamicRecommendationRuleEntity> getAllRules() {
        return dynamicRuleRepository.findAll();
    }

    @Transactional
    public DynamicRecommendationRuleEntity addRule(DynamicRecommendationRuleEntity dynamicRecommendationRuleEntity) {
        for (DynamicRecommendationQueryEntity dynamicRecommendationQueryEntity : (dynamicRecommendationRuleEntity.getQueries())) {
            DynamicRecommendationQueryEntity newDynamicRecommendationQueryEntity = new DynamicRecommendationQueryEntity();
            newDynamicRecommendationQueryEntity.setParentRule(dynamicRecommendationRuleEntity);
            newDynamicRecommendationQueryEntity.setArguments(dynamicRecommendationQueryEntity.getArguments());
            newDynamicRecommendationQueryEntity.setQueryType(dynamicRecommendationQueryEntity.getQueryType());
            newDynamicRecommendationQueryEntity.setNegate(dynamicRecommendationQueryEntity.isNegate());
            dynamicQueryRepository.save(newDynamicRecommendationQueryEntity);
        }
        return dynamicRuleRepository.save(dynamicRecommendationRuleEntity);
    }

    @Transactional
    public void deleteRule(UUID productId) {
        DynamicRecommendationRuleEntity dynamicRecommendationRuleEntity = dynamicRuleRepository.findByProductId(productId);
        dynamicQueryRepository.deleteByDynamicRecommendationRuleId(dynamicRecommendationRuleEntity.getId());
        dynamicRuleRepository.deleteByProductId(productId);
    }

    public RecommendationResponse getRecommendations(UUID userId) {
        List<RecommendationDto> recommendations = dynamicRuleSet.apply(userId);

        for (RecommendationRuleSet ruleSet : ruleSets) {
            Optional<RecommendationDto> recOpt = ruleSet.check(userId);
            recOpt.ifPresent(rec -> {
                recommendations.add(rec);
                ruleStatsService.incrementRuleCounter(rec.getId());
            });
        }

        return new RecommendationResponse(userId, recommendations);
    }

}
