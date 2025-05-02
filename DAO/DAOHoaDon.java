package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Statement;
import DTO.HoaDon;
import DTO.Nguoi;
import DataBase.duLieu;

public class DAOHoaDon implements DAOInterFace<HoaDon> {
    DAOKhachHang d = new DAOKhachHang();
    @Override
    public boolean insert(HoaDon t) {
        Nguoi n = new Nguoi();
        n.setName(t.getNameKhachHang());
        n.setNumberPhone(t.getSdt());

        int idKhachHang = d.insertAndGetId(n); // ← lấy ID khách hàng vừa thêm

        if (idKhachHang == -1) return false;
        String sql = "INSERT INTO HoaDon (id_khachHang,date,status) VALUES (?, ?, ?)";
    try (Connection conn = duLieu.ket_noi()){
        PreparedStatement ptm = conn.prepareStatement(sql);
        ptm.setInt(1, idKhachHang); 
        ptm.setDate(2, java.sql.Date.valueOf(t.getDay()));
        ptm.setString(3,t.getStatus());
        return ptm.executeUpdate()>0? true : false;

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    public int insertHoaDonVaLayId(HoaDon t) {
        Nguoi n = new Nguoi();
        n.setName(t.getNameKhachHang());
        n.setNumberPhone(t.getSdt());
        n.setStatus("T");

        int idKhachHang = d.insertAndGetId(n);
        int idHoaDon = -1;
        String sql = "INSERT INTO HoaDon (id_khachHang, date, status) VALUES (?, ?, ?)";
        try (Connection conn = duLieu.ket_noi()) {
            PreparedStatement ptm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptm.setInt(1, idKhachHang);
            ptm.setDate(2, java.sql.Date.valueOf(t.getDay()));
            ptm.setString(3, t.getStatus());
    
            int rowsAffected = ptm.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ptm.getGeneratedKeys();
                if (rs.next()) {
                    idHoaDon = rs.getInt(1);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idHoaDon;
    }

    @Override
    public boolean delete(HoaDon t) {
        String sql = "UPDATE HoaDon SET status = N'Đã hủy' WHERE id = ?";
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
    public boolean update(HoaDon t) {
        String sql = "UPDATE HoaDon SET status = ? WHERE id = ?";
        try (Connection conn = duLieu.ket_noi();
             PreparedStatement ptm = conn.prepareStatement(sql)) {                
                ptm.setString(1, t.getStatus()); 
                ptm.setInt(2,t.getId());
                return ptm.executeUpdate()> 0 ;
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<HoaDon> selectAll() {
        ArrayList<HoaDon> ds = new ArrayList<>();
        String sql = """ 
    SELECT 
        HoaDon.id AS maHoaDon,
        HoaDon.date,
        HoaDon.status,
        khachHang.name AS tenKhachHang,
        STRING_AGG(sanPham.name + ' (x' + CAST(ChiTietHoaDon.soLuong AS NVARCHAR) + ')', ', ') AS danhSachSanPham,
        SUM(sanPham.price * ChiTietHoaDon.soLuong) AS total
    FROM HoaDon
    JOIN khachHang ON khachHang.id = HoaDon.id_khachHang
    JOIN ChiTietHoaDon ON ChiTietHoaDon.id_HD = HoaDon.id
    JOIN sanPham ON sanPham.id = ChiTietHoaDon.id_SP
    GROUP BY 
        HoaDon.id,
        HoaDon.date,
        HoaDon.status,
        khachHang.name
      
""";
try(Connection conn = duLieu.ket_noi()) {
    PreparedStatement ptm = conn.prepareStatement(sql);
    ResultSet rs = ptm.executeQuery();
    while (rs.next()) {
        int id = rs.getInt("maHoaDon");
        LocalDate date = rs.getDate("date").toLocalDate();
        String status = rs.getString("status");
        String dssp = rs.getString("danhSachSanPham");
        int total = rs.getInt("total");
        HoaDon h = new HoaDon();
        h.setId(id);
        h.setDay(date);
        h.setStatus(status);
        h.setTotal(total);
        h.setDssp(dssp);
        h.setNameKhachHang(rs.getString("tenKhachHang"));
        ds.add(h);
    }
    return ds;
    
} catch (Exception e) {
    System.out.println(e.getMessage());
    return null;
}


    }

    @Override
    public ArrayList<HoaDon> selectCondition(String condition) {
        ArrayList<HoaDon> ds = new ArrayList<>();
        String sql = """ 
    SELECT 
        HoaDon.id AS maHoaDon,
        HoaDon.date,
        HoaDon.status,
        khachHang.name AS tenKhachHang,
        STRING_AGG(sanPham.name + ' (x' + CAST(ChiTietHoaDon.soLuong AS NVARCHAR) + ')', ', ') AS danhSachSanPham,
        SUM(sanPham.price * ChiTietHoaDon.soLuong) AS total
    FROM HoaDon
    JOIN khachHang ON khachHang.id = HoaDon.id_khachHang
    JOIN ChiTietHoaDon ON ChiTietHoaDon.id_HD = HoaDon.id
    JOIN sanPham ON sanPham.id = ChiTietHoaDon.id_SP
    WHERE HoaDon.status LIKE ? 
    GROUP BY 
        HoaDon.id,
        HoaDon.date,
        HoaDon.status,
        khachHang.name
   
""";
try(Connection conn = duLieu.ket_noi()) {
    PreparedStatement ptm = conn.prepareStatement(sql);
    ptm.setString(1,"%"+condition+"%");
    System.out.println("Condition: " + condition);
    System.out.println("SQL: " + ptm.toString());
    ResultSet rs = ptm.executeQuery();
    while (rs.next()) {
        int id = rs.getInt("maHoaDon");
        LocalDate date = rs.getDate("date").toLocalDate();
        String status = rs.getString("status");
        String dssp = rs.getString("danhSachSanPham");
        int total = rs.getInt("total");
        HoaDon h = new HoaDon();
        h.setId(id);
        h.setDay(date);
        h.setStatus(status);
        h.setTotal(total);
        h.setDssp(dssp);
        h.setNameKhachHang(rs.getString("tenKhachHang"));
        ds.add(h);
    }
    return ds;
    
} catch (Exception e) {
    System.out.println(e.getMessage());
    return null;
    }
    
}

public ArrayList<HoaDon> selectDay(Date condition) {
    ArrayList<HoaDon> ds = new ArrayList<>();
    String sql = """ 
SELECT 
    HoaDon.id AS maHoaDon,
    HoaDon.date,
    HoaDon.status,
    khachHang.name AS tenKhachHang,
    STRING_AGG(sanPham.name + ' (x' + CAST(ChiTietHoaDon.soLuong AS NVARCHAR) + ')', ', ') AS danhSachSanPham,
    SUM(sanPham.price * ChiTietHoaDon.soLuong) AS total
FROM HoaDon
JOIN khachHang ON khachHang.id = HoaDon.id_khachHang
JOIN ChiTietHoaDon ON ChiTietHoaDon.id_HD = HoaDon.id
JOIN sanPham ON sanPham.id = ChiTietHoaDon.id_SP
WHERE HoaDon.date = ? 
GROUP BY 
    HoaDon.id,
    HoaDon.date,
    HoaDon.status,
    khachHang.name

""";
try(Connection conn = duLieu.ket_noi()) {
PreparedStatement ptm = conn.prepareStatement(sql);
ptm.setDate(1,condition);
System.out.println("Condition: " + condition);
System.out.println("SQL: " + ptm.toString());
ResultSet rs = ptm.executeQuery();
while (rs.next()) {
    int id = rs.getInt("maHoaDon");
    LocalDate date = rs.getDate("date").toLocalDate();
    String status = rs.getString("status");
    String dssp = rs.getString("danhSachSanPham");
    int total = rs.getInt("total");
    HoaDon h = new HoaDon();
    h.setId(id);
    h.setDay(date);
    h.setStatus(status);
    h.setTotal(total);
    h.setDssp(dssp);
    h.setNameKhachHang(rs.getString("tenKhachHang"));
    ds.add(h);
}
return ds;

} catch (Exception e) {
System.out.println(e.getMessage());
return null;
}

}
}
