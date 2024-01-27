package com.veeva.application.objectRepository;

import com.veeva.custom.CustomWebElement;
import com.veeva.generic.Locators;

public class NBAWarriorsShopPageObjects {

    public CustomWebElement productName_radioButton = new CustomWebElement(Locators.XPATH, "//div[@data-trk-id='all-departments']//ul//span[text()='ProductName']", "ProductName product type");
    public CustomWebElement yourSelection_list = new CustomWebElement(Locators.XPATH, "//ul[@data-trk-id = 'remove-filter']//span[text()='ProductName']", "Your Selection - ProductName");
    public CustomWebElement eachProduct_element = new CustomWebElement(Locators.CLASSNAME, "column", "Product element");
    public CustomWebElement eachProductCost_text = new CustomWebElement(Locators.CLASSNAME, "sr-only", "Product amount");
    public CustomWebElement eachProductTitle_text = new CustomWebElement(Locators.CLASSNAME, "product-card-title", "Product Title");
    public CustomWebElement eachProductTopSellerMessage_text = new CustomWebElement(Locators.CLASSNAME, "top-seller-vibrancy-message", "Top seller message");
    public CustomWebElement nextPage_button = new CustomWebElement(Locators.XPATH, "//li[@class='next-page']", "Next page pagination");




}
