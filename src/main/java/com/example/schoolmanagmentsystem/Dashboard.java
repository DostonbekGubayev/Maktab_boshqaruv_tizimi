package com.example.schoolmanagmentsystem;

import data.Data;
import data.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import total.TotalStudentClass;

import java.io.File;
import java.lang.reflect.Executable;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Dashboard implements Initializable {
    @FXML
    private Button data,student,record,home,exit,
            lineChartButton,barChartButton,areaChartButton,showChartButton;
    @FXML
    private AnchorPane homePane,studentPane,recordPane,dataPane,navBar,nav_chart_bar;

    @FXML
    private Label user,filePath;
    @FXML
    private Button clearDate,deleteData,insertData,printData,updateData,insertImage;
    @FXML
    TableView<Data>tableView;
    @FXML
    public ComboBox<String> gender;
    @FXML
    private TableColumn<Data, Integer> columnID;
    @FXML
    private TableColumn<Data, String> columnGender;

    @FXML
    private TableColumn<Data, String> columnGiven;
    @FXML
    private TableColumn<Data, String> columnImage;

    @FXML
    private TableColumn<Data, String> columnSurename;
    @FXML
     public TextField id,surname,given,image;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<Data> sr_table_view;
    @FXML
    private TableColumn<Data, String> col_sr_image;
    @FXML
    private TableColumn<Data, String> col_sr_gender;
    @FXML
    private TableColumn<Data, String> col_sr_given;
    @FXML
    private TableColumn<Data, Integer> col_sr_id;
    @FXML
    private TableColumn<Data, String> col_sr_surname;
    @FXML
    private TableColumn<Data, String> col_sr_current;
    @FXML
    private ComboBox sr_current;
    @FXML
    private ImageView sr_image_view;
    @FXML
    private Button sr_clear,sr_update;

    @FXML
    private Label sr_id;
    @FXML
    private Label total_enrolled,total_graduated,total_inactive,total_student;
    @FXML
    public Pane studentCard,enrollCard,totalCard,inactiveCard;
    @FXML
    LineChart<String,Integer> lineChart;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private AreaChart<String, Integer> areaChart;
    @FXML
    private AnchorPane lineChartPage,areaChartPage,barChartPage;








    Image image1;
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;

     private ResultSet resultSet;

     HelloController controller;

    public Dashboard() {
        connection=new DataBaseConnection().connectDb();
    }


    public void account(){
        user.setText(User.username);
    }



//Menudagi tugmalarni harakatini nazorat qilish metodi
    public void actionDashboard(ActionEvent event){
        if (event.getSource().equals(home)){

            homePane.setVisible(true);
            studentPane.setVisible(false);
            recordPane.setVisible(false);
            dataPane.setVisible(false);

            nav_chart_bar.setVisible(false);

            totalStudent();
            totalGraduated();
            totalEnrolled();
            totalInactive();
        }else if (event.getSource().equals(student)){

            studentPane.setVisible(true);
            homePane.setVisible(false);
            recordPane.setVisible(false);
            dataPane.setVisible(false);

            nav_chart_bar.setVisible(false);
            showData();
            showStudentRecord();

        }else if (event.getSource().equals(record)){

            recordPane.setVisible(true);
            studentPane.setVisible(false);
            homePane.setVisible(false);
            dataPane.setVisible(false);

            nav_chart_bar.setVisible(false);
            showStudentRecord();
            showData();
        }else if (event.getSource().equals(data)){

            dataPane.setVisible(true);
            studentPane.setVisible(false);
            homePane.setVisible(false);
            recordPane.setVisible(false);

            nav_chart_bar.setVisible(true);
        }
    }

    //malumotlar omboridan malumotlarni jadvalarga o'zlashtirish metodi---
    public ObservableList<Data> listData(){
        ObservableList<Data>dataList= FXCollections.observableArrayList();

        String sql="SELECT * FROM student";


        try {
            statement=connection.prepareStatement(sql);

            resultSet=statement.executeQuery(sql);

            Data data;

            while (resultSet.next()){
                data=new Data(resultSet.getInt("id"),
                        resultSet.getString("surname"),
                        resultSet.getString("given"),
                        resultSet.getString("gender"),
                        resultSet.getString("image")
                        );
                dataList.addAll(data);
            }

        }catch (Exception e){
            e.printStackTrace();

        }

        return dataList;

    }

    //Talabalar rasmlarini joylashtirish metodi
    public void insertImageData(ActionEvent event){
        FileChooser open=new FileChooser();
        Stage stage=(Stage)navBar.getScene().getWindow();
        File file=open.showOpenDialog(stage);

        if (file !=null){
            System.out.println("Iamge Path"+file.getAbsolutePath());
            filePath.setText(file.getAbsolutePath());
           image1=new Image(file.toURI().toString(),110, 110, false,true);
            imageView.setImage(image1);
            insertImage.setText("");
        }else{
            System.out.println("Rasm topilmadi");
        }
    }


//Ma'lumotlar omboridagi ma'lumotlarni jadvalda ko'rsatish metodi--
    public void showData(){
        ObservableList<Data> show=listData();

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSurename.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnGiven.setCellValueFactory(new PropertyValueFactory<>("given"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        tableView.setItems(show);
    }

    //Talabarni genderini belgilash uchun combobox elementlari
    private String[]genderData={"Male","Famale","Others"};
    private String[]current={"Enrolled","Graduated","Inactive"};
    public void comboBox(){
        List<String> list=new ArrayList<String>(Arrays.asList(genderData));
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(current));
        sr_current.getItems().addAll(arrayList);
        gender.getItems().addAll(list);
    }




    //Ma'lumotlar omboriga ma'lumotlar joylashtirish metodi
    public void insertDataAction(ActionEvent event) {

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        String dateFormat=format.format(date);

        String sql="INSERT INTO student(ID,surname,given,gender, image,current, date)  VALUES (?,?,?,?,?,?,?)";

            if (!new Tekshirish(this).Check()) {
                try {
                    String file = filePath.getText().replace("\\", "\\\\\\");
                    prepare = connection.prepareStatement(sql);
                    prepare.setString(1, id.getText());
                    prepare.setString(2, surname.getText());
                    prepare.setString(3, given.getText());
                    prepare.setString(4, gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, file);
                    prepare.setString(6, "");
                    prepare.setString(7, dateFormat);

                    prepare.executeUpdate();

                    showData();
                    clear();
            Tekshirish.insertSuccAlertType();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                    Tekshirish.wrongAlertType();
                }
    }


    //Joylashtirish maydonlarini tozalash
    public void clear(){
        id.setText("");
        surname.setText("");
        given.setText("");
        gender.getSelectionModel().clearSelection();
        filePath.setText("");
        imageView.setImage(null);
        insertImage.setText("Insert Image");
    }

    //Jadvaldan ma'lumotlarni belgilash metodi
    public void selectionData(MouseEvent event){
        int num =tableView.getSelectionModel().getSelectedIndex();
      Data data1=  tableView.getSelectionModel().getSelectedItem();

            id.setText(String.valueOf(data1.getId()));
            surname.setText(data1.getSurname());
            given.setText(data1.getGiven());
            gender.getSelectionModel().clearSelection();
            gender.setPromptText("Choose...");
            filePath.setText(data1.getImage());
            image1=new Image("file:"+data1.getImage(),110,110,false,true);
            imageView.setImage(image1);
            insertImage.setText("");
        }


        //Javaldagi ma'lumotlarni yangilash
        public void update(ActionEvent event){
        connection=new DataBaseConnection().connectDb();
       String file= filePath.getText().replace("\\","\\\\") ;
       String sql="UPDATE student SET  surname = '"+surname.getText()
               +"' , given = '"+given.getText()
               +"' , gender = '"+gender.getSelectionModel().getSelectedItem()
               +"' , image = '"+file
               +"' WHERE ID = '"+id.getText() + "'";

                if (!new Tekshirish(this).Check()){
                    Tekshirish.updateSuccAlertType();
                    try {
                        statement=connection.prepareStatement(sql);
                        statement.executeUpdate(sql);
                        showData();
                        clear();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    Tekshirish.wrongAlertType();
                }
        }


        //Jadvaldagi ma'lumotlarni o'chirish
        public void delete(){

            connection=new DataBaseConnection().connectDb();
            String sql="DELETE FROM student WHERE ID = "+id.getText()+"";
            try {

                if(id.getText().isEmpty()){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter your student ID");
                    alert.showAndWait();

                }else {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Confirom messae");
                    alert.setContentText("Are you want Delete?");
                    Optional<ButtonType> option=alert.showAndWait();
                    if (option.get()!=ButtonType.OK){
                        return;
                    }else{
                        statement = connection.createStatement();
                        statement.executeUpdate(sql);
                    }

                    showData();
                    clear();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


//Student Record

        public ObservableList<Data> StudentRecordData(){
        ObservableList listData=FXCollections.observableArrayList();
        String sql="SELECT * FROM student";

        try {

            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);

            Data containData;

            while (resultSet.next()){
                containData=new Data(resultSet.getInt("id"),
                        resultSet.getString("surname"),
                        resultSet.getString("given"),
                        resultSet.getString("gender"),
                        resultSet.getString("current"),
                        resultSet.getString("image"));

                listData.addAll(containData);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
        }

//Student recordlarini jadvalda ko'rsatish
        public void showStudentRecord(){
        ObservableList<Data> studentRecord=StudentRecordData();
        String sql="";
        col_sr_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_sr_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            col_sr_given.setCellValueFactory(new PropertyValueFactory<>("given"));
            col_sr_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            col_sr_current.setCellValueFactory(new PropertyValueFactory<>("current"));
            col_sr_image.setCellValueFactory(new PropertyValueFactory<>("image"));

        sr_table_view.setItems(studentRecord);

        }

        public void clearStudentRecord(){
        sr_id.setText("");
        sr_current.getSelectionModel().clearSelection();
        sr_image_view.setImage(null);

        }

        //Student recordlarini yangilash--
    public void updateStudentRecord(){
        String sql="UPDATE student SET current='"+
                sr_current.getSelectionModel().getSelectedItem()+
                "' WHERE ID='"+sr_id.getText()+"'";
        try {

            if (sr_current.getSelectionModel().isEmpty()||sr_id.getText().isEmpty()){
                Tekshirish.selectStudentRecord();
            }
            else {
                statement = connection.createStatement();
                statement.executeUpdate(sql);

                showStudentRecord();
                clearStudentRecord();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


        public void selectStudentRecord(){
        int index=sr_table_view.getSelectionModel().getSelectedIndex();
        Data containData=sr_table_view.getSelectionModel().getSelectedItem();
        sr_id.setText(String.valueOf(containData.getId()));
        sr_current.getSelectionModel().clearSelection();

        String filePath=containData.getImage();

        image1=new Image(filePath,110,110,false,true);
        sr_image_view.setImage(image1);

        }

        //Total student metod
        public void totalStudent(){
            String sql="SELECT count(surname) FROM student WHERE current !=''";
            try {

                prepare=connection.prepareStatement(sql);
                resultSet=prepare.executeQuery();

                while (resultSet.next()){
                    String totalStudent=resultSet.getString("count(surname)");
                    total_student.setText(totalStudent);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        //Total graduated metod
    public void totalGraduated(){
        String sql="SELECT count(surname) FROM student WHERE current='Graduated'";

        try {
            prepare=connection.prepareStatement(sql);
            resultSet=prepare.executeQuery();

            while (resultSet.next()){

                String totalGraduated=resultSet.getString("count(surname)");
                total_graduated.setText(totalGraduated);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void totalEnrolled(){
        String sql="SELECT count(surname) FROM student WHERE current='Enrolled'";
        try {
            prepare=connection.prepareStatement(sql);
            resultSet=prepare.executeQuery();

            while (resultSet.next()){

                String totalEnrolled=resultSet.getString("count(surname)");
                total_enrolled.setText(totalEnrolled);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void totalInactive(){
        String sql="SELECT count(surname) FROM student WHERE current='Inactive'";
        try {
            prepare=connection.prepareStatement(sql);
            resultSet=prepare.executeQuery();

            while (resultSet.next()){

                String totalInactive=resultSet.getString("count(surname)");
                total_inactive.setText(totalInactive);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void navigationChartButton(){

        if(lineChartButton.isFocused()){
            lineChartPage.setVisible(true);
            barChartPage.setVisible(false);
            areaChartPage.setVisible(false);

        }else if (barChartButton.isFocused()){

            barChartPage.setVisible(true);
            lineChartPage.setVisible(false);
            areaChartPage.setVisible(false);

        }else if (areaChartButton.isFocused()){
            areaChartPage.setVisible(true);
            barChartPage.setVisible(false);
            lineChartPage.setVisible(false);


        }

    }

    //-----------------------------------------------------------------------
    //Data Analiysis

        public void showChart(){

        String sql="SELECT count(surname),date FROM student WHERE current !='' ORDER BY talaba.student.date DESC";
            XYChart.Series<String , Integer> chart=new XYChart.Series<>();
            try {
                prepare=connection.prepareStatement(sql);
                resultSet= prepare.executeQuery();

                while (resultSet.next()){

                    Integer count=Integer.parseInt(resultSet.getString("count(surname)"));
                    chart.getData().add(new XYChart.Data<String,Integer>(resultSet.getString("date"),count ));


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            lineChart.getData().add(chart);
        }




    //Initilize
    public void  initialize(URL location, ResourceBundle resources) {
        exit.setOnAction(event -> ExitManager.exit());
        //Account qiymatini oluvchi
        account();
        //Ma'lumotlarni jadvalda ko'rsatuvchi
        showData();
        //ComboBox elementlari
        comboBox();
        //Student recordlarini korish
        showStudentRecord();
       // updateStudentRecord();
        totalStudent();
        totalGraduated();
        totalEnrolled();
        totalInactive();

        studentCard.setOnMouseEntered(event->{
            studentCard.setEffect(TotalStudentClass.DropShadowEffect());

        });
        studentCard.setOnMouseExited(e->studentCard.setEffect(null));
    }
}
