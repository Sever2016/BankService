package ru.start.bank.rule;

import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.repository.TransactionRepository2;

import java.util.Optional;
import java.util.UUID;

@Component
public class Invest500 implements RecommendationRuleSet {

    private final TransactionRepository2 transactionRepository;

    public Invest500(TransactionRepository2 transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        double totalDebitOperations = transactionRepository.getInformationAboutDebit(userId);

        if (totalDebitOperations > 0) {
            return Optional.of(new RecommendationDto("rec1", "Кредитный продукт", "Рекомендуем вам ознакомиться с нашим кредитным предложением."));
        }

        return Optional.empty();
    }
}