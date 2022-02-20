package com.purerangers;

import com.purerangers.generators.RandomCourseGenerator;
import com.purerangers.generators.RandomNumberGenerator;
import com.purerangers.model.CourseType;
import com.purerangers.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;


public class Client
{
    private static ArrayList<Client> clientList;
    protected int spartanNeeded;
    protected Date openDate;
    protected static CourseType courseType=null;
    protected ArrayList<Person> spartans = filter(GraduateBenchHandler.getInstance().getGraduateBench()); //filters bench list
    protected ArrayList<Person> spartansAtClient= new ArrayList<>();
    protected int monthsTillReview=12;
    Boolean isClient=true;

    private static final Logger logger = LogManager.getLogger(Client.class.getName());

    public Client()
    {
        this.spartanNeeded = RandomNumberGenerator.getRandomNumbersUsingNextInt(15,50);
        this.openDate = new Date(TimeManager.getInstance().getCurrentDate().getTime());
        Boolean isClient=true;
        courseType= RandomCourseGenerator.RandomCourse();
        TimeManager.getInstance().clients.add(this);
        getClientList().add(this);
    }

    public static ArrayList<Client> getClientList()
    {
        if (clientList == null) {
            clientList = new ArrayList<>();
        }
        return clientList;
    }

    private static ArrayList<Person> filteredSpartans;

    public static ArrayList<Person> filter(ArrayList<Person> spartans)
    {
        if (spartans==null)
        {
            logger.info("Spartans available null arraylist, so empty arraylist made");
            return new ArrayList<>();
        }
        filteredSpartans= new ArrayList<>();
        if (spartans.size()>0)
        {
        for (Person p:spartans)
        {
            if (p.getCourseType()==Client.courseType)
            {
                filteredSpartans.add(p);
            }
        }
        }
        return filteredSpartans;
    }

    //amount of spartans at client
    public int getAmountOfSpartansAtClient()
    {
        if (spartansAtClient == null){
            logger.warn("Spartans at Client is null");
            return 0;
        }
        return spartansAtClient.size();
    }

    public Boolean isHappy()
    {
        return getFreeSpace() == 0;
    }

    public int getFreeSpace()
    {
        return spartanNeeded - spartansAtClient.size();
    }

    public Person getSpartanAtClient(Integer index)
    {
        return spartansAtClient.get(index);
    }

    public boolean addSpartan(Person spartan)
    {
        if (spartan ==null)
        {
            logger.error("Person attempting to be add is null");
            throw new NullPointerException();
        }

        if (getAmountOfSpartansAtClient() < spartanNeeded)
        {
            spartansAtClient.add(spartan);
            return true;
        }

        return false;
    }

    public void updateDate(Date newDate)
    {
        monthsTillReview--;

        if (newDate == null)
        {
            logger.error("There is no date to be updated");
            throw new NullPointerException();
        }

        if (monthsTillReview==0)
        {
            if (isHappy().equals(false))
            {
                closeAndReassign();
                spartansAtClient=new ArrayList<>();
            }
            else
            {
                spartanNeeded += spartanNeeded/5;
                logger.info("ClientID: "+getClientID()+" has increased their Spartans needed to "+spartanNeeded);
            }

            monthsTillReview=12;
        }

        if (getAmountOfSpartansAtClient() < spartanNeeded)
        {
            attemptToRecruitSpartans(spartans); // needs to be filtered
        }
    }

    public ArrayList<Person> attemptToRecruitSpartans(ArrayList<Person> spartans)
    {
        if (spartans == null)
        {
            return new ArrayList<>();
        }
        int b= RandomNumberGenerator.getRandomNumbersUsingNextInt(1,getFreeSpace());
        int i=0;
        do
        {
            if (spartans.size() > i && getAmountOfSpartansAtClient() < spartanNeeded)
            {
                Person spartan = spartans.get(i);
                spartans.remove(i);
                addSpartan(spartan);
            }
            i++;
        }
        while (i<b-1);

        return spartans;
    }

    public void closeAndReassign() {
        // adds the spartans back to the grad bench queue
        for (int i = 0; i < getAmountOfSpartansAtClient(); i++) {
            GraduateBenchHandler.getInstance().addToBench(getSpartanAtClient(i));
        }
        spartanNeeded=0;
        spartansAtClient=new ArrayList<>();
        isClient=false;
    }

    @Override
    public String toString()
    {
        return new StringBuilder()
                .append("Client{")
                .append("ClientID :")
                .append(getClientID())
                .append("spartanNeeded=")
                .append(spartanNeeded)
                .append(", openDate=")
                .append(openDate)
                .append(", spartans at client=")
                .append(spartansAtClient.size())
                .append(", Difference=")
                .append(getFreeSpace())
                .append(", monthsTillReview=")
                .append(monthsTillReview)
                .append(", is Client=")
                .append(isClient)
                .append('}')
                .toString();
    }

    //get client ID
    public int getClientID()
    {
        return getClientList().indexOf(this);
    }
}

