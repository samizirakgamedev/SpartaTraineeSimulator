package com.purerangers;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import com.purerangers.CheckCentresSpaces;
import static com.purerangers.RandomNumberGenerator.getRandomNumbersUsingNextInt;
import com.purerangers.GenerateCentreType;
import com.purerangers.CreateTables;

public class TimeTraveller {

    public static void main(String[] args) throws ParseException, SQLException {
        CreateTables.dropAllTables();
        CreateTables.createTables();
        int numberOfMonths = 12; //we'll get it from user input
        LocalDate today = LocalDate.now(); //start today because why not?
        LocalDate endDate = LocalDate.now().plusMonths(numberOfMonths); //calculate the end of all times
        int variable = 0;
        for (LocalDate date = today; date.isBefore(endDate); date = date.plusMonths(1)) { //loopy loop
            System.out.println(date + " - time to do some sketchy shit, doo daa, doo daa!"); //month by month
            System.out.println("Today we shall have " + getRandomNumbersUsingNextInt(50, 100) + " poor souls to enlist."); //get the random number of so called trainees
            if (variable % 2 == 0) {
                String newCentre = GenerateCentreType.generateType(date);
                System.out.println("It's time to generate a new centre. And the Gods have chosen: " + newCentre);
            }
            for (int i = 0; i <= 2; i++) {
                //the index for centres is 1-3
                System.out.println("Available spaces: " + CheckCentresSpaces.returnAvailable(i) + " for " + CheckCentresSpaces.returnCentreType(i+1));
            }
            GenerateCentreType.returnCentres();
            System.out.println("=======================");
            variable++;
        }
    }
}
