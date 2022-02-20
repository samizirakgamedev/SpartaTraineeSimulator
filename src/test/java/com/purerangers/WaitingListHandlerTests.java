package com.purerangers;

import com.purerangers.model.BootCamp;
import com.purerangers.model.TrainingCentre;
import com.purerangers.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingListHandlerTests
{
    @Test
    @DisplayName("The waiting list will be populated correctly")
    public void willPopulate()
    {
        WaitingListHandler waitingList = WaitingListHandler.getInstance();
        waitingList.clearWaitingList();
        Queue<Person> people = new LinkedList<>();

        for (int i = 0; i < 100; i++)
        {
            people.add(new Person());
        }

        waitingList.addPeople(people);

        Assertions.assertEquals(100, waitingList.getNumberOfPeopleWaiting());
    }

    @Test
    @DisplayName("The waiting list will return the ordered values")
    public void willReturnInOrder()
    {
        WaitingListHandler waitingList = WaitingListHandler.getInstance();
        waitingList.clearWaitingList();

        Queue<Person> people = new LinkedList<>();
        Person expected = new Person();

        people.add(expected);
        for (int i = 0; i < 5; i++)
        {
            people.add(new Person());
        }

        waitingList.addPeople(people);

        Person actual = waitingList.getNextPerson();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("The waiting list can be used by TrainingCentres to populate")
    public void willPopulateTrainingCentres()
    {
        WaitingListHandler waitingList = WaitingListHandler.getInstance();
        waitingList.clearWaitingList();

        Queue<Person> people = new LinkedList<>();

        for (int i = 0; i < 500; i++)
        {
            people.add(new Person());
        }

        waitingList.addPeople(people);

        TrainingCentre trainingCentre = new BootCamp();

        trainingCentre.attemptToRecruitTrainees(waitingList.getWaitingList());

        int actual = trainingCentre.getAmountOfTrainees();
        int expected = 500;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("The waiting list is correctly drawn from")
    public void willEmptyList()
    {
        WaitingListHandler waitingList = WaitingListHandler.getInstance();
        waitingList.clearWaitingList();

        Queue<Person> people = new LinkedList<>();

        for (int i = 0; i < 600; i++)
        {
            people.add(new Person());
        }

        waitingList.addPeople(people);

        TrainingCentre trainingCentre = new BootCamp();

        trainingCentre.attemptToRecruitTrainees(waitingList.getWaitingList());

        int actual = waitingList.getNumberOfPeopleWaiting();
        int expected = 100;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When given a null value the waiting list add will throw an exception")
    public void throwsExceptionWhenGivenNull()
    {
        WaitingListHandler waitingList = WaitingListHandler.getInstance();
        waitingList.clearWaitingList();

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            waitingList.addPeople(null);
        });
    }
}
