package com.purerangers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private static final Logger logger = LogManager.getLogger(DateFormat.class.getName());

    public static LocalDate formatDate(String s)
    {
        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate date = LocalDate.parse(s, formatter);
            return date;
        }
        catch (Exception e)
        {
            logger.warn("Unable to format String: " + s);
        }
        return null;
    }
}
