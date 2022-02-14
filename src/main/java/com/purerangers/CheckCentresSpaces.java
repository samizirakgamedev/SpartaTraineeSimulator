package com.purerangers;
import static com.purerangers.SimLogger.logger;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class CheckCentresSpaces {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();
    int result = 0;
    int returnAvailable(int c){
        try {
            String sqlCentresList;
            sqlCentresList = "SELECT COUNT(*) FROM `Trainees` WEHERE TC_ID=" + c + ";";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlCentresList);
            ResultSet rs = st.executeQuery(sqlCentresList);
            while (rs.next()) {
                result = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
