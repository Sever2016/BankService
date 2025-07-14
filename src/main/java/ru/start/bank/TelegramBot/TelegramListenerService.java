package ru.start.bank.TelegramBot;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.dto.RecommendationResponse;
import ru.start.bank.entity.UserEntity;
import ru.start.bank.repository.UserRepository;
import ru.start.bank.service.DynamicRuleService;


import java.util.List;

@Component
public class TelegramListenerService implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramListenerService.class);

    private final TelegramBot telegramBot;

    private final UserRepository userRepository;

   private final DynamicRuleService recommendationService;

    public TelegramListenerService(TelegramBot telegramBot, UserRepository userRepository, DynamicRuleService recommendationService) {
        this.telegramBot = telegramBot;
        this.userRepository = userRepository;
        this.recommendationService = recommendationService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();

                if (messageText.equals("/start")) {
                    String welcomeMessage = "Добро пожаловать! Я бот, который подбирает для вас персональные рекомендации.\n\n" +
                            "Команда:\n/recommend <username> — получить рекомендации.";
                    sendMessage(chatId, welcomeMessage);

                } else if (messageText.startsWith("/recommend")) {
                    String[] parts = messageText.split("\\s+");
                    if (parts.length != 2) {
                        sendMessage(chatId, "Используйте: /recommend <username>");
                    } else {
                        String username = parts[1];
                        processRecommendation(chatId, username);
                    }
                }
            }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processRecommendation(Long chatId, String username) {
        username = username.trim();
        logger.info("Ищу пользователя с username: '{}'", username);

        List<UserEntity> users = userRepository.findByUserNameIgnoreCase(username);

        logger.info("Найдено пользователей: {}", users.size());
        users.forEach(u -> logger.info("Пользователь: {} {}", u.getUserName(), u.getFirstName()));

        if (users.size() != 1) {
            sendMessage(chatId, "Пользователь не найден");
            return;
        }

        UserEntity user = users.get(0);
        String fullName = user.getFirstName() + " " + user.getLastName();
        RecommendationResponse response = recommendationService.getRecommendations(user.getId());

        List<RecommendationDto> recs = response.getRecommendations();

        if (recs.isEmpty()) {
            sendMessage(chatId, "Здравствуйте, " + fullName + "\n\nНовых рекомендаций нет.");
            return;
        }

        StringBuilder reply = new StringBuilder();
        reply.append("Здравствуйте").append(fullName).append("\n\nНовые продукты для вас:\n");
        for (RecommendationDto rec : recs) {
            reply.append(rec.getText()).append("\n");
        }
        sendMessage(chatId, reply.toString());
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        try {
            telegramBot.execute(sendMessage);
        } catch (Exception e) {
            logger.error("Ошибка при отправке сообщения в чат {}: {}", chatId, e.getMessage());
        }
    }
}
