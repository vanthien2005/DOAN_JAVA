package DTO;

import java.util.Scanner;

public class PhongChieu {
    private int id;
    private String name;
    private int soGhe;
    private String name_phim;
    private int emp_id;
    private String empName;
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public int getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
    Scanner sc = new Scanner(System.in);
    public PhongChieu()
    {

    }
    public PhongChieu(int id,String n,int soghe,String name_phim,int emp_id)
    {
        this.id = id;
        name = n;
        soGhe = soghe;
        this.name_phim = name_phim;
        this.emp_id = emp_id;

     
    }
    public PhongChieu(String n,int soghe,String name_phim,int emp_id)
    {
        name = n;
        soGhe = soghe;
        this.name_phim = name_phim;
        this.emp_id = emp_id;

     
    }
    public void setID(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    }
    public void setSoGhe(int cho)
    {
        soGhe = cho;
    }
    public int getSoGhe(){
        return soGhe;
    }
    public void setName(String ten){
        name = ten;
    }
    public String getName()
    {
        return name;
    }
    public void inThongTin(){
        System.out.println("ID phong: "+id);
        System.out.println("Ten Phong: "+name);
        System.out.println("So cho ngoi: "+soGhe);
        System.out.println("Tên phim: "+name_phim);
      
        
    }
    
}