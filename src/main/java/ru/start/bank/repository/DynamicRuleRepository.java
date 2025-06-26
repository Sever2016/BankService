package ru.start.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.start.bank.entity.DynamicRecommendationRuleEntity;

import java.util.UUID;

@Repository
public interface DynamicRuleRepository extends JpaRepository<DynamicRecommendationRuleEntity, Long> {
    void deleteByProductId(UUID productId);
}
