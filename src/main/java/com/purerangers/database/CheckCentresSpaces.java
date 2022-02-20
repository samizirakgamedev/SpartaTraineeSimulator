package com.purerangers.database;

import com.purerangers.database.TraineeDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckCentresSpaces
{
    static TraineeDatabase mysqlConnect = new TraineeDatabase();
    static int result = 0;
    static int maxSpaces = 0;

    public static void checkAvailability() throws SQLException {
        String sql1 = "SELECT DISTINCT TC_ID FROM Trainees;";
        PreparedStatement st3 = mysqlConnect.connect().prepareStatement(sql1);
        ResultSet rs3 = st3.executeQuery(sql1);
        while (rs3.next()) {
            int result3 = rs3.getInt("TC_ID");
            System.out.println(result3);
            System.out.println(returnAvailable(result3));
        }
    }

    public static int returnAvailable(int c) {
        try {
            String sqlCentresList;
            sqlCentresList = "SELECT COUNT(*) AS ID FROM `Trainees` WHERE TC_ID = " + c + ";";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlCentresList);
            ResultSet rs = st.executeQuery(sqlCentresList);
            while (rs.next()) {
                result = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (c == 0) maxSpaces = 100;
        else if (c == 1) maxSpaces = 500;
        else if (c == 2) maxSpaces = 200;

        return maxSpaces - result;
    }

    public static String returnCentreType(int t) throws SQLException {
        String type = "";
        String sqlGetType = "SELECT Name FROM `Centre_Type` WHERE ID = " + t + ";";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlGetType);
        ResultSet rs = st.executeQuery(sqlGetType);
        while (rs.next()) {
            type = rs.getString("Name");
        }
        return type;
    }
}
