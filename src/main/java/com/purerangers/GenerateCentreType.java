package com.purerangers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

public class GenerateCentreType {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();
    public static String generateType(LocalDate date) throws SQLException {
        String[] courses = {"Any", "Java", "C#", "Data", "DevOps", "Business"};
        Random random2 = new Random();
        String[] arr = {"Training Hub", "Bootcamp", "Tech Centre"};
        Random random = new Random();
        // randomly selects an index from the arr
        int chosen = random.nextInt(arr.length);
        int teaches = 0;
        int chosen2 = random2.nextInt(courses.length -1) + 1;
        System.out.println(date);
        if (chosen == 2) teaches = random2.nextInt(courses.length - 1) + 1;
        if (chosen == 1 && isBootcampAvailable() == false) generateType(date);
        System.out.println("Can we create bootcamp? " + isBootcampAvailable());
        String sqlCreateCentre = "INSERT INTO Training_Centres (Creation_Date, Type_ID, Teaching) VALUES ('" + date + "', " + chosen + ", " + teaches + ");";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlCreateCentre);
        st.executeUpdate(sqlCreateCentre);
        System.out.println(arr[chosen] + " was created and inserted into database.");
        return arr[chosen];
    }


    private static boolean isBootcampAvailable() throws SQLException {
        boolean result = true;
        String sqlBootcamp = "SELECT COUNT(*) AS Total FROM `Training_Centres` WHERE Type_ID = 1;";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlBootcamp);
        ResultSet rs = st.executeQuery(sqlBootcamp);
        int number = 0;
        while (rs.next()) {
            number = rs.getInt("Total");
        }
        if (number == 2) result = false;
        return result;
    }
    public static void returnCentres() throws SQLException {
        String sqlReturnCentres = "SELECT ID, Type_ID, Teaching FROM `Training_Centres` GROUP BY Type_ID;";
        Statement st = mysqlConnect.connect().createStatement();
        ResultSet rs = st.executeQuery(sqlReturnCentres);
        System.out.println("Training centres opened:");
        while (rs.next()) {
            int number = rs.getInt("ID");
            int number2 = rs.getInt("Type_ID");
            int number3 = rs.getInt("Teaching");
            System.out.println("ID: " + number + " - Type ID: " + number2 + " - Teaching: " + number3);
        }
    }
}
