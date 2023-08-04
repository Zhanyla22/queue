package com.example.queue.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            populateParams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateParams() throws IOException {
        String fileName = "config.properties";
        InputStream inputStream = new FileInputStream(fileName);
        properties.load(inputStream);
    }

    public static String getParamValue(String key) {
        return properties.getProperty(key);
    }
}
