package com.purerangers;

import com.purerangers.model.CourseType;
import com.purerangers.model.TrainingCentre;
import com.purerangers.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

public class Simulation
{
    private static final Logger logger = LogManager.getLogger(Simulation.class.getName());

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

    public void display()
    {
        ArrayList<TrainingCentre> openCentres = TrainingCentre.getOpenCentreList();
        logger.info("Number of open centres: " + openCentres.size());
        processCentreList(openCentres);

        ArrayList<TrainingCentre> closedCentres = TrainingCentre.getClosedCentreList();
        logger.info("Number of closed centres: " + closedCentres.size());
        processCentreList(closedCentres);

        ArrayList<TrainingCentre> fullCentres = new ArrayList<>();
        for (TrainingCentre centre : openCentres)
        {
            if (centre.isFull()) fullCentres.add(centre);
        }
        logger.info("Number of full centres: " + fullCentres.size());
        processCentreList(fullCentres);

        var ts = getTrainees();
        logger.info("Number of trainees in training: " + ts.size());
        processPersonList(ts);

        var wl = wlh.getWaitingList();
        ArrayList<Person> persons = new ArrayList<>();

        while (wl.size() > 0)
        {
            persons.add(wl.remove());
        }

        logger.info("Number of trainees on the waiting list: " + persons.size());
        processPersonList(persons);
    }

    public void processCentreList(ArrayList<TrainingCentre> centres)
    {
        int bootCamps = 0;
        int trainingHubs = 0;
        int techCentres = 0;

        for (TrainingCentre e : centres)
        {
            String cName = e.getClass().getSimpleName();

            switch (cName)
            {
                case "TrainingHub" -> trainingHubs++;
                case "BootCamp" -> bootCamps++;
                case "TechCentre" -> techCentres++;
            }
        }

        if (bootCamps > 0)
        {
            logger.info("  • Boot Camps: " + bootCamps);
        }

        if (trainingHubs > 0)
        {
            logger.info("  • Training Hubs: " + trainingHubs);
        }

        if (techCentres > 0)
        {
            logger.info("  • Tech Centres: " + techCentres);
        }
    }

    public void processPersonList(ArrayList<Person> person)
    {
        for (int i = 0; i < CourseType.values().length; i++)
        {
            ArrayList<Person> traineeList = person;

            CourseType currentType = CourseType.values()[i];
            int numberOnCourse = 0;

            for (int j = 0; j < traineeList.size(); j++)
            {
                if (traineeList.get(j).getCourseType() == currentType)
                {
                    numberOnCourse++;
                }
            }

            if (numberOnCourse > 0)
            {
                StringBuilder sb = new StringBuilder();
                sb.append("  • ");
                sb.append(currentType.getCourseName());
                sb.append(": ");
                sb.append(numberOnCourse);
                logger.info(sb.toString());
            }
        }
    }

    public ArrayList<Person> getTrainees()
    {
        var openCentres = TrainingCentre.getOpenCentreList();
        ArrayList<Person> allTrainees = new ArrayList<>();

        for (TrainingCentre openCentre : openCentres)
        {
            allTrainees.addAll(openCentre.getTrainees());
        }

        return allTrainees;
    }
}
