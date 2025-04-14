package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.duLieu;
import DTO.gheNgoi;

public class DAOGheNgoi implements DAOInterFace<gheNgoi> {
    public static DAOGheNgoi getInstance(){
        return new DAOGheNgoi();
    }
    public boolean examId_Room(int id){
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
    public boolean insert(gheNgoi t) {
        String sql="INSERT INTO gheNgoi(room_id , seatNumber,status) VALUES  (?,?,?) ";

        try(Connection con  = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setInt(1, t.getRoom_id());
            ptm.setString(2, t.getNumber_seat());
            ptm.setString(3, t.getStatus());
            int i = ptm.executeUpdate();
            if(i>0){
                return true;
            }else return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public  boolean delete(gheNgoi t) {

        try (Connection conn = duLieu.ket_noi()){
             CallableStatement cal = conn.prepareCall("{CALL deleteGheNgoi(?,?)}");
            cal.setInt(1, t.getId());
            cal.registerOutParameter(2, java.sql.Types.NVARCHAR); 
            cal.execute();   
            // nếu xóa thành công trả về true ngược lại trả về false
            return cal.getString(2).equalsIgnoreCase("xóa ghế ngồi thành công") ? true : false;    
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public boolean update(gheNgoi t) {
        String sql = "UPDATE gheNgoi SET room_id=?, seatNumber=?, status=? WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                    ptm.setInt(1, t.getRoom_id());
                    ptm.setString(2, t.getNumber_seat());
                    ptm.setString(3, t.getStatus()); 
                    ptm.setInt(4, t.getId());
                return ptm.executeUpdate()>0?true:false;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
            duLieu.close();
        }
    }

    @Override
    public ArrayList<gheNgoi> selectAll() {
        ArrayList<gheNgoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM gheNgoi ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("seatNumber");
                String status = rs.getString("status");
                gheNgoi g = new gheNgoi(id,room_id, name, status);
                ds.add(g);   
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
    public ArrayList<gheNgoi> selectCondition( String condition) {
        ArrayList<gheNgoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM gheNgoi " +
        "WHERE seatNumber LIKE ? ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1,"%"+condition+"%");
            ResultSet rs = ptm.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("seatNumber");
                String status = rs.getString("status");
                gheNgoi g = new gheNgoi(id,room_id, name, status);
                ds.add(g);
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
