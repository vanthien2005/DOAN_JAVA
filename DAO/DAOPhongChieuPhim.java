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

    @Override
    public void insert(PhongChieuPhim t) {
         String sql = "INSERT INTO PhongChieuPhim (id,room_id, movie_id) VALUES (?, ?, ?)";

    try (Connection conn = duLieu.ket_noi();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, t.getId()); 
        pstmt.setInt(2, t.getRoom_id());
        pstmt.setInt(3, t.getMovie_id());
        


        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Thêm chi tiết phòng chiếu phim thành công!");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }

    @Override
    public void delete(PhongChieuPhim t) {
        String sql = "DELETE FROM PhongChieu WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            pstmt.setInt(1, t.getId()); 
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("xóa chi tiết phòng chiếu phim thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PhongChieuPhim t) {
        String sql = "UPDATE PhongChieu SET id = ?,room_id=?,movie_id=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {                
                pstmt.setInt(1, t.getId());
                pstmt.setInt(2, t.getRoom_id());
                pstmt.setInt(3, t.getMovie_id()); 
                pstmt.setInt(4, t.getId());
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("xóa chi tiết phòng chiếu phim thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAll(ArrayList<PhongChieuPhim> ds) {
     String sql="SELECT * FROM PhongChieuPhim ";
     try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        if(rs!=null){
            while(rs.next()){
                int id = rs.getInt(1);
                int room_id = rs.getInt(2);
                int movie_id = rs.getInt(3);
                PhongChieuPhim p = new PhongChieuPhim(id, room_id, movie_id);
                ds.add(p);
            }
            for(PhongChieuPhim p :ds){
                p.inThongTin();
            }
        } else System.out.println("không có dữ liệu");

     } catch (Exception e) {
       
     }
    }

    @Override
    public void selectCondition( ArrayList<PhongChieuPhim> ds,String condition) {
       
    }
    
}
