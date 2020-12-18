package sample;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "test";
        String databaseUser = "Urmatbek";
        String databasePassword = "312515Real";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;

    }

}
