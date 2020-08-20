package com.aurel.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingTest {
    Greeting greeting = new Greeting();

    @Test
    void helloWord() {
        assertEquals(greeting.helloWord(), "Hello World!");
    }

    @Test
    void helloWorld() {
        assertEquals(greeting.helloWorld("Aurel"), "Hello Aurel!");
    }
}