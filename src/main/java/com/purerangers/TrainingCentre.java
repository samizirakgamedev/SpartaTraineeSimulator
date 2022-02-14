package com.purerangers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Queue;

public class TrainingCentre
{
    int maxTrainees;
    Date openDate;
    ArrayList<Person> trainees;
    ArrayList<Graduation> graduations;

    public TrainingCentre(int maxTrainees, Date openDate)
    {
        this.maxTrainees = maxTrainees;
        this.openDate = openDate;

        trainees = new ArrayList<>();
        graduations = new ArrayList<>();
    }

    public int getAmountOfTrainees()
    {
        return trainees.size();
    }

    public Person getTrainee(Integer index)
    {
        return trainees.get(index);
    }

    public boolean addTrainee(Person trainee)
    {
        if (trainee == null)
        {
            throw new NullPointerException();
        }

        if (getAmountOfTrainees() < maxTrainees)
        {
            trainees.add(trainee);
            graduations.add(trainee);
            return true;
        }

        return false;
    }

    public void updateDate(Date newDate)
    {
        if (newDate == null)
        {
            throw new NullPointerException();
        }

        ArrayList<Person> traineeListWithoutGraduates = new ArrayList<>();

        for (Person trainee : trainees)
        {

            if (!((Graduation) trainee).checkGraduation(newDate))
            {
                traineeListWithoutGraduates.add(trainee);
            }
        }

        trainees = traineeListWithoutGraduates;
    }

    public Queue<Person> attemptToRecruitTrainees(Queue<Person> trainees)
    {
        if (trainees == null)
        {
            throw new NullPointerException();
        }

        while (getAmountOfTrainees() < maxTrainees)
        {
            addTrainee(trainees.remove());
        }

        return trainees;
    }
}
