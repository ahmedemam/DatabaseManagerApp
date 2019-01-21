package databasemanagerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    private String databasePath;
    private String databaseName;
    // private String databaseTableName;
    private String databaseUsername;
    private String databaseUserPassword;
    static Connection connection;

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
            connection = DriverManager.getConnection(databasePath + "/"+databaseName, databaseUsername, databaseUserPassword);
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
        try {
            DatabaseManager DBM =  new DatabaseManager("jdbc:mysql://localhost:3306", "book", "Shawkat", "root");
            DBM.getDatabaseConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String queryString = new String("select * from Person");
            ResultSet rs = stmt.executeQuery(queryString) ;
            while(rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
