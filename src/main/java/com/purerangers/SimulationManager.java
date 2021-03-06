package com.purerangers;

import com.purerangers.model.BootCamp;
import com.purerangers.model.TechCentre;
import com.purerangers.model.TrainingCentre;
import com.purerangers.model.TrainingHub;

import java.util.Random;

public class SimulationManager
{
    private Random r;
    private int numberOfBootCamps;

    public SimulationManager()
    {
        numberOfBootCamps = 0;
        r = new Random();
    }

    public TrainingCentre getNewCentre()
    {
        int choice = r.nextInt(0,3);

        switch(choice)
        {
            case 0:
                if (numberOfBootCamps < 2)
                {
                    numberOfBootCamps++;
                    return new BootCamp();
                }
            case 1:
                return new TrainingHub();
            case 2:
                return new TechCentre();
        }

        return null;
    }
}
