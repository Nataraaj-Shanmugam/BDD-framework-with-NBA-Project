package com.veeva.generic.keywords;

import com.veeva.custom.CustomWebElement;
import com.veeva.ThreadLocalImplementation;
import com.veeva.utility.PropertiesUtility;
import com.veeva.utility.ReporterUtilities;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * GenericKeywords is a class that implements the SeleniumKeywords and NonSeleniumKeywords interfaces.
 * It provides various utility methods and includes operations like clicking, entering text, taking screenshots, and more.
 */
public class GenericKeywords implements SeleniumKeywords, NonSeleniumKeywords{

    static final ThreadLocalImplementation threadLocalImplementation = new ThreadLocalImplementation();
    private static final PropertiesUtility properties = new PropertiesUtility();

    /** Constructs a new instance of the {@link GenericKeywords} class. */
    public GenericKeywords(){}

    /**
     * Invokes a browser based on the provided browser type. Supports Chrome, Firefox, and Edge.
     * If "Random" is passed as a parameter, a random browser type will be selected.
     *
     * @param browser The type of browser to invoke. Can be "Chrome", "Firefox", "Edge", or "Random".
     */
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

    /**
     * Closes all open browsers and cleans up the WebDriver instance.
     */
    @Override
    public void closeBrowsers() {
        ReporterUtilities.log("Close all browser");
        getDriver().quit();
        threadLocalImplementation.removeDriver();
    }

    /**
     * Navigates to the specified URL in the current browser session.
     *
     * @param url The URL to navigate to.
     */
    @Override
    public void loadUrl(String url) {
        getDriver().get(url);
        ReporterUtilities.log("load url "+url);
    }

    /**
     * Retrieves the current WebDriver instance from the thread-local storage.
     *
     * @return The current WebDriver instance.
     */
    @Override
    public WebDriver getDriver(){
        return threadLocalImplementation.getWebDriver();
    }

    /**
     * Clicks on a given CustomWebElement. If the click is intercepted, attempts alternative click methods.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     */
    @Override
    public void click(CustomWebElement customWebElement) {
        this.click(customWebElement,Duration.ofSeconds(5));
    }

    /**
     * Attempts to click on a given CustomWebElement within the specified duration.
     * If the click is intercepted, it tries alternative methods such as using Actions or JavaScript Executor.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     * @param seconds The duration to wait for the element to be clickable.
     * @throws ElementClickInterceptedException If all click attempts are intercepted.
     */
    @Override
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

    /**
     * Clicks on a given CustomWebElement using JavaScript Executor.
     * This is typically used when standard click operations fail.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     */
    @Override
    public void clickUsingJSExecutor(CustomWebElement customWebElement) {
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", getElement(customWebElement));
        ReporterUtilities.log("Clicked using JavaScriptExecutor on "+customWebElement.getComment());
    }

    /**
     * Clicks on a given CustomWebElement using Actions class.
     * Useful for elements that need hover or other complex actions before clicking.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     */
    @Override
    public void clickUsingActions(CustomWebElement customWebElement) {
        new Actions(getDriver()).moveToElement(getElement(customWebElement)).click(getElement(customWebElement)).perform();
        ReporterUtilities.log("Clicked using JavaScriptExecutor on "+customWebElement.getComment());
    }

    /**
     * Enters text into a given CustomWebElement. Assumes the element is a text input field.
     *
     * @param customWebElement The CustomWebElement to enter text into.
     * @param textToEnter      The text to be entered.
     */
    @Override
    public void enterValue(CustomWebElement customWebElement, String textToEnter) {
        waitUntilVisible(customWebElement);
        getElement(customWebElement).sendKeys(textToEnter);
        ReporterUtilities.log("Entered value "+textToEnter+" in "+customWebElement.getComment());
    }

    /**
     * Retrieves the WebElement associated with the provided CustomWebElement.
     *
     * @param customWebElement The CustomWebElement for which to retrieve the WebElement.
     * @return The WebElement corresponding to the CustomWebElement.
     */
    @Override
    public WebElement getElement(CustomWebElement customWebElement) {
        return customWebElement.getWebElement();
    }

    /**
     * Retrieves a list of WebElements associated with the provided CustomWebElement.
     *
     * @param customWebElement The CustomWebElement for which to retrieve the WebElements.
     * @return A list of WebElements corresponding to the CustomWebElement.
     */
    @Override
    public List<WebElement> getElements(CustomWebElement customWebElement) {
        return customWebElement.getWebElements();
    }

    /**
     * Retrieves the By locator associated with the provided CustomWebElement.
     *
     * @param customWebElement The CustomWebElement for which to retrieve the By locator.
     * @return The By locator for the CustomWebElement.
     */
    @Override
    public By getByElement(CustomWebElement customWebElement) {
        return customWebElement.getByElement();
    }

