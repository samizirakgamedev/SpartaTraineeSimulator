package com.purerangers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Random;

import static com.purerangers.CreateTables.mysqlConnect;

public class PopulateDatabase {
    private static TraineeDatabase traineeDatabase;
    private static CreateTables createTables;
    public static void main(String[] args) throws SQLException {
        int k = 0;
        CreateTables.dropAllTables();
        CreateTables.createTables();
        for (int i = 0; i < 100; i++) {
            String[] arr = {"Training Hub", "Bootcamp", "Tech Centre"};
            Random random = new Random();
            // randomly selects an index from the arr
            int chosen = random.nextInt(arr.length);
            String sqlInsert = "INSERT INTO `Training_Centres` (ID, Creation_Date, Type_ID) VALUES (" + i + ", '2022-05-06', '" + random.nextInt(3) + "');";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlInsert);
            st.executeUpdate(sqlInsert);
            for (int j = 0; j < 10; j++) {
                String sqlInsertTrainees = "INSERT INTO `Trainees` (ID, TC_ID, Date_joined) VALUES (" + k + ", " + i + ", '2022-05-06');";
                PreparedStatement st2 = mysqlConnect.connect().prepareStatement(sqlInsertTrainees);
                st2.executeUpdate(sqlInsertTrainees);
                k++;
            }
        }
        String checkCentres = "SELECT COUNT(*) AS ID FROM Training_Centres;";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(checkCentres);
        ResultSet rs = st.executeQuery(checkCentres);
        int number = 0;
        while (rs.next()) {
            number = rs.getInt("ID");
        }
        System.out.println("Created " + number + " centres.");

        String checkTrainees = "SELECT COUNT(*) AS ID FROM Trainees;";
        PreparedStatement st2 = mysqlConnect.connect().prepareStatement(checkTrainees);
        ResultSet rs2 = st2.executeQuery(checkTrainees);
        int number2 = 0;
        while (rs2.next()) {
            number2 = rs2.getInt("ID");
        }
        System.out.println("With " + number2 + " trainees.");
    }
}
