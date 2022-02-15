package com.purerangers;

import java.sql.Date;
import java.util.Calendar;

public class Person implements Graduation
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
    private int weeksAtCamp;

    public Date getStartDate()
    {
        return startDate;
    }

    public int getWeeksAtCamp()
    {
        return weeksAtCamp;
    }

    public Person(Date startDate, int weeksAtCamp)
    {
        this.id = getID();
        this.startDate = startDate;
        this.weeksAtCamp = weeksAtCamp;
    }

    public Person()
    {
        this.startDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        weeksAtCamp = 8;
        this.id = getID();
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
            System.out.println(id + " graduated!");
            return true;
        }

        return false;
    }
}
