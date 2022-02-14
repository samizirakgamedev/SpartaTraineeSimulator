package com.purerangers;

public enum CourseType
{
    JAVA_SDET("Java SDET");

    public String getCourseName()
    {
        return courseName;
    }

    private String courseName;

    CourseType(String courseName)
    {
        this.courseName = courseName;
    }
}
