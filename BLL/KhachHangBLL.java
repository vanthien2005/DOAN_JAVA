package BLL;

import java.util.ArrayList;

import DAO.DAOKhachHang;
import DTO.Nguoi;
import DTO.SanPham;
import DTO.Ve;

public class KhachHangBLL {
    DAOKhachHang d = new DAOKhachHang();


   public ArrayList<Nguoi> getArrayList(){
        return d.selectAll();
    }

    public String insertKhachHang(Nguoi n){
        if(d.exam_id(n.getId())) return "ID đã tồn tại";
        return d.insert(n)? "thêm thành công" : "thêm thất bại";
    }

    public String deleteKhachHang(Nguoi n){
        return d.delete(n) ? "xóa thành công" : "xóa thất bại";
    }
    public String updateKhachHang(Nguoi n){
        return d.update(n) ? "cập nhật thành công" : "cập nhật thất bại";
    }
    public ArrayList<Nguoi> searchName(String condition){
        return d.selectCondition(condition);
    }
    public ArrayList<SanPham>dsSanPhamDaMua(int id){
        return d.dsSanPhamDaMua(id);
    }
    public ArrayList<Ve>dsVeDaDat(int id){
        return d.danhSachVeDaDat(id);
    }
}
