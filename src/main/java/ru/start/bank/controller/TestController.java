package ru.start.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.start.bank.repository.Test;

import java.util.UUID;

@RestController
public class TestController {

    private final Test test;

    public TestController(Test test) {
        this.test = test;
    }

    @GetMapping("/int")
    public int get() {
        return test.getRandomTransactionAmount(UUID.fromString("f37ba8a8-3cd5-4976-9f74-2b21f105da67"));
    }
}
