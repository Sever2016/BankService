package ru.start.bank.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.start.bank.dto.RecommendationDto;
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

    @Transactional
    public RecommendationDto addRule(RecommendationDto dto) {
        DynamicRecommendationRuleEntity entity = new DynamicRecommendationRuleEntity();
        entity.setName(dto.getName());
        entity.setProductText(dto.getText());
        entity.setProductId(UUID.randomUUID());

        DynamicRecommendationRuleEntity saved = dynamicRuleRepository.save(entity);
        return new RecommendationDto(saved.getId(), saved.getName(), saved.getProductText());
    }


    public void deleteRule(UUID productId) {
        dynamicRuleRepository.deleteByProductId(productId);
    }

    private DynamicRecommendationRuleEntity mapToEntity(RecommendationDto dto) {
        DynamicRecommendationRuleEntity entity = new DynamicRecommendationRuleEntity();
        entity.setName(dto.getName());
        entity.setProductText(dto.getText());
        return entity;
    }

    private RecommendationDto mapToDto(DynamicRecommendationRuleEntity entity) {
        return new RecommendationDto(entity.getId(), entity.getName(), entity.getProductText());
    }

}
