package ru.start.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.start.bank.service.DynamicRuleService;

@RestController
@RequestMapping("/dynamicRecommendations")
public class DynamicRecommendationController {
    private final DynamicRuleService dynamicRuleService;

    public DynamicRecommendationController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
    }


}
