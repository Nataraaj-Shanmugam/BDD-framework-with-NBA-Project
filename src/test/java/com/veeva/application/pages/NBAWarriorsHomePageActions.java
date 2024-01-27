package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBAWarriorsHomePageObjects;
import com.veeva.generic.GenericKeywords;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class NBAWarriorsHomePageActions extends GenericKeywords {

    NBAWarriorsHomePageObjects nbaWarriorsHomePageObjects = new NBAWarriorsHomePageObjects();

    public void waitForAds(){
       waitUntilVisible(nbaWarriorsHomePageObjects.ads_iframe, Duration.ofSeconds(15));
    }

    public void acceptCookies(){
        click(nbaWarriorsHomePageObjects.accept_cookies_Button, Duration.ofSeconds(10));
    }

    public void clickPreSalesClose(){
        try {
            click(nbaWarriorsHomePageObjects.preSales_Modal_CloseButton);
        }catch (NoSuchElementException | TimeoutException ignored){}
    }

    public void hoverOnShop(){
        hoverOn(nbaWarriorsHomePageObjects.shop_dropdown);
    }

    public void clickOnShopOptions(String category){
        click(nbaWarriorsHomePageObjects.shop_dropdownOption.updateCustomWebElement(
                nbaWarriorsHomePageObjects.shop_dropdownOption.getElement().replace("DropDownCategory",category),
                nbaWarriorsHomePageObjects.shop_dropdownOption.getComment().replace("DropDownCategory",category)));
    }

}
