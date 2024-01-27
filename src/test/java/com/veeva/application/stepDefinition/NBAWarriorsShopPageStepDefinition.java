package com.veeva.application.stepDefinition;


import com.veeva.application.pages.NBAWarriorsShopPageActions;
import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.en.Then;
import io.qameta.allure.model.Status;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class NBAWarriorsShopPageStepDefinition extends GenericKeywords {
    NBAWarriorsShopPageActions nbaWarriorsShopPageActions = new NBAWarriorsShopPageActions();

    @Then("Select {string} product")
    public void selectProduct(String productType) {
        productType = ThreadLocalImplementation.getTestData().get("productType");
        nbaWarriorsShopPageActions.selectProduct(productType);
        nbaWarriorsShopPageActions.validateSelectedProducts(productType);
    }

    @Then("Navigate to newly opened window")
    public void navigateToNewlyOpenedWindow() {
        Set<String> getAllWindow = getAllWindows();
        getAllWindow.remove(getWindowName());
        switchWindow(getAllWindow.iterator().next(), "Shop Warrior Tab");
        waitUntilURLIsNotEmpty(Duration.ofSeconds(30));
        Assert.assertTrue(getCurrentUrl().startsWith("https://shop.warriors.com/"));
    }

    @Then("Collect data for {string} {string} in {string}")
    public void collectDataOfEachProduct(String category, String productType, String browser) {
        String filePath =  ThreadLocalImplementation.getTestData().get("category")
                +" "+ThreadLocalImplementation.getTestData().get("productType") +" product details in "+
                ThreadLocalImplementation.getTestData().get("Browser") +".txt";
        nbaWarriorsShopPageActions.getAllProductData(filePath);
        nbaWarriorsShopPageActions.attachToReport(filePath);
        ReporterUtilities.updateTestStatus(Status.PASSED);
    }

}