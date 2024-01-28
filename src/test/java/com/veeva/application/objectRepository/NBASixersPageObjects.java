package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;

/**
 * Repository class for object locators of the NBA Sixers page.
 * This class holds the locators for various elements on the NBA Sixers page.
 */
public class NBASixersPageObjects {

    /** Constructs a new instance of the {@link NBASixersPageObjects} class. */
    public NBASixersPageObjects(){}

    /** CustomWebElement representing the slide modal. */
    public CustomWebElement slide_modal = new CustomWebElement(Locators.XPATH, "//div[contains(@class, 'swiper-slide-active')]/../div", "Slide modal");

    /** CustomWebElement representing the active slide modal. */
    public CustomWebElement activeSlide_modal = new CustomWebElement(Locators.XPATH, "//div[contains(@class, 'swiper-slide-active')]", "Active Slide modal");

    /** CustomWebElement representing the team's name in the slide modal. */
    public CustomWebElement slide_modal_teamsName_text = new CustomWebElement(Locators.XPATH, ".//img/following-sibling::span", "Slide modal team's name");

    /** CustomWebElement representing the team's title in the slide modal. */
    public CustomWebElement slide_modal_teamsTitle_text = new CustomWebElement(Locators.XPATH, ".//div[contains(@class,'Game_gameHeader')]", "Slide modal team's title");

    /** CustomWebElement representing the Move Left button in the slide modal. */
    public CustomWebElement slide_moveLeft_button = new CustomWebElement(Locators.XPATH, "//button[@aria-label='Move left']", "Move Left button");

    /** CustomWebElement representing the Move Right button in the slide modal. */
    public CustomWebElement slide_moveRight_button = new CustomWebElement(Locators.XPATH, "//button[@aria-label='Move right']", "Move Right button");

    /** CustomWebElement representing the Watch Replay button. */
    public CustomWebElement watchReplay = new CustomWebElement(Locators.XPATH, ".//a", "Watch replay button");

    /** CustomWebElement representing the Game Recap button. */
    public CustomWebElement gameRecap_button = new CustomWebElement(Locators.XPATH, "//button[text()='Game Recap']", "Game recap button");

    /** CustomWebElement representing the video element. */
    public CustomWebElement video = new CustomWebElement(Locators.XPATH, "//video[@class]", "video");

    /** CustomWebElement representing the advertiser link. */
    public CustomWebElement advertiser_link = new CustomWebElement(Locators.LINKTEXT, "Visit advertiser", "AD");
}
