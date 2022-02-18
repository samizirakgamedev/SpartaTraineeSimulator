package com.purerangers;

import java.sql.Date;
import java.util.ArrayList;

public class Client {
    private static ArrayList<Client> clientList;
    protected int spartanNeeded=0;
    protected Date openDate;
    protected static CourseType courseType=null;
    protected ArrayList<Person> spartans = filter(GraduateBenchHandler.getGraduateBench()); //filters bench list
    protected static ArrayList<Person> spartansAtClient= new ArrayList<>();


    protected boolean isClient;
    protected int monthsTillReview=12;


    public Client() {
        this.spartanNeeded = RandomNumberGenerator.getRandomNumbersUsingNextInt(15,50);
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        courseType= RandomCourseGenerator.RandomCourse();
        TimeManager.getInstance().clients.add(this);
        isClient = true;
        getClientList().add(this);
    }

    public static ArrayList<Client> getClientList() {
        if (clientList == null) {
            clientList = new ArrayList<>();
        }
        return clientList;
    }
    public static ArrayList<Person> getSpartansAtClient() {
        if (spartansAtClient == null) {
            spartansAtClient = new ArrayList<>();
        }
        return spartansAtClient;
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
    public int getSpartanNeeded(){
        return spartanNeeded;
    }

    public int getFreeSpace() {
        return getSpartanNeeded() - getAmountOfSpartansAtClient();
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

           // System.out.println("Amount of spartans needed:"+spartanNeeded+"amount they have:"+getAmountOfSpartansAtClient()+"Difference: "+getFreeSpace());
            spartansAtClient.add(spartan);
            //System.out.println("Spartan size"+ spartansAtClient.size());
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
                monthsTillReview=12;
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
        //System.out.println("Max they can add in Month:"+b);
        do  {
            if (spartans.size() > 0 && getAmountOfSpartansAtClient() < spartanNeeded) {
                Person spartan = spartans.get(i);
                spartans.remove(i);
                addSpartan(spartan);
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
        System.out.println("Removed Client at"+getClientList().indexOf(this));
        getClientList().remove(this); // removes client from list
        isClient = false; // shows client is over

    }

    @Override
    public String toString() {
        return "Client{" +
                "spartanNeeded=" + spartanNeeded +
                ", openDate=" + openDate +
                ", spartans=" + spartansAtClient +
                ", isClient=" + isClient +
                ", monthsTillReview=" + monthsTillReview +
                '}';
    }

    //get client ID
    public int getClientID() {
        return getClientList().indexOf(this);
    }

//    public static void main(String[] args) {
//        Client a= new Client();
//        System.out.println("A"+a.getSpartanNeeded());
//        System.out.println("A"+a.getFreeSpace());
//        System.out.println("A"+a);
//        getClientList().add(a);
//        System.out.println(getClientList());
//        Client b= new Client();
//        System.out.println("B"+b.getSpartanNeeded());
//        System.out.println("B"+b.getFreeSpace());
//        System.out.println("B"+b);
//
//        System.out.println(getClientList().add(b));
//        System.out.println(getClientList());
//    }
}

