package com.purerangers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static com.purerangers.generators.RandomNumberGenerator.getRandomNumbersUsingNextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomNumberGeneratorTest {
    @Test
    @DisplayName("RandomNumber: Check distribution probability above 90")
    public void checkRandomHigherNumDistribution() throws ParseException
    {
        /*for mean*/
        int total = 0;
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=50; i<100;i++){
            int x=getRandomNumbersUsingNextInt(50,100);
            total= total+x;
            a.add(x);
        }        // initialization of variables
        double Z, X, s, u;
        u = total/50; //mean
        X=90; // the value to compare distribution
        double total2 = 0;
        /*for Standard deviation*/
        for (int i=0;i<a.size();i++){
            double b= a.get(i)-u;
            double v = Math.pow(b,2);
            total2=total2+v;
        }
        double top =total2/50;
        s=Math.sqrt(top);
        // master formula
        Z = (X - u) / s;

       //System.out.println("the Z-value obtained is: " + Z);
       assertEquals(true,Z<3&&Z>-3);
    }
    @Test
    @DisplayName("RandomNumber: Check distribution probability below 60")
    public void checkRandomLowerNumDistribution() throws ParseException
    {
        //NORMAL DISTRIBUTION
        /*for mean*/
        int total = 0;
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=0; i<50;i++){
            int x=getRandomNumbersUsingNextInt(50,100);
            total= total+x;
            a.add(x);
        }        
        double Z, X, s, u;
        u = total/50; //mean
        X=60; // the value
        double total2 = 0;
        /*for Standard deviation and calculating Z*/
        for (int i=0;i<a.size();i++){
            double b= a.get(i)-u;
            double v = Math.pow(b,2);
            total2=total2+v;
        }
        double top =total2/50;
        s=Math.sqrt(top);
        // master formula
        Z = (X - u) / s;

        //System.out.println("the Z-value obtained is: " + Z);
        assertEquals(true,Z<3&&Z>-3);
    }
}
