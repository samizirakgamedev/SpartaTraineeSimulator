package com.purerangers.TrainingCentreTypes;

import com.purerangers.Graduation;
import com.purerangers.Person;
import com.purerangers.TimeManager;

import java.sql.Date;
import java.util.*;

public abstract class TrainingCentre
{
    protected static final int MAX_TRAINEES = 100;

    int strikes = 0;
    int maxStrikes = 1;

    private static ArrayList<TrainingCentre> openCentreList;

    public static ArrayList<TrainingCentre> getOpenCentreList()
    {
        if (openCentreList == null)
        {
            openCentreList = new ArrayList<TrainingCentre>();
        }

        return openCentreList;
    }

    public static void clearCentreList()
    {
        openCentreList = new ArrayList<>();
    }

    protected int maxTrainees;
    protected Date openDate;
    protected ArrayList<Person> trainees;

    public TrainingCentre()
    {
        this.maxTrainees = MAX_TRAINEES;
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        trainees = new ArrayList<>();
        getOpenCentreList().add(this);
        TimeManager.getInstance().trainingCentres.add(this);
    }

    public TrainingCentre(int maxTrainees)
    {
        this.maxTrainees = maxTrainees;
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        trainees = new ArrayList<>();
        getOpenCentreList().add(this);
        TimeManager.getInstance().trainingCentres.add(this);
    }

    public int getAmountOfTrainees()
    {
        return trainees.size();
    }

    public int getFreeSpace()
    {
        return maxTrainees - getAmountOfTrainees();
    }

    public Person getTrainee(Integer index)
    {
        return trainees.get(index);
    }

    public ArrayList<Person> getTrainees()
    {
        return trainees;
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

        /// close code

        if (getAmountOfTrainees() < 25)
        {
            closeAndReassign();
        }
        else
        {
            strikes = 0;
        }

        /// close code
    }

    public Queue<Person> attemptToRecruitTrainees(Queue<Person> trainees)
    {
        if (trainees == null)
        {
            throw new NullPointerException();
        }

        TimeManager tm = TimeManager.getInstance();

        while (getAmountOfTrainees() < maxTrainees && trainees.size() > 0)
        {
            Person trainee = trainees.remove();
            addTrainee(trainee);
        }

        return trainees;
    }

    public void closeAndReassign()
    {
        strikes++;

        if (strikes < maxStrikes)
        {
            return;
        }

        ArrayList<TrainingCentre> list = getOpenCentreList();

        LinkedList<Person> refuges = new LinkedList<>(trainees);

        for (int i = 0; i < list.size(); i++)
        {
            TrainingCentre tc = list.get(i);
            if (!tc.equals(this))
            {
                refuges = (LinkedList<Person>) tc.attemptToRecruitTrainees(refuges);
            }
        }

        getOpenCentreList().remove(this);
    }

    public boolean isFull()
    {
        if (getAmountOfTrainees() >= maxTrainees)
        {
            return true;
        }

        return false;
    }
}
