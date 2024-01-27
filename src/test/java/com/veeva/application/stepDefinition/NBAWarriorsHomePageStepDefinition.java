package com.veeva.application.stepDefinition;


import com.veeva.application.pages.NBAWarriorsHomePageActions;
import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;

public class NBAWarriorsHomePageStepDefinition extends GenericKeywords {

    NBAWarriorsHomePageActions nbaWarriorsHomePageActions = new NBAWarriorsHomePageActions();

    @Given("Open {string} and load {string} URL")
    public void loadNBAURL(String browser , String productName){
        invokeBrowser(browser);
        switch (productName){
            case "NBA Warrior" -> loadUrl(getProperty("warriorUrl"));
        }
    }

    @When("Page is Loaded")
    public void pageIsLoaded() {
        nbaWarriorsHomePageActions.acceptCookies();
//        nbaWarriorsHomePageActions.clickPreSalesClose();
        nbaWarriorsHomePageActions.waitForAds();
    }

    @Then("Navigate to {string} shop now")
    public void navigateToCategoryShopNow(String category) {
        nbaWarriorsHomePageActions.hoverOnShop();
        nbaWarriorsHomePageActions.clickOnShopOptions(category);
    }

    @And("Teardown")
    public void teardown(){
        closeBrowsers();
    }
}
