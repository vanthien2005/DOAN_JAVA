package BLL;

import java.util.ArrayList;

import DAO.DAOPhim;
import DTO.Phim;

public class PhimBLL {
    DAOPhim d = new DAOPhim();

    public ArrayList<Phim> getArrayList(){
        return d.selectAll();
    }

    public String deletePhim(Phim p){
        if(d.delete(p)){
            return "Xóa thành công";

        } else return "Xóa thất bại";
    }

    public String insertPhim(Phim p){
        if(d.insert(p)) return "Thêm thành công";
        else return "Thêm thất bại";
    }
    
    public String updatePhim(Phim p){
        if(d.update(p)) return "Sửa thành công ";
        else return  "Sửa thất bại";
    }

    public ArrayList<Phim>searchName(String condition){
        return d.selectCondition(condition);
    }
    public ArrayList<Phim>dsTheLoai(){
        return d.dsTheLoai();
    }
    public ArrayList<Phim> searchType(String type){
        return d.searchType(type);
    }
}
