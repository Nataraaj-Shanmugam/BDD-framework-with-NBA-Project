package com.veeva.generic.keywords;

import java.time.LocalDate;

/**
 * Interface defining non-Selenium related utility methods.
 * This includes operations like property retrieval, file writing, date manipulation, and CSV generation.
 */
public interface NonSeleniumKeywords {

    /**
     * Retrieves a property value from a properties file.
     *
     * @param key The key of the property to be retrieved.
     * @return The value of the specified property.
     */
    String getProperty(String key);

    /**
     * Writes content to a file at a specified file path.
     *
     * @param content  The content to write to the file.
     * @param filePath The file path where the content should be written.
     */
    void writeToFile(String content, String filePath);

    /**
     * Parses a date string into a LocalDate object using the specified format.
     *
     * @param date   The date string to parse.
     * @param format The format to use for parsing.
     * @return The parsed LocalDate object.
     */
    LocalDate formattedDate(String date, String format);

    /**
     * Parses a date string into a LocalDate object using the default format.
     *
     * @param date The date string to parse.
     * @return The parsed LocalDate object.
     */
    LocalDate formattedDate(String date);

    /**
     * Calculates the difference in days between two LocalDate objects.
     *
     * @param date1 The first date.
     * @param date2 The second date.
     * @return The number of days between the two dates.
     */
    long dateDifference(LocalDate date1, LocalDate date2);

    /**
     * Generates a CSV file from a two-dimensional array of strings.
     *
     * @param csvData   The data to be written to the CSV file.
     * @param fileName The name of the CSV file to create.
     */
    void generateCSV(String[][] csvData, String fileName);
}