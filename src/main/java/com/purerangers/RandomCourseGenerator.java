package com.purerangers;

import java.util.Random;

public class RandomCourseGenerator {

    public static CourseType RandomCourse() {
        Random random = new Random();
        return CourseType.values()[random.nextInt(CourseType.values().length)];
    }

    //
    public static void main(String[] args) {

        System.out.println(RandomCourse());
    }
}
