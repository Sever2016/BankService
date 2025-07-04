package ru.start.bank.dto;

import java.util.List;
import java.util.UUID;

public class RecommendationResponse {
    private UUID userId;
    private List<RecommendationDto> recommendations;

    public RecommendationResponse() {
    }

    public RecommendationResponse(UUID userId, List<RecommendationDto> recommendations) {
        this.userId = userId;
        this.recommendations = recommendations;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<RecommendationDto> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationDto> recommendations) {
        this.recommendations = recommendations;
    }
}