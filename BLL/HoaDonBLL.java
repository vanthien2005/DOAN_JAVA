package BLL;

import java.sql.Date;
import java.util.ArrayList;

import DAO.DAOHoaDon;
import DTO.HoaDon;

public class HoaDonBLL {
    DAOHoaDon d = new DAOHoaDon();

    public ArrayList<HoaDon> geArrayList(){
        return d.selectAll();
    }

    public String insertHoaDon(HoaDon h){
        return d.insert(h)? "thêm thành công": "thêm thất bại";
    }
    public int insertHoaDonGetId(HoaDon h){
        return d.insertHoaDonVaLayId(h) ;
    }

    public String deleteHoaDon(HoaDon h){
        return d.delete(h)? "Xóa thành công" : "Xóa thất bại";
    }

    public String updateHoaDon(HoaDon h){
        return d.update(h)? "cập nhật thành công":"Cập nhật thất bại";
    }
    public ArrayList<HoaDon>searchStatus(String condition){
        return d.selectCondition(condition);
    }

    public ArrayList<HoaDon> searchDate(Date day){
        return d.selectDay(day);
        
    }
}
