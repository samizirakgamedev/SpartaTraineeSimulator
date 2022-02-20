package com.purerangers.TrainingCentreTypes;

public class BootCamp extends TrainingCentre {
    protected static final int MAX_TRAINEES = 500;

    protected static final int MAX_STRIKES = 3;

    public BootCamp() {
        super(MAX_TRAINEES);
        maxStrikes = MAX_STRIKES;
    }
}
