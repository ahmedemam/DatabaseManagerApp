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

//    public String sendExceptionError(Exception exception){
//        return exception.getMessage();
//    }
    public int insertIntoDatabase(Person personObj, String databaseTabel) {
        try {
            boolean connectionState=getDatabaseConnection();
            if(connectionState==true){
                
            String sqlQueryString="INSERT INTO "+databaseTabel+" VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sqlQueryString);
            /////////////////// person id
            preparedStatement.setString(1, personObj.getPersonFirstName());
            preparedStatement.setString(2, personObj.getPersonMidName());
            preparedStatement.setString(3, personObj.getPersonLastName());
            preparedStatement.setString(4, personObj.getPersonPhoneNumber());
            preparedStatement.setString(5, personObj.getPersonEmail());
            return preparedStatement.executeUpdate();
            }
            else{
                System.out.println("databasemanagerapp.DatabaseManager.insertIntoDatabase():ERROR CONNECTION");
            }
        } catch (SQLException exception) {
            System.out.println("databasemanagerapp.DatabaseManager.insertIntoDatabase().error: "+exception.getMessage());
            return  0;
        }
        finally{
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println("databasemanagerapp.DatabaseManager.insertIntoDatabase().error: "+exception.getMessage());
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
