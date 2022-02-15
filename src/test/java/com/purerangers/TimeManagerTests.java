package com.purerangers;

import com.purerangers.TrainingCentreTypes.BootCamp;
import com.purerangers.TrainingCentreTypes.TrainingCentre;
import com.purerangers.TrainingCentreTypes.TrainingHub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TimeManagerTests
{
    @Test
    @DisplayName("Can graduate trainees.")
    public void canBeAddedTo()
    {
        TimeManager tm = TimeManager.getInstance();
        WaitingListHandler wlh = WaitingListHandler.getInstance();

        int numberOfCentres = 10;
        ArrayList<TrainingCentre> trainingCentres = new ArrayList<>();

        for (int i = 0; i < numberOfCentres; i++)
        {
            Queue<Person> trainingQueue = new LinkedList<>();

            for (int j = 0; j < 510; j++)
            {
                trainingQueue.add(new Person());
            }

            wlh.addPeople(trainingQueue);

            TrainingHub hub = new TrainingHub();
            hub.attemptToRecruitTrainees(wlh.getWaitingList());

            trainingCentres.add(hub);
        }

        tm.addMonth();
        tm.addMonth();
    }

    @Test
    @DisplayName("Closes centres.")
    public void centresClose()
    {
        TimeManager tm = TimeManager.getInstance();
        WaitingListHandler wlh = WaitingListHandler.getInstance();

        int numberOfCentres = 10;
        ArrayList<TrainingCentre> trainingCentres = new ArrayList<>();
        TrainingCentre.clearCentreList();

        for (int i = 0; i < numberOfCentres; i++)
        {
            if (i == 0)
            {
                BootCamp bootCamp = new BootCamp();
                trainingCentres.add(bootCamp);
            }
            else
            {
                TrainingHub hub = new TrainingHub();
                trainingCentres.add(hub);
            }
        }

        int monthsToSimulate = 12;

        for (int i = 0; i < monthsToSimulate; i++)
        {
            System.out.println(TrainingCentre.getOpenCentreList().size());
            tm.addMonth();
        }

        int expected = 0;
        int actual = TrainingCentre.getOpenCentreList().size();

        Assertions.assertEquals(expected, actual);
    }
}
