package ru.start.bank.service;

import org.springframework.stereotype.Service;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;
import ru.start.bank.repository.DynamicRuleRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DynamicRuleService {

    public DynamicRuleService(DynamicRuleRepository dynamicRuleRepository) {
        this.dynamicRuleRepository = dynamicRuleRepository;
    }

    private final DynamicRuleRepository dynamicRuleRepository;


    public List<DynamicRecommendationRuleEntity> getAllRules() {
        return dynamicRuleRepository.findAll();
    }

    public DynamicRecommendationRuleEntity addRule(DynamicRecommendationRuleEntity rule) {
        return dynamicRuleRepository.save(rule);
    }

    public void deleteRule(UUID productId) {
        dynamicRuleRepository.deleteByProductId(productId);
    }

}
