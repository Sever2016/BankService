package ru.start.bank.rule;


import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;

import java.util.Optional;

@Component
public class Recommendation2RuleSet implements RecommendationRuleSet {

    @Override
    public Optional<RecommendationDto> check(String userId) {
        if ("special_user".equals(userId)) {
            return Optional.of(new RecommendationDto("rec2", "Эксклюзивное предложение", "Только для вас!"));
        }
        return Optional.empty();
    }
}