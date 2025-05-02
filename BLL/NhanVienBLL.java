package BLL;

import java.util.ArrayList;

import DAO.DAONhanVien;
import DTO.NhanVien;

public class NhanVienBLL {
    DAONhanVien d = new DAONhanVien();
    public ArrayList<NhanVien> getArrayList(){
        return d.selectAll();
    }

    public String insertNhanVien(NhanVien n){
        if(d.insert(n)) return "thêm thành công";
        return "thêm thất bại";
    }

    public String deleteNhanVien(NhanVien n){
        if(d.delete(n)) return "xóa thành công" ;
        return "xóa thất bại";
    }
    public String updateNhanVien(NhanVien n){
        return d.update(n) ? "cập nhật thành công" : "cập nhật thất bại";
    }
    public ArrayList<NhanVien> searchName(String condition){
        return d.selectCondition(condition);
    }
}
