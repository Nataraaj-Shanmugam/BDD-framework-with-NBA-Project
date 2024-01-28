package com.veeva;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Provides thread-local storage for WebDriver and test data instances.
 * This class ensures that each thread has its own instance of WebDriver and test data,
 * allowing for thread-safe operations in parallel testing environments.
 */
public class ThreadLocalImplementation {

    /** Constructs a new instance of the {@link ThreadLocalImplementation} class. */
    public ThreadLocalImplementation(){}

    private final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param driver The WebDriver instance to be set for the current thread.
     */
    public void setWebDriverThreadLocal(WebDriver driver){
        webDriverThreadLocal.set(driver);
    }

    /**
     * Retrieves the WebDriver instance associated with the current thread.
     *
     * @return The WebDriver instance for the current thread.
     */
    public WebDriver getWebDriver(){
        return webDriverThreadLocal.get();
    }

    /**
     * Removes the WebDriver instance associated with the current thread.
     */
    public void removeDriver(){
        webDriverThreadLocal.remove();
    }

    private static final ThreadLocal<HashMap<String,String>> testDataThreadLocal = new ThreadLocal<>();

    /**
     * Sets the test data HashMap for the current thread.
     *
     * @param testData The HashMap containing test data to be set for the current thread.
     */
    public static void setTestDataThreadLocal(HashMap<String,String> testData){
        testDataThreadLocal.set(testData);
    }

    /**
     * Retrieves the test data HashMap associated with the current thread.
     *
     * @return The HashMap containing test data for the current thread.
     */
    public static HashMap<String,String> getTestData(){
        return testDataThreadLocal.get();
    }
}
