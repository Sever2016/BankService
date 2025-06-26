package ru.start.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.rule.DynamicRuleSet;
import ru.start.bank.service.DynamicRuleService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dynamicRecommendations")
public class DynamicRecommendationController {
    private final DynamicRuleService dynamicRuleService;
    private final DynamicRuleSet dynamicRuleSet;

    public DynamicRecommendationController(DynamicRuleService dynamicRuleService, DynamicRuleSet dynamicRuleSet) {
        this.dynamicRuleService = dynamicRuleService;
        this.dynamicRuleSet = dynamicRuleSet;
    }

    @PostMapping("/rule")
    public ResponseEntity<RecommendationDto> addRule (@RequestBody RecommendationDto recommendationDto){
        RecommendationDto savedDto = dynamicRuleService.addRule(recommendationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @DeleteMapping("/delete_rule/{productId}")
    public ResponseEntity<RecommendationDto> deleteRule(@PathVariable UUID productId){
        dynamicRuleService.deleteRule(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all_Rules")
    ResponseEntity<Map<String, List<RecommendationDto>>>getAllRules(){
        List<RecommendationDto> rules= dynamicRuleService.getAllRules();
        return ResponseEntity.ok(Map.of("Rules",rules));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RecommendationDto>> getRecommendations(@PathVariable UUID userId) {
        List<RecommendationDto> recommendations = dynamicRuleSet.apply(userId);

        if (recommendations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recommendations);
    }
}


