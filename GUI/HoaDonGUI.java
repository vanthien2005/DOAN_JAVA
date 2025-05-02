package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.ChiTietHoaDonBLL;
import BLL.HoaDonBLL;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.SanPham;

public class HoaDonGUI {
    JTable tableHoaDon = new JTable();
    HoaDonBLL hoaDonBLL = new HoaDonBLL();
    ChiTietHoaDonBLL chiTietBLL = new ChiTietHoaDonBLL();
    LamDepGUI lamDep = new LamDepGUI();


    public void showHoaDon(JPanel maiPanel){
        JPanel them, content, search;
        search = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    
        JComboBox<String> combo = new JComboBox<>(new String[]{"Tất cả", "Đã đặt", "Đã thanh toán", "Đã hủy"});
        combo.setPreferredSize(new Dimension(150, 30));
    
        JButton locTheoLoai = new JButton("Lọc theo trạng thái");
        locTheoLoai.setForeground(Color.RED);
    
    
        search.add(locTheoLoai);
        search.add(combo);

        // Tiếp tục như cũ phần table
        them = new JPanel();
        content = new JPanel(new BorderLayout());
        them.setLayout(new GridLayout());
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        model.addColumn("Số hóa đơn");
        model.addColumn("Tên khách hàng");
        model.addColumn("Sản phẩm");
        model.addColumn("Ngày tạo");
        model.addColumn("Trạng thái");
        model.addColumn("Tổng tiền");
        tableHoaDon.setModel(model);
        tableHoaDon.setRowHeight(50);
        tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(600);
        tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableHoaDon.getColumnModel().getColumn(5).setPreferredWidth(30);
        tableHoaDon.setFont(new Font("Time new roman", Font.BOLD, 15));
        JTableHeader header = tableHoaDon.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18)); 
        header.setBackground(new Color(173, 216, 230));
        JScrollPane js = new JScrollPane(tableHoaDon);
        content.add(js , BorderLayout.CENTER);
        maiPanel.removeAll();
        maiPanel.setLayout(new BorderLayout());
    
        ArrayList<HoaDon> ds = hoaDonBLL.geArrayList();
        for(HoaDon h : ds){

            Object row[] = {
                h.getId(),
                h.getNameKhachHang(),
                h.getDssp(),
                h.getDay(),
                h.getStatus(),
                lamDep.formatVNDPlain(h.getTotal())
            };
            model.addRow(row);
        }
    
        locTheoLoai.addActionListener(e -> {
            String status = (String) combo.getSelectedItem();
            System.out.println("Condition raw: [" + status + "]");
            System.out.println("Length: " + status.length());
            for (char c : status.toCharArray()) {
                System.out.print((int)c + " ");
            }
            if (status.equals("Tất cả")) {
                showHoaDon(maiPanel);
            } else {
                showHoaDon(maiPanel, status);
            }
        });
    
        maiPanel.add(content, BorderLayout.CENTER);
        maiPanel.add(search, BorderLayout.NORTH);
    
        maiPanel.revalidate();
        maiPanel.repaint();
    }

    

    public void showHoaDon(JPanel maiPanel,String condition){
        JPanel them, content, search;
        search = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    
        JComboBox<String> combo = new JComboBox<>(new String[]{"Tất cả", "Đã đặt", "Đã thanh toán", "Đã hủy"});
        combo.setPreferredSize(new Dimension(150, 30));
    
        JButton locTheoLoai = new JButton("Lọc theo loại");
        locTheoLoai.setForeground(Color.RED);
    
        search.add(locTheoLoai);
        search.add(combo);
    
        // Tiếp tục như cũ phần table
        them = new JPanel();
        content = new JPanel(new BorderLayout());
        them.setLayout(new GridLayout());
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        model.addColumn("Số hóa đơn");
        model.addColumn("Tên khách hàng");
        model.addColumn("Sản phẩm");
        model.addColumn("Ngày tạo");
        model.addColumn("Trạng thái");
        model.addColumn("Tổng tiền");
        tableHoaDon.setModel(model);
        tableHoaDon.setRowHeight(50);
        tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(400);
        tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableHoaDon.setFont(new Font("Time new roman", Font.BOLD, 15));
        JTableHeader header = tableHoaDon.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18)); 
        header.setBackground(new Color(173, 216, 230));
        JScrollPane js = new JScrollPane(tableHoaDon);
        content.add(js , BorderLayout.CENTER);
        maiPanel.removeAll();
        maiPanel.setLayout(new BorderLayout());
    
        ArrayList<HoaDon> ds = hoaDonBLL.searchStatus(condition);
        for(HoaDon h : ds){
            
            Object row[] = {
                h.getId(),
                h.getNameKhachHang(),
                h.getDssp(),
                h.getDay(),
                h.getStatus(),
                lamDep.formatVNDPlain(h.getTotal())
            };
            model.addRow(row);
        }
    
        locTheoLoai.addActionListener(e -> {
            String status = (String) combo.getSelectedItem();
            System.out.println("Condition raw: [" + status + "]");
            System.out.println("Length: " + status.length());
            for (char c : status.toCharArray()) {
                System.out.print((int)c + " ");
            }
            if (status.equals("Tất cả")) {
                showHoaDon(maiPanel);
            } else {
                showHoaDon(maiPanel, status);
            }
        });

        maiPanel.add(content, BorderLayout.CENTER);
        maiPanel.add(search, BorderLayout.NORTH);
    
        maiPanel.revalidate();
        maiPanel.repaint();
    }

    public void formThemHoaDon(View v, ArrayList<SanPham> dsSanPham) {

        JDialog dialog = new JDialog(v, "Thêm Hóa Đơn", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // ==== THÔNG TIN KHÁCH HÀNG ====
        JPanel thongTinKH = new JPanel(new GridLayout(2, 2, 10, 10));
        thongTinKH.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        JTextField tenKhach = new JTextField();
        JTextField sdt = new JTextField();
        thongTinKH.add(new JLabel("Tên khách hàng:"));
        thongTinKH.add(tenKhach);
        thongTinKH.add(new JLabel("Số điện thoại:"));
        thongTinKH.add(sdt);

        // ==== DANH SÁCH SẢN PHẨM + SỐ LƯỢNG ====
        JPanel danhSachSP = new JPanel();
        danhSachSP.setLayout(new BoxLayout(danhSachSP, BoxLayout.Y_AXIS));
        danhSachSP.setBorder(BorderFactory.createTitledBorder("Chọn sản phẩm"));

    Map<SanPham, JSpinner> soLuongMap = new HashMap<>();
    for (SanPham sp : dsSanPham) {
        JPanel spPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox check = new JCheckBox(sp.getName() + " - " + sp.getPrice() + " VND");
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinner.setEnabled(false);

        check.addActionListener(e -> {
            spinner.setEnabled(check.isSelected());
        });

        spPanel.add(check);
        spPanel.add(new JLabel("Số lượng:"));
        spPanel.add(spinner);
        danhSachSP.add(spPanel);
        soLuongMap.put(sp, spinner);
    }

    JScrollPane scrollPane = new JScrollPane(danhSachSP);
    scrollPane.setPreferredSize(new Dimension(480, 300));

    // ==== BUTTON ====
    JPanel buttons = new JPanel();
    JButton btnXacNhan = new JButton("Xác nhận");
    JButton btnHuy = new JButton("Hủy");
    buttons.add(btnXacNhan);
    buttons.add(btnHuy);

    // ==== XỬ LÝ SỰ KIỆN ====
    btnXacNhan.addActionListener(e -> {
        String ten = tenKhach.getText().trim();
        String soDienThoai = sdt.getText().trim();

        if (ten.isEmpty() || soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin khách hàng.");
            return;
        }
        HoaDon d = new HoaDon();
        d.setNameKhachHang(ten);
        d.setSdt(soDienThoai);
        d.setStatus("Đã thanh toán");
        d.setDay(LocalDate.now());
        int idHd = hoaDonBLL.insertHoaDonGetId(d);
        if(idHd>0){

            JOptionPane.showMessageDialog(null,"Thêm hóa đơn thành công" , "thông báo", JOptionPane.INFORMATION_MESSAGE);

            ArrayList<ChiTietHoaDon>danhSachChon = new ArrayList<>();
            for (Map.Entry<SanPham, JSpinner> entry : soLuongMap.entrySet()) {
                JSpinner spn = entry.getValue();
                if (spn.isEnabled()) {
                    int sl = (Integer) spn.getValue();
                    SanPham sp = entry.getKey();
                    int idSP = sp.getId();
                    danhSachChon.add(new ChiTietHoaDon(idSP, sl));
                }
            }
            if (danhSachChon.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ít nhất một sản phẩm.");
                return;
            }
    
            for (ChiTietHoaDon cthd : danhSachChon) {
                cthd.setId_HD(idHd);
                chiTietBLL.insertCT(cthd);
            }
        }else JOptionPane.showMessageDialog(null, "Thêm thất bại");
        
        dialog.dispose();
        showHoaDon(v.mainPanel);
    });

    btnHuy.addActionListener(e -> dialog.dispose());

    // ==== THÊM VÀO DIALOG ====
    dialog.add(thongTinKH, BorderLayout.NORTH);
    dialog.add(scrollPane, BorderLayout.CENTER);
    dialog.add(buttons, BorderLayout.SOUTH);
    dialog.setVisible(true);
}

public void XoaHoaDon(View v){
    int row = tableHoaDon.getSelectedRow();
    if(row==-1){
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa", "thông báo", JOptionPane.ERROR_MESSAGE);
    }
    else{
        HoaDon h1 = new HoaDon();
        h1.setId((int) tableHoaDon.getValueAt(row, 0));
        JOptionPane.showMessageDialog(null, hoaDonBLL.deleteHoaDon(h1), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        showHoaDon(v.mainPanel);
    }
}


    public void formSuaTrangThai(View v) {

        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin từ bảng
        int soHoaDon = (int) tableHoaDon.getValueAt(row, 0); // VD: "HD001"
        String trangThaiHienTai = (String) tableHoaDon.getValueAt(row, 4);
        // Tạo dialog
        JDialog dialog = new JDialog((Frame) null, "Sửa trạng thái hóa đơn", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout(10, 10));

        // Tạo combobox với giá trị mặc định là trạng thái hiện tại
        String[] trangThaiOptions = {"Đã đặt", "Đã thanh toán", "Đã hủy"};
        JComboBox<String> comboBox = new JComboBox<>(trangThaiOptions);
        comboBox.setSelectedItem(trangThaiHienTai);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Trạng thái:"));
        centerPanel.add(comboBox);

        // Nút xác nhận
        JButton btnXacNhan = new JButton("Cập nhật");
        btnXacNhan.addActionListener(e -> {
            String trangThaiMoi = (String) comboBox.getSelectedItem();
            HoaDon h = new HoaDon();
            h.setId(soHoaDon);
            h.setStatus(trangThaiMoi);
            JOptionPane.showMessageDialog(null, hoaDonBLL.updateHoaDon(h), "thông báo", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
            showHoaDon(v.mainPanel);
        });

        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(btnXacNhan, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}

