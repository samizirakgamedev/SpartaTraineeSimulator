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
        SimulationManager sm = new SimulationManager();
        GraduateBenchHandler gbh = GraduateBenchHandler.getInstance();
        TimeManager tm = TimeManager.getInstance();
        WaitingListHandler wlh = WaitingListHandler.getInstance();

        Random r = new Random();
        int monthsToSimulate = 24;
        int totalPeopleAdded = 0;
        ArrayList<TrainingCentre> trainingCentres = new ArrayList<>();
        TrainingCentre.clearCentreList();
        wlh.clearWaitingList();

        for (int i = 0; i < monthsToSimulate; i++)
        {
            if (i % 2 != 0) // if even month
            {
                int numberOfHubsToSpawn = r.nextInt(3);

                for (int j = 0; j < numberOfHubsToSpawn; j++)
                {
                    trainingCentres.add(sm.getNewCentre());
                }
            }

            System.out.println(new StringBuilder().append("Month: ").append(i).toString());

            int numberOfNewRecruits = r.nextInt(50, 100);
            wlh.addRandomPeopleToList(numberOfNewRecruits);
            totalPeopleAdded += numberOfNewRecruits;

            for (TrainingCentre trainingCentre : TrainingCentre.getOpenCentreList())
            {
                System.out.println(trainingCentre.toString());
            }

            tm.addMonth();

            System.out.println(new StringBuilder().append("Graduates on the bench: ").append(gbh.getGraduateBench().size()).toString());
            System.out.println(new StringBuilder().append("Total people added: ").append(totalPeopleAdded).toString());

            // System.out.println("Graduates on the bench: " + gbh.getGraduateBench().size());
            // System.out.println(totalPeopleAdded);
        }

        System.out.println(new StringBuilder().append("Graduates on the bench: ").append(gbh.getGraduateBench().size()).toString());
        System.out.println(new StringBuilder().append("Total people added: ").append(totalPeopleAdded).toString());
    }
}
