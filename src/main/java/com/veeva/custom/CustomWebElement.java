package com.veeva.custom;

import com.veeva.generic.keywords.GenericKeywords;
import com.veeva.generic.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Represents a custom web element in a web application. This class encapsulates details about a web element
 * including its locator type, locator value, and a descriptive comment. It provides methods to manipulate and
 * retrieve information about the web element, such as updating its properties, retrieving the underlying
 * WebElement, and accessing its locator and comment.
 */
public class CustomWebElement {

    Locators locatorType;
    String element;
    String comment;

    /**
     * Constructor for creating a new CustomWebElement.
     *
     * @param locatorType The type of locator (ID, XPATH, CLASSNAME, CSS, TAGNAME, LINKTEXT).
     * @param element The locator value.
     * @param comment A comment or description for the web element.
     */
    public CustomWebElement(Locators locatorType, String element, String comment){
        this.locatorType = locatorType;
        this.element = element;
        this.comment = comment;
    }

    /**
     * Updates the element and comment of this CustomWebElement.
     *
     * @param element The new locator value; if empty, the current value is retained.
     * @param comment The new comment; if empty, the current comment is retained.
     * @return The updated CustomWebElement.
     */
    public CustomWebElement updateCustomWebElement(String element, String comment){
        this.element = element.isEmpty() ? this.element : element;
        this.comment = comment.isEmpty() ? this.comment : comment;
        return this;
    }

    /**
     * Gets the locator value of this element.
     *
     * @return The locator value.
     */
    public String getElement() {
        return this.element;
    }

    private WebElement webElement;
    private By byElement;

    /**
     * Gets the WebElement based on the current locator.
     *
     * @return The WebElement found using the current locator.
     */
    public WebElement getWebElement() {
        return generateWebElement();
    }

    /**
     * Gets a list of WebElements based on the current locator.
     *
     * @return A list of WebElements found using the current locator.
     */
    public List<WebElement> getWebElements() {
        return generateWebElements();
    }

    /**
     * Gets the comment or description of this element.
     *
     * @return The comment for this element.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Reloads and returns the WebElement based on the current locator.
     *
     * @return The reloaded WebElement.
     */
    public WebElement reloadWebElement(){
        return generateWebElement();
    }

    /**
     * Generates a WebElement based on the current locator.
     *
     * @return The generated WebElement.
     */
    private WebElement generateWebElement(){
        return new GenericKeywords().getDriver().findElement(setByElement());
    }

    /**
     * Generates a list of WebElements based on the current locator.
     *
     * @return The list of generated WebElements.
     */
    private List<WebElement> generateWebElements(){
        return new GenericKeywords().getDriver().findElements(setByElement());
    }

    /**
     * Gets the By element based on the current locator.
     * If the By element is null, it sets the By element based on the locator type.
     *
     * @return The By element.
     */
    public By getByElement(){
        return byElement == null ? setByElement() : byElement;
    }

    /**
     * Sets the By element based on the current locator type and value.
     *
     * @return The By element for the locator.
     */
    private By setByElement(){
        return  switch (locatorType){
            case ID -> By.id(element);
            case XPATH -> By.xpath(element);
            case CLASSNAME -> By.className(element);
            case CSS -> By.cssSelector(element);
            case TAGNAME -> By.tagName(element);
            case LINKTEXT-> By.linkText(element);
        };
    }
}