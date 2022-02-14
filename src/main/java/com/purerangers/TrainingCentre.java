package com.purerangers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
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

        trainees = new ArrayList<Person>();
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
            Date startDate = trainee.getStartDate();
            int weeksAtCamp = trainee.getWeeksAtCamp();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, weeksAtCamp*7);
            Date graduation = new java.sql.Date(c.getTime().getTime());

            if (graduation.before(newDate))
            {
                System.out.println("This one graduated!");
            }
            else
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
}
