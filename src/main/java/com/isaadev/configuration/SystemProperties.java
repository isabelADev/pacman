package com.isaadev.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {

    private static final Properties systemProperties = loadProperties();

    private static Properties loadProperties() {
        Properties systemProperties = new Properties();
        try (InputStream input = SystemProperties.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }

            //load a properties file from class path, inside static method
            systemProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return systemProperties;
    }

    public static String getProperty(String propertyName) {
        return systemProperties.getProperty(propertyName);
    }
}
