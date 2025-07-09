package ru.start.bank.service;

import org.springframework.stereotype.Service;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.dto.RecommendationResponse;
import ru.start.bank.rule.RecommendationRuleSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecommendationService {
    private final List<RecommendationRuleSet> ruleSets;
    private final RuleStatsService ruleStatsService;

    public RecommendationService(List<RecommendationRuleSet> ruleSets, RuleStatsService ruleStatsService) {
        this.ruleSets = ruleSets;
        this.ruleStatsService = ruleStatsService;
    }

    public RecommendationResponse getRecommendations(UUID userId) {
        List<RecommendationDto> recommendations = new ArrayList<>();

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