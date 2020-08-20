package com.aurel.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @DisplayName("Test spring @Autowired Integration")
    @Test
    void get() {
        assertEquals("Hello JUnit 5", helloService.get());
    }
}