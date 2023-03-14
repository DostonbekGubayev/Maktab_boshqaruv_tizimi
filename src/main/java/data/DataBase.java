package data;

import com.example.schoolmanagmentsystem.ExitManager;
import com.example.schoolmanagmentsystem.HelloController;
import com.example.schoolmanagmentsystem.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.invoke.TypeDescriptor;
import java.nio.channels.ConnectionPendingException;
import java.sql.*;
import java.util.Properties;

public class DataBase {

    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;

    private ResultSet resultSet;

    private HelloController controller;



    public DataBase(HelloController controller) {
        this.controller = controller;
    }


    public boolean mantiqiy(){
        if (controller.usernameSignUp.getText().isEmpty()){
            return true;
        }if (controller.passwordSignUp.getText().isEmpty()){
            return true;
        }if (controller.emailSIgnUp.getText().isEmpty()){
            return true;
        }else
        return false;
    }


    /*public Connection connectDb() {
        Connection connection;
        Properties connectionProps = new Properties();
        connectionProps.put("user", controller.username);
        connectionProps.put("password", controller.password);// port & db name
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/student",
                    connectionProps
            );

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        return null;
    }
*/


    public void login(ActionEvent event){

//        Properties connectionProps = new Properties();
//        connectionProps.put("root", controller.username);
//        connectionProps.put("", controller.password);// port & db name
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/odamlar","root",""

            );
            String sql="SELECT * FROM `maktab` WHERE username=? and password=?";
            prepare =connection.prepareStatement(sql);
            prepare.setString(1,controller.username.getText());
            prepare.setString(2,controller.password.getText());
            resultSet= prepare.executeQuery();
            if(resultSet.next()&&!isFull()){

                User.username=resultSet.getString("username");
               ExitManager.alertManagerSuccess();
                controller.username.setText("");
                controller.password.setText("");
                controller.loginBtn.getScene().getWindow().hide();
            }else if (isFull()){
                ExitManager.writeUsOrPas();
            }
            else {
                ExitManager.alertManagerInvailed();
                controller.username.setText("");
                controller.password.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





       public void signup(ActionEvent event){

        String sql="INSERT INTO `maktab`(username, password, image) VALUES (?,?,?) ";
           if (!mantiqiy()) {
               try {
                   connection = DriverManager.getConnection("jdbc:mysql:///odamlar", "root", "");
                   String sql1 = "INSERT INTO `maktab`(username, password, image) VALUES (?,?,?) ";
                   prepare = connection.prepareStatement(sql1);
                   prepare.setString(1, controller.usernameSignUp.getText());
                   prepare.setString(2, controller.passwordSignUp.getText());
                   prepare.setString(3, controller.emailSIgnUp.getText());

                   prepare.execute();

                   //User.username=resultSet.getString("username");
                   User.username = controller.usernameSignUp.getText();
                   controller.usernameSignUp.setText("");
                   controller.passwordSignUp.setText("");
                   controller.emailSIgnUp.setText("");
                   ExitManager.alertManagerSuccess();
                   controller.signupBtn.getScene().getWindow().hide();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else
               ExitManager.bollText();
    }

    public boolean isFull(){
        if (controller.username.getText().isEmpty()){
            return true;
        }else if (controller.password.getText().isEmpty()){
            return true;
        }else
            return false;
    }
}
