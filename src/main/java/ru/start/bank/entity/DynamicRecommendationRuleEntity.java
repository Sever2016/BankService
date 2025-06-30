package ru.start.bank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dynamic_recommendation_rule")
public class DynamicRecommendationRuleEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String productName;

    @Column(name = "product_id")
    private UUID productId;

    @Column(columnDefinition = "TEXT")
    private String productText;

    @OneToMany(mappedBy = "parentRule")
    private List<DynamicRecommendationQueryEntity> queries;

    public DynamicRecommendationRuleEntity() {
    }

    public DynamicRecommendationRuleEntity(UUID id, String productName, UUID productId, String productText, List<DynamicRecommendationQueryEntity> queries) {
        this.id = id;
        this.productName = productName;
        this.productId = productId;
        this.productText = productText;
        this.queries = queries;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public List<DynamicRecommendationQueryEntity> getQueries() {
        return queries;
    }

    public void setQueries(List<DynamicRecommendationQueryEntity> queries) {
        this.queries = queries;
    }
}
