package com.purerangers.TrainingCentreTypes;

import com.purerangers.TimeManager;

import java.sql.Date;
import java.util.ArrayList;

public class BootCamp extends TrainingCentre
{
    protected static final int MAX_TRAINEES = 500;

    public BootCamp()
    {
        super(MAX_TRAINEES);
    }
}
