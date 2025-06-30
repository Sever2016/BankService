package ru.start.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.start.bank.dto.RecommendationResponse;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;
import ru.start.bank.rule.DynamicRuleSet;
import ru.start.bank.service.DynamicRuleService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dynamicRecommendations")
public class DynamicRecommendationController {
    private final DynamicRuleService dynamicRuleService;

    public DynamicRecommendationController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
    }

    @PostMapping("/rule")
    public ResponseEntity<DynamicRecommendationRuleEntity> addRule (@RequestBody DynamicRecommendationRuleEntity dynamicRecommendationRuleEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(dynamicRuleService.addRule(dynamicRecommendationRuleEntity));
    }

    @DeleteMapping("/delete_rule/{productId}")
    public ResponseEntity<String> deleteRule(@PathVariable UUID productId){
        dynamicRuleService.deleteRule(productId);
        return ResponseEntity.status(204).body("No content");
    }

    @GetMapping("/all_Rules")
    ResponseEntity<Map<String, List<DynamicRecommendationRuleEntity>>>getAllRules(){
        List<DynamicRecommendationRuleEntity> rules = dynamicRuleService.getAllRules();
        return ResponseEntity.ok(Map.of("Rules",rules));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RecommendationResponse> getRecommendations(@PathVariable UUID userId) {
        RecommendationResponse recommendations = dynamicRuleService.getRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }
}


