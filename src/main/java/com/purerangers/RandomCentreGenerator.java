package com.purerangers;

import com.purerangers.TrainingCentreTypes.BootCamp;
import com.purerangers.TrainingCentreTypes.TechCentre;
import com.purerangers.TrainingCentreTypes.TrainingCentre;
import com.purerangers.TrainingCentreTypes.TrainingHub;

import java.util.*;

public class RandomCentreGenerator {
    public static void main(String[] args) {
        RandomCentreGenerator r = new RandomCentreGenerator();
        System.out.println(r.generateCentre().size());
    }

    public ArrayList<TrainingCentre> generateCentre() {
        ArrayList<TrainingCentre> t = new ArrayList<>();
        TrainingCentreType centreType = TrainingCentreType.RandomCentre();
        switch (centreType) {
            case TRAININGHUB -> {
                Random r = new Random();
                for (int i = 0; i < r.nextInt(1, 4); i++) {
                    t.add(new TrainingHub());
                }
            }
            case BOOTCAMP -> t.add(new BootCamp());
            case TECHCENTRE -> t.add(new TechCentre());
        }
        return t;
    }

    public enum TrainingCentreType {
        TRAININGHUB,
        BOOTCAMP,
        TECHCENTRE;

        private static final List<RandomCentreGenerator.TrainingCentreType> CENTRE_TYPES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = CENTRE_TYPES.size();
        private static final Random RANDOM = new Random();

        public static RandomCentreGenerator.TrainingCentreType RandomCentre() {
            return CENTRE_TYPES.get(RANDOM.nextInt(SIZE));
        }
    }
}
