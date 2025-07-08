package ru.start.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class RuleStats {
    @Id
    private UUID id;

    private Long count;

    public RuleStats() {
    }

    public RuleStats(UUID id, Long count) {
        this.id = id;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}