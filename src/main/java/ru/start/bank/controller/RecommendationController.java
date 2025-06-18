package ru.start.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.start.bank.dto.RecommendationResponse;
import ru.start.bank.service.RecommendationService;

import java.util.UUID;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<RecommendationResponse> getRecommendations(@PathVariable UUID userId) {
        var response = recommendationService.getRecommendations(userId);
        return ResponseEntity.ok(response);
    }
}