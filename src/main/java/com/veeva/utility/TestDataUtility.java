package com.veeva.utility;

import com.veeva.generic.GenericKeywords;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestDataUtility {

    private static final HashMap<String, List<HashMap<String, String>>> overAllTestData = new HashMap<>();

    private HashMap<String, Object> readTestData(){
         return new YamlUtility(this.getClass()
                .getClassLoader()
                .getResourceAsStream("TestData.yaml")).getAllTestData();
    }

    public void setTestData(){
        HashMap<String, Object> getAllTestData = readTestData();
        String environment = new PropertiesUtility().getProperty("environment", "Stage");
        for(Map.Entry<String, Object> each: getAllTestData.entrySet()) {
            List<HashMap<String, String>> testDataSet = (List<HashMap<String, String>>) ((LinkedHashMap<String, Object>) (each.getValue())).get(environment);
            if(testDataSet != null) overAllTestData.put(each.getKey(), testDataSet);
        }
    }

    public List<HashMap<String, String>> getTestData(String testCase){
        return overAllTestData.get(testCase);
    }

}
