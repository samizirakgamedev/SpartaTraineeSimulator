package com.purerangers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisplayManager {
    private static final Logger logger = LogManager.getLogger(DisplayManager.class.getName());

    // Method for outputting a bespoke message to the user.
    public static void displayMessage(String message) {
        logger.info(message);
    }

    // Method for outputting a welcome message to the user.
    public static void displayWelcome() {
        displayMessage(
                new StringBuilder().append("\n======================================================================================\n").append("\n **********************|| Welcome to Sparta Trainee Simulator ||********************** \n").append("\n======================================================================================\n").toString());
    }

    public static void displaySeparator() {
        displayMessage("\n======================================================================================\n");
    }

    // Method for outputting the available program options.
    public static void outputOptions() {
        // Will be implemented when options within the program are more clear.

    }

    // Method for outputting the stats from the simulation to the user into the console.
    // Variables are currently placeholders as we will simply be passing through an arraylist of centre types and pulling the individual stats from the list.
    public static void outputStats(int openCentres,
                                   int tHubOpen,
                                   int bCampOpen,
                                   int tCentresOpen,
                                   int closedCentres,
                                   int tHubClosed,
                                   int bCampClosed,
                                   int tCentresClosed,
                                   int fullCentres,
                                   int tHubFull,
                                   int bCampFull,
                                   int tCentresFull,
                                   int traineesTraining,
                                   int javaTraining,
                                   int cSharpTraining,
                                   int dataTraining,
                                   int devOpsTraining,
                                   int businessTraining,
                                   int traineesWaiting,
                                   int javaWaiting,
                                   int cSharpWaiting,
                                   int dataWaiting,
                                   int devOpsWaiting,
                                   int businessWaiting) {
        displayMessage(
                new StringBuilder().append("\nSIMULATION STATISTICS\n").append("• Number of open centres: ").append(openCentres).append("\n").append("   • Training Hubs: ").append(tHubOpen).append("\n").append("   • Boot Camps: ").append(bCampOpen).append("\n").append("   • Tech Centres: ").append(tCentresOpen).append("\n").append("• Number of closed centres: ").append(closedCentres).append("\n").append("   • Training Hubs: ").append(tHubClosed).append("\n").append("   • Boot Camps: ").append(bCampClosed).append("\n").append("   • Tech Centres: ").append(tCentresClosed).append("\n").append("• Number of full centres: ").append(fullCentres).append("\n").append("   • Training Hubs: ").append(tHubFull).append("\n").append("   • Boot Camps: ").append(bCampFull).append("\n").append("   • Tech Centres: ").append(tCentresFull).append("\n").append("• Number of trainees in training: ").append(traineesTraining).append("\n").append("   • Java: ").append(javaTraining).append("\n").append("   • C#: ").append(cSharpTraining).append("\n").append("   • Data: ").append(dataTraining).append("\n").append("   • DevOps: ").append(devOpsTraining).append("\n").append("   • Business: ").append(businessTraining).append("\n").append("• Number of trainees on the waiting list: ").append(traineesWaiting).append("\n").append("   • Java: ").append(javaWaiting).append("\n").append("   • C#: ").append(cSharpWaiting).append("\n").append("   • Data: ").append(dataWaiting).append("\n").append("   • DevOps: ").append(devOpsWaiting).append("\n").append("   • Business: ").append(businessWaiting).append("\n").toString());
    }
}
