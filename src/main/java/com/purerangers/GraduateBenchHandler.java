package com.purerangers;

import com.purerangers.model.Person;

import java.util.ArrayList;
import java.util.Queue;

public class GraduateBenchHandler
{
    public ArrayList<Person> graduateBench;
    private static GraduateBenchHandler instance;

    public static GraduateBenchHandler getInstance()
    {
        if (instance == null)
        {
            instance = new GraduateBenchHandler();
        }

        return instance;
    }

    public ArrayList<Person> getGraduateBench()
    {
        if (graduateBench == null)
        {
            graduateBench = new ArrayList<>();
        }

        return graduateBench;
    }

    public void addToBench(Person personToBeAdded)
    {
        getGraduateBench().add(personToBeAdded);
    }

    public void addPeople(Queue<Person> peopleToAdd)
    {
        while (0 < peopleToAdd.size())
        {
            Person person = peopleToAdd.remove();
            if (!getGraduateBench().contains(person))
            {
                getGraduateBench().add(person);

            }
        }
    }
}
