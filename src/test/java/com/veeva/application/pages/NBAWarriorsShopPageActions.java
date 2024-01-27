package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBAWarriorsShopPageObjects;
import com.veeva.generic.keywords.GenericKeywords;
import com.veeva.utility.ReporterUtilities;
import org.testng.Assert;

/**
 * Class containing actions that can be performed on the NBA Warriors Shop page.
 * Extends GenericKeywords to leverage common web interaction methods.
 */
public class NBAWarriorsShopPageActions extends GenericKeywords {

    private final NBAWarriorsShopPageObjects nbaWarriorsShopPageObjects = new NBAWarriorsShopPageObjects();

    /**
     * Selects a product on the NBA Warriors Shop page by its name.
     *
     * @param product The name of the product to select.
     */
    public void selectProduct(String product) {
        click(nbaWarriorsShopPageObjects.productName_radioButton.updateCustomWebElement(
                nbaWarriorsShopPageObjects.productName_radioButton.getElement().replace("ProductName", product),
                nbaWarriorsShopPageObjects.productName_radioButton.getComment().replace("ProductName", product)));
    }

    /**
     * Validates that a selected product is displayed on the page.
     *
     * @param product The name of the product to validate.
     */
    public void validateSelectedProducts(String product) {
        Assert.assertNotNull(getElement(nbaWarriorsShopPageObjects.yourSelection_list.updateCustomWebElement(
                nbaWarriorsShopPageObjects.yourSelection_list.getElement().replace("ProductName", product),
                nbaWarriorsShopPageObjects.yourSelection_list.getComment().replace("ProductName", product))));
    }

    /**
     * Collects data of all products listed on the page and writes it to a specified file.
     *
     * @param filePathWithName The file path where the product data should be written.
     */
    public void getAllProductData(String filePathWithName){
        StringBuilder builder = new StringBuilder();
        builder.append("Price, Title, Top Seller Comments \n");
        // ... (code truncated for brevity)
    }

    /**
     * Attaches a specified file to the Allure report.
     *
     * @param path The path of the file to be attached to the report.
     */
    public void attachToReport(String path){
        ReporterUtilities.attachFileToAllureReport(path, path);
    }
}
