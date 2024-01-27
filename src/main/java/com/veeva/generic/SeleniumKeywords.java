package com.veeva.generic;

import com.veeva.custom.CustomWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public interface SeleniumKeywords {

    void invokeBrowser(String browser);

    void closeBrowsers();

    void loadUrl(String url);

    WebDriver getDriver();

    void click(CustomWebElement customWebElement);

    void clickUsingJSExecutor(CustomWebElement customWebElement);

    void clickUsingActions(CustomWebElement customWebElement);

    void enterValue(CustomWebElement element, String textToEnter);

    WebElement getElement(CustomWebElement customWebElement);

    List<WebElement> getElements(CustomWebElement customWebElement);

    By getByElement(CustomWebElement customWebElement);

    void waitUntilClickable(CustomWebElement customWebElement);

    void waitUntilClickable(CustomWebElement customWebElement, Duration seconds);

    void waitUntilVisible(CustomWebElement customWebElement);

    void waitUntilVisible(CustomWebElement customWebElement, Duration seconds);

    void waitUntilPresent(CustomWebElement customWebElement);

    void waitUntilPresent(CustomWebElement customWebElement, Duration seconds);

    void waitUntilURLIsNotEmpty(Duration seconds);

    byte[] takeScreenshot();

    void switchWindow(String window);

    Set<String> getAllWindows();

    String getWindowName();

    String getCurrentUrl();

    void hoverOn(CustomWebElement customWebElement);

}
