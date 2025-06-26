package ru.start.bank.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.start.bank.dto.RecommendationDto;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;
import ru.start.bank.repository.DynamicRuleRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DynamicRuleService {

    public DynamicRuleService(DynamicRuleRepository dynamicRuleRepository) {
        this.dynamicRuleRepository = dynamicRuleRepository;
    }

    private final DynamicRuleRepository dynamicRuleRepository;


    public List<RecommendationDto> getAllRules() {
        return dynamicRuleRepository.findAll().stream()
                .map(entity -> new RecommendationDto(
                        entity.getProductId(),
                        entity.getName(),
                        entity.getProductText()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public RecommendationDto addRule(RecommendationDto dto) {
        System.out.println(">>> dto.getText(): " + dto.getText());

        DynamicRecommendationRuleEntity entity = new DynamicRecommendationRuleEntity();
        entity.setName(dto.getName());
        entity.setProductText(dto.getText());
        entity.setProductId(UUID.randomUUID());

        DynamicRecommendationRuleEntity saved = dynamicRuleRepository.save(entity);
        System.out.println(">>> saved.getProductText(): " + saved.getProductText());
        return new RecommendationDto(saved.getId(), saved.getName(), saved.getProductText());
    }

    @Transactional
    public void deleteRule(UUID productId) {
        dynamicRuleRepository.deleteByProductId(productId);
    }

}
