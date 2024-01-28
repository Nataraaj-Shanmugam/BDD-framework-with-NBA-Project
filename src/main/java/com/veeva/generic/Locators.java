package com.veeva.generic;

/**
 * Enum defining different types of locators used in Selenium WebDriver.
 * This enum provides a standardized set of locator types for identifying web elements.
 */
public enum Locators {
    /** Represents an ID locator. */
    ID,

    /** Represents a class name locator. */
    CLASSNAME,

    /** Represents a tag name locator. */
    TAGNAME,

    /** Represents a link text locator. */
    LINKTEXT,

    /** Represents an XPath locator. */
    XPATH,

    /** Represents a CSS selector locator. */
    CSS
}
