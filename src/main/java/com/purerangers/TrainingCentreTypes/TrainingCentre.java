package com.purerangers.TrainingCentreTypes;

import com.purerangers.*;

import java.sql.Date;
import java.util.*;

public abstract class TrainingCentre
{
    protected static final int MAX_TRAINEES = 100;

    int strikes = 0;
    int maxStrikes = 1;

    private static ArrayList<TrainingCentre> openCentreList;
    private static ArrayList<TrainingCentre> closedCentreList;

    public static ArrayList<TrainingCentre> getOpenCentreList()
    {
        if (openCentreList == null)
        {
            openCentreList = new ArrayList<>();
        }

        return openCentreList;
    }

    public static ArrayList<TrainingCentre> getClosedCentreList()
    {
        if (closedCentreList == null)
        {
            closedCentreList = new ArrayList<>();
        }

        return closedCentreList;
    }

    public static void clearCentreList()
    {
        openCentreList = new ArrayList<>();
    }

    protected int maxTrainees;
    protected Date openDate;
    protected ArrayList<Person> trainees;
    protected boolean closed;

    public TrainingCentre()
    {
        this.maxTrainees = MAX_TRAINEES;
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        trainees = new ArrayList<>();
        getOpenCentreList().add(this);
        TimeManager.getInstance().trainingCentres.add(this);
        closed = false;
    }

    public TrainingCentre(int maxTrainees)
    {
        this.maxTrainees = maxTrainees;
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        trainees = new ArrayList<>();
        getOpenCentreList().add(this);
        TimeManager.getInstance().trainingCentres.add(this);
        closed = false;
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
        if (closed)
        {
            return;
        }

        if (newDate == null)
        {
            throw new NullPointerException();
        }

        ArrayList<Person> traineeListWithoutGraduates = new ArrayList<>();
        LinkedList<Person> graduateList = new LinkedList<>();

        for (Person trainee : trainees)
        {
            if (!((Graduation) trainee).checkGraduation(newDate))
            {
                traineeListWithoutGraduates.add(trainee);
            }
            else
            {
                graduateList.add(trainee);
            }
        }

        GraduateBenchHandler gbh = GraduateBenchHandler.getInstance();
        gbh.addPeople(graduateList);

        trainees = traineeListWithoutGraduates;

        // auto recruit

        if (getAmountOfTrainees() < maxTrainees)
        {

            //System.out.println("Size before: " + WaitingListHandler.getInstance().getWaitingList().size());
            attemptToRecruitTrainees(WaitingListHandler.getInstance().getWaitingList());
            //System.out.println("Size after: " + WaitingListHandler.getInstance().getWaitingList().size());
        }

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
        getClosedCentreList().add(this);
        closed = true;
    }

    public boolean isFull()
    {
        if (getAmountOfTrainees() >= maxTrainees)
        {
            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName());
        sb.append(" ");
        sb.append(getID());
        sb.append("\nPopulation: ");
        sb.append(getAmountOfTrainees());
        sb.append("\n");

        for (int i = 0; i < CourseType.values().length; i++)
        {
            ArrayList<Person> gottenTrainees = getTrainees();

            CourseType currentType = CourseType.values()[i];
            int numberOnCourse = 0;

            for (int j = 0; j < gottenTrainees.size(); j++)
            {
                if (gottenTrainees.get(j).getCourseType() == currentType)
                {
                    numberOnCourse++;
                }
            }

            if (numberOnCourse > 0)
            {
                sb.append(currentType.getCourseName());
                sb.append(": ");
                sb.append(numberOnCourse);
                sb.append("\n");
            }
        }

        sb.append("");

        return sb.toString();
    }

    public int getID()
    {
        return getOpenCentreList().indexOf(this);
    }
}
