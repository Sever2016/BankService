package ru.start.bank.rule;

import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.entity.DynamicRecommendationQueryEntity;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;
import ru.start.bank.repository.DynamicRuleRepository;
import ru.start.bank.repository.TransactionRepository2;

import java.util.List;
import java.util.UUID;

@Component
public class DynamicRuleSet {
    private final TransactionRepository2 transactionRepository;
    private final DynamicRuleRepository dynamicRuleRepository;

    public DynamicRuleSet(TransactionRepository2 transactionRepository, DynamicRuleRepository dynamicRuleRepository) {
        this.transactionRepository = transactionRepository;
        this.dynamicRuleRepository = dynamicRuleRepository;
    }

    public List<RecommendationDto> apply(UUID userId) {
        List<DynamicRecommendationRuleEntity> rules = dynamicRuleRepository.findAll();
        for (DynamicRecommendationRuleEntity rule : rules) {
            List<DynamicRecommendationQueryEntity> queries = rule.getQueries();
            for (DynamicRecommendationQueryEntity query : queries) {
                switch (query.getQueryType()){
                    case USER_OF -> {transactionRepository.isUserOf(userId,query.getParsedArguments().get(0));
                    }
                    case ACTIVE_USER_OF -> {
                    }
                    case TRANSACTION_SUM_COMPARE -> {
                    }
                    case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW -> {
                    }

                }
            }
        }
    }
}
