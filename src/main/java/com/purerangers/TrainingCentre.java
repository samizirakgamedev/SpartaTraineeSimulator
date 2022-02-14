package com.purerangers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Queue;

public class TrainingCentre
{
    int maxTrainees;
    Date openDate;
    ArrayList<Person> trainees;

    public TrainingCentre(int maxTrainees, Date openDate)
    {
        this.maxTrainees = maxTrainees;
        this.openDate = openDate;

        trainees = new ArrayList<>();
    }

    public int getAmountOfTrainees()
    {
        return trainees.size();
    }

    public Person getTrainee(int index)
    {
        return trainees.get(index);
    }

    public boolean addTrainee(Person trainee)
    {
        if (getAmountOfTrainees() < maxTrainees)
        {
            trainees.add(trainee);
            return true;
        }

        return false;
    }

    public void updateDate(Date newDate)
    {
        ArrayList<Person> traineeListWithoutGraduates = new ArrayList<>();

        for (int i = 0; i < trainees.size(); i++)
        {
            Person trainee = trainees.get(i);
            if (!trainee.checkGraduation(newDate))
            {
                traineeListWithoutGraduates.add(trainee);
            }
        }

        trainees = traineeListWithoutGraduates;
    }

    public Queue<Person> attemptToRecruitTrainees(Queue<Person> trainees)
    {
        while (getAmountOfTrainees() < maxTrainees)
        {
            addTrainee(trainees.remove());
        }

        return trainees;
    }
    public boolean isFull(){
        return (maxTrainees <=  getAmountOfTrainees());
    }
}
