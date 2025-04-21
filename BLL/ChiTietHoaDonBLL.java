package BLL;

import DAO.DAOChiTietHoaDon;
import DTO.ChiTietHoaDon;

public class ChiTietHoaDonBLL {
    DAOChiTietHoaDon d = new DAOChiTietHoaDon();

    public void insertCT(ChiTietHoaDon ct){
        d.insert(ct);
    }
}
