package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.Phim;

public class DAOPhim implements DAOInterFace<Phim> {

    public static DAOPhim getInstance(){
        return new  DAOPhim();
    }
    public void insert(Phim t) {
        String sql = "INSERT INTO Phim (id,name,type,duration) VALUES (?,?,?,?)";
        try(Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setInt(1,t.getId());
            ptm.setString(2, t.getName());
            ptm.setString(3, t.getType());
            ptm.setString(4,t.getDuration());
            int row = ptm.executeUpdate();
            if(row>0){
                System.out.println("thêm dữ liệu thành công");
            }
        } catch (Exception e) {
           
        }
    }

    @Override
    public void delete(Phim t) {
     String sql = "DELETE FROM Phim WHERE id=?  ";
     try(Connection con = duLieu.ket_noi()) {
        PreparedStatement ptm = con.prepareStatement(sql);
        ptm.setInt(1, t.getId());
        int row = ptm.executeUpdate();
        if(row>0){
            System.out.println("Xóa dữ liệu thành công");
        }
     } catch (Exception e) {
        // TODO: handle exception
     }
    }

    @Override
    public void update(Phim t) {
        String sql = "UPDATE Phim SET id=? ,name=?,type=?,duration=? WHERE id=? ";
        try(Connection con = duLieu.ket_noi();) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, t.getId());
            ptm.setString(2, t.getName());
            ptm.setString(2, t.getType());
            ptm.setString(4, t.getDuration());
            ptm.setInt(5, t.getId());

            if(ptm.executeUpdate()>0){
                System.out.println("thay dổ dữ liệu thành công");
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

   

    @Override
    public void selectCondition(ArrayList<Phim> ds ,String condition) {
        String sql = "SELECT Phim.id, Phim.name, Phim.type, Phim.duration, PhongChieu.name AS name_room " +
        "FROM Phim " +
        "INNER JOIN PhongChieuPhim ON PhongChieuPhim.movie_id = Phim.id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = PhongChieuPhim.room_id " +
        "WHERE " + condition;
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String duration = rs.getString("duration");
                    String name_room = rs.getString("name_room");
                    Phim p = new Phim(id, name, type, duration, name_room);
                    ds.add(p);
                }
                for(Phim p : ds){
                    p.inThongTin();
                }
            }else System.out.println("Không có dữ liệu");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void selectAll(ArrayList<Phim> ds) {
        String sql = "SELECT Phim.id, Phim.name, Phim.type, Phim.duration, PhongChieu.name AS name_room " +
        "FROM Phim " +
        "INNER JOIN PhongChieuPhim ON PhongChieuPhim.movie_id = Phim.id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = PhongChieuPhim.room_id";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String duration = rs.getString("duration");
                    String name_room = rs.getString("name_room");
                    Phim p = new Phim(id, name, type, duration, name_room);
                    ds.add(p);
                }
                for(Phim p : ds){
                    p.inThongTin();
                }
            }else System.out.println("Không có dữ liệu");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
