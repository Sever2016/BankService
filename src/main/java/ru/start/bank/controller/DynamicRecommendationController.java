package ru.start.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.service.DynamicRuleService;

@RestController
@RequestMapping("/dynamicRecommendations")
public class DynamicRecommendationController {
    private final DynamicRuleService dynamicRuleService;

    public DynamicRecommendationController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
    }

    @PostMapping("/rule")
    public ResponseEntity<RecommendationDto> addRule (@RequestBody RecommendationDto recommendationDto){
        RecommendationDto savedDto = dynamicRuleService.addRule(recommendationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);

    }
}
