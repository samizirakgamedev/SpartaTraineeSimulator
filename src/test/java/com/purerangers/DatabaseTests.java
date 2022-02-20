package com.purerangers;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.purerangers.database.TraineeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.purerangers.CreateTables.mysqlConnect;

class DatabaseTests {
    private static TraineeDatabase traineeDatabase;
    private static CreateTables createTables;
    //private Connection connection = TraineeDatabase.connect();

    @Test
    @DisplayName("Check if the tables are created in the database")
    void createTables() throws SQLException {
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
    @Test
    @DisplayName("Check if trainees are generated and inserted into database correctly")
    void checkTraineeCreation() throws SQLException {
        boolean isCreated = false;
        CreateTrainees.generateTrainees(2);
        String sqlCheckTrainee = "SELECT * FROM Queue;";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlCheckTrainee); //prepare java statement
        st.executeQuery(sqlCheckTrainee); //execute the query
        ResultSet rs = st.executeQuery(sqlCheckTrainee); //execute the query
        if (rs.next()) {
            isCreated = true;
        }
        Assertions.assertTrue(isCreated);
    }
    @Test
    @DisplayName("Check if it stops creating a third Bootcamp")
    void checkBootcamp() throws SQLException {
        CreateTables.dropAllTables();
        CreateTables.createTables();
        Connection connection = TraineeDatabase.connect();
        String sqlAddBootcamp = "INSERT INTO Training_Centres (Type_ID) VALUES (2);";
        for (int i = 0; i < 2; i++) {
            PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlAddBootcamp); //prepare java statement
            st.executeUpdate(sqlAddBootcamp); //execute the query
        }
        Boolean checkBootcamp = GenerateCentreType.isBootcampAvailable();
        Assertions.assertFalse(checkBootcamp);
    }
    @Test
    @DisplayName("Check if Queue is parsed correctly")
    void checkQueueParser() throws SQLException {
        boolean isGone = false;
        CreateTables.dropAllTables();
        CreateTables.createTables();
        Connection connection = TraineeDatabase.connect();
        String addInQueue = "INSERT INTO Queue (ID_Trainee, ID_Course) VALUES (1, 1);";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(addInQueue); //prepare java statement
        st.executeUpdate(addInQueue); //execute the query
        String addInTrainingCentres = "INSERT INTO Training_Centres (Type_ID, Teaching) VALUES (1, 1);";
        st = mysqlConnect.connect().prepareStatement(addInTrainingCentres); //prepare java statement
        st.executeUpdate(addInTrainingCentres); //execute the query
        CreateTrainees.parseQueue();
        String checkQueue = "SELECT ID_Trainee FROM Queue WHERE ID_Trainee = 1;";
        st = mysqlConnect.connect().prepareStatement(checkQueue); //prepare java statement
        ResultSet rs = st.executeQuery(checkQueue); //execute the query
        Integer IDTrainee = null;
        if (!rs.isBeforeFirst()) {
            isGone = true;
        }
        Assertions.assertTrue(isGone);
    }
    @Test
    @DisplayName("Check if TrainingCentres are generated and returned correctly from database")
    void checkTrainingCentres() throws SQLException {
        String generated = null;
        LocalDate date = LocalDate.now();
        generated = GenerateCentreType.generateType(date);
        Assertions.assertNotNull(generated);
    }
    @Test
    @DisplayName("Check if the correct centre type is returned from the database")
    void checkCentreType() throws SQLException {
        boolean correctType = false;
        LocalDate date = LocalDate.now();
        int chosen = 1; //Training Hub
        int teaches = 2; //Java
        String sqlCreateCentre = "INSERT INTO Training_Centres (ID, Creation_Date, Type_ID, Teaching) VALUES (1, '" + date + "', " + chosen + ", " + teaches + ");";
        PreparedStatement st = mysqlConnect.connect().prepareStatement(sqlCreateCentre);
        String type = CheckCentresSpaces.returnCentreType(1);
        if ("Training Hub".equals(type)) correctType = true;
        Assertions.assertTrue(correctType);
    }

}