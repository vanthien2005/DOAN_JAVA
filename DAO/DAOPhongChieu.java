package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.PhongChieu;

public class DAOPhongChieu implements DAOInterFace<PhongChieu> {
    public static DAOPhongChieu getInstance(){
        return new DAOPhongChieu();
    }

    public boolean examId_emp(int id){
        String sql = "SELECT * FROM NhanVien WHERE id = ?";
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
    public boolean examId(int id){
        String sql = "SELECT * FROM PhongChieu WHERE id = ?";
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
    public boolean insert(PhongChieu t) {
        String sql = "INSERT INTO PhongChieu (name, capacity,emp_id) VALUES (?, ?,?)";

    try (Connection conn = duLieu.ket_noi()){
        PreparedStatement ptm = conn.prepareStatement(sql);
        
        ptm.setString(1, t.getName());
        ptm.setInt(2, t.getSoGhe());
        ptm.setInt(3, t.getEmp_id());
        return ptm.executeUpdate()>0 ? true : false;

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    finally{
        duLieu.close();
    }
    }

    @Override
    public boolean delete(PhongChieu t) {

    try (Connection conn = duLieu.ket_noi()){
        CallableStatement cal  = conn.prepareCall("{CALL deletePhongChieu(?,?)}");
        cal.setInt(1, t.getID());
        cal.registerOutParameter(2, java.sql.Types.NVARCHAR);
        cal.execute();
        return cal.getString(2).equalsIgnoreCase("xóa thành công")? true : false;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    finally{
        duLieu.close();
    }
    }

    @Override
    public boolean update(PhongChieu t) {
        String sql = "UPDATE PhongChieu SET name=? ,capacity=?,  emp_id = ? WHERE id = ?";

        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setString(1, t.getName());
                ptm.setInt(2, t.getSoGhe()); 
                ptm.setInt(3, t.getEmp_id());
                ptm.setInt(4, t.getID());  

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
    public ArrayList<PhongChieu> selectCondition( String condition) {
        ArrayList<PhongChieu> ds = new ArrayList<>();
        String sql = "SELECT PhongChieu.id, PhongChieu.name, PhongChieu.capacity,PhongChieu.emp_id, Phim.name AS name_phim, NhanVien.name AS nameNhanVien " +
        "FROM PhongChieu " +
        "LEFT JOIN PhongChieuPhim ON PhongChieuPhim.room_id = PhongChieu.id " +
        "LEFT JOIN Phim ON Phim.id = PhongChieuPhim.movie_id " +
        "LEFT JOIN NhanVien ON NhanVien.id = PhongChieu.emp_id " +
        "WHERE name LIKE ?";
        
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1,"%"+condition+"%");
           ResultSet rs =  ptm.executeQuery();
           
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sucChua = rs.getInt("capacity");
                String name_phim = rs.getString("name_phim");
                int emp_id = rs.getInt("emp_id");
                PhongChieu p = new PhongChieu(id,name,sucChua,name_phim,emp_id);
                p.setEmpName(rs.getString("nameNhanVien"));
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
    public ArrayList<PhongChieu> selectAll() {
        ArrayList<PhongChieu> ds = new ArrayList<>();
        String sql = "SELECT PhongChieu.id, PhongChieu.name, PhongChieu.capacity,PhongChieu.emp_id, Phim.name AS name_phim, NhanVien.name AS nameNhanVien " +
        "FROM PhongChieu " +
        "INNER JOIN PhongChieuPhim ON PhongChieuPhim.room_id = PhongChieu.id " +
        "INNER JOIN Phim ON Phim.id = PhongChieuPhim.movie_id " +
        "INNER JOIN NhanVien ON NhanVien.id = PhongChieu.emp_id ";
        
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
           ResultSet rs =  ptm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sucChua = rs.getInt("capacity");
                String name_phim = rs.getString("name_phim");
                int emp_id = rs.getInt("emp_id");
                PhongChieu p = new PhongChieu(id,name,sucChua,name_phim,emp_id);
                p.setEmpName(rs.getString("nameNhanVien"));
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
    

