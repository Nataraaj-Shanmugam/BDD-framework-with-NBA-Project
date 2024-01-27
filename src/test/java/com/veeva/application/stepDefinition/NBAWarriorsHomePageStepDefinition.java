package com.veeva.application.stepDefinition;


import com.veeva.application.pages.NBAWarriorsHomePageActions;
import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Param;
import io.qameta.allure.model.Parameter;

import java.util.HashMap;

public class NBAWarriorsHomePageStepDefinition extends GenericKeywords {

    @Before
    public void before(){

    }

    NBAWarriorsHomePageActions nbaWarriorsHomePageActions = new NBAWarriorsHomePageActions();

    @Given("Load {string} URL")
    public void loadNBAURL(String productName){

        AllureLifecycle allureLifeCycle = Allure.getLifecycle();
        ThreadLocalImplementation.getTestData().forEach((key, value) ->
                allureLifeCycle.updateTestCase(testResult ->
                        testResult.getParameters().add(new Parameter().setName(key).setValue(value))
                )
        );

        System.out.println(allureLifeCycle.getCurrentTestCase());
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
        category = ThreadLocalImplementation.getTestData().get("category");
        nbaWarriorsHomePageActions.hoverOnShop();
        nbaWarriorsHomePageActions.clickOnShopOptions(category);
    }

    @And("Teardown")
    public void teardown(){
        closeBrowsers();
    }
}