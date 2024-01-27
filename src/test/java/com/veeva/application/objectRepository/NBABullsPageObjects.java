package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;

/**
 * Repository class for object locators of the NBA Bulls page.
 * This class holds the locators for various elements on the NBA Bulls page.
 */
public class NBABullsPageObjects {

    public CustomWebElement footer_title = new CustomWebElement(Locators.XPATH, "//footer//h2", "Footer title");
    public CustomWebElement footer_link = new CustomWebElement(Locators.XPATH, "following-sibling::ul//a", "Hyperlink for each footer list");
   }
