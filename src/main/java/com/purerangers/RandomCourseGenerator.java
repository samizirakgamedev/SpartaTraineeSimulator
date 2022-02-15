package com.purerangers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomCourseGenerator {
    public enum CourseType{
        JAVA,
        CSHARP,
        DATA,
        DEVOPS,
        BUSINESS;

        private static final List<CourseType> COURSES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = COURSES.size();
        private static final Random RANDOM = new Random();

        public static CourseType RandomCourse(){
            return  COURSES.get(RANDOM.nextInt(SIZE));
        }
    }
    //
    public static void main(String[] args) {
        System.out.println(CourseType.RandomCourse());
    }
}
