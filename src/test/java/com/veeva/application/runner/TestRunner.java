package com.veeva.application.runner;

import com.veeva.generic.GenericKeywords;
import com.veeva.generic.ThreadLocalImplementation;
import com.veeva.utility.TestDataUtility;
import io.cucumber.testng.*;
import org.testng.annotations.*;

import java.util.*;
import java.util.stream.Collectors;

@CucumberOptions(
        features = "src/test/java/com/veeva/application/feature",
        glue = "com.veeva.application.stepDefinition",
        plugin = { "pretty", "html:target/cucumber-reports"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    GenericKeywords genericKeywords = new GenericKeywords();
    private static final TestDataUtility testDataUtility =  new TestDataUtility();

    @BeforeSuite
    public void setTestData(){
        testDataUtility.setTestData();
    }

    @BeforeMethod
    public void openBrowser(Object[] executionData){
        genericKeywords.invokeBrowser(((HashMap<String, String>) ((Object[])(executionData[0]))[1]).get("Browser"));
    }

    @AfterMethod
    public void tearDown(){
        genericKeywords.closeBrowsers();
    }

    private Set<String> cucumberTags;

    @BeforeClass
    @Parameters("cucumberTags")
    public void bc(String cucumberTags){
        this.cucumberTags = Arrays.stream(cucumberTags.split(" ")).collect(Collectors.toSet());
    }

    @Test(dataProvider = "dp")
    public void runScenario(Object[] executionData) {
        ThreadLocalImplementation.setTestDataThreadLocal((HashMap<String, String>) executionData[1]);
        new TestNGCucumberRunner(this.getClass()).runScenario(((PickleWrapper) ((Object[])executionData[0])[0]).getPickle());
    }

    @Override
    @DataProvider
    public Object[][] scenarios() {
        // If you don't want any scenarios to be picked up and run automatically, you can return an empty array.
        return new Object[0][0];
    }

    @DataProvider
    public Object[][] dp(){
        List<Object[]> executionData = new ArrayList<>();
        for(Object[] eachValidScenario : getValidScenarios())
            for(HashMap<String,String> eachTestData: testDataUtility.getTestData(((PickleWrapper)eachValidScenario[0]).getPickle().getName()))
                executionData.add(new Object[]{eachValidScenario, eachTestData});

        Object[][] executionDataArray = new Object[executionData.size()][];
        for (int i = 0; i < executionData.size(); i++) {
            executionDataArray[i] = executionData.get(i);
        }
        return executionDataArray;
    }

    private Set<Object[]> getValidScenarios(){
        return Arrays.stream(super.scenarios())
                .filter(each -> {
                    List<String> scenarioTags = ((PickleWrapper) each[0]).getPickle().getTags();
                    return scenarioTags.stream().anyMatch(tag -> this.cucumberTags.contains(tag));
                })
                .collect(Collectors.toSet());
    }
}
