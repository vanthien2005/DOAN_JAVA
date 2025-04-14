package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.NhanVien;
import DataBase.duLieu;

public class DAONhanVien implements DAOInterFace<NhanVien> {

    @Override
    public boolean insert(NhanVien t) {
         String sql = "INSERT INTO NhanVien (id,name,age,numberPhone,email,status,position) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setInt(1,t.getId());
            ptm.setString(2, t.getName());
            ptm.setInt(3, t.getAge());
            ptm.setString(4,t.getNumberPhone());
            ptm.setString(5, t.getEmail());
            ptm.setString(6, t.getStatus());
            ptm.setString(7, t.getPosition());
            int row = ptm.executeUpdate();
            return row>0?true:false;

        } catch (Exception e) {
           return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public boolean delete(NhanVien t) {
        try (Connection conn = duLieu.ket_noi()){
            CallableStatement cal = conn.prepareCall("{CALL deleteNhanVien(?,?)}");
            cal.setInt(1, t.getId()); 
            cal.registerOutParameter(2, java.sql.Types.NVARCHAR);
            cal.execute();
            return cal.getString(2).equalsIgnoreCase("Xóa thành công")? true : false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public boolean update(NhanVien t) {
        String sql = "UPDATE NhanVien SET name = ?,age = ?, numberPhone=?, email=?, status=?, position = ? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setString(1, t.getName());
                ptm.setInt(2, t.getAge()); 
                ptm.setString(3, t.getNumberPhone());
                ptm.setString(4, t.getEmail());
                ptm.setString(5,t.getStatus());
                ptm.setString(6, t.getPosition());
                ptm.setInt(7, t.getId());
            
                return ptm.executeUpdate()>0 ? true : false;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
         ArrayList<NhanVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE status = 'T'";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String numberPhone = rs.getString("numberPhone");
                    String email = rs.getString("email");
                    String status = rs.getString("status");
                    NhanVien k = new NhanVien(id,name, age, numberPhone, email, status,rs.getString("position"));
                    ds.add(k);                  
                }
                return ds;
               
            }          
         catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public ArrayList<NhanVien> selectCondition(String condition) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE name LIKE ?";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1,"%"+condition+"%");
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int age = rs.getInt(3);
                    String numberPhone = rs.getString(4);
                    String email = rs.getString(5);
                    String status = rs.getString(6);
                    NhanVien k = new NhanVien(id,name, age, numberPhone, email, status,rs.getString("position"));
                    ds.add(k);                  
                }
                return ds;
               
            }          
         catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally{
            duLieu.close();
        }
    }
    
}
