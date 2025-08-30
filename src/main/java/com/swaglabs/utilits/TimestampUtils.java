package com.swaglabs.utilits;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {
    /*To make unique name for files or any attach*/
    public static String getTimestamp()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return formatter.format(date);
    }

    public static void main(String[] args)
    {
        System.out.println(getTimestamp());
    }
}
