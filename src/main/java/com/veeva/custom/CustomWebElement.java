package com.veeva.custom;

import com.veeva.generic.GenericKeywords;
import com.veeva.generic.Locators;
import com.veeva.generic.ThreadLocalImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomWebElement {

    Locators locatorType;
    String element;
    String comment;

    public CustomWebElement(Locators locatorType, String element, String comment){
        this.locatorType = locatorType;
        this.element = element;
        this.comment = comment;
    }

    public CustomWebElement updateCustomWebElement(String element, String comment){
        this.element = element.isEmpty() ? this.element : element;
        this.comment = comment.isEmpty() ? this.comment : comment;
        return this;
    }

    public String getElement() {
        return this.element;
    }



    private WebElement webElement;
    private By byElement;

    public WebElement getWebElement() {
//        return webElement == null ? generateWebElement() : webElement;
        return generateWebElement();
    }

    public  List<WebElement> getWebElements() {
        return generateWebElements();
    }

    public String getComment() {
        return this.comment;
    }

    public WebElement reloadWebElement(){
        return generateWebElement();
    }

    private WebElement generateWebElement(){
        return new GenericKeywords().getDriver().findElement(setByElement());
    }

    private List<WebElement> generateWebElements(){
        return new GenericKeywords().getDriver().findElements(setByElement());
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
