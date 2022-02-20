package com.purerangers.model;

public enum CourseType {
    JAVA("Java"),
    CSharp("C#"),
    DATA("Data"),
    DEV_OPS("DevOps"),
    BUSINESS("Business");

    private String courseName;

    CourseType(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int courseTypeSize() {
        return CourseType.values().length;
    }
}
