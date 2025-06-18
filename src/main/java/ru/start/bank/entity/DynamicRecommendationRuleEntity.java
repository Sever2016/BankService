package ru.start.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dynamic_recommendation_rule")
public class DynamicRecommendationRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String productName;
    @Column
    private UUID productId;
    @Lob
    private String productText;

    @OneToMany(mappedBy = "parentRule",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<DynamicRecommendationQueryEntity> queries = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
