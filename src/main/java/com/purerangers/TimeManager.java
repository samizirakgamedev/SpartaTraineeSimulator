package com.purerangers;
import com.purerangers.model.TrainingCentre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


public class TimeManager {
    private static TimeManager instance;
    public ArrayList<TrainingCentre> trainingCentres;
    public ArrayList<Client> clients;
    private Date simulationStartDate;
    private Date currentDate;

    private static final Logger logger = LogManager.getLogger(TimeManager.class.getName());

    private TimeManager()
    {
        simulationStartDate = new Date(System.currentTimeMillis());
        ;
        currentDate = new Date(simulationStartDate.getTime());
        trainingCentres = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public static TimeManager getInstance()
    {
        if (instance == null) {
            instance = new TimeManager();
        }

        return instance;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void addMonth()
    {
        long hours = 24L * 7 * 4;
        currentDate = new Date(currentDate.getTime() + hours * 60 * 60 * 1000);
        logger.info("Simulation advanced by 1 month");

        for (TrainingCentre trainingCentre : trainingCentres)
        {
            trainingCentre.updateDate(currentDate);
        }

        for (Client c : clients)
        {
            c.updateDate(currentDate);
        }
    }
}
