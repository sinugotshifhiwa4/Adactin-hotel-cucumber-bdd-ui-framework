package com.adactinhotel.reusableComponents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomException {

    private static final Logger logger = LogManager.getLogger(CustomException.class);

    public static void handleException(String methodName, Exception e) {
        logger.error("An Exception occurred while executing the method: {}", methodName, e);
    }
}
