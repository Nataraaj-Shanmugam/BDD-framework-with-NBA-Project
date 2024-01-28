package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;
/**
 * Repository class for object locators of the NBA Warrior Shop page.
 * This class holds the locators for various elements on the NBA Warrior Shop page.
 */
public class NBAWarriorsShopPageObjects {

    /** Constructs a new instance of the {@link NBAWarriorsShopPageObjects} class. */
    public NBAWarriorsShopPageObjects(){}

    /** CustomWebElement representing the ProductName radio button. */
    public CustomWebElement productName_radioButton = new CustomWebElement(Locators.XPATH, "//div[@data-trk-id='all-departments']//ul//span[text()='ProductName']", "ProductName product type");

    /** CustomWebElement representing the Your Selection - ProductName element. */
    public CustomWebElement yourSelection_list = new CustomWebElement(Locators.XPATH, "//ul[@data-trk-id = 'remove-filter']//span[text()='ProductName']", "Your Selection - ProductName");

    /** CustomWebElement representing a Product element. */
    public CustomWebElement eachProduct_element = new CustomWebElement(Locators.CLASSNAME, "column", "Product element");

    /** CustomWebElement representing the Product amount text. */
    public CustomWebElement eachProductCost_text = new CustomWebElement(Locators.CLASSNAME, "sr-only", "Product amount");

    /** CustomWebElement representing the Product Title text. */
    public CustomWebElement eachProductTitle_text = new CustomWebElement(Locators.CLASSNAME, "product-card-title", "Product Title");

    /** CustomWebElement representing the Top seller message. */
    public CustomWebElement eachProductTopSellerMessage_text = new CustomWebElement(Locators.CLASSNAME, "top-seller-vibrancy-message", "Top seller message");

    /** CustomWebElement representing the Next page pagination button. */
    public CustomWebElement nextPage_button = new CustomWebElement(Locators.XPATH, "//li[@class='next-page']", "Next page pagination");
}