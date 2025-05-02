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
import DTO.Nguoi;
import DTO.Phim;
import DTO.Ve;
import DTO.gheNgoi;

public class DAOVe implements DAOInterFace<Ve> {
    DAOKhachHang d = new DAOKhachHang();
    public static DAOVe getInstance(){
        return new DAOVe();
    }

    public ArrayList<gheNgoi>dsGheNgoi(int id){
        ArrayList<gheNgoi> ds = new ArrayList<>();
        String sql = "SELECT Seat.name,Seat.id " + 
                        "FROM Seat " + 
                        "WHERE Seat.id NOT IN ( " + 
                        "    SELECT Ve.seat_id " + 
                        "    FROM Ve " + 
                        "    WHERE Ve.showTime_id = ? " +
                        ")" ;
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1,id);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                gheNgoi g = new gheNgoi();
                g.setId(rs.getInt("id"));
                g.setNumber_seat(rs.getString("name"));
                ds.add(g);
                
            }
            return ds;
   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public ArrayList<Phim> dsPhim(){
        ArrayList<Phim>ds = new ArrayList<>();
        String sql = "SELECT * FROM PHIM WHERE status = 1 ";
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
    public ArrayList<LichChieu>dsLichChieu(int id){
        LocalDate ngay = LocalDate.now();
        String sql = "SELECT LichChieu.id,LichChieu.date,LichChieu.time,PhongChieu.name " + 
        "FROM LichChieu " +
        "JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "WHERE movie_id = ? AND LichChieu.date >= ? ";
        ArrayList<LichChieu>ds = new ArrayList<>();
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, id);
            ptm.setDate(2, java.sql.Date.valueOf(ngay));
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                LichChieu l = new LichChieu();
                l.setID(rs.getInt("id"));
                l.setTg(rs.getDate("date").toLocalDate());
                l.setTime(rs.getTime("time").toLocalTime());
                l.setRoomName(rs.getString("name"));
                ds.add(l);
            }
            return ds;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
           
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
        Nguoi n = new Nguoi();
        n.setName(t.getNameUser());
        n.setNumberPhone(t.getNumberPhone());
        n.setStatus("T");
        int idKhachHang = d.insertAndGetId(n);

        String sql = "INSERT INTO Ve (user_id, seat_id,showTime_id,status,price) VALUES (?, ?, ?,?,?)";
        try (Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setInt(1, idKhachHang); 
            ptm.setInt(2, t.getSeat_id());
            ptm.setInt(3, t.getShowTime_id());
            ptm.setString(4, t.getStatus());
            ptm.setInt(5, t.getPrice());
            return ptm.executeUpdate()>0;

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    @Override
    public boolean delete(Ve t) {
        String sql = "UPDATE Ve SET status = N'Đã hủy' WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {  
            ptm.setInt(1, t.getId());
            return ptm.executeUpdate()>0 ;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Ve t) {
        String sql = "UPDATE Ve SET status=?  WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setString(1, t.getStatus());
                ptm.setInt(2, t.getId());
                return ptm.executeUpdate()> 0 ;
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Ve> selectAll() {
        ArrayList<Ve> ds = new ArrayList<>();
        String sql = "SELECT Ve.id, Ve.user_id, Ve.seat_id, Ve.showTime_id, Ve.status , Ve.price , LichChieu.room_id, LichChieu.movie_id, Phim.name AS nameMovie, LichChieu.time AS timeChieu,LichChieu.date AS dayChieu,Seat.name AS nameNumber, khachHang.name AS nameUser, khachHang.numberPhone AS numUser, PhongChieu.name AS roomName " +
        "FROM Ve " +
        "LEFT JOIN khachHang  ON khachHang.id = Ve.user_id " +
        "LEFT JOIN LichChieu ON LichChieu.id = Ve.showTime_id " +
        "LEFT JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "LEFT JOIN Phim ON Phim.id = LichChieu.movie_id " +
        "LEFT JOIN Seat ON Seat.id = Ve.seat_id ";
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
    public ArrayList<Ve> selectCondition(String condition) {
        String c = "N'" + condition + "'";
        ArrayList<Ve> ds = new ArrayList<>();
        String sql = "SELECT Ve.id, Ve.user_id, Ve.seat_id, Ve.showTime_id, Ve.status , Ve.price ,LichChieu.room_id, LichChieu.movie_id, Phim.name AS nameMovie, LichChieu.time AS timeChieu, LichChieu.date AS dayChieu,gheNgoi.seatNumber AS nameNumber, khachHang.name AS nameUser, khachHang.numberPhone AS numUser, PhongChieu.name AS roomName " +
        "FROM Ve " +
        "JOIN khachHang  ON khachHang.id = Ve.user_id " +
        "JOIN LichChieu ON LichChieu.id = Ve.showTime_id " +
        "JOIN PhongChieu ON PhongChieu.id = LichChieu.room_id " +
        "JOIN Phim ON Phim.id = LichChieu.movie_id " +
        "JOIN gheNgoi ON gheNgoi.id = Ve.seat_id " +
        "WHERE Ve.status = " + c;
        try(Connection conn = duLieu.ket_noi()) {
        PreparedStatement ptm = conn.prepareStatement(sql);
        System.out.println(sql);
        ResultSet rs = ptm.executeQuery();
       
            while(rs.next()){
                try{
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
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
            return ds;
    }catch(Exception e){
        System.out.println(e.getMessage());
        return null;
    }
    }
   
}
