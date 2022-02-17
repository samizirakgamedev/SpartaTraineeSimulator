package com.purerangers;

import com.purerangers.TrainingCentreTypes.BootCamp;
import com.purerangers.TrainingCentreTypes.TrainingCentre;
import com.purerangers.TrainingCentreTypes.TrainingHub;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Simulation simulation = new Simulation(true, 24);
        simulation.runSimulation();
    }
}
