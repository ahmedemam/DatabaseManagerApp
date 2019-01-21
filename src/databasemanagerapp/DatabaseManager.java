package databasemanagerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    private String databasePath;
    private String databaseName;
    private String databaseUsername;
    private String databaseUserPassword;
    private ResultSet res;
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
            connection = DriverManager.getConnection(databasePath + "/" + databaseName, databaseUsername, databaseUserPassword);
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

    public int deleteFromDatabase(int personID, String databaseTable) {
        try {
            boolean connectionState = getDatabaseConnection();
            if (connectionState == true) {

                String sqlQueryString = "DELETE FROM " + databaseTable + " WHERE idPerson = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
                preparedStatement.setInt(1, personID);
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

    public Person getPerson(String index) {
        Person personObj = null;
        try {
            if (index == "first") {
                res.first();
            } else if (index == "last") {
                res.last();
            } else if (index == "next") {
                res.next();
            } else if (index == "pervious") {
                res.previous();
            }
                personObj = new Person();
                personObj.setPersonID(Integer.parseInt(res.getString(1)));
                personObj.setPersonFirstName(res.getString(2));
                personObj.setPersonMidName(res.getString(3));
                personObj.setPersonLastName(res.getString(4));
                personObj.setPersonEmail(res.getString(5));
                personObj.setPersonPhoneNumber(res.getString(6));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personObj;
    }

    public static void main(String[] args) {
        try {
            DatabaseManager DBM = new DatabaseManager("jdbc:mysql://localhost:3306", "book", "Shawkat", "root");
            DBM.getDatabaseConnection();
            Statement stmt = DBM.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Person p = new Person("Ahmed", "Mostafa", "Sayed", "012", "m@.com");
            //DBM.insertIntoDatabase(p, "Person");
            String queryString = new String("select * from Person");
            DBM.res = stmt.executeQuery(queryString);
            DBM.res.first();
            System.out.println(DBM.res.getString(3));
            DBM.res.last();
            System.out.println(DBM.res.getString(3));
            DBM.res.previous();
            System.out.println(DBM.res.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
