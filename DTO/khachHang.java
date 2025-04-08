package DTO;

public class khachHang {
    private int id;
    private String name;
    private int age;
    private String numberPhone;
    private String email;
    private String status;
    public khachHang(String name, int age, String numberPhone, String email, String status) {
        this.name = name;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
        this.status = status;
    }
    public khachHang(int id,String name, int age, String numberPhone, String email, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
        this.status = status;
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
