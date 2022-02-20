package com.purerangers;

import com.purerangers.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTests {
    @Test
    @DisplayName("Given no spartans available for recruit, empty arraylist returned")
    public void noSpartansReturnsEmptyArrayList()
    {
        ArrayList a= new ArrayList<>();
        Client b=new Client();
        b.attemptToRecruitSpartans(a);
        ArrayList<Person> actual = b.spartansAtClient;
        ArrayList<Person> expected=new ArrayList<>();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Given null spartans available for recruit, empty arraylist returned")
    public void nullSpartansReturnsEmptyArrayList()
    {
        ArrayList a= null;
        Client b=new Client();
        b.attemptToRecruitSpartans(a);
        ArrayList<Person> actual = b.spartansAtClient;
        ArrayList<Person> expected=new ArrayList<>();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Spartan check for add, null spartan should throw exception")
    public void nullSpartansReturnExceptionForAddCheck() {
        Client b = new Client();
        Person p = null;
       assertThrows(NullPointerException.class,
                ()->{b.addSpartan(p);
                      });
        }
    @Test
    @DisplayName("Spartan Check for add, spartans should return true")
    public void SpartanReturnsEmptyArrayList()
    {
        Client b = new Client();
        Person p= new Person();
        Boolean actual = b.addSpartan(p);;
        boolean expected=Boolean.TRUE;
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Check a month after opening, one less month to review")
    public void monthsTillReviewReturnsInt()
    {
        Client b = new Client();
        b.updateDate(b.openDate);
        //given a month has passed since opening
        int expected=11;
        int actual= b.monthsTillReview;
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Given empty list to filter returns empty array")
    public void emptyListToFilterReturnsEmptyArrayList()
    {
        ArrayList a= new ArrayList<>();
        ArrayList<Person> actual=Client.filter(a);
        ArrayList<Person> expected=new ArrayList<>();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Given null list to filter returns empty array")
    public void nullListToFilterReturnsEmptyArrayList()
    {
        ArrayList a= null;
        ArrayList<Person> actual=Client.filter(a);
        ArrayList<Person> expected=new ArrayList<>();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Client Happy if no more space for spartans")
    public void spartansNeedMetReturnsTrue()
    {
        Client b = new Client();
        b.spartanNeeded=3; //as no months have passed,shouldnt have spartans working there
        Boolean actual = b.isHappy();
        boolean expected=Boolean.FALSE;
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Close and resign, Client status=false")
    public void clientClosed()
    {
        Client b = new Client();
        b.closeAndReassign();
        Boolean actual = b.isClient;
        boolean expected=Boolean.FALSE;
        assertEquals(expected,actual);
    }
}
