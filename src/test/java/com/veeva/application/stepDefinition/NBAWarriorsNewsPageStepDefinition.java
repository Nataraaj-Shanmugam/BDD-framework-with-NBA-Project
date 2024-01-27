package com.veeva.application.stepDefinition;

import com.veeva.application.pages.NBAWarriorsNewsPageActions;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.en.Then;

public class NBAWarriorsNewsPageStepDefinition {

    NBAWarriorsNewsPageActions nbaWarriorsNewsPageActions = new NBAWarriorsNewsPageActions();

    @Then("Count overAll videos")
    public void countAllVideos(){
        ReporterUtilities.log("Overall videos posted : "+ nbaWarriorsNewsPageActions.getAllVideosSize());
    }


    @Then("Count videos {string} days")
    public void countVideosInRange(String numberOfDays){
        ReporterUtilities.log("Videos posted "+numberOfDays+" days : "+nbaWarriorsNewsPageActions.videosPostedInRange(numberOfDays));
    }

}
