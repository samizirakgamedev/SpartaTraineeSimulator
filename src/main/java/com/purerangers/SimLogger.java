package com.purerangers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimLogger {
    // Initialises the implemented Log4j logger.
    private static final Logger logger = LogManager.getLogger("SimLogger");
    // Called when wanting to add log information.
    public static void logInfo(String message){
        logger.info(message);
    }
    // Called when wanting to log errors.
    public static void logError(String message){
        logger.error(message);
    }
    // Called when wanting to log a warning.
    public static void logWarn(String message){
        logger.warn(message);
    }
}
