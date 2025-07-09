package ru.start.bank.rule;

import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.entity.DynamicRecommendationQueryEntity;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;
import ru.start.bank.repository.DynamicRuleRepository;
import ru.start.bank.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DynamicRuleSet implements RecommendationRuleSet{
    private final TransactionRepository transactionRepository;
    private final DynamicRuleRepository dynamicRuleRepository;

    public DynamicRuleSet(TransactionRepository transactionRepository, DynamicRuleRepository dynamicRuleRepository) {
        this.transactionRepository = transactionRepository;
        this.dynamicRuleRepository = dynamicRuleRepository;
    }

    public List<RecommendationDto> apply(UUID userId) {
        List<RecommendationDto> recommendations = new ArrayList<>();
        List<DynamicRecommendationRuleEntity> rules = dynamicRuleRepository.findAll();

        for (DynamicRecommendationRuleEntity rule : rules) {
            boolean isRuleValid = true;

            for (DynamicRecommendationQueryEntity query : rule.getQueries()) {
                boolean result = false;

                switch (query.getQueryType()) {
                    case USER_OF -> {
                        String productType = query.getArguments().get(0);
                        result = query.isNegate() != transactionRepository.isUserOf(userId, productType);
                    }
                    case ACTIVE_USER_OF -> {
                        String productType = query.getArguments().get(0);
                        result = query.isNegate() != transactionRepository.isActiveUserOf(userId, productType);

                    }
                    case TRANSACTION_SUM_COMPARE -> {
                        String productType = query.getArguments().get(0);
                        String transactionType = query.getArguments().get(1);
                        String operator = query.getArguments().get(2);
                        int value = Integer.parseInt(query.getArguments().get(3));
                        result = query.isNegate() != transactionRepository.transactionSumCompare(userId, productType, transactionType, operator, value);
                    }
                    case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW -> {
                        String productType = query.getArguments().get(0);
                        String operator = query.getArguments().get(1);
                        result = query.isNegate() != transactionRepository.transactionSumCompareDepositWithdraw(userId, productType, operator);
                    }
                }

                if (!result) {
                    isRuleValid = false;
                    break;
                }
            }
            if (isRuleValid) {
                RecommendationDto recommendation = new RecommendationDto();
                recommendation.setId(rule.getId());
                recommendation.setName(rule.getProductName());
                recommendation.setText(rule.getProductText());
                recommendations.add(recommendation);
            }
        }
        return recommendations;
    }

    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        return Optional.empty();
    }
}
