package com.swaglabs.utilits;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static final String ALLURE_RESULTS_IS_PATH = "test-outputs/allure-result";
    private AllureUtils()
    {
        super();
    }

    public static void attacheLogsToAllureReport()
    {
        try {
            File logFile = FilesUtiles.getLatesFile(LogsUtil.LOGG_PATH);
            if(!logFile.exists())
            {
                LogsUtil.warn("Log file does not exist" + LogsUtil.LOGG_PATH);
                return;
            }
            Allure.addAttachment("logs.log",Files.readString(Path.of( logFile.getPath() ) ));
            LogsUtil.info("Logs attached to Allure Report");
        } catch (Exception e){
            LogsUtil.error("Failed to attach logs to Allure report: "+ e.getMessage());
        }

    }

    public static void attachScreenshotToAllure(String screenshotsName, String screenshotsPath)
    {
        try{
            Allure.addAttachment(screenshotsName, Files.newInputStream(Path.of(screenshotsPath)));
        } catch (Exception e) {
            LogsUtil.error("Failed to attach screenshot to Allure report: " + e.getMessage());
        }
    }

}
