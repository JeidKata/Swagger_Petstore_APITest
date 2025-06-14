package com.globant.swaggerPetstoreAPI.config;

import lombok.Getter;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestRunner {

    public static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    @Getter
    private static String apiUrl;
    @Getter
    private static String apiKey;

    @BeforeSuite
    public void setup() {
        loadProperties();
        apiUrl = PROPERTIES.getProperty("url");
        apiKey = PROPERTIES.getProperty("apiKey");
    }

    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(fileInputStream);
        }catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
