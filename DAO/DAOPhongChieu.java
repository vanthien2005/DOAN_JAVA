package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.PhongChieu;

public class DAOPhongChieu implements DAOInterFace<PhongChieu> {
    public static DAOPhongChieu getInstance(){
        return new DAOPhongChieu();
    }

    @Override
    public void insert(PhongChieu t) {
        String sql = "INSERT INTO PhongChieu (id,name, capacity) VALUES (?, ?, ?)";

    try (Connection conn = duLieu.ket_noi();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, t.getID()); 
        pstmt.setString(2, t.getName());
        pstmt.setInt(3, t.getSoGhe());
        


        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Thêm phòng chiếu thành công!");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }

    @Override
    public void delete(PhongChieu t) {
        String sql = "DELETE FROM PhongChieu WHERE id = ?";

    try (Connection conn = duLieu.ket_noi();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {  
        pstmt.setInt(1, t.getID()); 
        


        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("xóa phòng chiếu thành công!");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void update(PhongChieu t) {
        String sql = "UPDATE PhongChieu SET id=?,name=? ,capacity=?,  emp_id = ? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
                pstmt.setInt(1, t.getID());               
                pstmt.setString(2, t.getName());
                pstmt.setInt(3, t.getSoGhe()); 
                pstmt.setInt(4, t.getEmp_id());
                pstmt.setInt(5, t.getID());  

            
    
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cập nhật phòng chiếu thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }



    @Override
    public void selectCondition( ArrayList<PhongChieu> ds,String condition) {
        String sql = "SELECT PhongChieu.id, PhongChieu.name, PhongChieu.capacity,PhongChieu.emp_id, Phim.name AS name_phim, NhanVien.name AS nameNhanVien " +
        "FROM PhongChieu " +
        "INNER JOIN PhongChieuPhim ON PhongChieuPhim.room_id = PhongChieu.id " +
        "INNER JOIN Phim ON Phim.id = PhongChieuPhim.movie_id " +
        "INNER JOIN NhanVien ON NhanVien.id = PhongChieu.emp_id ";
        
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
           ResultSet rs =  ptm.executeQuery();
           if(rs!=null){
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sucChua = rs.getInt("capacity");
                String name_phim = rs.getString("name_phim");
                int emp_id = rs.getInt("emp_id");
                PhongChieu p = new PhongChieu(id,name,sucChua,name_phim,emp_id);
                ds.add(p);
                p.inThongTin();
                System.out.println("Nhân viên phụ trách: "+rs.getString("nameNhanVien"));
                
            }
            
           } else System.out.println("không có dữ liệu");

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
    

    @Override
    public void selectAll(ArrayList<PhongChieu> ds) {
        String sql = "SELECT PhongChieu.id, PhongChieu.name, PhongChieu.capacity,PhongChieu.emp_id, Phim.name AS name_phim, NhanVien.name AS nameNhanVien " +
        "FROM PhongChieu " +
        "INNER JOIN PhongChieuPhim ON PhongChieuPhim.room_id = PhongChieu.id " +
        "INNER JOIN Phim ON Phim.id = PhongChieuPhim.movie_id " +
        "INNER JOIN NhanVien ON NhanVien.id = PhongChieu.emp_id ";
        
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
           ResultSet rs =  ptm.executeQuery();
           if(rs!=null){
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sucChua = rs.getInt("capacity");
                String name_phim = rs.getString("name_phim");
                int emp_id = rs.getInt("emp_id");
                PhongChieu p = new PhongChieu(id,name,sucChua,name_phim,emp_id);
                ds.add(p);
                p.inThongTin();
                System.out.println("Nhân viên phụ trách: "+rs.getString("nameNhanVien"));
                System.out.println("-----------------------------------");
                
            }
            
           } else System.out.println("không có dữ liệu");

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
}
    

