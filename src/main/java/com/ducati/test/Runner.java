package com.ducati.test;

import org.slf4j.Logger;

/**
 * Created by Roshane on 8/22/2015.
 */
public class Runner {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Runner.class);

    public void logSomething(String... args) {
        LOGGER.debug("LOGGING [{}]", args);
    }
}
