package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;

/**
 * Repository class for object locators of the NBA Warrior Home page.
 * This class holds the locators for various elements on the NBA Warrior Home page.
 */
public class NBAWarriorsHomePageObjects {

    public CustomWebElement preSales_Modal_CloseButton = new CustomWebElement(Locators.XPATH, "//div[text()='x']", "Pre-Sales modal close button");
    public CustomWebElement accept_cookies_Button = new CustomWebElement(Locators.ID, "onetrust-accept-btn-handler", "Accept Cookies button");
    public CustomWebElement shop_dropdown = new CustomWebElement(Locators.XPATH, "//span[text()='Shop']", "Shop Dropdown");
    public CustomWebElement shop_dropdownOption = new CustomWebElement(Locators.LINKTEXT, "DropDownCategory", "Dropdown option - DropDownCategory");
    public CustomWebElement ads_iframe = new CustomWebElement(Locators.XPATH, "//iframe[@title='3rd party ad content']", "ADs iframe");
    public CustomWebElement menu_options = new CustomWebElement(Locators.XPATH, "//ul[@role='menubar']//span[text()='...']", "Menu options");
    public CustomWebElement newsAndFeatures_link = new CustomWebElement(Locators.LINKTEXT, "News & Features", "Option - News & Features");
}
