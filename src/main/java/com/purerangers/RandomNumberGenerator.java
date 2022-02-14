package com.purerangers;

public class RandomNumberGenerator {

        public static void main(String[] args) {
            getRandomNumbersUsingNextInt();
        }

        private static int getRandomNumbersUsingNextInt() {
            int min = 0;
            int max = 50;
            //Generate random int value from 50 to 100
            System.out.println("Random value in int from "+min+" to "+max+ ":");
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            //System.out.println(random_int);
            return random_int;
        }


    }

