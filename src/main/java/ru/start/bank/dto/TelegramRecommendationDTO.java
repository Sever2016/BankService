package ru.start.bank.dto;

import java.util.List;

public class TelegramRecommendationDTO {

    public String fullName;
    private List<String> recommendations;

    public TelegramRecommendationDTO() {
    }

    public TelegramRecommendationDTO(String fullName, List<String> recommendations) {
        this.fullName = fullName;
        this.recommendations = recommendations;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}
