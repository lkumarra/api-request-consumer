package org.api.request.consumer.helpers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.Properties;
@Getter
@Slf4j
public class PropertyReaderHelper {

    private final Properties properties;

    public PropertyReaderHelper(String propertyFilePath) {
        properties = new Properties();
        try (FileReader reader = new FileReader(propertyFilePath)) {
            properties.load(reader);
        } catch (Exception e) {
            log.error("Error occurred while reading the file : {}", e.getMessage(), e);
        }
    }


    public String getProperty(String key) {
        assert key != null;
        return properties.getProperty(key);
    }
}
