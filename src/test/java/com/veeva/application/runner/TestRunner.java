package com.veeva.application.runner;

import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import com.veeva.utility.TestDataUtility;
import io.cucumber.java.After;
import io.cucumber.testng.*;
import org.testng.annotations.*;

import java.util.*;
import java.util.stream.Collectors;

@CucumberOptions(
        features = "src/test/java/com/veeva/application/feature",
        glue = "com.veeva.application.stepDefinition",
        plugin = { "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "pretty", "html:target/cucumber-reports"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
