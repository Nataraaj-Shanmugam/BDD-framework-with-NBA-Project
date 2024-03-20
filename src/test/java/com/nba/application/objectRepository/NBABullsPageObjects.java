package com.nba.application.objectRepository;

import com.framework.custom.CustomWebElement;
import com.framework.generic.Locators;

/**
 * Repository class for object locators of the NBA Bulls page.
 * This class holds the locators for various elements on the NBA Bulls page.
 */
public class NBABullsPageObjects {

    /** Constructs a new instance of the {@link NBABullsPageObjects} class. */
    public NBABullsPageObjects(){}

    /** CustomWebElement representing the footer title. */
    public CustomWebElement footer_title = new CustomWebElement(Locators.XPATH, "//footer//h2", "Footer title");

    /** CustomWebElement representing the hyperlinks in the footer. */
    public CustomWebElement footer_link = new CustomWebElement(Locators.XPATH, "following-sibling::ul//a", "Hyperlink for each footer list");
}
