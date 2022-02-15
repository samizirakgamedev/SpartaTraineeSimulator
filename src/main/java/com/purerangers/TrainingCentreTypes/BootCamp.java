package com.purerangers.TrainingCentreTypes;

import com.purerangers.Person;
import com.purerangers.TimeManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;

public class BootCamp extends TrainingCentre
{
    protected static final int MAX_TRAINEES = 500;

    protected static final int MAX_STRIKES = 3;

    public BootCamp()
    {
        super(MAX_TRAINEES);
        maxStrikes = MAX_STRIKES;
    }
}
