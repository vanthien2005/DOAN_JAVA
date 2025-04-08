package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.PhongChieuPhim;
import DTO.Ve;

public class DAOVe implements DAOInterFace<Ve> {
    public static DAOVe getInstance(){
        return new DAOVe();
    }
    @Override
    public void insert(Ve t) {
           String sql = "INSERT INTO Ve (user_id, seat_id,showTime_id,status,price) VALUES (?, ?, ?,?,?)";

    try (Connection conn = duLieu.ket_noi();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, t.getUser_id()); 
        pstmt.setInt(2, t.getSeat_id());
        pstmt.setInt(3, t.getShowTime_id());
        pstmt.setString(4, t.getStatus());
        pstmt.setInt(5, t.getPrice());

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Thêm chi tiết Vé thành công!");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }

    @Override
    public void delete(Ve t) {
        String sql = "DELETE FROM Ve WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            pstmt.setInt(1, t.getId()); 
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("xóa chi tiết vé phim thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ve t) {
        String sql = "UPDATE Ve SET id = ?,user_id = ?,seat_id = ?, showTime_id=?, status=?, price=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {                
                pstmt.setInt(1, t.getId());
                pstmt.setInt(2, t.getUser_id());
                pstmt.setInt(3, t.getSeat_id()); 
                pstmt.setInt(4, t.getShowTime_id());
                pstmt.setString(5, t.getStatus());
                pstmt.setInt(6,t.getPrice());
                pstmt.setInt(7, t.getId());
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("cập nhật chi tiết vé phim thành công!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAll(ArrayList<Ve> ds) {
        String sql = "SELECT Ve.id, Ve.user_id, Ve.seat_id, Ve.showTime_id, Ve.status , Ve.price ,LichChieu.room_id, LichChieu.movie_id, Phim.name AS nameMovie, LichChieu.time AS timeChieu,gheNgoi.seatNumber AS nameNumber, khachHang.name AS nameUser, khachHang.numberPhone AS numUser, PhongChieu.name AS roomName " +
        "FROM Ve " +
        "INNER JOIN khachHang  ON khachHang.id = Ve.user_id " +
        "INNER JOIN LichChieu ON LichChieu.id = Ve.showTime_id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "INNER JOIN Phim ON Phim.id = LichChieu.movie_id " +
        "INNER JOIN gheNgoi ON gheNgoi.id = Ve.seat_id ";
        try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        if(rs!=null){
            while(rs.next()){
                System.out.println("mã vé: "+ rs.getInt("id"));
                System.out.println("Tên khách hàng: "+rs.getString("nameUser"));
                System.out.println("Số điện thoại: "+rs.getString("numUser"));
                System.out.println("Phòng chiếu: "+rs.getString("roomName"));
                System.out.println("Tên Phim: "+rs.getString("nameMovie"));
                System.out.println("Thời gian: " + rs.getTimestamp("timeChieu"));
                System.out.println("Số ghế: "+rs.getString("nameNumber"));
                System.out.println("Giá vé: " + rs.getBigDecimal("price"));
                System.out.println("Trạng thái: "+rs.getString("status"));
                System.out.println("---------------------------------------------------------");
                
            }

    }
}catch(Exception e){
    System.out.println(e.getMessage());
}
    }

    @Override
    public void selectCondition(ArrayList<Ve> ds, String condition) {
        String sql = "SELECT Ve.id, Ve.user_id, Ve.seat_id, Ve.showTime_id, Ve.status , Ve.price ,LichChieu.room_id, LichChieu.movie_id, Phim.name AS nameMovie, LichChieu.time AS timeChieu,gheNgoi.seatNumber AS nameNumber, khachHang.name AS nameUser, khachHang.numberPhone AS numUser, PhongChieu.name AS roomName " +
        "FROM Ve " +
        "INNER JOIN khachHang  ON khachHang.id = Ve.user_id " +
        "INNER JOIN LichChieu ON LichChieu.id = Ve.showTime_id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "INNER JOIN Phim ON Phim.id = LichChieu.movie_id " +
        "INNER JOIN gheNgoi ON gheNgoi.id = Ve.seat_id " +
        "WHERE " + condition;
        try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        if(rs!=null){
            while(rs.next()){
                System.out.println("mã vé: "+ rs.getInt("id"));
                System.out.println("Tên khách hàng: "+rs.getString("nameUser"));
                System.out.println("Số điện thoại: "+rs.getString("numUser"));
                System.out.println("Phòng chiếu: "+rs.getString("roomName"));
                System.out.println("Tên Phim: "+rs.getString("nameMovie"));
                System.out.println("Thời gian: " + rs.getTimestamp("timeChieu"));
                System.out.println("Số ghế: "+rs.getString("nameNumber"));
                System.out.println("Giá vé: " + rs.getBigDecimal("price"));
                System.out.println("Trạng thái: "+rs.getString("status"));
                System.out.println("---------------------------------------------------------");
                
            }

    }
}catch(Exception e){
    System.out.println(e.getMessage());
}
    }
  
    
}
