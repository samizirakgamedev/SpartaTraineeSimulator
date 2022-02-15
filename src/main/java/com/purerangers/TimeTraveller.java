package com.purerangers;

import java.text.ParseException;
import java.time.LocalDate;

public class TimeTraveller {

    public static void main(String[] args) throws ParseException {
        int numberOfMonths = 7; //we'll get it from user input
        LocalDate today = LocalDate.now(); //start today because why not?
        LocalDate endDate = LocalDate.now().plusMonths(numberOfMonths); //calculate the end of all times

        for (LocalDate date = today; date.isBefore(endDate); date = date.plusMonths(1)) { //loopy loop
            System.out.println(date + " time to do some sketchy shit!");
        }
    }
}
