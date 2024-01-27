package com.veeva.generic;

public interface NonSeleniumKeywords {

    String getProperty(String key);

    void writeToFile(String content, String filePath);
}
