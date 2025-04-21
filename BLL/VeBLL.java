package BLL;

import java.util.ArrayList;

import DAO.DAOVe;
import DTO.LichChieu;
import DTO.Phim;
import DTO.PhongChieu;
import DTO.Ve;
import DTO.gheNgoi;

public class VeBLL {
    DAOVe d = new DAOVe();

   public ArrayList<Ve> getArrayList(){
        return d.selectAll();
    }

    public String insertVe(Ve v){
        if(d.insert(v)) return "thêm thành công";
        return "thêm thất bại";
    }
    public String deleteVe(Ve v){
        return d.delete(v) ? "xóa thành công" : "xóa thất bại";
    }

    public String updateVe(Ve v){
        if(d.update(v)) return "sửa thành công";
        return "sửa thất bại";
    }
    
    public ArrayList<Ve> searchStatus(String condition){
        return d.selectCondition(condition);
    }

    public ArrayList<Phim>dsPhim(){
        return d.dsPhim();
    }
    public ArrayList<LichChieu>dsLichChieu(int id){
        return d.dsLichChieu(id);
    }
    public ArrayList<gheNgoi>dsGheNgoi(int id){
        return d.dsGheNgoi(id);
    }
   
}
