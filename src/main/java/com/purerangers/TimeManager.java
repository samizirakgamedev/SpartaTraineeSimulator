package com.purerangers;

import com.purerangers.TrainingCentreTypes.TrainingCentre;

import java.sql.Date;
import java.util.ArrayList;

import static com.purerangers.SimLogger.logInfo;

public class TimeManager {
    private static TimeManager instance;
    public ArrayList<TrainingCentre> trainingCentres;
    public ArrayList<Client> clients;
    private Date simulationStartDate;
    private Date currentDate;

    private TimeManager() {
        simulationStartDate = new Date(System.currentTimeMillis());
        ;
        currentDate = new Date(simulationStartDate.getTime());
        trainingCentres = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }

        return instance;
    }

    public Date getSimulationStartDate() {
        return simulationStartDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void addMonth() {
        long hours = 24L * 7 * 4;
        currentDate = new Date(currentDate.getTime() + hours * 60 * 60 * 1000);
        logInfo("Month being added to all Training centres and Clients");
        for (TrainingCentre trainingCentre : trainingCentres) {
            trainingCentre.updateDate(currentDate);
        }
        for (Client c : clients) {
            c.updateDate(currentDate);
        }
    }
}
