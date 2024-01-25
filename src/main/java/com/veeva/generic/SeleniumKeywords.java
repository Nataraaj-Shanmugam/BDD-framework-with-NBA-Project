package com.veeva.generic;

import com.veeva.custom.CustomWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SeleniumKeywords {

    void invokeBrowser();

    void closeBrowsers();

    WebDriver getDriver();

    void click(CustomWebElement customWebElement);

    void clickUsingJSExecutor(CustomWebElement customWebElement);

    void clickUsingActions(CustomWebElement customWebElement);

    void enterValue(CustomWebElement element, String textToEnter);

    WebElement getElement(CustomWebElement customWebElement);

    By getByElement(CustomWebElement customWebElement);

    void waitUntilClickable(CustomWebElement customWebElement);

    void waitUntilVisible(CustomWebElement customWebElement);

    void waitUntilPresent(CustomWebElement customWebElement);

    byte[] takeScreenshot();

}
