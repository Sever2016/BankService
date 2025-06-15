package ru.start.bank.rule;

import ru.start.bank.dto.RecommendationDto;

import java.util.Optional;

public interface RecommendationRuleSet {
    Optional<RecommendationDto> check(String userId);
}