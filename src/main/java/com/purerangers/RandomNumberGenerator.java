package com.purerangers;

public class RandomNumberGenerator {

    public static void main(String[] args) {
        System.out.println("Random Number" + getRandomNumbersUsingNextInt(50, 100));
    }

    public static int getRandomNumbersUsingNextInt(int min, int max) {
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }


}

