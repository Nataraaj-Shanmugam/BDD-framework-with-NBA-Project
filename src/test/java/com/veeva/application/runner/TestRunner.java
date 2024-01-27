package com.veeva.application.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/com/veeva/application/feature",
        glue = "com.veeva.application.stepDefinition",
        plugin = { "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
