package com.nba.application.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

/**
 * The TestRunner class is used to run Cucumber tests in TestNG.
 * It extends AbstractTestNGCucumberTests to provide TestNG compatibility.
 * The class also defines a DataProvider for parallel execution of test scenarios.
 */
public class TestRunner extends AbstractTestNGCucumberTests {

    /** Constructs a new instance of the {@link TestRunner} class. */
    public TestRunner(){}

    /**
     * Provides the test scenarios as a DataProvider for parallel execution.
     * Overriding the super class, to make parallel execution
     *
     * @return An array of test scenarios.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
