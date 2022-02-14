package purerangers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.purerangers.DateFormat.formatDate;
import static com.purerangers.RandomNumberGenerator.getRandomNumbersUsingNextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomNumberGeneratorTest {
    @Test
    @DisplayName("RandomNumber: Check distribution probability above 40")
    public void checkRandomHigherNumDistribution() throws ParseException
    {
        /*for mean*/
        int total = 0;
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=0; i<50;i++){
            int x=getRandomNumbersUsingNextInt();
            total= total+x;
            a.add(x);
        }        // initialization of variables
        double Z, X, s, u;
        u = total/50; //mean
        X=40; // the value to compare distribution
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
    @DisplayName("RandomNumber: Check distribution probability above 40")
    public void checkRandomLowerNumDistribution() throws ParseException
    {
        //NORMAL DISTRIBUTION
        /*for mean*/
        int total = 0;
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=0; i<50;i++){
            int x=getRandomNumbersUsingNextInt();
            total= total+x;
            a.add(x);
        }        
        double Z, X, s, u;
        u = total/50; //mean
        X=10; // the value
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
