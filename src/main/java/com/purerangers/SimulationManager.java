package com.purerangers;

import com.purerangers.TrainingCentreTypes.BootCamp;
import com.purerangers.TrainingCentreTypes.TechCentre;
import com.purerangers.TrainingCentreTypes.TrainingCentre;
import com.purerangers.TrainingCentreTypes.TrainingHub;

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
                    ///System.out.println("Spawned a boot camp");
                    numberOfBootCamps++;
                    return new BootCamp();
                }
            case 1:
                //System.out.println("Spawned a training hub");
                return new TrainingHub();
            case 2:
                //System.out.println("Spawned a tech centre");
                return new TechCentre();
        }

        return null;
    }
}
