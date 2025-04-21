package DTO;

public class Nguoi {
    private int id;
    private String name;
    private int age;
    private String numberPhone;
    private String email;
    private String status;
    public Nguoi(String name, int age, String numberPhone, String email, String status) {
        this.name = name;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
        this.status = status;
    }
    public Nguoi(int id,String name, int age, String numberPhone, String email, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
        this.status = status;
    }
    public Nguoi() {
        //TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNumberPhone() {
        return numberPhone;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
