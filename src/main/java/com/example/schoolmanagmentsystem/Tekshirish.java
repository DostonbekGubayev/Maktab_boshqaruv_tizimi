package com.example.schoolmanagmentsystem;

import javafx.scene.control.Alert;

public class Tekshirish {
    private   Dashboard dashboard;
    public Tekshirish(Dashboard dashboard){
        this.dashboard=dashboard;
    }

    public boolean Check(){
        if (dashboard.id.getText().isEmpty()){
            return true;
        } else if (dashboard.surname.getText().isEmpty()) {
           return true;
        }else if (dashboard.given.getText().isEmpty()){
            return true;
        }else if (dashboard.gender.getSelectionModel().isEmpty()){
            return true;
        }else
            return false;
    }

    public static void wrongAlertType(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
        alert.setContentText("Please enter Student Data");
        alert.showAndWait();
    }

    public static void updateSuccAlertType(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("INFORATION MESSAGE!");
        alert.setHeaderText(null);
        alert.setContentText("Successfully updated");
        alert.showAndWait();
    }
    public static void insertSuccAlertType(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION MESSAGE!");
        alert.setHeaderText(null);
        alert.setContentText("Successfully inserted Student Data");
        alert.showAndWait();
    }
    public static void deletSuccAlertType(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
        alert.setContentText("Please enter Student Data");
        alert.showAndWait();
    }

    public static void selectStudentRecord(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Select the Student Data!");
        alert.setTitle("Error Message");
        alert.showAndWait();
    }
}
