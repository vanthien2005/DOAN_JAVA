package DTO;

import java.time.LocalDate;

public class HoaDon {
    private int id;
    private int id_user;
    private LocalDate day;
    private String status;
    private int total;
    private String dssp;
    private String nameKhachHang;
    private String sdt ;
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getNameKhachHang() {
        return nameKhachHang;
    }
    public void setNameKhachHang(String nameKhachHang) {
        this.nameKhachHang = nameKhachHang;
    }
    public String getDssp() {
        return dssp;
    }
    public void setDssp(String dssp) {
        this.dssp = dssp;
    }
    public HoaDon(){}
    public HoaDon(int id_user, LocalDate day, String status, int total) {
        this.id_user = id_user;
        this.day = day;
        this.status = status;
        this.total = total;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public LocalDate getDay() {
        return day;
    }
    public void setDay(LocalDate day) {
        this.day = day;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
