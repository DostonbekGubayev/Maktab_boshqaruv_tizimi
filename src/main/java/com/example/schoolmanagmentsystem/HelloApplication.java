package com.example.schoolmanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    private double x=0;
    private double y=0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = new FXMLLoader(HelloApplication.class.getResource("login-signup.fxml")).load();

        parent.setOnMousePressed(event -> {

            x=event.getSceneX();
            y=event.getSceneY();

        });

        parent.setOnMouseDragged(e->{

            stage.setX(e.getScreenX()-x);
            stage.setY(e.getScreenY()-y);

        });

        Scene scene = new Scene(parent);
        stage.setTitle("School Management");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(true);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}