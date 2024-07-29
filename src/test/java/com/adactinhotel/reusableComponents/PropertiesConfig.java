package com.adactinhotel.reusableComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {

    private static final Properties properties = new Properties();
    private static final String propertiesPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

    static {

        try (FileInputStream inputStream = new FileInputStream(propertiesPath)) {
            properties.load(inputStream);

        } catch (IOException e) {
            CustomException.handleException("Could not load properties file", e);
        }
    }

    public static String getPropertyKey(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            CustomException.handleException("getPropertyKey", new IOException("Invalid key specified " + key + " on properties file"));

            return null;
        }

        return value;
    }
}
