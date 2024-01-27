package com.veeva.application.stepDefinition;


import com.veeva.application.pages.NBAWarriorsShopPageActions;
import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.Set;

public class NBAWarriorsShopPageStepDefinition extends GenericKeywords {
    NBAWarriorsShopPageActions nbaWarriorsShopPageActions = new NBAWarriorsShopPageActions();

    @Then("Select {string} product")
    public void selectProduct(String productType) {
        loadUrl("https://shop.warriors.com/golden-state-warriors-men/t-14145130+ga-67+z-978556-2897172570");
        productType = ThreadLocalImplementation.getTestData().get("productType");
        nbaWarriorsShopPageActions.selectProduct(productType);
        nbaWarriorsShopPageActions.validateSelectedProducts(productType);
    }

    @Then("Navigate to newly opened window")
    public void navigateToNewlyOpenedWindow() {
        Set<String> getAllWindow = getAllWindows();
        getAllWindow.remove(getWindowName());
        switchWindow(getAllWindow.iterator().next());
        Assert.assertTrue(getCurrentUrl().startsWith("https://shop.warriors.com/"));
    }

    @Then("Collect data of each product")
    public void collectDataOfEachProduct() {
        String filePath = ThreadLocalImplementation.getTestData().get("category")+" "+ThreadLocalImplementation.getTestData().get("productType")+" product details.txt";
        nbaWarriorsShopPageActions.getAllProductData(filePath);
        nbaWarriorsShopPageActions.attachToReport(filePath);
    }

}
