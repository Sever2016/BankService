package ru.start.bank.controller;

import ru.start.bank.service.DynamicRuleService;

public class DynamicRecommendationController {
    private final DynamicRuleService dynamicRuleService;

    public DynamicRecommendationController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
    }
}
