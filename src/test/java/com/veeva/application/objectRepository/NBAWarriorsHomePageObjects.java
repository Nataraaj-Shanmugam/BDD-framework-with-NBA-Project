package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;

public class NBAWarriorsHomePageObjects {

    public CustomWebElement preSales_Modal_CloseButton = new CustomWebElement(Locators.XPATH, "//div[text()='x']", "Pre-Sales modal close button");
    public CustomWebElement accept_cookies_Button = new CustomWebElement(Locators.ID, "onetrust-accept-btn-handler", "Accept Cookies button");
    public CustomWebElement shop_dropdown = new CustomWebElement(Locators.XPATH, "//span[text()='Shop']", "Shop Dropdown");
    public CustomWebElement shop_dropdownOption = new CustomWebElement(Locators.LINKTEXT, "DropDownCategory", "DropDownCategory Dropdown option");
    public CustomWebElement ads_iframe = new CustomWebElement(Locators.XPATH, "//iframe[@title='3rd party ad content']", "ADs iframe");
}
