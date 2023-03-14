package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {


    Connection connection;
    public  Connection connectDb(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/talaba","root","");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
        }
        return connection;
    }
}
