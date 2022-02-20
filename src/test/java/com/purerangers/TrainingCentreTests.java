package com.purerangers;

import com.purerangers.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.*;

public class TrainingCentreTests
{
    @Test
    @DisplayName("When trying to add trainees past the centre's capacity it should not allow it.")
    public void willNotOverflow()
    {
        TrainingCentre trainingCentre = new BootCamp();

        for (int i = 0; i < 500; i++)
        {
            trainingCentre.addTrainee(new Person());
        }

        Assertions.assertFalse(trainingCentre.addTrainee(new Person()));
    }

    @Test
    @DisplayName("When given a trainee the training centre should add it.")
    public void canBeAddedTo()
    {
        TrainingCentre trainingCentre = new BootCamp();

        Person expected = new Person();
        trainingCentre.addTrainee(expected);
        Person actual = trainingCentre.getTrainee(0);

        Assertions.assertTrue (actual == expected);
    }

    @Test
    @DisplayName("When the academy is given a date it will output graduation results")
    public void willOutputGraduationResults()
    {
        TrainingCentre trainingCentre = new BootCamp();

        Person person = new Person();
        trainingCentre.addTrainee(person);

        Calendar c = Calendar.getInstance();
        Date today = new java.sql.Date(c.getTime().getTime());

        c.add(Calendar.DATE, (person.getWeeksAtCamp()*7) + 1);
        Date graduation = new java.sql.Date(c.getTime().getTime());

        trainingCentre.updateDate(graduation);

        int expected = 0;
        int actual = trainingCentre.getAmountOfTrainees();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When given a queue of people it will add what it can and then return the rest")
    public void doesntLoseDataWhenRecruiting()
    {
        TrainingCentre trainingCentre = new BootCamp();

        Queue<Person> trainingQueue = new LinkedList<>();

        for (int i = 0; i < 510; i++)
        {
            trainingQueue.add(new Person());
        }

        trainingQueue = trainingCentre.attemptToRecruitTrainees(trainingQueue);

        int expected = 10;
        int actual = trainingQueue.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When given a null value the get trainee method will throw an exception")
    public void getTraineeThrowsExceptionWhenGivenNull()
    {
        TrainingCentre trainingCentre = new BootCamp();

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            trainingCentre.getTrainee(null);
        });
    }

    @Test
    @DisplayName("When given a null value the add trainee method will throw an exception")
    public void addTraineeThrowsExceptionWhenGivenNull()
    {
        TrainingCentre trainingCentre = new BootCamp();

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            trainingCentre.addTrainee(null);
        });
    }

    @Test
    @DisplayName("When given a null value the add trainee method will throw an exception")
    public void updateDateThrowsExceptionWhenGivenNull()
    {
        TrainingCentre trainingCentre = new BootCamp();

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            trainingCentre.updateDate(null);
        });
    }

    @Test
    @DisplayName("When constructed instances add themselves to the static list of centres")
    public void addItselfToStaticList()
    {
        TrainingCentre expected = new BootCamp();

        boolean actual = TrainingCentre.getOpenCentreList().contains(expected);

        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("When closed disperse recruits")
    public void disperseRecruits()
    {
        WaitingListHandler wlh = WaitingListHandler.getInstance();
        wlh.clearWaitingList();

        int recruitsToGenerate = 100;
        LinkedList<Person> spartans = new LinkedList<>();
        for (int i = 0; i < recruitsToGenerate; i++)
        {
            spartans.add(new Person());
        }

        wlh.addPeople(spartans);

        TrainingCentre.clearCentreList();
        TrainingCentre centreOne = new BootCamp();
        TrainingCentre centreTwo = new BootCamp();


        centreOne.attemptToRecruitTrainees(wlh.getWaitingList());
        centreOne.closeAndReassign();
        centreOne.closeAndReassign();
        centreOne.closeAndReassign();

        int expected = 100;
        int actual = centreTwo.getAmountOfTrainees();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("TechCentre can only take the specific students")
    public void techCentre()
    {
        TrainingCentre techCentre = new TechCentre();

        Queue<Person> trainingQueue = new LinkedList<>();

        for (int i = 0; i < 510; i++)
        {
            trainingQueue.add(new Person());
        }

        techCentre.attemptToRecruitTrainees(trainingQueue);

        ArrayList<Person> techCentreTrainees = techCentre.getTrainees();

        boolean hadIncorrectCourseType = false;

        for (Person person : techCentreTrainees) {
            if (person.getCourseType() != CourseType.JAVA) {
                hadIncorrectCourseType = true;
            }


            Assertions.assertFalse(hadIncorrectCourseType);
        }
    }
}
