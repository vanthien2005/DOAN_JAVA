package DAO;

import java.sql.CallableStatement;
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
    }


    public boolean insert(Phim t) {
        String sql = "INSERT INTO Phim (id,name,type,duration,image) VALUES (?,?,?,?,?)";
        try(Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setInt(1, t.getId());
            ptm.setString(2, t.getName());
            ptm.setString(3, t.getType());
            ptm.setString(4,t.getDuration());
            ptm.setString(5,t.getUrl());
            return ptm.executeUpdate()>0 ? true : false;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public boolean delete(Phim t) {
     try(Connection con = duLieu.ket_noi()) {
        CallableStatement cal = con.prepareCall("{CALL deletePhim(?,?)}");
        cal.setInt(1, t.getId());
        cal.registerOutParameter(2, java.sql.Types.NVARCHAR);
        cal.execute();
        return cal.getString(2).equalsIgnoreCase("xóa thành công")?true : false;
       
     } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
     }
     finally{
        duLieu.close();
    }
    }

    @Override
    public boolean update(Phim t) {
        String sql = "UPDATE Phim SET name=?,type=?,duration=?, image = ? WHERE id=? ";
        try(Connection con = duLieu.ket_noi();) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, t.getName());
            ptm.setString(2, t.getType());
            ptm.setString(3, t.getDuration());
            ptm.setString(4, t.getUrl());
            ptm.setInt(5, t.getId());

           return ptm.executeUpdate()>0 ? true : false;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
        }
        finally{
            duLieu.close();
        }
    }

   

    @Override
    public ArrayList<Phim> selectCondition(String condition) {
        ArrayList<Phim> ds = new ArrayList<>();
        String sql = "SELECT Phim.id, Phim.name, Phim.type, Phim.duration,Phim.image, PhongChieu.name AS name_room " +
        "FROM Phim " +
        "LEFT JOIN PhongChieuPhim ON PhongChieuPhim.movie_id = Phim.id " +
        "LEFT JOIN PhongChieu ON PhongChieu.id = PhongChieuPhim.room_id " +
        "WHERE Phim.name LIKE ? AND Phim.status = 1" ;
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1,"%" + condition + "%");
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String duration = rs.getString("duration");
                    String name_room = rs.getString("name_room");
                    String url = rs.getString("image");
                    Phim p = new Phim(id, name, type, duration, name_room);
                    p.setUrl(url);
                    ds.add(p);
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

    public ArrayList<Phim> searchType(String condition) {
        ArrayList<Phim> ds = new ArrayList<>();
        String sql = "SELECT Phim.id, Phim.name, Phim.type, Phim.duration,Phim.image, PhongChieu.name AS name_room " +
        "FROM Phim " +
        "INNER JOIN PhongChieuPhim ON PhongChieuPhim.movie_id = Phim.id " +
        "INNER JOIN PhongChieu ON PhongChieu.id = PhongChieuPhim.room_id " +
        "WHERE type LIKE ? " ;
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, "%"+condition+"%");
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String duration = rs.getString("duration");
                    String name_room = rs.getString("name_room");
                    String url = rs.getString("image");
                    Phim p = new Phim(id, name, type, duration, name_room);
                    p.setUrl(url);
                    ds.add(p);
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
    public ArrayList<Phim> selectAll() {
        ArrayList<Phim> ds = new ArrayList<>();
        String sql = "SELECT Phim.id, Phim.name, Phim.type,Phim.image, Phim.duration, PhongChieu.name AS name_room " +
        "FROM Phim " +
        "LEFT JOIN PhongChieuPhim ON PhongChieuPhim.movie_id = Phim.id " +
        "LEFT JOIN PhongChieu ON PhongChieu.id = PhongChieuPhim.room_id " +
        "WHERE status = 1";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String duration = rs.getString("duration");
                    String name_room = rs.getString("name_room");
                    String url = rs.getString("image");
                    Phim p = new Phim(id, name, type, duration, name_room);
                    p.setUrl(url);
                    ds.add(p);
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
    
}
