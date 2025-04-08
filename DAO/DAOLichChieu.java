package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.LichChieu;

public class DAOLichChieu implements DAOInterFace<LichChieu> {
    public static DAOLichChieu getInstance(){
        return new DAOLichChieu();
    }

    @Override
    public void insert(LichChieu t) {
    String sql = "INSERT INTO LichChieu (id, movie_id, room_id, date, time) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = duLieu.ket_noi();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, t.getId());  // Nếu id là số nguyên
        pstmt.setInt(2, t.getIdPhim());
        pstmt.setInt(3, t.getIdPhong());
        pstmt.setDate(4, java.sql.Date.valueOf(t.getTg()));  // Nếu tg là LocalDate
        pstmt.setTime(5, java.sql.Time.valueOf(t.getTime())); // Nếu time là LocalTime

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Thêm lịch chiếu thành công!");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    @Override
    public void delete(LichChieu t) {
        try (Connection conn = duLieu.ket_noi()) {
            String query = "DELETE FROM LichChieu WHERE id = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, t.getId()); // Gán giá trị vào dấu ?
            
            // Thực thi truy vấn
            int rowsAffected = pstmt.executeUpdate();
            
            // Kiểm tra xem có dòng nào bị xóa không
            if (rowsAffected > 0) {
                System.out.println("Xóa thành công lịch chiếu có ID: " + t.getId());
            } else {
                System.out.println("Không tìm thấy lịch chiếu có ID: " + t.getId());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa lịch chiếu: " + e.getMessage());
        }
    }

    @Override
    public void update(LichChieu t) {
        try (Connection conn = duLieu.ket_noi()) {
            // Câu SQL đúng
            String query = "UPDATE LichChieu SET movie_id = ?, room_id = ?, date = ?, time = ? WHERE id = ?";
    
            // Dùng PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, t.getIdPhim());         // Gán giá trị movie_id
            pstmt.setInt(2, t.getIdPhong());        // Gán giá trị room_id
            pstmt.setDate(3, java.sql.Date.valueOf(t.getTg()));  // Chuyển LocalDate thành java.sql.Date
            pstmt.setTime(4, java.sql.Time.valueOf(t.getTime())); // Chuyển LocalTime thành java.sql.Time
            pstmt.setInt(5, t.getId());             // Điều kiện WHERE id = ?
    
            // Thực thi truy vấn
            int rowsAffected = pstmt.executeUpdate();
    
            // Kiểm tra xem có dòng nào bị cập nhật không
            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công lịch chiếu có ID: " + t.getId());
            } else {
                System.out.println("Không tìm thấy lịch chiếu có ID: " + t.getId());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật lịch chiếu: " + e.getMessage());
        }
    
}

  

    @Override
    public void selectCondition(ArrayList<LichChieu> ds,String condition) {
        String sql = "SELECT l.id, l.movie_id, l.room_id, l.date , l.time , PhongChieu.name AS roomName , Phim.name AS movieName " +
        "FROM LichChieu l " +
        "JOIN PhongChieu ON PhongChieu.id = l.room_id " +
        "JOIN Phim ON Phim.id = l.movie_id " +
        "WHERE " + condition;
 
        try(Connection con = duLieu.ket_noi()) {
        PreparedStatement ptm = con.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        if(rs!=null){
         while ((rs.next())) {
             int id = rs.getInt(1);
             int movie_id = rs.getInt(2);
             int room_id = rs.getInt(3);
             LocalDate date = rs.getDate("date").toLocalDate();
             LocalTime time = rs.getTime(5).toLocalTime();
             LichChieu l = new LichChieu(id, movie_id, room_id,date, time);
             ds.add(l);
             l.inThongTin();
             System.out.println("Tên Phim: "+rs.getString("movieName"));
             System.out.println("Phong chiếu số: "+rs.getString("roomName"));
             System.out.println("-----------------------------------------------------");
 
         }
        }
 
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
       

    @Override
    public void selectAll(ArrayList<LichChieu> ds) {
        String sql = "SELECT l.id, l.movie_id, l.room_id, l.date , l.time , PhongChieu.name AS roomName , Phim.name AS movieName " +
        "FROM LichChieu l " +
        "JOIN PhongChieu ON PhongChieu.id = l.room_id " +
        "JOIN Phim ON Phim.id = l.movie_id";
 
        try(Connection con = duLieu.ket_noi()) {
        PreparedStatement ptm = con.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
        if(rs!=null){
         while ((rs.next())) {
             int id = rs.getInt(1);
             int movie_id = rs.getInt(2);
             int room_id = rs.getInt(3);
             LocalDate date = rs.getDate("date").toLocalDate();
             LocalTime time = rs.getTime(5).toLocalTime();
             LichChieu l = new LichChieu(id, movie_id, room_id,date, time);
             ds.add(l);
             l.inThongTin();
             System.out.println("Tên Phim: "+rs.getString("movieName"));
             System.out.println("Phong chiếu số: "+rs.getString("roomName"));
             System.out.println("-----------------------------------------------------");
 
         }
        }
 
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
