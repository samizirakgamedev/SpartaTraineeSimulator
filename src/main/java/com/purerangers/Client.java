package com.purerangers;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Client {
    private static ArrayList<Client> clientList;
    protected int spartanNeeded;
    protected Date openDate;
    protected static CourseType courseType;
    protected ArrayList<Person> spartans = filter(GraduateBenchHandler.getGraduateBench()); //filters bench list
    protected ArrayList<Person> spartansAtClient= new ArrayList<>();


    protected boolean isClient;
    protected int monthsTillReview=11;

    //Not sure if coursetype is needed as a parameter because of RandomCourseGenerator.RandomCourse();
    public Client() {
        this.spartanNeeded = RandomNumberGenerator.getRandomNumbersUsingNextInt(15,50);
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        courseType= RandomCourseGenerator.RandomCourse();
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

    private static ArrayList<Person> filteredSpartans;

    public static ArrayList<Person> filter(ArrayList<Person> spartans){
        if (spartans.isEmpty()){
            filteredSpartans= new ArrayList<>();
        }
        for (Person p:spartans){
            if (p.getCourseType()==Client.courseType){
                filteredSpartans.add(p);
            }
        }
        return filteredSpartans;
    }

    //Clear the clientlist
    public static void clearClientList() {
        clientList = new ArrayList<>();
    }

    //amount of available spartans
    public int getAmountOfSpartans() {
        return spartans.size();
    }

    //amount of spartans at client
    public int getAmountOfSpartansAtClient() {
        if (spartansAtClient == null){
            return 0;
        }
        return spartansAtClient.size();
    }

    public Boolean isHappy() {
        return getFreeSpace() != 0;
    }

    public int getFreeSpace() {
        return spartanNeeded - getAmountOfSpartansAtClient();
    }

    public Person getSpartan(Integer index) {
        return spartans.get(index);
    }
    public Person getSpartanAtClient(Integer index) {
        return spartansAtClient.get(index);
    }

    public ArrayList<Person> getSpartans() {
        return spartans;
    }

    public boolean addSpartan(Person spartan) {
        if (spartan ==null) {
            throw new NullPointerException();
        }

        if (getAmountOfSpartansAtClient() < spartanNeeded) {
            //System.out.println("SPARTAN SIZE"+spartansAtClient.size());
            spartansAtClient.add(spartan);
            return true;
        }
        return false;
    }

    public void updateDate(Date newDate) {
        monthsTillReview--;
        if (isClient = false) {
            return;
        }
        if (newDate == null) {
            throw new NullPointerException();
        }
        if (monthsTillReview==0){
            if (isHappy()==false){
                closeAndReassign();
            }
            else{
                spartanNeeded=+spartanNeeded/5;
                monthsTillReview=11;
            }

        }
        if (getAmountOfSpartansAtClient() < spartanNeeded) {
            attemptToRecruitSpartans(spartans); // needs to be filtered
        }
    }

    public ArrayList<Person> attemptToRecruitSpartans(ArrayList<Person> spartans) {
        if (spartans == null) {
            throw new NullPointerException();
        }
        int b= RandomNumberGenerator.getRandomNumbersUsingNextInt(1,getFreeSpace());
        //System.out.println("Random number"+ b);
        int i=0;
        do  {
            if (spartans.size() > 0 && getAmountOfSpartansAtClient() < spartanNeeded) {
                System.out.println("Amount of Spartans at Client:"+getAmountOfSpartansAtClient()+" Client ID"+getClientID());
                System.out.println("Spartans needed"+spartanNeeded);
                System.out.println("Is empty" + spartans.isEmpty());
                Person spartan = spartans.get(i);
                spartans.remove(i);
                addSpartan(spartan);
                System.out.println("Spartan "+spartans.get(i)+"Added to "+getClientID());
            }
            i++;
        }
        while (i<b-1);

        return spartans;
    }

    //takes client off clientlist and adds Spartans back to bench (back of Q)
    public void closeAndReassign() {
        // adds the spartans back to the grad bench queue
        for (int i = 0; i < getAmountOfSpartansAtClient(); i++) {
            GraduateBenchHandler.addToBench(getSpartanAtClient(i));
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
                ", isClient=" + isClient +
                '}';
    }

    //get client ID
    public int getClientID() {
        return getClientList().indexOf(this);
    }

}

