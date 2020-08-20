package com.aurel.service;

import com.aurel.repository.HelloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HelloServiceMockTest {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService = new HelloServiceImpl();

    @BeforeEach
    void setUp() {
        when(helloRepository.get()).thenReturn("Hello Mockito From Repository");
    }

    @Test
    void get() {
        assertEquals("Hello Mockito From Repository", helloService.get());
    }
}