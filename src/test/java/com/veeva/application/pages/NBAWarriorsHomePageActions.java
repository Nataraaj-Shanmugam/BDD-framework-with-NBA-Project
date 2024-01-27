package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBAWarriorsHomePageObjects;
import com.veeva.generic.keywords.GenericKeywords;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.time.Duration;

/**
 * Class containing actions that can be performed on the NBA Warriors Home page.
 * Extends GenericKeywords to leverage common web interaction methods.
 */
public class NBAWarriorsHomePageActions extends GenericKeywords {

    private final NBAWarriorsHomePageObjects nbaWarriorsHomePageObjects = new NBAWarriorsHomePageObjects();

    /**
     * Waits for the ads iframe to become visible on the page.
     */
    public void waitForAds(){
        waitUntilVisible(nbaWarriorsHomePageObjects.ads_iframe, Duration.ofSeconds(15));
    }

    /**
     * Hovers over the menu options on the NBA Warriors Home page.
     */
    public void hoverToMenu(){
        hoverOn(nbaWarriorsHomePageObjects.menu_options);
    }

    /**
     * Clicks on the 'News and Features' link and asserts that the URL ends with '/news'.
     */
    public void clickNewsAndFeatures(){
        click(nbaWarriorsHomePageObjects.newsAndFeatures_link);
        Assert.assertTrue(getCurrentUrl().endsWith("/news"));
    }

    /**
     * Clicks on the 'Accept Cookies' button, if present, with an extended wait time.
     */
    public void acceptCookies(){
        click(nbaWarriorsHomePageObjects.accept_cookies_Button, Duration.ofSeconds(25));
    }

    /**
     * Attempts to close the pre-sales modal by clicking on its close button.
     * Ignores NoSuchElementException and TimeoutException.
     */
    public void clickPreSalesClose(){
        try {
            click(nbaWarriorsHomePageObjects.preSales_Modal_CloseButton);
        } catch (NoSuchElementException | TimeoutException ignored){}
    }

    /**
     * Hovers over the 'Shop' dropdown menu.
     */
    public void hoverOnShop(){
        hoverOn(nbaWarriorsHomePageObjects.shop_dropdown);
    }

    /**
     * Clicks on a specific shop category option in the dropdown menu.
     *
     * @param category The category to select in the shop dropdown.
     */
    public void clickOnShopOptions(String category){
        click(nbaWarriorsHomePageObjects.shop_dropdownOption.updateCustomWebElement(
                nbaWarriorsHomePageObjects.shop_dropdownOption.getElement().replace("DropDownCategory",category),
                nbaWarriorsHomePageObjects.shop_dropdownOption.getComment().replace("DropDownCategory",category)));
    }
}
