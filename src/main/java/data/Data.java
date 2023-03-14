package data;

public class Data {
    private  Integer id;
    private  String surname;
    private  String given;
    private  String gender;
    private  String image;
    private String current;
    private String date;

    public Data(Integer id, String surname, String given, String gender, String image){
        this.id= id;
        this.surname = surname;
        this.given=given;
        this.gender=gender;
        this.image=image;
    }


    public Data(Integer id, String surname, String given, String gender,String current,String image) {
        this.id = id;
        this.surname = surname;
        this.given = given;
        this.gender = gender;
        this.current=current;
        this.image=image;
    }



    public Integer getId() {
        return id;
    }

    public  String getSurname() {
        return surname;
    }

    public  String getGiven() {
        return given;
    }

    public  String getGender() {
        return gender;
    }

    public  String getImage() {
        return image;
    }

    public String getCurrent() {
        return current;
    }

    public String getDate() {
        return date;
    }
}
