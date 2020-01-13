package com.foxminded.university.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foxminded.university.exception.PropertyReaderException;

public class PropertyReader {
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    public static Properties readProperties(String propertiesFileName) {
        Properties dbProperties = new Properties();

        try (InputStream fileInput = ClassLoader.getSystemClassLoader().getResourceAsStream(propertiesFileName)) {
            logger.debug("readProperties() [{}]", propertiesFileName);
            dbProperties.load(fileInput);
        } catch (IOException e) {
            throw new PropertyReaderException();
        }
        logger.trace("Result: [true] ");
        return dbProperties;
    }
}
