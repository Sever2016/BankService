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
        double totalInvestOperations = transactionRepository.getInformationAboutInvest(userId);
        Integer totalSavingDeposit = transactionRepository.getInformationAboutSaving(userId);

        if (totalDebitOperations > 0 && totalInvestOperations < 0 && totalSavingDeposit != null && totalSavingDeposit > 1000) {
            return Optional.of(new RecommendationDto(userId,
                    "Invest 500",
                    "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!"));
        }
        return Optional.empty();
    }
}