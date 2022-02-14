package com.purerangers;

public class DisplayManager {
    // Called when wanting to display a message to the user in the console.
    public static void displayMessage(String message){
        System.out.println(message);
    }
    // Called when wanting to print the available options in the program to the user.
    public static void displayWelcome(){
        displayMessage(
                "\n======================================================================================\n"+
                        "\n **********************|| Welcome to Sparta Trainee Simulator ||********************** \n" +
                        "\n======================================================================================\n");

    }
    public static void outputOptions(){

    }
    public static void outputStats(int openCentres, int fullCentres, int traineesTraining, int traineesWaiting){
        displayMessage(
                "\nSimulation Complete:\n" +
                        "\n• Number of open centres: " + openCentres + "\n" +
                        "\n• Number of full centres: " + fullCentres + "\n" +
                        "\n• Number of trainees in training: " + traineesTraining + "\n" +
                        "\n• Number of trainees on the waiting list: " + openCentres + "\n");
    }
}
