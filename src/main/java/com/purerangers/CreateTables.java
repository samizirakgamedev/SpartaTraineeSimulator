package com.purerangers;
import com.purerangers.TraineeDatabase;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static com.purerangers.SimLogger.logger;

public class CreateTables {
    static TraineeDatabase mysqlConnect = new TraineeDatabase();

    public static void dropAllTables() {
        String[] tables = {"Training_Centres", "Trainees"};
        for (String t : tables) {

            String dropTable = "DROP TABLE IF EXISTS `" + t + "`;";
            try {
                PreparedStatement st = mysqlConnect.connect().prepareStatement(dropTable);
                st.executeUpdate(dropTable); //execute the query
            } catch (
                    SQLException e) {
                logger.fatal("Error dropping table.");
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    public static void createTables(){
        try {
            String createTable = "CREATE TABLE `Training_Centres` (" +
                    "`ID` INT," +
                    "`Creation_Date` DATE," +
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
                    "`ID` INT," +
                    "`TC_ID` INT," +
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
    }
}
