package com.ducati.test;

import static org.junit.Assert.*;

public class RunnerTest {

    @org.junit.Test
    public void testLogSomething() throws Exception {
        new Runner().logSomething("Hello ... ","How are you");
    }
}