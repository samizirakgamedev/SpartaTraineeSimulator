package com.purerangers;

import com.purerangers.model.Person;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingListHandler {
    private static WaitingListHandler instance;
    private Queue<Person> waitingList = new LinkedList<>();

    private WaitingListHandler() {

    }

    public static WaitingListHandler getInstance() {
        if (instance == null) {
            instance = new WaitingListHandler();
        }

        return instance;
    }

    public Person getNextPerson() {
        return waitingList.remove();
    }

    public void addPeople(Queue<Person> peopleToAdd) {
        while (0 < peopleToAdd.size()) {
            waitingList.add(peopleToAdd.remove());
        }
    }

    public int getNumberOfPeopleWaiting() {
        return waitingList.size();
    }

    public void clearWaitingList() {
        waitingList = new LinkedList<>();
    }

    public Queue<Person> getWaitingList() {
        return waitingList;
    }

    public void addRandomPeopleToList(int amountToAdd) {
        //System.out.println("Adding " + amountToAdd + " trainees");
        //System.out.println("Size before: " + getWaitingList().size());

        Queue<Person> trainingQueue = new LinkedList<>();

        for (int j = 0; j < amountToAdd; j++) {
            Person person = new Person();

            trainingQueue.add(person);
        }

        addPeople(trainingQueue);

        //System.out.println("Size after: " + getWaitingList().size());
    }
}
