package com.purerangers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomTrainingCentreTypeGenerator {
    public enum TrainingCentreType{
        TRAININGHUB,
        BOOTCAMP,
        TECHCENTRE;

        private static final List<RandomTrainingCentreTypeGenerator.TrainingCentreType> CENTRE_TYPES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = CENTRE_TYPES.size();
        private static final Random RANDOM = new Random();

        public static RandomTrainingCentreTypeGenerator.TrainingCentreType RandomCentre(){
            return  CENTRE_TYPES.get(RANDOM.nextInt(SIZE));
        }
    }

    public static void main(String[] args) {
        System.out.println(TrainingCentreType.RandomCentre());
    }

}
