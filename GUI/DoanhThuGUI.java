package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import BLL.DoanhThuBLL;
import DTO.DoanhThu;

public class DoanhThuGUI {
    DoanhThuBLL doanhThuBLL = new DoanhThuBLL();
    LamDepGUI lamDepGUI = new LamDepGUI();
    JTable tableDoanhThuSP = new JTable();
    JTable tableDoanhThuPhim = new JTable();
    JTable tableTong = new JTable();
    ArrayList <DoanhThu>doanhThuSP = new ArrayList<>();
    ArrayList<DoanhThu>doanhThuPhim = new ArrayList<>();

    public void showDoanhThu(JPanel main ){
        int tongTienTuSP = 0;
        int tongTienTuPhim = 0;
        int tongTien = 0;
        JPanel locTheoKhoangThoiGian = new JPanel(new FlowLayout(FlowLayout.CENTER , 20,20));

       

        JLabel fromDay = new JLabel("Từ ngày (yyyy-mm-dd): ");
        fromDay.setFont(new Font("Time new roman", Font.PLAIN, 15));
        fromDay.setForeground(Color.red);
        JLabel toDay = new JLabel("Đến ngày (yyyy-mm-dd): ");
        toDay.setFont(new Font("Time new roman", Font.PLAIN, 15));
        toDay.setForeground(Color.red);
        JTextField from = new JTextField(10); // đặt chiều rộng vừa đủ nhập
        JTextField to = new JTextField(10);
        JButton loc = new JButton("Lọc");

        locTheoKhoangThoiGian.add(fromDay);
        locTheoKhoangThoiGian.add(from);
        locTheoKhoangThoiGian.add(toDay);
        locTheoKhoangThoiGian.add(to);
        locTheoKhoangThoiGian.add(loc);

        JPanel content = new JPanel(new GridLayout(0,1,0,10));

        // Bảng sản phẩm
        DefaultTableModel modelSanPham = new DefaultTableModel();
        modelSanPham.addColumn("Tên sản phẩm");
        modelSanPham.addColumn("Số lượng đã bán");
        modelSanPham.addColumn("Đơn giá");
        modelSanPham.addColumn("Tổng thu");
        tableDoanhThuSP.setModel(modelSanPham);
        tableDoanhThuSP.setRowHeight(50);
        tableDoanhThuSP.setFont(new Font("Time new roman", Font.BOLD, 15));

        // Bảng phim
        DefaultTableModel modelPhim = new DefaultTableModel();
        modelPhim.addColumn("Tên bộ phim");
        modelPhim.addColumn("Thể loại");
        modelPhim.addColumn("Số vé đã bán");
        modelPhim.addColumn("Tổng thu");
        tableDoanhThuPhim.setModel(modelPhim);
        tableDoanhThuPhim.setRowHeight(50);
        tableDoanhThuPhim.setFont(new Font("Time new roman", Font.BOLD, 15));

        JScrollPane js1 = new JScrollPane(tableDoanhThuSP);
        JScrollPane js = new JScrollPane(tableDoanhThuPhim);
        JTableHeader header = tableDoanhThuSP.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18)); 
        header.setBackground(new Color(173, 216, 230));

        JTableHeader header1 = tableDoanhThuPhim.getTableHeader();
        header1.setFont(new Font("Time new roman", Font.BOLD, 18)); 
        header1.setBackground(new Color(173, 216, 230));

        DefaultTableModel modelTong = new DefaultTableModel();

        content.add(js1);
        content.add(js);

        // Xử lý sự kiện nút lọc
        loc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromDay = from.getText().trim();
                String toDay = to.getText().trim();
                if(fromDay.isEmpty() || toDay.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ ngày tháng năm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                    Date dateFrom = java.sql.Date.valueOf(fromDay);
                    Date dateTo = java.sql.Date.valueOf(toDay);
                    if(dateFrom.after(dateTo)){
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được lớn hơn ngày kết thúc", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    doanhThuSP = doanhThuBLL.getDoanhThuSP(dateFrom, dateTo);
                    doanhThuPhim = doanhThuBLL.getDoanhThuPhim(dateFrom, dateTo);

                    modelSanPham.setRowCount(0); // clear dữ liệu cũ
                    modelPhim.setRowCount(0);
                    modelTong.setRowCount(0);

                    for(DoanhThu d : doanhThuSP){
                        Object[] row = {d.getNameSP(),d.getTotalSLDaBan(),lamDepGUI.formatVNDPlain(d.getGiaSp()),lamDepGUI.formatVNDPlain(d.getTotalTienThuTuSP())};
                        modelSanPham.addRow(row);
                    }

                    for(DoanhThu d : doanhThuPhim){
                        Object[] row1 = {d.getNamePhim(),d.getType(),d.getSoLanDuocDat(),lamDepGUI.formatVNDPlain(d.getTotalTienThuPhim())};
                        modelPhim.addRow(row1);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không đúng định dạng ngày (yyyy-mm-dd)", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Nếu dữ liệu chưa được nạp sẵn
        if(doanhThuSP.isEmpty() && doanhThuPhim.isEmpty()){
            LocalDate tamDate = LocalDate.of(1900, 1, 1);
            LocalDate tam1Date = LocalDate.now().plusDays(1);
            java.sql.Date sqlTamDate = java.sql.Date.valueOf(tamDate);
            java.sql.Date sqlTam1Date = java.sql.Date.valueOf(tam1Date);
            doanhThuSP = doanhThuBLL.getDoanhThuSP(sqlTamDate, sqlTam1Date);
            doanhThuPhim = doanhThuBLL.getDoanhThuPhim(sqlTamDate, sqlTam1Date);
        }
        

        for(DoanhThu d : doanhThuSP){
            Object[] row = {d.getNameSP(),d.getTotalSLDaBan(),lamDepGUI.formatVNDPlain(d.getGiaSp()),lamDepGUI.formatVNDPlain(d.getTotalTienThuTuSP())};
            modelSanPham.addRow(row);
            tongTienTuSP+=d.getTotalTienThuTuSP();
        
        }
        for(DoanhThu d : doanhThuPhim){
            Object[] row1 = {d.getNamePhim(),d.getType(),d.getSoLanDuocDat(),lamDepGUI.formatVNDPlain(d.getTotalTienThuPhim())};
            modelPhim.addRow(row1);
            tongTienTuPhim+=d.getTotalTienThuPhim();
        }
        tongTien = tongTienTuSP+tongTienTuPhim;

        modelTong.addColumn("Tổng doanh thu từ sản phẩm");
        modelTong.addColumn("Tổng doanh thu từ Phim");
        modelTong.addColumn("Tổng cộng");
        JTableHeader header2 = tableTong.getTableHeader();
        header2.setFont(new Font("Time new roman", Font.BOLD, 18)); 
        header2.setBackground(new Color(173, 216, 230));
        tableTong.setRowHeight(30);
        tableTong.setFont(new Font("Time new roman", Font.BOLD, 18));
        tableTong.setModel(modelTong);
        String row[] = {lamDepGUI.formatVNDPlain(tongTienTuSP),lamDepGUI.formatVNDPlain(tongTienTuPhim),lamDepGUI.formatVNDPlain(tongTien)};
        modelTong.addRow(row);
        // content.add(tong);
        JScrollPane js3 = new JScrollPane(tableTong);
        content.add(js3);
        main.removeAll();
        main.setLayout(new BorderLayout());
        main.add(locTheoKhoangThoiGian, BorderLayout.NORTH);
        main.add(content, BorderLayout.CENTER);
        main.revalidate();
        main.repaint();
    }

}
