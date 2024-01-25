package com.veeva.utility;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;

public class YamlUtility {
    private static HashMap<String, Object> yaml;

    YamlUtility(InputStream inputStream){
        yaml = new Yaml().load(inputStream);
    }

    //convert to multi-dimension array
    public Object readYaml(String key){
        return yaml.getOrDefault(key, "");
    }

    public HashMap<String, Object> getAllTestData(){
        return yaml;
    }
}
