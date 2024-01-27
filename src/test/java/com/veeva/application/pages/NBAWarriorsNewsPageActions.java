package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBAWarriorsNewsPageObjects;
import com.veeva.generic.GenericKeywords;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class NBAWarriorsNewsPageActions extends GenericKeywords {

    NBAWarriorsNewsPageObjects nbaWarriorsNewsPageObjects = new NBAWarriorsNewsPageObjects();

    public int getAllVideosSize(){
        waitUntilVisible(nbaWarriorsNewsPageObjects.videos_modal);
        return getElements(nbaWarriorsNewsPageObjects.videos_modal).size();
    }

    public int videosPostedInRange(String range){
        int count = 0;
        for (WebElement element : getElements(nbaWarriorsNewsPageObjects.videos_modal)) {
            if (dateCompare(range, dateDifference(formattedDate(getAttributeValue(element.findElement(getByElement(nbaWarriorsNewsPageObjects.datePosted_text)), "datetime"), "EEE MMM dd yyyy"), LocalDate.now())))
                count++;
        }
        return count;
    }

    private boolean dateCompare(String condition, long validateNumber) {
        String[] parts = condition.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        return switch (parts[0]) {
            case ">=" -> validateNumber >= Integer.parseInt(parts[1]) ;
            case "<=" -> validateNumber <= Integer.parseInt(parts[1]);
            case ">" -> validateNumber > Integer.parseInt(parts[1]);
            case "<" -> validateNumber < Integer.parseInt(parts[1]);
            case "==" -> validateNumber == Integer.parseInt(parts[1]);
            default -> throw new IllegalArgumentException("Invalid operator: " + parts[0]);
        };
    }
}
