package ru.start.bank.rule;

import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.repository.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class Invest500 implements RecommendationRuleSet {

    private final TransactionRepository transactionRepository;

    public Invest500(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        double totalDebitOperations = transactionRepository.getInformationAboutDebit(userId);
        double totalInvestOperations = transactionRepository.getInformationAboutInvest(userId);
        Integer totalSavingDeposit = transactionRepository.getInformationAboutSumDepositSaving(userId);

        if (totalDebitOperations > 0
                && totalInvestOperations == 0
                && totalSavingDeposit != null
                && totalSavingDeposit > 1000) {
            return Optional.of(new RecommendationDto(userId,
                    "Invest 500",
                    "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! ..."));
        }
        return Optional.empty();
    }
}