package com.purerangers;


import com.purerangers.TrainingCentreTypes.BootCamp;
import com.purerangers.TrainingCentreTypes.TrainingCentre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class RecruiterManager extends TrainingCentre {

    //list of centres, for loop on size of centres
    //que of person class (LinkedList)
    ArrayList<TrainingCentre> centres = new ArrayList<>();
    Queue<Person> trainingQueue = new LinkedList<>();

    public void createCentres() {
        TrainingCentre trainingCentre = new BootCamp();

    }

    public void addTrainees() {
        for (int i = 0; i < centres.size(); i++) {
            TrainingCentre trainingCentre = centres.get(i);
            if (!trainingCentre.isFull()) {
                trainingCentre.attemptToRecruitTrainees(trainingQueue);
            }
        }
    }

}
