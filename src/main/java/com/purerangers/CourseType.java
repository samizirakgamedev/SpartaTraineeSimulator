package com.purerangers;

public enum CourseType
{
    JAVA("Java"),
    CSharp("C#"),
    DATA("Data"),
    DEV_OPS("DevOps"),
    BUSINESS("Business");

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
