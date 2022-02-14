package com.purerangers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class TrainingCentreTests
{
    @Test
    @DisplayName("When trying to add trainees past the centre's capacity it should not allow it.")
    public void willNotOverflow()
    {
        TrainingCentre trainingCentre = new TrainingCentre(1, new Date(System.currentTimeMillis()));
        trainingCentre.addTrainee(new Person());
        Assertions.assertFalse(trainingCentre.addTrainee(new Person()));
    }

    @Test
    @DisplayName("When given a trainee the training centre should add it.")
    public void canBeAddedTo()
    {
        TrainingCentre trainingCentre = new TrainingCentre(100, new Date(System.currentTimeMillis()));

        Person expected = new Person();
        trainingCentre.addTrainee(expected);
        Person actual = trainingCentre.getTrainee(0);

        Assertions.assertTrue (actual == expected);
    }

    @Test
    @DisplayName("When the academy is given a date it will output graduation results")
    public void willOutputGraduationResults()
    {
        TrainingCentre trainingCentre = new TrainingCentre(100, new Date(System.currentTimeMillis()));

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
        TrainingCentre trainingCentre = new TrainingCentre(1, new Date(System.currentTimeMillis()));

        Queue<Person> trainingQueue = new LinkedList<>();
        trainingQueue.add(new Person());
        trainingQueue.add(new Person());
        trainingQueue.add(new Person());
        trainingQueue.add(new Person());
        trainingQueue.add(new Person());

        trainingQueue = trainingCentre.attemptToRecruitTrainees(trainingQueue);

        int expected = 4;
        int actual = trainingQueue.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When given a null value the get trainee method will throw an exception")
    public void getTraineeThrowsExceptionWhenGivenNull()
    {
        TrainingCentre trainingCentre = new TrainingCentre(100, new Date(System.currentTimeMillis()));

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            trainingCentre.getTrainee(null);
        });
    }

    @Test
    @DisplayName("When given a null value the add trainee method will throw an exception")
    public void addTraineeThrowsExceptionWhenGivenNull()
    {
        TrainingCentre trainingCentre = new TrainingCentre(100, new Date(System.currentTimeMillis()));

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            trainingCentre.addTrainee(null);
        });
    }

    @Test
    @DisplayName("When given a null value the add trainee method will throw an exception")
    public void updateDateThrowsExceptionWhenGivenNull()
    {
        TrainingCentre trainingCentre = new TrainingCentre(100, new Date(System.currentTimeMillis()));

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            trainingCentre.updateDate(null);
        });
    }
}
