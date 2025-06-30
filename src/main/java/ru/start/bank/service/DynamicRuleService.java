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

import java.util.List;
import java.util.UUID;

@Service
public class DynamicRuleService {

    public DynamicRuleService(DynamicRuleRepository dynamicRuleRepository, DynamicQueryRepository dynamicQueryRepository, DynamicRuleSet dynamicRuleSet) {
        this.dynamicRuleRepository = dynamicRuleRepository;
        this.dynamicQueryRepository = dynamicQueryRepository;
        this.dynamicRuleSet = dynamicRuleSet;
    }

    private final DynamicQueryRepository dynamicQueryRepository;
    private final DynamicRuleRepository dynamicRuleRepository;
    private final DynamicRuleSet dynamicRuleSet;

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
        return new RecommendationResponse(userId, recommendations);
    }

}
