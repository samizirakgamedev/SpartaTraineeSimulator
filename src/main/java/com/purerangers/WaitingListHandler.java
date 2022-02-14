package com.purerangers;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingListHandler
{
    private static WaitingListHandler instance;

    public static WaitingListHandler getInstance()
    {
        if (instance == null)
        {
            instance = new WaitingListHandler();
        }

        return instance;
    }

    private Queue<Person> waitingList = new LinkedList<>();

    public Person getNextPerson()
    {
        return waitingList.remove();
    }

    public void addPeople(Queue<Person> peopleToAdd)
    {
        while (0 < peopleToAdd.size())
        {
            waitingList.add(peopleToAdd.remove());
        }
    }

    public int getNumberOfPeopleWaiting()
    {
        return waitingList.size();
    }

    public void clearWaitingList()
    {
        waitingList = new LinkedList<>();
    }

    public Queue<Person> getWaitingList()
    {
        return waitingList;
    }

    private WaitingListHandler()
    {

    }
}
