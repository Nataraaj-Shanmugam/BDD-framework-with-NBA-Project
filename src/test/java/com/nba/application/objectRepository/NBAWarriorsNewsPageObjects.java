package com.nba.application.objectRepository;

import com.framework.custom.CustomWebElement;
import com.framework.generic.Locators;

/**
 * Repository class for object locators of the NBA Warrior News page.
 * This class holds the locators for various elements on the NBA Warrior News page.
 */
public class NBAWarriorsNewsPageObjects {

    /** Constructs a new instance of the {@link NBAWarriorsNewsPageObjects} class. */
    public NBAWarriorsNewsPageObjects(){}

    /** CustomWebElement representing the Videos modal. */
    public CustomWebElement videos_modal = new CustomWebElement(Locators.XPATH, "//h3[text()='VIDEOS']/../..//ul[@data-testid='content-grid-']//li", "Videos modal");

    /** CustomWebElement representing the Date/Time when the video was posted. */
    public CustomWebElement datePosted_text = new CustomWebElement(Locators.TAGNAME, "time", "Date/Time video posted");
}
