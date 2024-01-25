package com.veeva;

import com.veeva.generic.GenericKeywords;
import com.veeva.utility.TestDataUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestExecutionEngine {

    GenericKeywords genericKeywords = new GenericKeywords();

    @BeforeSuite
    public void setTestData(){
        new TestDataUtility().setTestData();
    }

    @BeforeMethod
    public void openBrowser(){
        genericKeywords.invokeBrowser();
    }

    @AfterMethod
    public void tearDown(){
        genericKeywords.closeBrowsers();
    }

}
