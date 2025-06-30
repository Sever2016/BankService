package ru.start.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import ru.start.bank.dto.QueryType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dynamic_recommendation_query")
public class DynamicRecommendationQueryEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private QueryType queryType;

    @Column(name = "arguments")
    private List<String> arguments;

    @Column(name = "negate")//является пользователем или нет тру//фалс
    private boolean negate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dynamic_recommendation_rule_id")
    private DynamicRecommendationRuleEntity parentRule;

    public DynamicRecommendationQueryEntity() {
    }

    public DynamicRecommendationQueryEntity(UUID id, QueryType queryType, List<String> arguments, boolean negate) {
        this.id = id;
        this.queryType = queryType;
        this.arguments = arguments;
        this.negate = negate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public DynamicRecommendationRuleEntity getParentRule() {
        return parentRule;
    }

    public void setParentRule(DynamicRecommendationRuleEntity parentRule) {
        this.parentRule = parentRule;
    }
}
