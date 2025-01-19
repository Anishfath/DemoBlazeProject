package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    // Method to capture a screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        // Create a timestamp for unique file names
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Capture the screenshot as a file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Specify the destination path for the screenshot
        String destination = "screenshots/" + screenshotName + "_" + timestamp + ".png";

        // Save the screenshot to the destination
        try {
            FileUtils.copyFile(srcFile, new File(destination));
            System.out.println("Screenshot saved at: " + destination);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}

