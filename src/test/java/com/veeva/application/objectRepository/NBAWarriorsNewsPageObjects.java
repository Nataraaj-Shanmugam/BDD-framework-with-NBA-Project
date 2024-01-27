package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;

public class NBAWarriorsNewsPageObjects {

    public CustomWebElement videos_modal = new CustomWebElement(Locators.XPATH, "//h3[text()='VIDEOS']/../..//ul[@data-testid='content-grid-']//li", "Videos modal");
    public CustomWebElement datePosted_text = new CustomWebElement(Locators.TAGNAME, "time", "Date/Time video posted");
   }
