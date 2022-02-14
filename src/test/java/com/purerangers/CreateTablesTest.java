package com.purerangers;

import com.mysql.cj.jdbc.DatabaseMetaData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.purerangers.TraineeDatabase;
import com.purerangers.CreateTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CreateTablesTest {
    private static TraineeDatabase traineeDatabase;
    private static CreateTables createTables;
    //private Connection connection = TraineeDatabase.connect();

    @Test
    void createTables() {
        CreateTables.dropAllTables();
        CreateTables.createTables();
        Boolean exists = false;
        //String query = "SELECT * FROM `EmployeeRecords`;";
        try {
            Connection connection = TraineeDatabase.connect();
            DatabaseMetaData dbmd = (DatabaseMetaData) connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                if ("Training_Centres".equals(rs.getString("TABLE_NAME"))) exists = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(exists);
    }
}