package com.veeva.utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
    Properties properties = null;

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

    public String getProperty(String key){
        return this.getProperty(key, "");
    }

    public String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
}
