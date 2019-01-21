package databasemanagerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private String databasePath;
    private String databaseName;
    // private String databaseTableName;
    private String databaseUsername;
    private String databaseUserPassword;
    Connection connection;

    public DatabaseManager(String databasePath, String databaseName, String databaseUsername, String databaseUserPassword) {
        this.databasePath = databasePath;
        this.databaseName = databaseName;
        this.databaseUsername = databaseUsername;
        this.databaseUserPassword = databaseUserPassword;
    }

    public boolean getDatabaseConnection() {
        boolean state = true;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(databasePath + databaseName, databaseUsername, databaseUserPassword);
            return true;

        } catch (SQLException exception) {
            System.out.println("databasemanagerapp.DatabaseManager.databaseConnection().error: " + exception.getMessage());
            return false;
        }
    }

//    public String sendExceptionError(Exception exception){
//        return exception.getMessage();
//    }
    public String getDatabasePath() {
        return databasePath;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public static void main(String[] args) {

    }
}
