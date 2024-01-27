package com.veeva.application.stepDefinition;


import com.veeva.application.pages.NBAWarriorsHomePageActions;
import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.qameta.allure.model.Status;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class NBAWarriorsHomePageStepDefinition extends GenericKeywords {

    NBAWarriorsHomePageActions nbaWarriorsHomePageActions = new NBAWarriorsHomePageActions();

    @Given("Open {string} and load {string} URL")
    public void loadNBAURL(String browser , String productName){
        invokeBrowser(browser);
        if ("NBA Warrior".equals(productName)) loadUrl(getProperty("warriorUrl"));
        else if("NBA Bulls".equals(productName)) loadUrl(getProperty("bullsURL"));
    }

    @When("Page is Loaded")
    public void pageIsLoaded() {
        nbaWarriorsHomePageActions.acceptCookies();
//        nbaWarriorsHomePageActions.clickPreSalesClose();
        try {
            nbaWarriorsHomePageActions.waitForAds();
        }catch (TimeoutException | NoSuchElementException ignored){}
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

    @Then("Navigate to menu and to {string}")
    public void navigateToMenuAndToNewsAndFeatures(String menu) {
        nbaWarriorsHomePageActions.hoverToMenu();
        if(menu.equals("News and Features")) nbaWarriorsHomePageActions.clickNewsAndFeatures();
    }
}
