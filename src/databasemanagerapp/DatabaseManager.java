package databasemanagerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private String databasePath;
    private String databaseName;
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

//    public String sendExceptionError(Exception exception) {
//        
//    }

    public int insertIntoDatabase(Person personObj, String databaseTable) {
        try {
            boolean connectionState = getDatabaseConnection();
            if (connectionState == true) {

                String sqlQueryString = "INSERT INTO " + databaseTable + " VALUES (?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
                preparedStatement.setInt(1, personObj.getPersonID());
                preparedStatement.setString(2, personObj.getPersonFirstName());
                preparedStatement.setString(3, personObj.getPersonMidName());
                preparedStatement.setString(4, personObj.getPersonLastName());
                preparedStatement.setString(5, personObj.getPersonPhoneNumber());
                preparedStatement.setString(6, personObj.getPersonEmail());
                return preparedStatement.executeUpdate();
            } else {
                System.out.println("databasemanagerapp.DatabaseManager.insertIntoDatabase():ERROR CONNECTION");
            }
        } catch (SQLException exception) {
            System.out.println("databasemanagerapp.DatabaseManager.insertIntoDatabase().error: " + exception.getMessage());
            return 0;
        } finally {
            try {
                connection.close();
                return 0;
            } catch (SQLException exception) {
                System.out.println("databasemanagerapp.DatabaseManager.insertIntoDatabase().error: " + exception.getMessage());
                return 0;
            }
        }
    }

    public int updateIntoDatabase(Person personObj, String databaseTable) {
        try {
            boolean connectionState = getDatabaseConnection();
            if (connectionState == true) {

                String sqlQueryString = "UPDATE " + databaseTable + " SET idPerson = ?, PersonFirstName=?,PersonMidName=?,PersonLastName=?,PersonPhoneNumber=?,PersonEmail=? WHERE idPerson=?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
                preparedStatement.setInt(1, personObj.getPersonID());
                preparedStatement.setString(2, personObj.getPersonFirstName());
                preparedStatement.setString(3, personObj.getPersonMidName());
                preparedStatement.setString(4, personObj.getPersonLastName());
                preparedStatement.setString(5, personObj.getPersonPhoneNumber());
                preparedStatement.setString(6, personObj.getPersonEmail());
                preparedStatement.setInt(7, personObj.getPersonID());
                return preparedStatement.executeUpdate();
            } else {
                System.out.println("databasemanagerapp.DatabaseManager.updateIntoDatabase():ERROR CONNECTION");
            }
        } catch (SQLException exception) {
            System.out.println("databasemanagerapp.DatabaseManager.updateIntoDatabase().error: " + exception.getMessage());
            return 0;
        } finally {
            try {
                connection.close();
                return 0;
            } catch (SQLException exception) {
                System.out.println("databasemanagerapp.DatabaseManager.updateIntoDatabase().error: " + exception.getMessage());
                return 0;
            }
        }
    }

    public int deleteFromDatabase(Person personObj, String databaseTable) {
        try {
            boolean connectionState = getDatabaseConnection();
            if (connectionState == true) {

                String sqlQueryString = "DELETE FROM " + databaseTable + " WHERE idPerson = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
                preparedStatement.setInt(1, personObj.getPersonID());
                return preparedStatement.executeUpdate();
            } else {
                System.out.println("databasemanagerapp.DatabaseManager.deleteFromDatabase().ERROR CONNECTION");
            }
        } catch (SQLException exception) {
            System.out.println("databasemanagerapp.DatabaseManager.deleteFromDatabase().error: " + exception.getMessage());
            return 0;
        } finally {
            try {
                connection.close();
                return 0;
            } catch (SQLException exception) {
                System.out.println("databasemanagerapp.DatabaseManager.deleteFromDatabase().error: " + exception.getMessage());
                return 0;
            }
        }
    }

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

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabaseUserPassword() {
        return databaseUserPassword;
    }

    public void setDatabaseUserPassword(String databaseUserPassword) {
        this.databaseUserPassword = databaseUserPassword;
    }

    public static void main(String[] args) {
    }
}
