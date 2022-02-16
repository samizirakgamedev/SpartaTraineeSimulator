package com.purerangers;

import java.util.ArrayList;
import java.util.Queue;

public class GraduateBenchHandler
{
    private static GraduateBenchHandler instance;

    public static GraduateBenchHandler getInstance()
    {
        if (instance == null)
        {
            instance = new GraduateBenchHandler();
        }

        return instance;
    }

    public static ArrayList<Person> graduateBench;
    public static ArrayList<Person> getGraduateBench()
    {
        if (graduateBench == null)
        {
            graduateBench = new ArrayList<>();
        }

        return graduateBench;
    }

    public static void addToBench(Person personToBeAdded)
    {
        getGraduateBench().add(personToBeAdded);
    }

    public static void addPeople(Queue<Person> peopleToAdd)
    {
        while (0 < peopleToAdd.size())
        {
            getGraduateBench().add(peopleToAdd.remove());
        }
    }

    public static void main(String[] args) {
        GraduateBenchHandler a= new GraduateBenchHandler();

    }
}
