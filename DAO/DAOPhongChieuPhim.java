package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.PhongChieuPhim;

public class DAOPhongChieuPhim implements DAOInterFace<PhongChieuPhim> {
    public static DAOPhongChieuPhim getInstance(){
        return new DAOPhongChieuPhim();
    }
    public boolean examId_movies(int id_movies){
        String sql = "SELECT * FROM Phim WHERE id = ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id_movies);
            ResultSet rs = ptm.executeQuery();
            return rs.next();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean examId_Room(int id_room){
        String sql = "SELECT * FROM PhongChieu WHERE id = ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id_room);
            ResultSet rs = ptm.executeQuery();
            return rs.next();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean insert(PhongChieuPhim t) {
     String sql = "INSERT INTO PhongChieuPhim (room_id, movie_id) VALUES ( ?, ?)";
    try (Connection conn = duLieu.ket_noi()){

        PreparedStatement ptm = conn.prepareStatement(sql);
        ptm.setInt(1, t.getRoom_id());
        ptm.setInt(2, t.getMovie_id());
        
        return  ptm.executeUpdate()>0? true : false;

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    @Override
    public boolean delete(PhongChieuPhim t) {
        String sql = "DELETE FROM PhongChieu WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
            PreparedStatement ptm = conn.prepareStatement(sql)) {  
            ptm.setInt(1, t.getId()); 
            return ptm.executeUpdate()>1? true : false;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PhongChieuPhim t) {
        String sql = "UPDATE PhongChieu SET room_id=?,movie_id=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setInt(1, t.getRoom_id());
                ptm.setInt(2, t.getMovie_id()); 
                ptm.setInt(3, t.getId());
                return ptm.executeUpdate()>0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<PhongChieuPhim> selectAll() {
        ArrayList<PhongChieuPhim> ds = new ArrayList<>();
     String sql="SELECT * FROM PhongChieuPhim ";
     try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int room_id = rs.getInt(2);
                int movie_id = rs.getInt(3);
                PhongChieuPhim p = new PhongChieuPhim(id, room_id, movie_id);
                ds.add(p);
            }
            return ds;

     } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
     }
    }

    @Override
    public ArrayList<PhongChieuPhim> selectCondition(String condition) {
        return null;    
    }
    
}
