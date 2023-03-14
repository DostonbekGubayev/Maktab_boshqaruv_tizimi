package com.example.schoolmanagmentsystem;

import data.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    AnchorPane loginFrom,signupFrom;
    @FXML
    private Hyperlink creatAcc,loginAcc;

    @FXML
   public TextField emailSIgnUp,username,usernameSignUp;
    public PasswordField passwordSignUp,password;
    @FXML
    public Button loginBtn;
    @FXML
    public Button signupBtn;
    @FXML
    Button exit;
    @FXML
    Button exit1;

    @FXML
    private Label m,m1;

        @FXML
    public void textFieldDesign(){
        if (username.isFocused()){
           // username.setStyle("-fx-background-color:#fff" + "-fx-border-width:2px");

            //password.setStyle("-fx-background-color:transparent" + "-fx-border-width:1px");

        }else if (password.isFocused()){
          //  password.setStyle("-fx-background-color:#fff"+ "-fx-border-width:2px");

//        username.setStyle("-fx-background-color:transparent" + "-fx-border-width:1px");

        }
    }

    public void textFieldDesign1(){
        if (usernameSignUp.isFocused()){
            usernameSignUp.setStyle("-fx-background-color : #fff" + "-fx-border-width : 2px");

           // passwordSignUp.setStyle("-fx-background-color : transparent" + "-fx-border-width : 1px");
           emailSIgnUp.setStyle("-fx-background-color : transparent"  +  "-fx-border-width : 1px");

        }else if (passwordSignUp.isFocused()){
          //  passwordSignUp.setStyle("-fx-background-color : #fff" + "-fx-border-width : 2px");
            emailSIgnUp.setStyle("-fx-background-color : transparent" + "-fx-border-width : 1px");
            usernameSignUp.setStyle("-fx-background-color : transparent" + "-fx-border-width : 1px");

        }else if (emailSIgnUp.isFocused()){
            usernameSignUp.setStyle("-fx-background-color : #fff" + "-fx-border-width : 2px");

          //  passwordSignUp.setStyle("-fx-background-color : transparent" + "-fx-border-width : 1px");
            emailSIgnUp.setStyle("-fx-background-color : transparent" + "-fx-border-width : 1px");

        }
    }

    public void dropShadowEffect(){

            DropShadow orginal=new DropShadow(40,Color.valueOf("#ae44a5"));
            m.setEffect(orginal);
            m.setOnMouseEntered(event -> {
                DropShadow shadow=new DropShadow(70, Color.valueOf("#ae44a5"));
                shadow.setRadius(30);
                shadow.setInput(new Reflection());
                m.setEffect(shadow);
                m.setCursor(Cursor.HAND);

                m.setStyle("-fx-text-fill:#fff");
            });
            m.setOnMouseExited(e-> {
                m.setEffect(orginal);
                m.setStyle("-fx-text-fill:#000");
            });
        m.setEffect(orginal);

        m1.setOnMouseEntered(event -> {
            DropShadow shadow=new DropShadow(70, Color.valueOf("#ae44a5"));
            shadow.setRadius(30);
            shadow.setInput(new Reflection());
            m1.setEffect(shadow);
            m1.setCursor(Cursor.HAND);

            m1.setStyle("-fx-text-fill: #ffaaaa");
        });
        m1.setOnMouseExited(e-> {
            m1.setEffect(orginal);
            m1.setStyle("-fx-text-fill: #000");
        });
    }

    public void changeFrom(ActionEvent event){
            if (event.getSource().equals(creatAcc)){

                signupFrom.setVisible(true);
                loginFrom.setVisible(false);
                username.setText("");
                password.setText("");
            }else if (event.getSource().equals(loginAcc)){
                loginFrom.setVisible(true);
                signupFrom.setVisible(false);
                usernameSignUp.setText("");
                passwordSignUp.setText("");
                emailSIgnUp.setText("");
            }
    }


    public void action(ActionEvent event){
        if (event.getSource().equals(loginBtn)){
            new DataBase(this).login(event);
        }else if (event.getSource().equals(signupBtn)){
            new DataBase(this).signup(event);
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropShadowEffect();
        exit.setOnMouseClicked(event -> ExitManager.exit());
        exit1.setOnMouseClicked(event -> ExitManager.exit());
        loginBtn.setOnAction(this::action);
        signupBtn.setOnAction(this::action);
    }
}