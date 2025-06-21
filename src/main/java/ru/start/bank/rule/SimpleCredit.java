package ru.start.bank.rule;

import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.repository.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

public class SimpleCredit implements RecommendationRuleSet {

    private final TransactionRepository transactionRepository;

    public SimpleCredit(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Optional<RecommendationDto> check(UUID userId) {
        double totalCreditOperation = transactionRepository.getInformationAboutCredit(userId);
        Integer sumDepositDebit = transactionRepository.getInformationAboutSumDepositDebit(userId);
        Integer sumWithdrawDebit = transactionRepository.getInformationAboutSumWithdrawDebit(userId);

        if (totalCreditOperation == 0
                && sumDepositDebit > sumWithdrawDebit
                && sumWithdrawDebit > 100_000) {
            return Optional.of(new RecommendationDto(userId,
                    "Simple Credit",
                    "Откройте мир выгодных кредитов с нами! Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту. +Почему выбирают нас:Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!"));
        }
        return Optional.empty();
    }
}
