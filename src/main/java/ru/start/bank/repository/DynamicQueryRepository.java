package ru.start.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.start.bank.entity.DynamicRecommendationQueryEntity;

import java.util.UUID;


@Repository
public interface DynamicQueryRepository extends JpaRepository<DynamicRecommendationQueryEntity, Long> {
    @Modifying
    @Query(value = "DELETE FROM dynamic_recommendation_query WHERE dynamic_recommendation_rule_id = ?", nativeQuery = true)
    void deleteByDynamicRecommendationRuleId(UUID id);
}
