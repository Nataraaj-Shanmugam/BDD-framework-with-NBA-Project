package com.veeva.generic;

import java.time.LocalDate;

public interface NonSeleniumKeywords {

    String getProperty(String key);

    void writeToFile(String content, String filePath);

    LocalDate formattedDate(String date, String format);

    LocalDate formattedDate(String date);

    long dateDifference(LocalDate date1, LocalDate date2);

    void generateCSV(String[][] csvData,String fileName);
}