    /**
     * Waits until the specified CustomWebElement is clickable. Defaults to a 5-second wait.
     *
     * @param customWebElement The CustomWebElement to wait for.
     */
    @Override
    public void waitUntilClickable(CustomWebElement customWebElement) {
        waitUntilPresent(customWebElement);
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(getElement(customWebElement)));
    }

    /**
     * Waits for a specified duration until the CustomWebElement is clickable.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is clickable.
     */
    @Override
    public void waitUntilClickable(CustomWebElement customWebElement, Duration seconds) {
        waitUntilPresent(customWebElement, seconds);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.elementToBeClickable(getElement(customWebElement)));
    }

    /**
     * Waits until the specified CustomWebElement is visible. Defaults to a 5-second wait.
     *
     * @param customWebElement The CustomWebElement to wait for.
     */
    @Override
    public void waitUntilVisible(CustomWebElement customWebElement) {
        waitUntilPresent(customWebElement);
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Waits for a specified duration until the CustomWebElement is visible.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is visible.
     */
    @Override
    public void waitUntilVisible(CustomWebElement customWebElement, Duration seconds) {
        waitUntilPresent(customWebElement, seconds);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Waits until the specified CustomWebElement is present in the DOM. Defaults to a 5-second wait.
     *
     * @param customWebElement The CustomWebElement to wait for.
     */
    @Override
    public void waitUntilPresent(CustomWebElement customWebElement) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Waits for a specified duration until the CustomWebElement is present in the DOM.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is present.
     */
    @Override
    public void waitUntilPresent(CustomWebElement customWebElement, Duration seconds) {
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.presenceOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Waits for a specified duration until the CustomWebElement is invisible in the DOM.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is present.
     */
    @Override
    public void waitUntilInvisible(CustomWebElement customWebElement, Duration seconds) {
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.invisibilityOfElementLocated(getByElement(customWebElement)));
    }

    /**
     * Waits until the specified attribute of the given element becomes the expected value within the specified time duration.
     *
     * @param customWebElement The custom web element to check.
     * @param seconds          The maximum time to wait for the attribute to become the expected value, in seconds.
     * @param attribute        The name of the attribute to check.
     * @param value            The expected value of the attribute.
     * @throws TimeoutException If the attribute does not become the expected value within the specified time.
     */
    @Override
    public void waitUntilAttributePresent(CustomWebElement customWebElement, Duration seconds,String attribute, String value) {
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.domAttributeToBe(customWebElement.getWebElement(), attribute, value));
    }
/*
    @Override
    public void waitUntilURLIsNotEmpty(Duration seconds){
        WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
        wait.until((ExpectedCondition<Boolean>) webDriver -> !webDriver.getCurrentUrl().isEmpty() || !webDriver.getCurrentUrl().equals("about:blank"));
    }*/

    /**
     * Waits for a specified duration until the URL of the current page is not empty.
     *
     * @param seconds The duration to wait until the URL is not empty.
     */
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
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Retrieves the set of window handles which can be used to iterate over all open windows of this WebDriver instance.
     *
     * @return A Set of window handles.
     */
    @Override
    public Set<String> getAllWindows(){
        return getDriver().getWindowHandles();
    }

    /**
     * Retrieves the URL of the page currently loaded in the browser.
     *
     * @return The URL of the current page.
     */
    @Override
    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    /**
     * Retrieves the name of the window currently being used in the session.
     *
     * @return The window handle of the current window.
     */
    @Override
    public String getWindowName(){
        return getDriver().getWindowHandle();
    }

    /**
     * Hovers over a specified CustomWebElement.
     *
     * @param customWebElement The CustomWebElement to hover on.
     */
    @Override
    public void hoverOn(CustomWebElement customWebElement){
        new Actions(getDriver()).moveToElement(customWebElement.getWebElement()).perform();
        ReporterUtilities.log("Moved to/Hover on "+customWebElement.getComment());
    }

    /**
     * Retrieves the value of a given attribute of a WebElement.
     *
     * @param element   The WebElement from which to retrieve the attribute value.
     * @param attribute The name of the attribute.
     * @return The value of the specified attribute.
     */
    @Override
    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }


    /**
     * Retrieves the text of a WebElement.
     *
     * @param element The WebElement from which to retrieve the text.
     * @return The text of the specified WebElement.
     */
    @Override
    public String getText(WebElement element){
        return element.getText();
    }

    /**
     * Switches the focus of future commands for this driver to the window with the given name/handle.
     *
     * @param window The name of the window to switch to.
     * @param name   A descriptive name for the window to assist in logging.
     */
    @Override
    public void switchWindow(String window,String name){
        getDriver().switchTo().window(window);
        ReporterUtilities.log("Switching to window "+name);
    }

    /**
     * Retrieves a property value from a properties file.
     *
     * @param key The key of the property to be retrieved.
     * @return The value of the specified property.
     */
    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Writes content to a file at a specified file path.
     *
     * @param content  The content to write to the file.
     * @param filePath The file path where the content should be written.
     */
    @Override
    public void writeToFile(String content, String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReporterUtilities.log("Writing to a file in "+filePath);
    }

    /**
     * Parses a date string into a LocalDate object using the specified format.
     *
     * @param date   The date string to parse.
     * @param format The format to use for parsing.
     * @return The parsed LocalDate object.
     */
    @Override
    public LocalDate formattedDate(String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Parses a date string into a LocalDate object using the default format.
     *
     * @param date The date string to parse.
     * @return The parsed LocalDate object.
     */
    @Override
    public LocalDate formattedDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Calculates the difference in days between two LocalDate objects.
     *
     * @param date1 The first date.
     * @param date2 The second date.
     * @return The number of days between the two dates.
     */
    @Override
    public long dateDifference(LocalDate date1, LocalDate date2) {
        return  ChronoUnit.DAYS.between(date1, date2);
    }

    /**
     * Generates a CSV file from a two-dimensional array of strings.
     *
     * @param csvData   The data to be written to the CSV file.
     * @param fileName The name of the CSV file to create.
     */
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

    /**
     * Scrolls the browser view to the specified CustomWebElement.
     *
     * @param customWebElement The CustomWebElement to scroll to within the browser.
     */
    @Override
    public void scrollTo(CustomWebElement customWebElement){
     new Actions(getDriver()).scrollToElement(customWebElement.getWebElement()).perform();
     ReporterUtilities.log("Scrolling to "+customWebElement.getComment());
    }
}