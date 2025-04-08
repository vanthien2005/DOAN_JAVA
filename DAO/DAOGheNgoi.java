package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.gheNgoi;

public class DAOGheNgoi implements DAOInterFace<gheNgoi> {
    public static DAOGheNgoi getInstance(){
        return new DAOGheNgoi();
    }

    @Override
    public void insert(gheNgoi t) {
        String sql="INSERT INTO gheNgoi(room_id , seatNumber,status) VALUES  (?,?,?) ";
        try(Connection con  = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, t.getRoom_id());
            ptm.setString(2, t.getNumber_seat());
            ptm.setString(3, t.getStatus());
            int i = ptm.executeUpdate();
            if(i>0){
                System.out.println("Thêm ghế ngồi thành công");
            }else{ 
                System.out.println("thêm ghế ngồi thất bại");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(gheNgoi t) {
         String sql = "DELETE FROM gheNgoi WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            pstmt.setInt(1, t.getId()); 
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("xóa ghế ngồi thành công thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(gheNgoi t) {
        String sql = "UPDATE gheNgoi SET id = ?,room_id=?, seatNumber=?, status=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {                
                pstmt.setInt(1, t.getId());
                pstmt.setInt(2, t.getRoom_id());
                pstmt.setString(3, t.getNumber_seat());
                pstmt.setString(4, t.getStatus()); 
                pstmt.setInt(5, t.getId());
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("xóa ghế ngồi thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAll(ArrayList<gheNgoi> ds) {
        String sql = "SELECT * FROM gheNgoi ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            if(rs==null){ System.out.println("không có dữ liệu"); return;}
            while(rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("seatNumber");
                String status = rs.getString("status");
                gheNgoi g = new gheNgoi(id,room_id, name, status);
                ds.add(g);
                g.inThongTin();
                
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void selectCondition(ArrayList<gheNgoi> ds, String condition) {
        String sql = "SELECT * FROM gheNgoi " +
        "WHERE " + condition;
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            if(rs==null){ System.out.println("không có dữ liệu"); return;}
            while(rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("seatNumber");
                String status = rs.getString("status");
                gheNgoi g = new gheNgoi(id,room_id, name, status);
                ds.add(g);
                g.inThongTin();
                
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
   
    
}
