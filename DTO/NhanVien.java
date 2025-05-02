package DTO;

public class NhanVien extends Nguoi {
    private String position;
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public NhanVien(String name, int age, String numberPhone, String email, String status, String position){
        super(name, age, numberPhone, email, status);
        this.position = position;

    }
    public NhanVien(int id,String name, int age, String numberPhone, String email, String status, String position){
        super(id,name, age, numberPhone, email, status);
        this.position = position;
    
}
    public NhanVien() {
        //TODO Auto-generated constructor stub
    }
}
