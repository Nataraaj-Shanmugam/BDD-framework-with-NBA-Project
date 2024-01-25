package com.veeva.generic;

import com.veeva.custom.CustomWebElement;
import com.veeva.utility.PropertiesUtility;
import com.veeva.utility.ReporterUtilities;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericKeywords implements SeleniumKeywords{

    ThreadLocalImplementation threadLocalImplementation = new ThreadLocalImplementation();
    static WebDriverWait wait;

    private static final PropertiesUtility properties = new PropertiesUtility();

    @Override
    public void invokeBrowser() {
        WebDriver driver = switch (properties.getProperty("browser")) {
            case "Chrome" -> new ChromeDriver();
            case "Firefox" -> new FirefoxDriver();
            case "Edge" -> new EdgeDriver();
            default -> null;
        };
        threadLocalImplementation.setWebDriverThreadLocal(driver);
    }

    @Override
    public void closeBrowsers() {
        getDriver().quit();
    }

    @Override
    public WebDriver getDriver(){
        return threadLocalImplementation.getWebDriver();
    }

    @Override
    public void click(CustomWebElement customWebElement) {
        waitUntilClickable(customWebElement);
        try{
            getElement(customWebElement).click();
            ReporterUtilities.log("Clicked using selenium click on "+customWebElement.getComment());
        }catch (ElementClickInterceptedException e1){
            try{
                clickUsingActions(customWebElement);
            }catch (ElementClickInterceptedException e2){
                try{
                    clickUsingJSExecutor(customWebElement);
                }catch (ElementClickInterceptedException e3){
                    //todo create custom exception and fail the Testcase file
                    ReporterUtilities.log("Clicked using selenium on "+customWebElement.getComment());
                }
            }
        }
    }

    @Override
    public void clickUsingJSExecutor(CustomWebElement customWebElement) {
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", getElement(customWebElement));
        ReporterUtilities.log("Clicked using JavaScriptExecutor on "+customWebElement.getComment());
    }

    @Override
    public void clickUsingActions(CustomWebElement customWebElement) {
        new Actions(getDriver()).moveToElement(getElement(customWebElement)).click(getElement(customWebElement)).perform();
        ReporterUtilities.log("Clicked using JavaScriptExecutor on "+customWebElement.getComment());
    }

    @Override
    public void enterValue(CustomWebElement customWebElement, String textToEnter) {
        waitUntilVisible(customWebElement);
        getElement(customWebElement).sendKeys(textToEnter);
        ReporterUtilities.log("Entered value "+textToEnter+" in "+customWebElement.getComment());
    }

    @Override
    public WebElement getElement(CustomWebElement customWebElement) {
        return customWebElement.getElement();
    }

    @Override
    public By getByElement(CustomWebElement customWebElement) {
        return customWebElement.getByElement();
    }

    @Override
    public void waitUntilClickable(CustomWebElement customWebElement) {
        waitUntilPresent(customWebElement);
        wait = wait == null ? new WebDriverWait(getDriver(), Duration.ofSeconds(5)) : wait;
        wait.until(ExpectedConditions.elementToBeClickable(getElement(customWebElement)));
    }

    @Override
    public void waitUntilVisible(CustomWebElement customWebElement) {
        waitUntilPresent(customWebElement);
        wait = wait == null ? new WebDriverWait(getDriver(), Duration.ofSeconds(5)) : wait;
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByElement(customWebElement)));
    }

    @Override
    public void waitUntilPresent(CustomWebElement customWebElement) {
        wait = wait == null ? new WebDriverWait(getDriver(), Duration.ofSeconds(5)) : wait;
        wait.until(ExpectedConditions.presenceOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Takes a screenshot of the current page.
     *
     * @return A byte array representing the screenshot in PNG format.
     */
    @Attachment(type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
