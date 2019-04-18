package com.liyv.ssm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jExample.class);
    public static void main(String[] args) {
        LOGGER.debug("Debug log message");
        LOGGER.info("Info log message");
        LOGGER.error("Error log message");
    }
}
