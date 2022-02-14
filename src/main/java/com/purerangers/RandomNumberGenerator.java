package com.purerangers;

public class RandomNumberGenerator {

        public static void main(String[] args) {
            System.out.println("Random Number"+getRandomNumbersUsingNextInt());
        }

        public static int getRandomNumbersUsingNextInt() {
            int min = 0;
            int max = 50;
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            return random_int;
        }


    }

