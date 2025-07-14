package ru.start.bank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.start.bank.entity.RuleStats;
import ru.start.bank.service.RuleStatsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Контролер статистики использования")
@RestController
@RequestMapping("/ruleStats")
public class RuleStatsController {
    private final RuleStatsService ruleStatsService;

    public RuleStatsController(RuleStatsService ruleStatsService) {
        this.ruleStatsService = ruleStatsService;
    }

    @Operation(
            summary = "Количество срабатываний каждого правила"
    )
    @GetMapping("/stats")
    public Map<String, List<Map<String, Object>>> getStats() {
        List<Map<String, Object>> list = ruleStatsService.getAllStats().stream()
                .map(s -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("rule_id", s.getId().toString());
                    map.put("count", s.getCount());
                    return map;
                })
                .collect(Collectors.toList());

        return Map.of("stats", list);
    }
}

