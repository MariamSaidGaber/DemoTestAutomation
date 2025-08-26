package com.swaglabs.utilits;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FilesUtiles {
    private FilesUtiles(){
        super();
    }

    /*This fun to report the log files*/
    public static File getLatesFile(String folderPath)
    {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if(files == null || files.length == 0)
        {
            LogsUtil.warn("No files found i directory" + folderPath);
            return null;
        }

        File latestFile = files[0];
        for(File file: files)
        {
            if(file.lastModified() > latestFile.lastModified())
            {
                latestFile = file;
            }
        }
        return latestFile;


    }
    /* This fun to remove the files of Allure before running */
    /*Send directory path of files*/
    public static void deleteFiles(File dirPath)
    {
        if(dirPath == null || !(dirPath.exists()))
        {
            LogsUtil.warn("Directory does not exist" + dirPath);
            return;
        }
        File[] filesList = dirPath.listFiles();
        if(filesList == null)
        {
            LogsUtil.warn("Failed to list files in: " + dirPath);
            return;
        }

        for(File file : filesList)
        {
            if(file.isDirectory())
            {
                deleteFiles(file);
            }
            else
            {
               try
               {
                   Files.delete(file.toPath());
               } catch (IOException e)
                 {
                   LogsUtil.error("Failed to delete file: "+ file);
                 }

            }
        }
    }


    public static void copyFile(File source, File destination) {
        try {
            // Ensure parent directory exists
            File parentDir = destination.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Copy the file (REPLACE_EXISTING if file already exists)
            Files.copy(source.toPath(), destination.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LogsUtil.error("Failed to copy file from " + source + " to " + destination + ": " + e.getMessage());
        }
    }

    public static void cleanDirectory(File file) {
        try {
            FileUtils.cleanDirectory(file);
        } catch (Exception e)
        {
            LogsUtil.info(e.getMessage());
        }
    }

}
