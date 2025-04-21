package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.SanPham;
import DataBase.duLieu;

public class DAOSanPham implements DAOInterFace<SanPham> {

    @Override
    public boolean insert(SanPham t) {
         String sql = "INSERT INTO sanPham (name,type,price,status,image) VALUES (?, ?, ?,?,?)";

    try (Connection conn = duLieu.ket_noi()){
        PreparedStatement ptm = conn.prepareStatement(sql);
        ptm.setString(1, t.getName()); 
        ptm.setString(2, t.getType());
        ptm.setInt(3, t.getPrice());
        ptm.setString(4,t.getStatus());
        ptm.setString(5, t.getImage());
        return ptm.executeUpdate()>0? true : false;

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    @Override
    public boolean delete(SanPham t) {
        String sql = "UPDATE sanPham SET status = 0 WHERE id = ?";
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
    public boolean update(SanPham t) {
        String sql = "UPDATE sanPham SET name = ?, type = ?, image = ?, price = ?  WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setString(1, t.getName());
                ptm.setString(2, t.getType()); 
                ptm.setString(3, t.getImage());
                ptm.setInt(4, t.getPrice());
                ptm.setInt(5,t.getId());
                return ptm.executeUpdate()> 0 ;
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> ds = new ArrayList<>();
        String sql = "SELECT * FROM sanPham WHERE status = 1 ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    int price = rs.getInt("price");
                    String status = rs.getString("status");
                    String url = rs.getString("image");
                    SanPham p = new SanPham(id, name, type, price, status,url);
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
    public ArrayList<SanPham> selectCondition(String condition) {
        ArrayList<SanPham> ds = new ArrayList<>();
        String sql = "SELECT * FROM sanPham WHERE status = 1 AND name LIKE ? ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, "%"+condition+"%");
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    int price = rs.getInt("price");
                    String status = rs.getString("status");
                    String url = rs.getString("image");
                    SanPham p = new SanPham(id, name, type, price, status,url);
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
