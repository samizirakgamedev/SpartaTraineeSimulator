package com.purerangers;

import com.purerangers.TrainingCentreTypes.TrainingCentre;

import java.sql.Date;
import java.util.ArrayList;

public class TimeManager
{
    private static TimeManager instance;

    public Date getSimulationStartDate()
    {
        return simulationStartDate;
    }

    private Date simulationStartDate;

    public Date getCurrentDate()
    {
        return currentDate;
    }

    private Date currentDate;

    public static TimeManager getInstance()
    {
        if (instance == null)
        {
            instance = new TimeManager();
        }

        return instance;
    }

    public ArrayList<TrainingCentre> trainingCentres;
    public ArrayList<Client> clients ;

    private TimeManager()
    {
        simulationStartDate = new Date(System.currentTimeMillis());;
        currentDate = new Date(simulationStartDate.getTime());
        trainingCentres = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public void addMonth()
    {
        long hours = 24L * 7 * 4;
        currentDate = new Date(currentDate.getTime() + hours*60*60*1000);

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
