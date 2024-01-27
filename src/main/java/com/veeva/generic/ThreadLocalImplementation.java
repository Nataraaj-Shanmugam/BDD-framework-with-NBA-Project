package com.veeva.generic;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ThreadLocalImplementation {

    private ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public void setWebDriverThreadLocal(WebDriver driver){
        webDriverThreadLocal.set(driver);
    }

    public WebDriver getWebDriver(){
        return webDriverThreadLocal.get();
    }

    public void removeDriver(){
        webDriverThreadLocal.remove();
    }


    private static ThreadLocal<HashMap<String,String>> testDataThreadLocal = new ThreadLocal<>();

    public static void setTestDataThreadLocal(HashMap<String,String> testData){
        testDataThreadLocal.set(testData);
    }

    public static HashMap<String,String> getTestData(){
        return testDataThreadLocal.get();
    }
}
