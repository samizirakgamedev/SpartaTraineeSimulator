package com.purerangers;

import com.purerangers.TrainingCentreTypes.BootCamp;
import com.purerangers.TrainingCentreTypes.TechCentre;
import com.purerangers.TrainingCentreTypes.TrainingCentre;
import com.purerangers.TrainingCentreTypes.TrainingHub;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomCentreGenerator {
    public enum TrainingCentreType{
        TRAININGHUB,
        BOOTCAMP,
        TECHCENTRE;

        private static final List<RandomCentreGenerator.TrainingCentreType> CENTRE_TYPES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = CENTRE_TYPES.size();
        private static final Random RANDOM = new Random();

        public static RandomCentreGenerator.TrainingCentreType RandomCentre(){
            return  CENTRE_TYPES.get(RANDOM.nextInt(SIZE));
        }
    }

    public TrainingCentre generateCentre(){
        TrainingCentre t = null;
        TrainingCentreType centreType = TrainingCentreType.RandomCentre();
        switch (centreType){
            case TRAININGHUB -> t = new TrainingHub();
            case BOOTCAMP -> t = new BootCamp();
            case TECHCENTRE -> new TechCentre();
        }
        return t;
    }
}
