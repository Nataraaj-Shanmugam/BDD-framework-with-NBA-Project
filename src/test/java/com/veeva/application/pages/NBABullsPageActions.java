package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBABullsPageObjects;
import com.veeva.application.objectRepository.NBAWarriorsHomePageObjects;
import com.veeva.generic.keywords.GenericKeywords;
import com.veeva.utility.ReporterUtilities;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * Class containing actions that can be performed on the NBA Bulls page.
 * Extends GenericKeywords to leverage common web interaction methods.
 */
public class NBABullsPageActions extends GenericKeywords {


   /** Constructs a new instance of the {@link NBABullsPageActions} class. */
   public NBABullsPageActions(){}

   private final NBABullsPageObjects nbaBullsPageObjects = new NBABullsPageObjects();

   /**
    * Scrolls to the footer section of the NBA Bulls page.
    */
   public void scrollToFooter(){
      scrollTo(nbaBullsPageObjects.footer_title);
   }

   /**
    * Retrieves all hyperlinks present in the footer of the NBA Bulls page.
    *
    * @return A HashMap with the title of the section as the key and a list of hyperlinks as the value.
    */
   public HashMap<String, List<String>> getAllLinks(){
      Set<String> uniqueHref = new HashSet<>();
      HashMap<String, List<String>> hyperlinks = new HashMap<>();
      StringBuilder builder = new StringBuilder();
      for(WebElement ele: getElements(nbaBullsPageObjects.footer_title)){
         List<String> href = new ArrayList<>();
         for(WebElement ele1 : ele.findElements(getByElement(nbaBullsPageObjects.footer_link))){
            href.add(getAttributeValue(ele1, "href"));
            if(!uniqueHref.add(getAttributeValue(ele1, "href"))){
               builder.append(getText(ele1));
            }
         }
         hyperlinks.put(getText(ele), href);
      }
      ReporterUtilities.log(builder.isEmpty() ? "No duplicate hyperlinks" : "Duplicate Hyperlink : "+builder);
      return hyperlinks;
   }

   /**
    * Generates a CSV file from the provided hyperlink data.
    *
    * @param data     The hyperlink data to be included in the CSV.
    * @param filename The name of the CSV file to be generated.
    */
   public void generateCSV(HashMap<String, List<String>> data,String filename){
      String[][] csvData = new String[data.values().stream().max(Comparator.comparingInt(List::size)).get().size()+1][data.size()];
      Arrays.stream(csvData).forEach(row -> Arrays.fill(row, ""));
      int columnNumber = 0;
      for(Map.Entry<String, List<String>> entry : data.entrySet()){
         csvData[0][columnNumber] = entry.getKey();
         for(int i =0; i < entry.getValue().size();i++)
            csvData[i+1][columnNumber]=entry.getValue().get(i);
         columnNumber++;
      }
      generateCSV(csvData, filename);
   }
}
