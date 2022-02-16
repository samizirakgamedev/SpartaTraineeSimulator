package com.purerangers;
import com.purerangers.TraineeDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import static com.purerangers.SimLogger.logger;

public class CreateTables {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();

    public static void dropAllTables() {
        String[] tables = {"Training_Centres", "Trainees", "Centre_Type", "Course_Type"};

        for (String t : tables) {

            String dropTable = "DROP TABLE IF EXISTS `" + t + "`;";
            try {
                PreparedStatement st = mysqlConnect.connect().prepareStatement(dropTable);
                st.executeUpdate(dropTable); //execute the query
                System.out.println("dropped " + t);
            } catch (
                    SQLException e) {
                logger.fatal("Error dropping table.");
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    public static void createTables() throws SQLException {
        try {
            String createTable = "CREATE TABLE `Training_Centres` (" +
                    "`ID` INT AUTO_INCREMENT," +
                    "`Creation_Date` DATE," +
                    "`Type_ID` INT," +
                    "`Teaching` INT," +
                    "PRIMARY KEY (`ID`)" +
                    ");";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(createTable); //prepare java statement
            st.executeUpdate(createTable); //execute the query
            logger.info("Successfully created 'Training_Centres' table");
        } catch (SQLException e) {
            logger.fatal("Error while creating the table ", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        try {
            String createTable = "CREATE TABLE `Trainees` (" +
                    "`ID` INT AUTO_INCREMENT," +
                    "`TC_ID` INT," +
                    "`Course_ID` INT," +
                    "`Date_Joined` DATE," +
                    "PRIMARY KEY (`ID`)" +
                    ");";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(createTable); //prepare java statement
            st.executeUpdate(createTable); //execute the query
            logger.info("Successfully created 'Trainees' table");
        } catch (SQLException e) {
            logger.fatal("Error while creating the table ", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        try {
            String createTable = "CREATE TABLE `Course_Type` (" +
                    "`ID` INT AUTO_INCREMENT," +
                    "`Name` VARCHAR(255)," +
                    "PRIMARY KEY (`ID`)" +
                    ");";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(createTable); //prepare java statement
            st.executeUpdate(createTable); //execute the query
            logger.info("Successfully created 'Course_Type' table");
        } catch (SQLException e) {
            logger.fatal("Error while creating the table ", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        try {
            String createTable = "CREATE TABLE `Centre_Type` (" +
                    "`ID` INT AUTO_INCREMENT," +
                    "`Name` VARCHAR(255)," +
                    "PRIMARY KEY (`ID`)" +
                    ");";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(createTable); //prepare java statement
            st.executeUpdate(createTable); //execute the query
            logger.info("Successfully created 'Centre_Type' table");
        } catch (SQLException e) {
            logger.fatal("Error while creating the table ", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        //populate Course_Type and Centre_Type with data
        try {
            String createTable = "INSERT INTO `Centre_Type` (`Name`) VALUES ('Training Hub'), ('Bootcamp'), ('Tech Centre');";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(createTable); //prepare java statement
            st.executeUpdate(createTable); //execute the query
            logger.info("Successfully inserted data into 'Centre_Type' table");
        } catch (SQLException e) {
            logger.fatal("Error while inserting data in 'Centre_Type' table ", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        try {
            String createTable = "INSERT INTO `Course_Type` (`Name`) VALUES ('Java'), ('C#'), ('Data'), ('DevOps'), ('Business');";
            PreparedStatement st = mysqlConnect.connect().prepareStatement(createTable); //prepare java statement
            st.executeUpdate(createTable); //execute the query
            logger.info("Successfully inserted data into 'Centre_Type' table");
        } catch (SQLException e) {
            logger.fatal("Error while inserting data in 'Centre_Type' table ", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }

        String sqlReturnCentres = "SELECT ID, Name FROM `Centre_Type`;";
        Statement st = mysqlConnect.connect().createStatement();
        ResultSet rs = st.executeQuery(sqlReturnCentres);
        while (rs.next()) {
            int number = rs.getInt("ID");
            String course = rs.getString("Name");
            System.out.println(number + " - " + course);
        }
        }
    }

