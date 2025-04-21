package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import DataBase.duLieu;
import DTO.Nguoi;

public class DAOKhachHang implements DAOInterFace<Nguoi> {
    public static DAOKhachHang getInstance(){
        return new DAOKhachHang();
    }
   
    public boolean exam_id(int id){
        String sql = "SELECT * FROM khachHang WHERE id = ?";
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
    public boolean insert(Nguoi t) {
        String sql = "INSERT INTO khachHang (name,age,numberPhone,email,status) VALUES (?,?,?,?,?)";
        try(Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(1, t.getName());
            ptm.setInt(2, t.getAge());
            ptm.setString(3,t.getNumberPhone());
            ptm.setString(4, t.getEmail());
            ptm.setString(5, t.getStatus());
            int row = ptm.executeUpdate();
            return row>0?true:false;

        } catch (Exception e) {
           return false;
        }
        finally{
            duLieu.close();
        }
    }

    public int insertAndGetId(Nguoi n) {
        String sql = "INSERT INTO khachHang (name, numberPhone) VALUES (?, ?)";
        try (Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptm.setString(1, n.getName());
            ptm.setString(2, n.getNumberPhone());
            int affectedRows = ptm.executeUpdate();
    
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ptm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // ID vừa được tạo
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    

    @Override
    public boolean delete(Nguoi t) {

        try (Connection conn = duLieu.ket_noi()){
            CallableStatement cal = conn.prepareCall("{CALL deleteKhachHang(?,?)}");
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
    public boolean update(Nguoi t) {
        String sql = "UPDATE khachHang SET name = ?,age = ?, numberPhone=?, email=?, status=? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setString(1, t.getName());
                ptm.setInt(2, t.getAge()); 
                ptm.setString(3, t.getNumberPhone());
                ptm.setString(4, t.getEmail());
                ptm.setString(5,t.getStatus());
                ptm.setInt(6, t.getId());
            
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
    public ArrayList<Nguoi> selectAll() {
        ArrayList<Nguoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM khachHang WHERE status = 'T'";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int age = rs.getInt(3);
                    String numberPhone = rs.getString(4);
                    String email = rs.getString(5);
                    String status = rs.getString(6);
                    Nguoi k = new Nguoi(id,name, age, numberPhone, email, status);
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
    public ArrayList<Nguoi>  selectCondition( String condition) {
        ArrayList<Nguoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM khachHang WHERE status = 'T' AND name LIKE ? ";
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
                    Nguoi k = new Nguoi(id,name, age, numberPhone, email, status);
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
