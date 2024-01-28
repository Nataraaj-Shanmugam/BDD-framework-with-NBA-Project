package com.veeva.utility;

import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for handling properties files.
 * This class facilitates the reading of configuration properties from a properties file.
 */
public class PropertiesUtility {
    private final Properties properties;

    /**
     * Constructor for {@link PropertiesUtility}.
     * Loads properties from a file named 'configurations.properties' located in the resources folder.
     */
    public PropertiesUtility(){
        properties = new Properties();
        try {
            properties.load(this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("configurations.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the property value associated with the specified key. Returns an empty string if the key is not found.
     *
     * @param key The key for the property to be retrieved.
     * @return The property value or an empty string if the key is not found.
     */
    public String getProperty(String key){
        return this.getProperty(key, "");
    }

    /**
     * Retrieves the property value associated with the specified key. Returns a default value if the key is not found.
     *
     * @param key          The key for the property to be retrieved.
     * @param defaultValue The default value to return if the key is not found.
     * @return The property value or the default value if the key is not found.
     */
    public String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
}
