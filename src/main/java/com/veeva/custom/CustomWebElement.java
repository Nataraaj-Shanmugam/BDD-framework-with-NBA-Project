package com.veeva.custom;

import com.veeva.generic.Locators;
import com.veeva.generic.ThreadLocalImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomWebElement {

    Locators locatorType;
    String element;
    String comment;

    CustomWebElement(Locators locatorType, String element, String comment){
        this.locatorType = locatorType;
        this.element = element;
        this.comment = comment;
    }

    private WebElement webElement;
    private By byElement;
    static ThreadLocalImplementation threadLocalImplementation = new ThreadLocalImplementation();

    public WebElement getElement() {
        return webElement == null ? generateWebElement() : webElement;
    }

    public String getComment() {
        return this.comment;
    }

    public WebElement reloadWebElement(){
        return generateWebElement();
    }

    private WebElement generateWebElement(){
        return threadLocalImplementation.getWebDriver().findElement(setByElement());
    }

    public By getByElement(){
        return byElement == null ? setByElement() : byElement;
    }

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
