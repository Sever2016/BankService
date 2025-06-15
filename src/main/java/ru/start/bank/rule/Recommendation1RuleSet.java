package ru.start.bank.rule;

import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.repository.TransactionRepository;

import java.util.Optional;

@Component
public class Recommendation1RuleSet implements RecommendationRuleSet {

    private final TransactionRepository transactionRepository;

    public Recommendation1RuleSet(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<RecommendationDto> check(String userId) {
        double totalDebitSum = transactionRepository.getSumByUserAndType(userId, "DEBIT");

        if (totalDebitSum > 100000) {
            return Optional.of(new RecommendationDto("rec1", "Кредитный продукт", "Рекомендуем вам ознакомиться с нашим кредитным предложением."));
        }

        return Optional.empty();
    }
}