package com.veeva.application.stepDefinition;

import com.veeva.application.objectRepository.NBAWarriorsHomePageObjects;
import com.veeva.application.pages.NBAWarriorsShopPageActions;
import com.veeva.generic.keywords.GenericKeywords;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

/**
 * Step definitions for Cucumber scenarios targeting the NBA Warriors Shop page.
 * This class provides the steps to select products, navigate between windows, and collect product data.
 */
public class NBAWarriorsShopPageStepDefinition extends GenericKeywords {

    /** Constructs a new instance of the {@link NBAWarriorsShopPageStepDefinition} class. */
    public NBAWarriorsShopPageStepDefinition(){}

    private final NBAWarriorsShopPageActions nbaWarriorsShopPageActions = new NBAWarriorsShopPageActions();

    /**
     * Selects a specified product and validates that the product is correctly selected.
     *
     * @param productType The type of product to select.
     */
    @Then("Select {string} product")
    public void selectProduct(String productType) {
        nbaWarriorsShopPageActions.selectProduct(productType);
        nbaWarriorsShopPageActions.validateSelectedProducts(productType);
    }

    /**
     * Navigates to a newly opened window and asserts the URL to confirm successful navigation.
     */
    @Then("Navigate to newly opened window from {string}")
    public void navigateToNewlyOpenedWindow(String name) {
        Set<String> getAllWindow = getAllWindows();
        getAllWindow.remove(getWindowName());
        switchWindow(getAllWindow.iterator().next(), name.equals("Warrior") ? "Shop tab" : "Match tab");
        waitUntilURLIsNotEmpty(Duration.ofSeconds(30));
        Assert.assertTrue(name.equals("Warrior") ? getCurrentUrl().startsWith("https://shop.warriors.com/") : getCurrentUrl().endsWith("watch"));
    }

    /**
     * Collects data for each product in a specified category and browser, and saves it to a file.
     *
     * @param category    The product category.
     * @param productType The type of product.
     * @param browser     The browser being used for the test.
     */
    @Then("Collect data for {string} {string} in {string}")
    public void collectDataOfEachProduct(String category, String productType, String browser) {
        String filePath = category + " " + productType + " product details in " + browser + ".txt";
        nbaWarriorsShopPageActions.getAllProductData(filePath);
        nbaWarriorsShopPageActions.attachToReport(filePath);
    }
}