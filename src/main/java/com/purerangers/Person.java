package com.purerangers;

import java.sql.Date;
import java.util.Calendar;

public class Person implements Graduation, SQLReadable
{
    public static int idCounter = 0;
    public static int getID()
    {
        int id = idCounter;
        idCounter++;
        return id;
    }

    private int id;
    private Date startDate;
    private CourseType courseType;
    private int weeksAtCamp;

    public Date getStartDate()
    {
        return startDate;
    }

    public int getWeeksAtCamp()
    {
        return weeksAtCamp;
    }

    public Person()
    {
        this.startDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        weeksAtCamp = 4 * 12;
        this.id = getID();
        this.courseType = CourseType.JAVA;
    }

    @Override
    public boolean checkGraduation(Date date)
    {
        if (date == null)
        {
            throw new NullPointerException();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, weeksAtCamp*7);
        Date graduation = new java.sql.Date(calendar.getTime().getTime());

        if (graduation.before(date))
        {
            //System.out.println(id + " graduated!");
            return true;
        }

        return false;
    }

    @Override
    public Object[] readSQLObject()
    {
        Object[] objectArray = new Object[3];

        objectArray[0] = id;
        objectArray[1] = startDate;
        objectArray[2] = courseType;

        return objectArray;
    }

    public CourseType getCourseType()
    {
        return courseType;
    }
}
