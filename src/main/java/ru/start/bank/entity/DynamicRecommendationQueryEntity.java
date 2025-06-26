package ru.start.bank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import ru.start.bank.dto.QueryType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dynamic_recommendation_query")
public class DynamicRecommendationQueryEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private QueryType queryType;

    @Column(name = "arguments")
    private String arguments;

    @Column(name = "negate")//является пользователем или нет тру//фалс
    private boolean negate;

    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    private DynamicRecommendationRuleEntity parentRule;

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

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public DynamicRecommendationRuleEntity getParentRule() {
        return parentRule;
    }

    public void setParentRule(DynamicRecommendationRuleEntity parentRule) {
        this.parentRule = parentRule;
    }

    public List<String> getParsedArguments() {
        return List.of(arguments.split(","));
    }

    public void setParsedArguments(List<String> args) {
        this.arguments = String.join(",", args);
    }
}
