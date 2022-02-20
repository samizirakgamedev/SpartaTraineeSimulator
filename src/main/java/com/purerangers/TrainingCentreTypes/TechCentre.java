package com.purerangers.TrainingCentreTypes;

import com.purerangers.CourseType;
import com.purerangers.Person;
import com.purerangers.RandomCourseGenerator;
import com.purerangers.TimeManager;

import java.util.LinkedList;
import java.util.Queue;

public class TechCentre extends TrainingCentre {
    protected static final int MAX_TRAINEES = 200;
    protected CourseType centreType;

    public TechCentre() {
        super(MAX_TRAINEES);
        this.centreType = getRandomCourseType();
    }

    private CourseType getRandomCourseType() {
        CourseType b = RandomCourseGenerator.RandomCourse();
        return b;
    }

    @Override
    public Queue<Person> attemptToRecruitTrainees(Queue<Person> trainees) {
        if (trainees == null) {
            throw new NullPointerException();
        }

        Queue<Person> skippedList = new LinkedList<>();
        TimeManager tm = TimeManager.getInstance();

        while (getAmountOfTrainees() < maxTrainees && trainees.size() > 0) {
            if (trainees.peek().getCourseType() == centreType) {
                Person trainee = trainees.remove();
                addTrainee(trainee);
            } else {
                skippedList.add(trainees.remove());
            }
        }

        skippedList.addAll(trainees);

        return skippedList;
    }
}
