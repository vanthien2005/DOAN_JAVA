package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.khachHang;

public class DAOKhachHang implements DAOInterFace<khachHang> {
    public static DAOKhachHang getInstance(){
        return new DAOKhachHang();
    }
    @Override
    public void insert(khachHang t) {
        String sql = "INSERT INTO khachHang (id,name,age,numberPhone,email,status) VALUES (?,?,?,?,?,?)";
        try(Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setInt(1,t.getId());
            ptm.setString(2, t.getName());
            ptm.setInt(3, t.getAge());
            ptm.setString(4,t.getNumberPhone());
            ptm.setString(5, t.getEmail());
            ptm.setString(6, t.getStatus());
            int row = ptm.executeUpdate();
            if(row>0){
                System.err.println("thêm dữ liệu thành công");
            }
        } catch (Exception e) {
           
        }
    }

    @Override
    public void delete(khachHang t) {
        String sql = "DELETE FROM khachHang WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            pstmt.setInt(1, t.getId()); 
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("xóa khách hàng thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(khachHang t) {
        String sql = "UPDATE khachHang SET id = ?,name = ?,age = ?, numberPhone=?, email=?, status=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {                
                pstmt.setInt(1, t.getId());
                pstmt.setString(2, t.getName());
                pstmt.setInt(3, t.getAge()); 
                pstmt.setString(4, t.getNumberPhone());
                pstmt.setString(5, t.getEmail());
                pstmt.setString(6,t.getStatus());
                pstmt.setInt(7, t.getId());
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("cập nhật khách hàng thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAll(ArrayList<khachHang> ds) {
        String sql = "SELECT * FROM khachHang";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int age = rs.getInt(3);
                    String numberPhone = rs.getString(4);
                    String email = rs.getString(5);
                    String status = rs.getString(6);
                    khachHang k = new khachHang(id,name, age, numberPhone, email, status);
                    ds.add(k);                  
                }

            }
                
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void selectCondition(ArrayList<khachHang> ds, String condition) {
     
    }
    
}
