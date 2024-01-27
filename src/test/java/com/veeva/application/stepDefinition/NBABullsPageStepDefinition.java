package com.veeva.application.stepDefinition;

import com.veeva.application.pages.NBABullsPageActions;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.en.Then;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NBABullsPageStepDefinition {

    NBABullsPageActions bullsPageActions = new NBABullsPageActions();

    @Then("Navigate to footer")
    public void navigateToFooter(){
        bullsPageActions.scrollToFooter();
    }
    HashMap<String, List<String>> hyperLink = null;
    @Then("Get all footer links")
    public void getLinks(){
        hyperLink = bullsPageActions.getAllLinks();
    }

    @Then("Export to csv file")
    public void exportTOCSV(){
        bullsPageActions.generateCSV(hyperLink, "Hyperlinks.csv");
        ReporterUtilities.attachFileToAllureReport("Hyperlinks.csv", "Hyperlinks.csv");
    }
}
