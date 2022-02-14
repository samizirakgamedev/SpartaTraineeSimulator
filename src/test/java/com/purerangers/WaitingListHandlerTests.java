package com.purerangers;

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
}
