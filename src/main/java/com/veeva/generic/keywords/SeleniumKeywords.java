package com.veeva.generic.keywords;

import com.veeva.custom.CustomWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * Interface defining the generic keywords for Selenium operations.
 * This interface encapsulates methods for browser manipulation, element interaction,
 * and other common web automation tasks.
 */
public interface SeleniumKeywords {

    /**
     * Invokes a browser based on the provided browser type.
     *
     * @param browser The type of browser to invoke. Can be "Chrome", "Firefox", "Edge", or "Random".
     */
    void invokeBrowser(String browser);

    /**
     * Closes all open browsers and cleans up the WebDriver instance.
     */
    void closeBrowsers();

    /**
     * Navigates to the specified URL in the current browser session.
     *
     * @param url The URL to navigate to.
     */
    void loadUrl(String url);

    /**
     * Retrieves the current WebDriver instance.
     *
     * @return The current WebDriver instance.
     */
    WebDriver getDriver();

    /**
     * Clicks on a given CustomWebElement.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     */
    void click(CustomWebElement customWebElement);

    /**
     * Attempts to click on a given CustomWebElement within the specified duration.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     * @param seconds The duration to wait for the element to be clickable.
     */
    void click(CustomWebElement customWebElement, Duration seconds);

    /**
     * Clicks on a given CustomWebElement using JavaScript Executor.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     */
    void clickUsingJSExecutor(CustomWebElement customWebElement);

    /**
     * Clicks on a given CustomWebElement using Actions class.
     *
     * @param customWebElement The CustomWebElement to be clicked.
     */
    void clickUsingActions(CustomWebElement customWebElement);

    /**
     * Enters text into a given CustomWebElement.
     *
     * @param customWebElement The CustomWebElement to enter text into.
     * @param textToEnter The text to be entered.
     */
    void enterValue(CustomWebElement customWebElement, String textToEnter);

    /**
     * Retrieves the WebElement associated with the provided CustomWebElement.
     *
     * @param customWebElement The CustomWebElement for which to retrieve the WebElement.
     * @return The WebElement corresponding to the CustomWebElement.
     */
    WebElement getElement(CustomWebElement customWebElement);

    /**
     * Retrieves a list of WebElements associated with the provided CustomWebElement.
     *
     * @param customWebElement The CustomWebElement for which to retrieve the WebElements.
     * @return A list of WebElements corresponding to the CustomWebElement.
     */
    List<WebElement> getElements(CustomWebElement customWebElement);

    /**
     * Retrieves the By locator associated with the provided CustomWebElement.
     *
     * @param customWebElement The CustomWebElement for which to retrieve the By locator.
     * @return The By locator for the CustomWebElement.
     */
    By getByElement(CustomWebElement customWebElement);

    /**
     * Waits until the specified CustomWebElement is clickable.
     *
     * @param customWebElement The CustomWebElement to wait for.
     */
    void waitUntilClickable(CustomWebElement customWebElement);

    /**
     * Waits for a specified duration until the CustomWebElement is clickable.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is clickable.
     */
    void waitUntilClickable(CustomWebElement customWebElement, Duration seconds);

    /**
     * Waits until the specified CustomWebElement is visible.
     *
     * @param customWebElement The CustomWebElement to wait for.
     */
    void waitUntilVisible(CustomWebElement customWebElement);

    /**
     * Waits for a specified duration until the CustomWebElement is visible.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is visible.
     */
    void waitUntilVisible(CustomWebElement customWebElement, Duration seconds);

    /**
     * Waits until the specified CustomWebElement is present in the DOM.
     *
     * @param customWebElement The CustomWebElement to wait for.
     */
    void waitUntilPresent(CustomWebElement customWebElement);

    /**
     * Waits for a specified duration until the CustomWebElement is present in the DOM.
     *
     * @param customWebElement The CustomWebElement to wait for.
     * @param seconds The duration to wait until the element is present.
     */
    void waitUntilPresent(CustomWebElement customWebElement, Duration seconds);

    /**
     * Waits for a specified duration until the URL of the current page is not empty.
     *
     * @param seconds The duration to wait until the URL is not empty.
     */
    void waitUntilURLIsNotEmpty(Duration seconds);

    /**
     * Takes a screenshot of the current page.
     *
     * @return A byte array representing the screenshot in PNG format.
     */
    byte[] takeScreenshot();

    /**
     * Switches the focus of future commands for this driver to the window with the given name/handle.
     *
     * @param window The name of the window to switch to.
     * @param name   A descriptive name for the window to assist in logging.
     */
    void switchWindow(String window, String name);

    /**
     * Retrieves the set of window handles which can be used to iterate over all open windows of this WebDriver instance.
     *
     * @return A Set of window handles.
     */
    Set<String> getAllWindows();

    /**
     * Retrieves the name of the window currently being used in the session.
     *
     * @return The window handle of the current window.
     */
    String getWindowName();

    /**
     * Retrieves the URL of the page currently loaded in the browser.
     *
     * @return The URL of the current page.
     */
    String getCurrentUrl();

    /**
     * Hovers over a specified CustomWebElement.
     *
     * @param customWebElement The CustomWebElement to hover on.
     */
    void hoverOn(CustomWebElement customWebElement);

    /**
     * Retrieves the value of a given attribute of a WebElement.
     *
     * @param element   The WebElement from which to retrieve the attribute value.
     * @param attribute The name of the attribute.
     * @return The value of the specified attribute.
     */
    String getAttributeValue(WebElement element, String attribute);

    /**
     * Retrieves the text of a WebElement.
     *
     * @param element The WebElement from which to retrieve the text.
     * @return The text of the specified WebElement.
     */
    String getText(WebElement element);

    /**
     * Scrolls the browser view to the specified CustomWebElement.
     *
     * @param customWebElement The CustomWebElement to scroll to within the browser.
     */
    void scrollTo(CustomWebElement customWebElement);
}
