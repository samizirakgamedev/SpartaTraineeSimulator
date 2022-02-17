package com.purerangers;

public class DisplayManager {
    // Method for outputting a bespoke message to the user.
    public static void displayMessage(String message){
        System.out.println(message);
    }
    // Method for outputting a welcome message to the user.
    public static void displayWelcome(){
        displayMessage(
                "\n======================================================================================\n"+
                        "\n **********************|| Welcome to Sparta Trainee Simulator ||********************** \n" +
                        "\n======================================================================================\n");
    }

    public static void displaySeparator(){
        displayMessage("\n======================================================================================\n");
    }

    // Method for outputting the available program options.
    public static void outputOptions(){
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
                                   int businessWaiting){
        displayMessage(
                "\nSIMULATION STATISTICS\n" +
                        "• Number of open centres: " + openCentres + "\n" +
                        "   • Training Hubs: " + tHubOpen + "\n" +
                        "   • Boot Camps: " + bCampOpen + "\n" +
                        "   • Tech Centres: " + tCentresOpen + "\n" +
                        "• Number of closed centres: " + closedCentres + "\n" +
                        "   • Training Hubs: " + tHubClosed + "\n" +
                        "   • Boot Camps: " + bCampClosed + "\n" +
                        "   • Tech Centres: " + tCentresClosed + "\n" +
                        "• Number of full centres: " + fullCentres + "\n" +
                        "   • Training Hubs: " + tHubFull + "\n" +
                        "   • Boot Camps: " + bCampFull + "\n" +
                        "   • Tech Centres: " + tCentresFull + "\n" +
                        "• Number of trainees in training: " + traineesTraining + "\n" +
                        "   • Java: " + javaTraining + "\n" +
                        "   • C#: " + cSharpTraining + "\n" +
                        "   • Data: " + dataTraining + "\n" +
                        "   • DevOps: " + devOpsTraining + "\n" +
                        "   • Business: " + businessTraining + "\n" +
                        "• Number of trainees on the waiting list: " + traineesWaiting + "\n" +
                        "   • Java: " + javaWaiting + "\n" +
                        "   • C#: " + cSharpWaiting + "\n" +
                        "   • Data: " + dataWaiting + "\n" +
                        "   • DevOps: " + devOpsWaiting + "\n" +
                        "   • Business: " + businessWaiting + "\n");
    }
}
