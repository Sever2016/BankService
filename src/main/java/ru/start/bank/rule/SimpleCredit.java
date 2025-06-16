package ru.start.bank.rule;

import ru.start.bank.dto.RecommendationDto;

import java.util.Optional;
import java.util.UUID;

public class SimpleCredit implements RecommendationRuleSet{


    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        return Optional.empty();
    }
}
