package ru.start.bank.service;

import org.springframework.stereotype.Service;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.dto.RecommendationResponse;
import ru.start.bank.rule.RecommendationRuleSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    private final List<RecommendationRuleSet> ruleSets;

    public RecommendationService(List<RecommendationRuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    public RecommendationResponse getRecommendations(String userId) {
        List<RecommendationDto> recommendations = new ArrayList<>();

        for (RecommendationRuleSet ruleSet : ruleSets) {
            Optional<RecommendationDto> recOpt = ruleSet.check(userId);
            recOpt.ifPresent(recommendations::add);
        }

        return new RecommendationResponse(userId, recommendations);
    }
}