package ru.start.bank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
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
    private String name;

    @Column(name = "product_id")
    private UUID productId;

//    @Lob
    @Column(columnDefinition = "TEXT")
//    @Type(type = "org.hibernate.type.TextType")
    private String productText;

    @OneToMany(mappedBy = "parentRule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DynamicRecommendationQueryEntity> queries = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
