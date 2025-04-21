package BLL;

import java.util.ArrayList;

import DAO.DAOKhachHang;
import DTO.Nguoi;

public class KhachHangBLL {
    DAOKhachHang d = new DAOKhachHang();


   public ArrayList<Nguoi> geArrayList(){
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

}
