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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GenericKeywords implements SeleniumKeywords, NonSeleniumKeywords{

    static final ThreadLocalImplementation threadLocalImplementation = new ThreadLocalImplementation();
    static WebDriverWait wait; // need to solutionise

    private static final PropertiesUtility properties = new PropertiesUtility();

    @Override
    public void invokeBrowser(String browser) {
        WebDriver driver = null;
        MutableCapabilities  capabilities;
        if(browser.equals("Random")){
            String[] browsers = new String[]{"Chrome", "Firefox", "Edge"};
            browser = browsers[new Random().nextInt(browsers.length)];
        }
        switch (browser) {
            case "Chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--start-maximized");
                capabilities = chromeOptions;
                driver = new ChromeDriver((ChromeOptions) capabilities);
            }
            case "Firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addArguments("--start-maximized");
                capabilities = firefoxOptions;
                driver = new FirefoxDriver((FirefoxOptions) capabilities);
            }
            case "Edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--start-maximized");
                capabilities = edgeOptions;
                driver = new EdgeDriver((EdgeOptions) capabilities);
            }
        }
        threadLocalImplementation.setWebDriverThreadLocal(driver);
        ReporterUtilities.log("Launch "+browser);
    }

    @Override
    public void closeBrowsers() {
        ReporterUtilities.log("Close all browser");
        getDriver().quit();
        threadLocalImplementation.removeDriver();
    }

    @Override
    public void loadUrl(String url) {
        getDriver().get(url);
        ReporterUtilities.log("load url "+url);
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
/*
    @Override
    public void waitUntilURLIsNotEmpty(Duration seconds){
        WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
        wait.until((ExpectedCondition<Boolean>) webDriver -> !webDriver.getCurrentUrl().isEmpty() || !webDriver.getCurrentUrl().equals("about:blank"));
    }*/

    @Override
    public void waitUntilURLIsNotEmpty(Duration seconds){
        int count = (int) (seconds.toMillis()/500);
        do{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!getDriver().getCurrentUrl().contains("//") && count-- >= 0);
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
    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    @Override
    public String getText(WebElement element){
        return element.getText();
    }

    @Override
    public void switchWindow(String window,String name){
        getDriver().switchTo().window(window);
        ReporterUtilities.log("Switching to window "+name);
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
        ReporterUtilities.log("Writing to a file in "+filePath);
    }

    @Override
    public LocalDate formattedDate(String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    @Override
    public LocalDate formattedDate(String date) {
        return LocalDate.parse(date);
    }

    @Override
    public long dateDifference(LocalDate date1, LocalDate date2) {
        return  ChronoUnit.DAYS.between(date1, date2);
    }

    @Override
    public void generateCSV(String[][] csvData,String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String[] row : csvData) {
                writer.append(String.join(",", row)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReporterUtilities.log("Generated "+fileName);
    }

    @Override
    public void scrollTo(CustomWebElement customWebElement){
     new Actions(getDriver()).scrollToElement(customWebElement.getWebElement()).perform();
     ReporterUtilities.log("Scrolling to "+customWebElement.getComment());
    }
}