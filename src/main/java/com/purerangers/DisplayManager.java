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
    // Method for outputting the available program options.
    public static void outputOptions(){
        // Will be implemented when options within the program are more clear.

    }
    // Method for outputting the stats from the simulation to the user into the console.
    public static void outputSimStats(int openCentres, int fullCentres, int traineesTraining, int traineesWaiting){
        displayMessage(
                "\nSimulation Complete:\n" +
                        "\n• Number of open centres: " + openCentres + "\n" +
                        "\n• Number of full centres: " + fullCentres + "\n" +
                        "\n• Number of trainees in training: " + traineesTraining + "\n" +
                        "\n• Number of trainees on the waiting list: " + traineesWaiting + "\n");
    }
}
