package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DoanhThu;
import DataBase.duLieu;

public class DAODoanhThu  {

    public ArrayList<DoanhThu>doanhThuSP(Date from , Date to){
        ArrayList<DoanhThu>ds = new ArrayList<>();
        String sql = "select sanPham.price,sanPham.name,sanPham.id,SUM(ChiTietHoaDon.soLuong) AS totalSL ,SUM(ChiTietHoaDon.soLuong) * sanPham.price AS totalTien " + 
        "from sanPham " + 
        "join ChiTietHoaDon on ChiTietHoaDon.id_SP = sanPham.id " + 
        "join HoaDon on HoaDon.id = ChiTietHoaDon.id_HD " + 
        "Where HoaDon.status = N'Đã thanh toán' AND HoaDon.date > ? AND HoaDon.date < ? " + 
        "group by sanPham.price,sanPham.name,sanPham.id ";
        try(Connection con = duLieu.ket_noi()) {
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setDate(1, from);
            ptm.setDate(2, to);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                DoanhThu d = new DoanhThu();
                d.setNameSP(rs.getString("name"));
                d.setTotalSLDaBan(rs.getInt("totalSL"));
                d.setGiaSp(rs.getInt("price"));
                d.setTotalTienThuTuSP(rs.getInt("totalTien"));
                ds.add(d);
            }
            return ds;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
            }

        public ArrayList<DoanhThu>doanhThuPhim(Date from , Date to){
            ArrayList<DoanhThu> ds = new ArrayList<>();
            String sql = "SELECT " + 
                                "    Phim.name, " + 
                                "    Phim.type, " + 
                                "    COUNT(Ve.id) AS ticket_count, " + 
                                "    COUNT(Ve.id) * 45000 AS total_revenue " + 
                                "FROM Phim " + 
                                "JOIN LichChieu ON LichChieu.movie_id = Phim.id " + 
                                "JOIN Ve ON Ve.showTime_id = LichChieu.id " + 
                                "WHERE LichChieu.date > ? AND LichChieu.date < ? AND Ve.status = N'Đã thanh toán'" +
                                "GROUP BY Phim.name, Phim.type ";
            try (Connection con = duLieu.ket_noi()){
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setDate(1, from);
                ptm.setDate(2, to);
                ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    DoanhThu d = new DoanhThu();
                    d.setNamePhim(rs.getString("name"));
                    d.setType(rs.getString("type"));
                    d.setSoLanDuocDat(rs.getInt("ticket_count"));
                    d.setTotalTienThuPhim(rs.getInt("total_revenue"));
                    ds.add(d);        
                }
                return ds;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    

}
