package com.veeva.application.pages;

import com.veeva.application.objectRepository.NBABullsPageObjects;
import com.veeva.application.objectRepository.NBAWarriorsNewsPageObjects;
import com.veeva.generic.GenericKeywords;
import com.veeva.utility.ReporterUtilities;
import io.cucumber.java.hu.Ha;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.*;

public class NBABullsPageActions extends GenericKeywords {

   NBABullsPageObjects nbaBullsPageObjects = new NBABullsPageObjects();

   public void scrollToFooter(){
       scrollTo(nbaBullsPageObjects.footer_title);
   }

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
