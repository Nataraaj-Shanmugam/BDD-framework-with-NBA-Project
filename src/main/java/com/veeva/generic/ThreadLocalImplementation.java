package com.veeva.generic;

import org.openqa.selenium.WebDriver;

public class ThreadLocalImplementation {

    private ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public void setWebDriverThreadLocal(WebDriver driver){
        webDriverThreadLocal.set(driver);
    }

    public WebDriver getWebDriver(){
        return webDriverThreadLocal.get();
    }
}
