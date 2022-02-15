package com.purerangers;

import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class GenerateCentreType {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();
    public static String generateType() throws SQLException {
        String[] arr = {"Training Hub", "Bootcamp", "Tech Centre"};
        Random random = new Random();
        // randomly selects an index from the arr
        int chosen = random.nextInt(arr.length);
        if (chosen == 1 && isBootcampAvailable() == false) generateType();
        return arr[chosen];
    }


    private static boolean isBootcampAvailable() throws SQLException {
        boolean result = true;
        String sqlBootcamp = "SELECT COUNT(*) FROM `Training_Centres` WHERE Type LIKE 'Bootcamp';";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlBootcamp);
        int rs = st.executeUpdate(sqlBootcamp);
        if (rs == 2) result = false;
        return result;
    }
}
