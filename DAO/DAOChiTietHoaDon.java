package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import DTO.ChiTietHoaDon;
import DataBase.duLieu;

public class DAOChiTietHoaDon implements DAOInterFace<ChiTietHoaDon> {

    @Override
    public boolean insert(ChiTietHoaDon t) {
        String sql = "INSERT INTO ChiTietHoaDon(id_HD,id_SP,soLuong) VALUES (?,?,?)";
        try(Connection conn = duLieu.ket_noi()){
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setInt(1, t.getId_HD());
            ptm.setInt(2, t.getId_SP());
            ptm.setInt(3, t.getSoLuong());
            return ptm.executeUpdate()>0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(ChiTietHoaDon t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean update(ChiTietHoaDon t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<ChiTietHoaDon> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public ArrayList<ChiTietHoaDon> selectCondition(String condition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCondition'");
    }
    
}
