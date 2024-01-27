package com.veeva.generic;

import com.veeva.custom.CustomWebElement;
import com.veeva.utility.PropertiesUtility;
import com.veeva.utility.ReporterUtilities;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class GenericKeywords implements SeleniumKeywords, NonSeleniumKeywords{

    static final ThreadLocalImplementation threadLocalImplementation = new ThreadLocalImplementation();
    static WebDriverWait wait; // need to solutionise

    private static final PropertiesUtility properties = new PropertiesUtility();

    @Override
    public void invokeBrowser(String browser) {
        WebDriver driver = null;
        MutableCapabilities  capabilities;
        switch (browser){
            case "Chrome":{
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                capabilities = chromeOptions;
                driver = new ChromeDriver((ChromeOptions) capabilities);
                break;
            }
            case "Firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                capabilities = firefoxOptions;
                driver = new FirefoxDriver((FirefoxOptions) capabilities);
                break;
            case "Edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                capabilities = edgeOptions;
                driver = new EdgeDriver((EdgeOptions) capabilities);
                break;
        }
        threadLocalImplementation.setWebDriverThreadLocal(driver);
    }

    @Override
    public void closeBrowsers() {
        getDriver().quit();
        threadLocalImplementation.removeDriver();
    }

    @Override
    public void loadUrl(String url) {
        getDriver().get(url);
    }

    @Override
    public WebDriver getDriver(){
        return threadLocalImplementation.getWebDriver();
    }

    @Override
    public void click(CustomWebElement customWebElement) {
        this.click(customWebElement,Duration.ofSeconds(5));
    }
    public void click(CustomWebElement customWebElement, Duration seconds) {
        waitUntilClickable(customWebElement, seconds);
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
        return customWebElement.getWebElement();
    }

    @Override
    public List<WebElement> getElements(CustomWebElement customWebElement) {
        return customWebElement.getWebElements();
    }

    @Override
    public By getByElement(CustomWebElement customWebElement) {
        return customWebElement.getByElement();
    }

    @Override
    public void waitUntilClickable(CustomWebElement customWebElement) {
        waitUntilPresent(customWebElement);
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(getElement(customWebElement)));
    }

    @Override
    public void waitUntilClickable(CustomWebElement customWebElement, Duration seconds) {
        waitUntilPresent(customWebElement, seconds);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.elementToBeClickable(getElement(customWebElement)));
    }

    @Override
    public void waitUntilVisible(CustomWebElement customWebElement) {
        waitUntilPresent(customWebElement);
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(getByElement(customWebElement)));
    }


    @Override
    public void waitUntilVisible(CustomWebElement customWebElement, Duration seconds) {
        waitUntilPresent(customWebElement, seconds);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOfElementLocated(getByElement(customWebElement)));
    }

    @Override
    public void waitUntilPresent(CustomWebElement customWebElement) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(getByElement(customWebElement)));
    }

    @Override
    public void waitUntilPresent(CustomWebElement customWebElement, Duration seconds) {
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.presenceOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Takes a screenshot of the current page.
     *
     * @return A byte array representing the screenshot in PNG format.
     */
    @Override
    @Attachment(type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public Set<String> getAllWindows(){
        return getDriver().getWindowHandles();
    }

    @Override
    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    @Override
    public String getWindowName(){
        return getDriver().getWindowHandle();
    }

    @Override
    public void hoverOn(CustomWebElement customWebElement){
        new Actions(getDriver()).moveToElement(customWebElement.getWebElement()).perform();
        ReporterUtilities.log("Moved to/Hover on "+customWebElement.getComment());
    }

    @Override
    public void switchWindow(String window){
        getDriver().switchTo().window(window);
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @Override
    public void writeToFile(String content, String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
