package com.nba.application.stepDefinition;

import com.nba.application.pages.NBASixersPageActions;
import com.framework.utility.ReporterUtilities;
import io.cucumber.java.en.Then;

/**
 * Step definition class for the NBA Sixers page.
 * This class contains step definitions for various scenarios related to the NBA Sixers page.
 */
public class NBASixersPageStepDefinition {

    /** Constructs a new instance of the {@link NBASixersPageStepDefinition} class. */
    public NBASixersPageStepDefinition(){}

    private final NBASixersPageActions nbaSixersPageActions = new NBASixersPageActions();

    /**
     * Collects and logs the total number of slides on the NBA Sixers page.
     */
    @Then("Collect total number of slides")
    public void collectTotalNumberOfSlides() {
        ReporterUtilities.log("Total number of slides : " + nbaSixersPageActions.totalNumberOfSlides());
    }

    /**
     * Validates the team title displayed on the slide modal.
     * @param title The expected team title.
     * @param teamName The expected team names.
     */
    @Then("Validate title {string} for team {string}")
    public void validateTitle(String title, String teamName) {
        nbaSixersPageActions.moveToFirstSlide();
        nbaSixersPageActions.validateTeamTitle(title, teamName);
    }

    /**
     * Clicks the "Watch Replay" button on the NBA Sixers page.
     */
    @Then("Click WatchReplay")
    public void clickWatchReplay() {
        nbaSixersPageActions.clickWatchReplay();
    }

    /**
     * Clicks on the "Game Recap" button on the NBA Sixers page.
     */
    @Then("Click Game Recap")
    public void clickGameRecap() {
        nbaSixersPageActions.clickOnRecap();
    }

    /**
     * Waits until the AD gets over on the NBA Sixers page.
     */
    @Then("Wait till AD gets over")
    public void waitTillADGetsOver() {
        nbaSixersPageActions.waitTillADGetsOver();
    }

    /**
     * Validates if the video is playing on the NBA Sixers page.
     */
    @Then("Validate video is playing")
    public void validateProgressBar() {
        nbaSixersPageActions.validateIsVideoPlaying();
    }
}
