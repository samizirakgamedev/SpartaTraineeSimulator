package com.purerangers;

import java.sql.Date;
import java.util.ArrayList;

/*
 * -CHECK ISHAPPY after 12 months, from Database, if happy, they go for another year
 * - From database, pass filtered graduateslist that have the same courseType
 * -NEED TO MAKE ATTEMPTTORECRUITRANDOM random number of spartans to add from min=1 max= remaining
 * */
public class Client {
    private static ArrayList<Client> clientList;
    protected int spartanNeeded;
    protected Date openDate;
    protected ArrayList<Person> spartans = GraduateBenchHandler.getGraduateBench(); //make filtered gradlist?
    protected boolean isClient;

    //Not sure if coursetype is needed as a parameter because of RandomCourseGenerator.RandomCourse();
    public Client(int spartansNeeded, CourseType courseType) {
        this.spartanNeeded = spartansNeeded;
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        //courseType= RandomCourseGenerator.RandomCourse();
        getClientList().add(this);
        TimeManager.getInstance().clients.add(this);
        isClient = true;
    }

    public static ArrayList<Client> getClientList() {
        if (clientList == null) {
            clientList = new ArrayList<>();
        }
        return clientList;
    }

    //Clear the clientlist
    public static void clearClientList() {
        clientList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Client a = new Client(30, CourseType.JAVA);

        Person ab = new Person();
        a.addSpartan(ab);
        System.out.println(a.getAmountOfSpartans());
        System.out.println(a);
    }

    public int getAmountOfSpartans() {
        return spartans.size();
    }

    //NEEDS TO BE CHECKED AFTER 12 MONTHS
    public Boolean isHappy() {
        return getFreeSpace() != 0;
    }

    public int getFreeSpace() {
        return spartanNeeded - getAmountOfSpartans();
    }

    public Person getSpartan(Integer index) {
        return spartans.get(index);
    }

    public ArrayList<Person> getSpartans() {
        return spartans;
    }

    public boolean addSpartan(Person spartan) {
        if (spartan == null) {
            throw new NullPointerException();
        }

        if (getAmountOfSpartans() < spartanNeeded) {
            spartans.add(spartan);
            return true;
        }
        return false;
    }

    public void updateDate(Date newDate) {
        if (isClient = false) {
            return;
        }

        if (newDate == null) {
            throw new NullPointerException();
        }

        if (getAmountOfSpartans() < spartanNeeded) {
            attemptToRecruitSpartans(GraduateBenchHandler.getGraduateBench());
        }

    }
///////////////////////////////////Easy fix but pulling anyway so people can see//// but needs to do random number
    public ArrayList<Person> attemptToRecruitSpartans(ArrayList<Person> spartans) {
        if (spartans == null) {
            throw new NullPointerException();
        }

        while (spartans.size() > 0) {
            for (int i = 0; getAmountOfSpartans() < spartanNeeded; i++) {
                Person spartan = spartans.get(i);
                spartans.remove(i);
                addSpartan(spartan);
            }
        }
        return spartans;
    }

    //takes client off clientlist and adds Spartans back to bench (back of Q)
    public void closeAndReassign() {
        ArrayList<Client> list = getClientList();

        // adds the spartans back to the grad bench queue
        for (int i = 0; i < list.size(); i++) {
            GraduateBenchHandler.addToBench(getSpartan(i));
        }
        getClientList().remove(this); // removes client from list
        isClient = false; // shows client is over
    }

    @Override
    public String toString() {
        return "Client{" +
                "spartanNeeded=" + spartanNeeded +
                ", openDate=" + openDate +
                ", spartans=" + spartans +
                ", closed=" + isClient +
                '}';
    }

    //get client ID
    public int getClientID() {
        return getClientList().indexOf(this);
    }
}
