package com.nba.application.pages;

import com.nba.application.objectRepository.NBASixersPageObjects;
import com.framework.generic.keywords.GenericKeywords;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

/**
 * Page actions class for the NBA Sixers page.
 * This class contains methods to interact with and perform actions on the NBA Sixers page.
 */
public class NBASixersPageActions extends GenericKeywords {

   /** Constructs a new instance of the {@link NBASixersPageActions} class. */
   public NBASixersPageActions(){}

   private final NBASixersPageObjects nbaSixersPageObjects = new NBASixersPageObjects();

   /**
    * Gets the total number of slides on the NBA Sixers page.
    * @return The total number of slides.
    */
   public int totalNumberOfSlides(){
      return getElements(nbaSixersPageObjects.slide_modal).size()-2;
   }

   /**
    * Moves to the first slide on the NBA Sixers page.
    */
   public void moveToFirstSlide(){
      for(WebElement ele : getElements(nbaSixersPageObjects.slide_modal)){
         if(!ele.getAttribute("aria-label").startsWith("2 /"))
            click(nbaSixersPageObjects.slide_moveLeft_button);
      }
   }

   /**
    * Validates the team title displayed on the slide modal.
    * @param title The expected team title.
    * @param teamName The expected team names.
    */
   public void validateTeamTitle(String title, String teamName) {
      for(WebElement element: getElements(nbaSixersPageObjects.slide_modal)){
         List<WebElement> teamNames = element.findElements(getByElement(nbaSixersPageObjects.slide_modal_teamsName_text));
         if(teamNames.size()!= 0 && teamName.equalsIgnoreCase(teamNames.get(0).getText() +" vs "+teamNames.get(1).getText())){
            Assert.assertTrue(element.findElement(getByElement(nbaSixersPageObjects.slide_modal_teamsTitle_text)).getText().equalsIgnoreCase(title));
            break;
         }else click(nbaSixersPageObjects.slide_moveRight_button);
      }
   }

   /**
    * Clicks the "Watch Replay" button on the active slide modal.
    */
   public void clickWatchReplay(){
      getElement(nbaSixersPageObjects.activeSlide_modal).findElement(getByElement(nbaSixersPageObjects.watchReplay)).click();
   }

   /**
    * Clicks on the "Game Recap" button.
    */
   public void clickOnRecap(){
      click(nbaSixersPageObjects.gameRecap_button);
   }

   /**
    * Waits until the AD gets over.
    */
   public void waitTillADGetsOver(){
      waitUntilPresent(nbaSixersPageObjects.advertiser_link, Duration.ofSeconds(20));
      waitUntilInvisible(nbaSixersPageObjects.advertiser_link, Duration.ofMinutes(5));
   }

   /**
    * Validates if the video is playing.
    */
   public void validateIsVideoPlaying() {
      waitUntilAttributePresent(nbaSixersPageObjects.video, Duration.ofSeconds(30), "data-playback", "playing");
   }
}