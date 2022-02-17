package com.purerangers;

import com.purerangers.TrainingCentreTypes.TrainingCentre;

import java.util.ArrayList;
import java.util.Random;

public class Simulation
{
    SimulationManager sm;
    GraduateBenchHandler gbh;
    TimeManager tm;
    WaitingListHandler wlh;
    boolean monthlyProgressReporting;
    int monthsToSimulate;
    int totalPeopleAdded;
    ArrayList<TrainingCentre> trainingCentres;
    Random r;

    public Simulation(boolean monthlyProgressReporting, int monthsToSimulate)
    {
        this.sm = new SimulationManager();
        this.gbh = GraduateBenchHandler.getInstance();
        this.tm = TimeManager.getInstance();
        this.wlh = WaitingListHandler.getInstance();

        this.monthlyProgressReporting = monthlyProgressReporting;

        this.monthsToSimulate = monthsToSimulate;
        totalPeopleAdded = 0;
        trainingCentres = new ArrayList<>();
        TrainingCentre.clearCentreList();
        wlh.clearWaitingList();
        r = new Random();
    }

    public void runSimulation()
    {


        for (int i = 0; i < monthsToSimulate; i++)
        {


            if (i % 2 != 0) // if the month is even
            {
                int numberOfHubsToSpawn = r.nextInt(3);

                for (int j = 0; j < numberOfHubsToSpawn; j++)
                {
                    trainingCentres.add(sm.getNewCentre());
                }
            }

            //addPeopleToTrainingList(50, 100);

            for (TrainingCentre trainingCentre : TrainingCentre.getOpenCentreList())
            {
                addPeopleToTrainingList(50, 100);
                //System.out.println(trainingCentre.toString());
            }


            tm.addMonth();
            Client c= new Client();

            if (monthlyProgressReporting)
            {
                // output a monthly report here
                //System.out.println(new StringBuilder().append("Graduates on the bench: ").append(gbh.getGraduateBench().size()).toString());
                //System.out.println(new StringBuilder().append("Total people added: ").append(totalPeopleAdded).toString());
            }

        }

        // output a final report here
        //System.out.println(new StringBuilder().append("Graduates on the bench: ").append(gbh.getGraduateBench().size()).toString());
        //System.out.println(new StringBuilder().append("Total people added: ").append(totalPeopleAdded).toString());
    }

    public void addPeopleToTrainingList(int origin, int bound)
    {
        /// here is the bit that adds recruits each month
        int numberOfNewRecruits = r.nextInt(origin, bound);
        wlh.addRandomPeopleToList(numberOfNewRecruits);
        totalPeopleAdded += numberOfNewRecruits;
        /// if neil says that we need each centre to have it's own trainees generated move this into the enhanced for loop below
    }
}
