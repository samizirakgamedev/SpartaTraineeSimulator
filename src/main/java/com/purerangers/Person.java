package com.purerangers;

import java.sql.Date;
import java.util.Calendar;

public class Person
{
    public Date getStartDate() {
        return startDate;
    }

    public int getWeeksAtCamp() {
        return weeksAtCamp;
    }

    private Date startDate;
    private int weeksAtCamp;

    public Person(Date startDate, int weeksAtCamp)
    {
        this.startDate = startDate;
        this.weeksAtCamp = weeksAtCamp;
    }

    public Person()
    {
        this.startDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        weeksAtCamp = 8;
    }
}
