package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBAWarriorsNewsPageObjects;
import com.veeva.generic.keywords.GenericKeywords;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;

/**
 * Class containing actions that can be performed on the NBA Warriors News page.
 * Extends GenericKeywords to leverage common web interaction methods.
 */
public class NBAWarriorsNewsPageActions extends GenericKeywords {

    private final NBAWarriorsNewsPageObjects nbaWarriorsNewsPageObjects = new NBAWarriorsNewsPageObjects();

    /**
     * Gets the count of all video elements present on the NBA Warriors News page.
     *
     * @return The number of video elements found.
     */
    public int getAllVideosSize(){
        waitUntilVisible(nbaWarriorsNewsPageObjects.videos_modal);
        return getElements(nbaWarriorsNewsPageObjects.videos_modal).size();
    }

    /**
     * Counts the number of videos posted within a specific date range.
     *
     * @param range A string representing the date range condition (e.g., ">=5").
     * @return The count of videos matching the date range condition.
     */
    public int videosPostedInRange(String range){
        int count = 0;
        for (WebElement element : getElements(nbaWarriorsNewsPageObjects.videos_modal)) {
            if (dateCompare(range, dateDifference(formattedDate(getAttributeValue(element.findElement(getByElement(nbaWarriorsNewsPageObjects.datePosted_text)), "datetime"), "EEE MMM dd yyyy"), LocalDate.now())))
                count++;
        }
        return count;
    }

    /**
     * Compares a given date difference with a condition.
     *
     * @param condition       The condition to compare against (e.g., ">=", "<=").
     * @param validateNumber  The number representing the date difference to validate.
     * @return true if the date difference meets the specified condition, false otherwise.
     */
    private boolean dateCompare(String condition, long validateNumber) {
        String[] parts = condition.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        return switch (parts[0]) {
            case ">=" -> validateNumber >= Integer.parseInt(parts[1]);
            case "<=" -> validateNumber <= Integer.parseInt(parts[1]);
            case ">" -> validateNumber > Integer.parseInt(parts[1]);
            case "<" -> validateNumber < Integer.parseInt(parts[1]);
            case "==" -> validateNumber == Integer.parseInt(parts[1]);
            default -> throw new IllegalArgumentException("Invalid operator: " + parts[0]);
        };
    }
}
