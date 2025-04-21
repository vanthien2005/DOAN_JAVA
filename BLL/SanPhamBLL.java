package BLL;

import java.util.ArrayList;

import DAO.DAOSanPham;
import DTO.SanPham;

public class SanPhamBLL {
    DAOSanPham d = new DAOSanPham();

    public ArrayList<SanPham> getArrayList(){
        return d.selectAll();
    }

    public String insertSanPham(SanPham p){
        return d.insert(p) ? "Thêm thành công": "thêm thất bại";
    }

    public String deleteSanPham(SanPham p){
        return d.delete(p)? "Xóa thành công" : "Xóa thất bại";
    }

    public String updateSanPham(SanPham p){
        return d.update(p)? "Cập nhật thành công": "cập nhật thất bại";
    }
    public ArrayList<SanPham>searchName(String condition){
        return d.selectCondition(condition);
    }
}
