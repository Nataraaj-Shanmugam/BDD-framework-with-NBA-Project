package com.veeva.application.stepDefinition;

import com.veeva.application.objectRepository.NBAWarriorsHomePageObjects;
import com.veeva.application.pages.NBAWarriorsNewsPageActions;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.en.Then;

/**
 * Step definitions for scenarios dealing with the NBA Warriors News page.
 * This class defines steps for counting videos and determining the number of videos posted within a certain time range.
 */
public class NBAWarriorsNewsPageStepDefinition {

    /** Constructs a new instance of the {@link NBAWarriorsNewsPageStepDefinition} class. */
    public NBAWarriorsNewsPageStepDefinition(){}

    private final NBAWarriorsNewsPageActions nbaWarriorsNewsPageActions = new NBAWarriorsNewsPageActions();

    /**
     * Counts the total number of videos posted on the NBA Warriors News page and logs the count.
     */
    @Then("Count overAll videos")
    public void countAllVideos(){
        int videoCount = nbaWarriorsNewsPageActions.getAllVideosSize();
        ReporterUtilities.log("Overall videos posted: " + videoCount);
    }

    /**
     * Counts the number of videos posted within a specified number of days on the NBA Warriors News page.
     * The count is logged for reporting purposes.
     *
     * @param numberOfDays The range in days to filter the video count.
     */
    @Then("Count videos {string} days")
    public void countVideosInRange(String numberOfDays){
        int videoCount = nbaWarriorsNewsPageActions.videosPostedInRange(numberOfDays);
        ReporterUtilities.log("Videos posted in " + numberOfDays + " days: " + videoCount);
    }
}
