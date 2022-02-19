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
    ArrayList<Client> clientList;
    ArrayList<Client> debugList;
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
        clientList = new ArrayList<>();
        TrainingCentre.clearCentreList();
        wlh.clearWaitingList();
        r = new Random();
    }

    public void runSimulation()
    {
        for (int i = 0; i < monthsToSimulate; i++)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Month"+i);
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
//            debugList= Client.getClientList();
//            for (Client c:debugList){
//                System.out.println("C"+c.toString());
//            }
            if (i% 12==0&& i != 0){
                new Client();
            }
                if(i%3==0&& i%12!=0){
                    new Client();
            }




            tm.addMonth();

            if (monthlyProgressReporting)
            {
                // output a monthly report here

                //System.out.println(new StringBuilder().append("Graduates on the bench: ").append(gbh.getGraduateBench().size()).toString());
                //System.out.println(new StringBuilder().append("Total people added: ").append(totalPeopleAdded).toString());

               // display();

            }
        }
        System.out.println("----------------------------------\n");
        System.out.println("Client List:");
        for (int i=0;i<Client.getClientList().size();i++){
            System.out.println(Client.getClientList().get(i));
        }
        System.out.println("----------------------------------\n");
        // output a final report here

        //System.out.println(new StringBuilder().append("Graduates on the bench: ").append(gbh.getGraduateBench().size()).toString());
        //System.out.println(new StringBuilder().append("Total people added: ").append(totalPeopleAdded).toString());

        //display();

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
