package com.swaglabs.utilits;

import com.swaglabs.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.nio.file.Files;

import static com.swaglabs.utilits.TimestampUtils.getTimestamp;

public class ScreenshotsUtils {
    public static final String SCREENSHOT_PATH = "test-outputs/screenshots/";
    private ScreenshotsUtils()
    {
        super();
    }
    public static void takeScreenshots(String screenshotName)
    {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName +"_" + getTimestamp() + ".png");
            FilesUtiles.copyFile(screenshot, screenshotFile);
            AllureUtils.attachScreenshotToAllure(screenshotName, screenshotFile.getPath());
        } catch (Exception e) {
            LogsUtil.error("Failed to take screenshot: "+ e.getMessage());
        }


    }
}
