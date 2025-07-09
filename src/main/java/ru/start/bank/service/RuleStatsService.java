package ru.start.bank.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.start.bank.entity.RuleStats;
import ru.start.bank.repository.RuleStatsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RuleStatsService {
    private final RuleStatsRepository ruleStatsRepository;

    public RuleStatsService(RuleStatsRepository ruleStatsRepository) {
        this.ruleStatsRepository = ruleStatsRepository;
    }

    @Transactional
    public void incrementRuleCounter(UUID ruleId) {
        RuleStats stats = ruleStatsRepository.findById(ruleId).orElse(new RuleStats(ruleId, 0L));
        stats.setCount(stats.getCount() + 1);
        ruleStatsRepository.save(stats);
    }

    public List<RuleStats> getAllStats() {
        return ruleStatsRepository.findAll();
    }

    @Transactional
    public void deleteRuleStats(UUID ruleId) {
        ruleStatsRepository.deleteById(ruleId);
    }
}
