package ru.start.bank.rule;


import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.repository.TransactionRepository2;

import java.util.Optional;
import java.util.UUID;

@Component
public class TopSaving implements RecommendationRuleSet {

    private final TransactionRepository2 transactionRepository;

    public TopSaving(TransactionRepository2 transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        double totalDebitOperations = transactionRepository.getInformationAboutDebit(userId);
        Integer sumDepositDebit = transactionRepository.getInformationAboutSumDepositDebit(userId);
        Integer sumSaving = transactionRepository.getInformationAboutSumDepositSaving(userId);
        Integer sumWithdrawDebit = transactionRepository.getInformationAboutSumWithdrawDebit(userId);

        if (totalDebitOperations > 0
                && (sumDepositDebit >= 50000 || sumSaving >= 50000)
                && sumDepositDebit > sumWithdrawDebit) {
            return Optional.of(new RecommendationDto(
                    userId,
                    "Top Saving",
                    "Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем! Преимущества «Копилки»:Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!"));
        }
        return Optional.empty();
    }
}