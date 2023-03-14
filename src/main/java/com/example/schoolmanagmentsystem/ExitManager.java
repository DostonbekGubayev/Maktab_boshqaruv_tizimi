package com.example.schoolmanagmentsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ExitManager {
    private static double x=0;
    private static double y=0;

    public static void exit(){
        System.exit(0);
    }

    public  void loadDashboard(){
        try {
            Parent parent= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage stage=new Stage();
            parent.setOnMousePressed(event -> {

                x=event.getSceneX();
                y=event.getSceneY();

            });

            parent.setOnMouseDragged(e->{

                stage.setX(e.getScreenX()-x);
                stage.setY(e.getScreenY()-y);

            });



            stage.setScene(new Scene(parent));
            stage.setTitle("Dashboard Design");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void alertManagerSuccess(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Login Successfully");
        alert.showAndWait();
        new ExitManager().loadDashboard();
    }

    public static void alertManagerInvailed(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Wrong Username or Password");
        alert.showAndWait();
    }

    public static void writeUsOrPas(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Write username and password");
        alert.showAndWait();

    }


    public static void bollText(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Invailed");
        alert.showAndWait();
    }
}
