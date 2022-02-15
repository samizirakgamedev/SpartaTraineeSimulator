package com.purerangers;

import java.util.Random;

public class GenerateCentreType {
    public static String generateType(){
        String[] arr = {"Training Hub", "Bootcamp", "Tech Centre"};
        Random random = new Random();
        // randomly selects an index from the arr
        int chosen = random.nextInt(arr.length);
        return arr[chosen];
    }
}
