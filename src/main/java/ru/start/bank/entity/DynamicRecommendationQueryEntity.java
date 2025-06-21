package ru.start.bank.entity;

import jakarta.persistence.*;
import ru.start.bank.dto.QueryType;

import java.util.List;

@Entity
@Table
public class DynamicRecommendationQueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private QueryType queryType;

    @Column(name = "negate")//является пользователем или нет тру//фалс
    private boolean negate;

    @Column(name = "arguments")
    private String arguments;

    @ManyToOne
    private DynamicRecommendationRuleEntity parentRule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
