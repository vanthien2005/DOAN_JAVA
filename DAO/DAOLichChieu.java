package DAO;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.LichChieu;
import DTO.Phim;
import DTO.PhongChieu;

public class DAOLichChieu implements DAOInterFace<LichChieu> {
    public static DAOLichChieu getInstance(){
        return new DAOLichChieu();
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
        finally{
            duLieu.close();
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
        finally{
            duLieu.close();
        }
    }
    public boolean examId(int id){
        String sql = "SELECT * FROM LichChieu WHERE id = ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            return rs.next();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public boolean insert(LichChieu t) {
    String sql = "INSERT INTO LichChieu (id,movie_id, room_id, date, time) VALUES ( ?,?, ?, ?, ?)";

    try (Connection conn = duLieu.ket_noi()){
        PreparedStatement ptm = conn.prepareStatement(sql) ; 
        ptm.setInt(1, t.getId());
        ptm.setInt(2, t.getIdPhim());
        ptm.setInt(3, t.getIdPhong());
        ptm.setDate(4, java.sql.Date.valueOf(t.getTg()));  // Nếu tg là LocalDate
        ptm.setTime(5, java.sql.Time.valueOf(t.getTime())); // Nếu time là LocalTime

        return ptm.executeUpdate()>0 ? true : false;
      

    }catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    finally{
        duLieu.close();
    }
}
    @Override
    public boolean delete(LichChieu t) {
        try (Connection conn = duLieu.ket_noi()) { 
            CallableStatement cal = conn.prepareCall("{CALL deleteLichChieu(?,?)}");
            cal.setInt(1,t.getId());
            cal.registerOutParameter(2,java.sql.Types.NVARCHAR);
            cal.execute();
            return cal.getString(2).equalsIgnoreCase("xóa thành công")? true : false;
            
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa lịch chiếu: " + e.getMessage());
            return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public boolean update(LichChieu t) {
        try (Connection conn = duLieu.ket_noi()) {
            String query = "UPDATE LichChieu SET movie_id = ?, room_id = ?, date = ?, time = ? WHERE id = ?";
    
            // Dùng PreparedStatement
            PreparedStatement ptm = conn.prepareStatement(query);
            ptm.setInt(1, t.getIdPhim());         // Gán giá trị movie_id
            ptm.setInt(2, t.getIdPhong());        // Gán giá trị room_id
            ptm.setDate(3, java.sql.Date.valueOf(t.getTg()));  // Chuyển LocalDate thành java.sql.Date
            ptm.setTime(4, java.sql.Time.valueOf(t.getTime())); // Chuyển LocalTime thành java.sql.Time
            ptm.setInt(5, t.getId());             // Điều kiện WHERE id = ?
    
            return ptm.executeUpdate()>0? true : false;
   
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật lịch chiếu: " + e.getMessage());
            return false;
        }
        finally{
            duLieu.close();
        }
    
}

  

    @Override
    public ArrayList<LichChieu> selectCondition(String condition) {
        ArrayList<LichChieu>ds = new ArrayList<>();
        String sql = "SELECT l.id, l.movie_id, l.room_id, l.date , l.time , PhongChieu.name AS roomName , Phim.name AS movieName, Phim.image AS anhPhim " +
        "FROM LichChieu l " +
        "JOIN PhongChieu ON PhongChieu.id = l.room_id " +
        "JOIN Phim ON Phim.id = l.movie_id " +
        "WHERE Phim.name LIKE ? ";
 
        try(Connection con = duLieu.ket_noi()) {
        PreparedStatement ptm = con.prepareStatement(sql);
        ptm.setString(1, "%" + condition + "%");
        ResultSet rs = ptm.executeQuery();
         while ((rs.next())) {
             int id = rs.getInt(1);
             int movie_id = rs.getInt(2);
             int room_id = rs.getInt(3);
             LocalDate date = rs.getDate("date").toLocalDate();
             LocalTime time = rs.getTime(5).toLocalTime();
             LichChieu l = new LichChieu(id, movie_id, room_id,date, time);
             l.setMovieName(rs.getString("movieName"));
             l.setRoomName(rs.getString("roomName"));
             l.setUrl(rs.getString("anhPhim"));
             ds.add(l);
         }
        return ds;
 
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            duLieu.close();
        }
    }
       

    @Override
    public ArrayList<LichChieu> selectAll() {
        ArrayList<LichChieu>ds = new ArrayList<>();
        String sql = "SELECT l.id, l.movie_id, l.room_id, l.date , l.time , PhongChieu.name AS roomName , Phim.image AS anhPhim, Phim.name AS movieName " +
        "FROM LichChieu l " +
        "JOIN PhongChieu ON PhongChieu.id = l.room_id " +
        "JOIN Phim ON Phim.id = l.movie_id " +
        "WHERE Phim.status = 1 ";
 
        try(Connection con = duLieu.ket_noi()) {
        PreparedStatement ptm = con.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
         while ((rs.next())) {
             int id = rs.getInt(1);
             int movie_id = rs.getInt(2);
             int room_id = rs.getInt(3);
             LocalDate date = rs.getDate("date").toLocalDate();
             LocalTime time = rs.getTime(5).toLocalTime();
             LichChieu l = new LichChieu(id, movie_id, room_id,date, time);
             l.setMovieName(rs.getString("movieName"));
             l.setRoomName(rs.getString("roomName"));
             l.setUrl(rs.getString("anhPhim"));
             ds.add(l);
             
        }
        return ds;
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            duLieu.close();
        }
    }

    public ArrayList<Phim>dsPhim(){
        ArrayList<Phim>ds = new ArrayList<>();
        String sql = "SELECT * FROM Phim WHERE status = 1 ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Phim p = new Phim();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                ds.add(p);
            }
            return ds;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
        }
    }
    public ArrayList<PhongChieu> dsPhongChieu(){
        ArrayList<PhongChieu>ds = new ArrayList<>();
        String sql = "SELECT * FROM PhongChieu ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                PhongChieu p = new PhongChieu();
                p.setID(rs.getInt("id"));
                p.setName(rs.getString("name"));
                ds.add(p);
            }
            return ds;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
        }
    }
}
