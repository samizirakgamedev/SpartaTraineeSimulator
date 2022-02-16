package com.purerangers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateTrainees {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();
    public static void generateTrainees(int numberOfTrainees) throws SQLException {
        String[] courses = {"Java", "C#", "Data", "DevOps", "Business"};
        Random random = new Random();
        String sqlSelectQueue = "SELECT MAX(ID_Trainee) AS Max FROM Queue;";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlSelectQueue); //prepare java statement
        ResultSet rs = st.executeQuery(sqlSelectQueue); //execute the query
        int result = 0;
        while (rs.next()) {
            result = rs.getInt("Max");
        }
        sqlSelectQueue = "SELECT MAX(ID) AS Max2 FROM Trainees;";
        st = mysqlConnect.connect().prepareStatement(sqlSelectQueue); //prepare java statement
        rs = st.executeQuery(sqlSelectQueue); //execute the query
        int result2 = 0;
        while (rs.next()) {
            result2 = rs.getInt("Max2");
        }
        if (result2 > result) result = result2;
        for (int i = result+1; i < numberOfTrainees + result; i++) {
            int chosen = random.nextInt(1, 6);
            String randomCourse = courses[chosen-1];
            String sqlInsertInQueue = "INSERT INTO `Queue` (`ID_Trainee`, `ID_Course`) VALUES (" + i + ", " + chosen + ");";
            PreparedStatement st2 = mysqlConnect.connect().prepareStatement(sqlInsertInQueue); //prepare java statement
            st2.executeUpdate(sqlInsertInQueue); //execute the query
        }
    }
    public static void showQueue() throws SQLException {
        String[] courses = {"Java", "C#", "Data", "DevOps", "Business"};
        String sqlShowQueue = "SELECT * FROM Queue;";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlShowQueue); //prepare java statement
        ResultSet rs = st.executeQuery(sqlShowQueue); //execute the query
        int trainee = 0;
        int course = 0;
        while (rs.next()) {
            trainee = rs.getInt("ID_Trainee");
            course = rs.getInt("ID_Course");
            String courseName = courses[course-1];
            System.out.println("Trainee " + trainee + " is waiting for course " + courseName);
        }
    }
    public static void parseQueue() throws SQLException {
        String findCentreType = "SELECT DISTINCT Type_ID FROM Training_Centres;";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(findCentreType); //prepare java statement
        ResultSet rs = st.executeQuery(findCentreType); //execute the query
        int[] res = new int[10000];
        int i = 0;
        while (rs.next()){
            int type = rs.getInt("Type_ID");
            res[i] = type;
            //System.out.println(res[i]);
            i++;
        }
        for (int e : res) {
            if (e == 3) {
                int centreType = 0;
                int id = 0;
                int traineeID = 0;
                String sqlGetCentresActive = "SELECT Training_Centres.ID AS ID, Training_Centres.Type_ID AS Type_ID, Training_Centres.Teaching, Queue.ID_Trainee AS Trainee_ID FROM Training_Centres INNER JOIN Queue ON Training_Centres.Teaching = Queue.ID_Course;";
                st = mysqlConnect.connect().prepareStatement(sqlGetCentresActive); //prepare java statement
                rs = st.executeQuery(sqlGetCentresActive); //execute the query
                while (rs.next()) {
                    centreType = rs.getInt("Type_ID");
                    id = rs.getInt("ID");
                    traineeID = rs.getInt("Trainee_ID");
                    String sqlInsertTrainee = "INSERT INTO Trainees (ID, TC_ID, Course_ID) VALUES (" + traineeID + "," + id + "," + centreType + ");";
                    //System.out.println(sqlInsertTrainee);
                    PreparedStatement st3 = mysqlConnect.connect().prepareStatement(sqlInsertTrainee); //prepare java statement
                    st3.executeUpdate(sqlInsertTrainee); //execute the query
                    String sqlDelete = "DELETE FROM Queue WHERE ID_Trainee = " + traineeID + ";";
                    //System.out.println(sqlDelete);
                    PreparedStatement st4 = mysqlConnect.connect().prepareStatement(sqlDelete); //prepare java statement
                    st4.executeUpdate(sqlDelete); //execute the query

                }
            } else {
                String sqlGetCentresActive = "SELECT * FROM Queue;";
                st = mysqlConnect.connect().prepareStatement(sqlGetCentresActive); //prepare java statement
                rs = st.executeQuery(sqlGetCentresActive); //execute the query
                int centreType = 0;
                int id = 0;
                int traineeID = 0;
                while (rs.next()) {
                    //centreType = rs.getInt("Type_ID");
                    id = rs.getInt("ID_Course");
                    traineeID = rs.getInt("ID_trainee");
                    String sqlInsertTrainee = "INSERT INTO Trainees (ID, TC_ID, Course_ID) VALUES (" + traineeID + "," + id + "," + centreType + ");";
                    //System.out.println(sqlInsertTrainee);
                    PreparedStatement st3 = mysqlConnect.connect().prepareStatement(sqlInsertTrainee); //prepare java statement
                    st3.executeUpdate(sqlInsertTrainee); //execute the query
                    String sqlDelete = "DELETE FROM Queue WHERE ID_Trainee = " + traineeID + ";";
                    //System.out.println(sqlDelete);
                    PreparedStatement st4 = mysqlConnect.connect().prepareStatement(sqlDelete); //prepare java statement
                    st4.executeUpdate(sqlDelete); //execute the query

                }
            }
        }
    }
}
