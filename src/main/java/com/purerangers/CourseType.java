package com.purerangers;

public enum CourseType
{
    JAVA("Java"),
    CSharp("C#"),
    DATA("Data"),
    DEV_OPS("DevOps"),
    BUSINESS("Business");

    private String courseName;

    CourseType(String courseName)
    {
        this.courseName = courseName;
    }
}
