package DTO;

public class ChiTietHoaDon {
    private SanPham sanPham;
    private int soLuong;
    private int id_HD;
    private int id_sp;
    public int getId_sp() {
        return id_sp;
    }
    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }
    public int getId_HD() {
        return id_HD;
    }
    public void setId_HD(int id_HD) {
        this.id_HD = id_HD;
    }
    private int id_SP;
    public int getId_SP() {
        return id_SP;
    }
    public void setId_SP(int id_SP) {
        this.id_SP = id_SP;
    }
    public ChiTietHoaDon(int id_sp, int soLuong) {
        this.id_SP = id_sp;
        this.soLuong = soLuong;
    }
    public SanPham getSanPham() {
        return sanPham;
    }
    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
   
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
