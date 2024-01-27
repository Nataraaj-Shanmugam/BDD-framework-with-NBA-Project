package com.veeva.application.stepDefinition;


import com.veeva.application.pages.NBAWarriorsHomePageActions;
import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NBAWarriorsHomePageStepDefinition extends GenericKeywords {

    NBAWarriorsHomePageActions nbaWarriorsHomePageActions = new NBAWarriorsHomePageActions();

    @Given("Load {string} URL")
    public void loadNBAURL(String productName){
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

    @Then("Navigate to {string} Shop Now")
    public void navigateToShopNow(String category) {
        category = ThreadLocalImplementation.getTestData().get("category");

        nbaWarriorsHomePageActions.hoverOnShop();
        nbaWarriorsHomePageActions.clickOnShopOptions(category);
    }
}
