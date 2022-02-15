package com.purerangers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class DisplayMain {
    public static void main(String[] args) {
        DisplayManager.displayWelcome();
        DisplayManager.displayMessage("Please enter how many months you wish the simulation to run for: ");

        InputManager.getDouble();

        DisplayManager.outputSimStats(0,0,0,0);
    }
}
