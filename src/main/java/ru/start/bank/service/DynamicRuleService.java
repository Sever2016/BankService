package ru.start.bank.service;

import org.springframework.stereotype.Service;
import ru.start.bank.repository.DynamicRuleRepository;

@Service
public class DynamicRuleService {

    public DynamicRuleService(DynamicRuleRepository dynamicRuleRepository) {
        this.dynamicRuleRepository = dynamicRuleRepository;
    }

    private final DynamicRuleRepository dynamicRuleRepository;



}
