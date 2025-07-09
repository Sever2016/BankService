package ru.start.bank.repository;

import org.h2.bnf.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.start.bank.entity.RuleStats;

import java.util.UUID;

public interface RuleStatsRepository extends JpaRepository<RuleStats,UUID> {
    void deleteById(UUID ruleId);
}
