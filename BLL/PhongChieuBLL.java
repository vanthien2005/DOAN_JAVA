package BLL;

import java.util.ArrayList;

import DAO.DAOPhongChieu;
import DTO.PhongChieu;

public class PhongChieuBLL {
    DAOPhongChieu d= new DAOPhongChieu();
    public ArrayList<PhongChieu> getArrayList(){
        return d.selectAll();
    }

    public String insertPhongChieu(PhongChieu p){
        if(!d.examId_emp(p.getEmp_id())) return "không tồn tại nhân viên";
        if(d.examId(p.getID())) return "id đã tồn tại";
        if(d.insert(p)) return "thêm thành công";
        else return "thêm thất bại";
    }

    public String deletePhongChieu(PhongChieu p){
        if(d.delete(p)) return "xóa thành công";
        else return "xóa thất bại";
    }

    public String updatePhongChieu(PhongChieu p){
        if(d.update(p)) return "cập nhật thành công";
        else return "cập nhật thất bại";
    }
    public ArrayList<PhongChieu> searchName(String condition){
        return d.selectCondition(condition);
    }

}
