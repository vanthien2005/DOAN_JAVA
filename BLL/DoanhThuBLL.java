package BLL;

import java.sql.Date;
import java.util.ArrayList;

import DAO.DAODoanhThu;
import DTO.DoanhThu;

public class DoanhThuBLL {
    DAODoanhThu d = new DAODoanhThu();

    public ArrayList<DoanhThu>getDoanhThuSP(Date from , Date to){
        return d.doanhThuSP(from, to);
    }
    public ArrayList<DoanhThu>getDoanhThuPhim(Date from, Date to){
        return d.doanhThuPhim(from, to);
    }
}
