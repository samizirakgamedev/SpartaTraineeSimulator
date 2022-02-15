package com.purerangers;

import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

public class GenerateCentreType {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();
    public static String generateType(LocalDate date) throws SQLException {
        String[] arr = {"Training Hub", "Bootcamp", "Tech Centre"};
        Random random = new Random();
        // randomly selects an index from the arr
        int chosen = random.nextInt(arr.length);
        System.out.println(date);
        if (chosen == 1 && isBootcampAvailable() == false) generateType(date);
        String sqlCreateCentre = "INSERT INTO Training_Centres (Creation_Date, Type_ID) VALUES ('" + date + "', " + chosen + ");";
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
}
