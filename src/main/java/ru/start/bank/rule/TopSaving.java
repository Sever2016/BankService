package ru.start.bank.rule;


import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;

import java.util.Optional;
import java.util.UUID;

@Component
public class TopSaving implements RecommendationRuleSet {

    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        return Optional.empty();
    }
}