package com.veeva.utility;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Utility class for handling YAML files.
 * This class provides methods to read and process data from YAML files.
 */
public class YamlUtility {
    private static HashMap<String, Object> yaml;

    /**
     * Constructor for YamlUtility.
     * Loads the YAML data from the provided InputStream.
     *
     * @param inputStream InputStream of the YAML file.
     */
    YamlUtility(InputStream inputStream){
        yaml = new Yaml().load(inputStream);
    }

    /**
     * Reads a value from the YAML file based on the provided key.
     *
     * @param key The key for which to retrieve the value.
     * @return The value associated with the provided key, or an empty string if the key is not found.
     */
    public Object readYaml(String key){
        return yaml.getOrDefault(key, "");
    }

    /**
     * Retrieves all data from the YAML file.
     *
     * @return A HashMap containing all the data from the YAML file.
     */
    public HashMap<String, Object> getAllTestData(){
        return yaml;
    }
}
