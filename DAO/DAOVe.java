package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.PhongChieuPhim;
import DTO.Ve;

public class DAOVe implements DAOInterFace<Ve> {
    public static DAOVe getInstance(){
        return new DAOVe();
    }
    public boolean examId_User(int id){
        String sql = "SELECT * FROM khachHang  WHERE id = ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean examId_seat(int id){
        String sql = "SELECT * FROM gheNgoi WHERE id = ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean examId_showTime(int id){
        String sql = "SELECT * FROM LichChieu WHERE id = ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean insert(Ve t) {
           String sql = "INSERT INTO Ve (user_id, seat_id,showTime_id,status,price) VALUES (?, ?, ?,?,?)";

    try (Connection conn = duLieu.ket_noi()){
        PreparedStatement ptm = conn.prepareStatement(sql);
        ptm.setInt(1, t.getUser_id()); 
        ptm.setInt(2, t.getSeat_id());
        ptm.setInt(3, t.getShowTime_id());
        ptm.setString(4, t.getStatus());
        ptm.setInt(5, t.getPrice());
        return ptm.executeUpdate()>0? true : false;

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    @Override
    public boolean delete(Ve t) {
        String sql = "DELETE FROM Ve WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {  
            ptm.setInt(1, t.getId()); 
            
            return ptm.executeUpdate()>0 ? true : false;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Ve t) {
        String sql = "UPDATE Ve SET user_id = ?,seat_id = ?, showTime_id=?, status=?, price=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setInt(1, t.getUser_id());
                ptm.setInt(2, t.getSeat_id()); 
                ptm.setInt(3, t.getShowTime_id());
                ptm.setString(4, t.getStatus());
                ptm.setInt(5,t.getPrice());
                ptm.setInt(6, t.getId());
                
                return ptm.executeUpdate()>0 ? true : false;
          
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Ve> selectAll() {
        ArrayList<Ve> ds = new ArrayList<>();
        String sql = "SELECT Ve.id, Ve.user_id, Ve.seat_id, Ve.showTime_id, Ve.status , Ve.price ,LichChieu.room_id, LichChieu.movie_id, Phim.name AS nameMovie, LichChieu.time AS timeChieu,LichChieu.date AS dayChieu,gheNgoi.seatNumber AS nameNumber, khachHang.name AS nameUser, khachHang.numberPhone AS numUser, PhongChieu.name AS roomName " +
        "FROM Ve " +
        "INNER JOIN khachHang  ON khachHang.id = Ve.user_id " +
        "INNER JOIN LichChieu ON LichChieu.id = Ve.showTime_id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "INNER JOIN Phim ON Phim.id = LichChieu.movie_id " +
        "INNER JOIN gheNgoi ON gheNgoi.id = Ve.seat_id ";
        try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        ResultSet rs = ptm.executeQuery();
            while(rs.next()){
                Ve v= new Ve( rs.getInt("id"),rs.getInt("price") );
                v.setNameSeat(rs.getString("nameNumber"));
                v.setNameUser(rs.getString("nameUser"));
                v.setNameRoom(rs.getString("roomName"));
                v.setSeat_id(rs.getInt("seat_id"));
                v.setShowTime_id(rs.getInt("showTime_id"));
                v.setUser_id(rs.getInt("user_id"));
                v.setTime(rs.getObject("timeChieu",LocalTime.class));
                v.setDay(rs.getObject("dayChieu",LocalDate.class));
                v.setNameMovie(rs.getString("nameMovie"));
                v.setStatus(rs.getString("status"));
                ds.add(v);
            }
            return ds;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
            }

    @Override
    public ArrayList<Ve> selectCondition( String condition) {
        ArrayList<Ve> ds = new ArrayList<>();
        String sql = "SELECT Ve.id, Ve.user_id, Ve.seat_id, Ve.showTime_id, Ve.status , Ve.price ,LichChieu.room_id, LichChieu.movie_id, Phim.name AS nameMovie, LichChieu.time AS timeChieu,gheNgoi.seatNumber AS nameNumber, khachHang.name AS nameUser, khachHang.numberPhone AS numUser, PhongChieu.name AS roomName " +
        "FROM Ve " +
        "INNER JOIN khachHang  ON khachHang.id = Ve.user_id " +
        "INNER JOIN LichChieu ON LichChieu.id = Ve.showTime_id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "INNER JOIN Phim ON Phim.id = LichChieu.movie_id " +
        "INNER JOIN gheNgoi ON gheNgoi.id = Ve.seat_id " +
        "WHERE status LIKE ? " ;
        try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        ptm.setString(1, "%"+condition+"%");
        ResultSet rs = ptm.executeQuery();
       
            while(rs.next()){
                Ve v= new Ve( rs.getInt("id"),rs.getInt("price") );
                v.setNameSeat(rs.getString("nameNumber"));
                v.setNameUser(rs.getString("nameUser"));
                v.setNameRoom(rs.getString("roomName"));
                v.setSeat_id(rs.getInt("seat_id"));
                v.setShowTime_id(rs.getInt("showTime_id"));
                v.setUser_id(rs.getInt("user_id"));
                v.setTime(rs.getObject("timeChieu",LocalTime.class));
                v.setDay(rs.getObject("dayChieu",LocalDate.class));
                v.setNameMovie(rs.getString("nameMovie"));
                v.setStatus(rs.getString("status"));
                ds.add(v);
            }
            return ds;
    }catch(Exception e){
        System.out.println(e.getMessage());
        return null;
    }
    }
  
    
}
