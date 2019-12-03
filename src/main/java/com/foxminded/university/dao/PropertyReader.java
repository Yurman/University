package com.foxminded.university.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties readProperties(String propertiesFileName) {
        Properties dbProperties = new Properties();

        try (InputStream fileInput = ClassLoader.getSystemClassLoader().getResourceAsStream(propertiesFileName)) {
            dbProperties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbProperties;
    }
}
