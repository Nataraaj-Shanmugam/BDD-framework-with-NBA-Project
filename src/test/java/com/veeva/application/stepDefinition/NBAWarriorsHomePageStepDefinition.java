package com.veeva.application.stepDefinition;

import com.veeva.application.pages.NBAWarriorsHomePageActions;
import com.veeva.generic.keywords.GenericKeywords;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.model.Status;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

/**
 * Step definitions for scenarios dealing with the NBA Warriors Home page.
 * This class defines steps for various actions like opening URLs, accepting cookies,
 * navigating to specific sections, and handling post-scenario activities.
 */
public class NBAWarriorsHomePageStepDefinition extends GenericKeywords {

    private final NBAWarriorsHomePageActions nbaWarriorsHomePageActions = new NBAWarriorsHomePageActions();

    /**
     * Executes actions after each scenario. Updates the test status in the report and closes browsers.
     *
     * @param scenario The current Cucumber scenario being executed.
     */
    @After
    public void after(Scenario scenario){
        if(scenario.isFailed()) {
            ReporterUtilities.updateTestStatus(Status.FAILED);
        } else {
            ReporterUtilities.updateTestStatus(Status.PASSED);
        }
        closeBrowsers();
    }


    /**
     * Opens a specified browser and loads a designated URL.
     * URLs are determined based on the provided product name.
     *
     * @param browser The name of the browser to open.
     * @param productName The name of the product to determine which URL to load.
     */
    @Given("Open {string} and load {string} URL")
    public void loadNBAURL(String browser, String productName){
        invokeBrowser(browser);
        if ("NBA Warrior".equals(productName)) {
            loadUrl(getProperty("warriorUrl"));
        } else if("NBA Bulls".equals(productName)) {
            loadUrl(getProperty("bullsURL"));
        }
    }

    /**
     * Ensures the page is fully loaded by performing necessary actions like accepting cookies.
     */
    @When("Page is Loaded")
    public void pageIsLoaded() {
        nbaWarriorsHomePageActions.acceptCookies();
        try {
            nbaWarriorsHomePageActions.waitForAds();
        } catch (TimeoutException | NoSuchElementException ignored){}
    }


    /**
     * Navigates to a specific category's shop page.
     *
     * @param category The category to navigate to.
     */
    @Then("Navigate to {string} shop now")
    public void navigateToCategoryShopNow(String category) {
        nbaWarriorsHomePageActions.hoverOnShop();
        nbaWarriorsHomePageActions.clickOnShopOptions(category);
    }

    /**
     * Navigates to a specific menu item, such as 'News and Features', on the NBA Warriors Home page.
     *
     * @param menu The menu item to navigate to.
     */
    @Then("Navigate to menu and to {string}")
    public void navigateToMenuAndToNewsAndFeatures(String menu) {
        nbaWarriorsHomePageActions.hoverToMenu();
        if(menu.equals("News and Features")) {
            nbaWarriorsHomePageActions.clickNewsAndFeatures();
        }
    }
}
