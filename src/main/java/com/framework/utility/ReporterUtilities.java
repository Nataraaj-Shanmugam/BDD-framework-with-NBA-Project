package com.framework.utility;

import com.framework.generic.keywords.GenericKeywords;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.model.Status;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for reporting and logging in tests.
 * This class provides methods for logging messages, updating test statuses, and attaching files to Allure reports.
 */
public class ReporterUtilities {

    private static final Logger logger = LoggerUtility.getLogger(ReporterUtilities.class);

    /** Constructs a new instance of the {@link ReporterUtilities} class. */
    public ReporterUtilities(){    }

    /**
     * Logs a comment both to the Allure report and Log4j logger.
     *
     * @param comment The comment to be logged.
     */
    public static void log(String comment){
        Allure.step(comment);
        logger.log(Level.INFO, comment);
    }

    /**
     * Updates the test status in the Allure report and takes a screenshot.
     * Logs the test status using Log4j. If the test status is not 'PASSED',
     * it logs as info, otherwise, it logs as an error.
     *
     * @param status The status of the test (e.g., PASSED, FAILED, SKIPPED, etc.) from Allure's Status enum.
     */
    public static void updateTestStatus(Status status){
        Allure.addAttachment("Page Screenshot", "image/png", new ByteArrayInputStream(new GenericKeywords().takeScreenshot()), ".png");
        if(status == Status.PASSED) {
            logger.log(Level.INFO,"Testcase Passed");
        } else {
            logger.log(Level.ERROR,"Testcase " + status);
        }
        Allure.step("Test Case " + status.value(), status);
    }

    /**
     * Attaches a file to the Allure report.
     *
     * @param filePath       The path of the file to attach.
     * @param attachmentName The name for the attachment in the report.
     */
    public static void attachFileToAllureReport(String filePath, String attachmentName) {
        try {
            Path content = Paths.get(filePath);
            Allure.addAttachment(attachmentName, Files.newInputStream(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
