package BLL;

import java.util.ArrayList;

import DAO.DAOLichChieu;
import DTO.LichChieu;
import DTO.Phim;
import DTO.PhongChieu;

public class LichChieuBLL {
    DAOLichChieu d = new DAOLichChieu();
    public ArrayList<LichChieu> getArrayList(){
        return d.selectAll();
    }

    public String insertLichChieu(LichChieu l){
        if(d.examId(l.getId())) return "ID lịch chiếu đã tồn tại";
        if(d.insert(l)) return "thêm thành công";
        else return "thêm thất bại";
    }

    public String deleteLichChieu(LichChieu l){
        if(d.delete(l)) return "xóa thành công";
        return "xóa thất bại";
    }
    
    public String updateLichChieu(LichChieu l){
        if(d.update(l)) return "cập nhật thành công";
        return "cập nhật thất bại";
    }
    public ArrayList<LichChieu> searchNamePhim(String condition){
        return d.selectCondition(condition);

    }
    public ArrayList<Phim> dsPhim(){
        return d.dsPhim();
    }
     public ArrayList<PhongChieu>dsPhongChieu(){
        return d.dsPhongChieu();
     }
}
