package com.purerangers.generators;
import com.purerangers.model.CourseType;

import java.util.Random;
public class RandomCourseGenerator {
    public static CourseType RandomCourse() {
        Random random = new Random();
        return CourseType.values()[random.nextInt(CourseType.values().length)];
    }
}
