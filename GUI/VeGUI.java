package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import BLL.PhimBLL;
import BLL.VeBLL;
import DTO.HoaDon;
import DTO.LichChieu;
import DTO.Phim;
import DTO.Ve;
import DTO.gheNgoi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Locale;


public class VeGUI {
    public JTable tableVe = new JTable();
    LamDepGUI lamDep = new LamDepGUI();
    VeBLL veBLL = new VeBLL();

    public void showVe(JPanel maiPanel){
        JPanel showVe = new JPanel(new BorderLayout());
        JPanel tk = new JPanel(new FlowLayout());
        JComboBox<String> comTrangThai = new JComboBox<>(new String[]{"Tất cả", "Đã đặt", "Đã thanh toán", "Đã hủy"});
        comTrangThai.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(lamDep.scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
        search.setPreferredSize(new Dimension(40, 32)); // Set kích thước nút nhỏ gọn
        search.setFocusPainted(false); // Bỏ viền focus
        search.setContentAreaFilled(false); // Nền trong suốt (tuỳ chọn)
        search.setBorderPainted(false); // Không viền (tuỳ chọn)
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Khách hàng");
        model.addColumn("Phim");
        model.addColumn("Phòng");
        model.addColumn("Số ghế");
        model.addColumn("Ngày");
        model.addColumn("Giờ");
        model.addColumn("Giá");
        model.addColumn("Trạng thái");

        tableVe.setModel(model);
        tableVe.setRowHeight(30);
        tableVe.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableVe.getColumnModel().getColumn(2).setPreferredWidth(300);
        tableVe.setFont(new Font("Time new roman", Font.PLAIN, 15));
        JTableHeader header = tableVe.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18));
        header.setBackground(new Color(173, 216, 230));

        // styleIconButton(search);
        tk.add(comTrangThai);
        tk.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = (String) comTrangThai.getSelectedItem();
                System.out.println("Condition raw: [" + condition + "]");
                System.out.println("Length: " + condition.length());
                for (char c : condition.toCharArray()) {
                    System.out.print((int)c + " ");
                }
                if(condition.equals("Tất cả")){
                    showVe(maiPanel);
                }
                else showVe(maiPanel,condition);
            }
      
        });
        maiPanel.removeAll();
        maiPanel.setLayout(new BorderLayout());
        ArrayList<Ve> ds = new ArrayList<>();
        ds = veBLL.getArrayList();
        for(Ve v: ds){
            int maVe = v.getId();
            String tenKh = v.getNameUser();
            String tenPhim = v.getNameMovie();
            String tenPhong = v.getNameRoom();
            String soGhe = v.getNameSeat();
            LocalDate ngay = v.getDay();
            LocalTime gio = v.getTime();
            int gia = v.getPrice();
            String trangThai = v.getStatus();
            Object []row = {maVe,tenKh,tenPhim,tenPhong,soGhe,ngay,gio,lamDep.formatVNDPlain(gia),trangThai};
            model.addRow(row);
        }
        maiPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(tableVe);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn
        showVe.add(scrollPane,BorderLayout.CENTER);
        maiPanel.add(showVe, BorderLayout.CENTER);

        maiPanel.revalidate(); // cập nhật lại layout
        maiPanel.repaint();
    }

    public void showVe(JPanel maiPanel,String condition){

        JPanel showVe = new JPanel(new BorderLayout());
        JPanel tk = new JPanel(new FlowLayout());
        JComboBox<String> comTrangThai = new JComboBox<>(new String[]{"Tất cả", "Đã đặt","Đã hủy","Đã thanh toán"});
        comTrangThai.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(lamDep.scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
        search.setPreferredSize(new Dimension(40, 32)); // Set kích thước nút nhỏ gọn
        search.setFocusPainted(false); // Bỏ viền focus
        search.setContentAreaFilled(false); // Nền trong suốt (tuỳ chọn)
        search.setBorderPainted(false); // Không viền (tuỳ chọn)
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Khách hàng");
        model.addColumn("Phim");
        model.addColumn("Phòng");
        model.addColumn("Số ghế");
        model.addColumn("Ngày");
        model.addColumn("Giờ");
        model.addColumn("Giá");
        model.addColumn("Trạng thái");

        tableVe.setModel(model);
        tableVe.setRowHeight(30);
        tableVe.setFont(new Font("Time new roman", Font.PLAIN, 15));
        JTableHeader header = tableVe.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18));
        header.setBackground(new Color(173, 216, 230));

        // styleIconButton(search);
        tk.add(comTrangThai);
        tk.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = ((String) comTrangThai.getSelectedItem());
                System.out.println("Condition raw: [" + condition + "]");
                if(condition.equals("Tất cả")){
                    showVe(maiPanel);
                }
                else showVe(maiPanel,condition);
            }
      
        });
        maiPanel.removeAll();
        maiPanel.setLayout(new BorderLayout());
        ArrayList<Ve> ds = new ArrayList<>();
        veBLL.searchStatus(condition);
        for(Ve v: ds){
            int maVe = v.getId();
            String tenKh = v.getNameUser();
            String tenPhim = v.getNameMovie();
            String tenPhong = v.getNameRoom();
            String soGhe = v.getNameSeat();
            LocalDate ngay = v.getDay();
            LocalTime gio = v.getTime();
            int gia = v.getPrice();
            String trangThai = v.getStatus();
            Object []row = {maVe,tenKh,tenPhim,tenPhong,soGhe,ngay,gio,lamDep.formatVNDPlain(gia),trangThai};
            model.addRow(row);
        }
        maiPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(tableVe);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn
        showVe.add(scrollPane,BorderLayout.CENTER);
        maiPanel.add(showVe, BorderLayout.CENTER);

        maiPanel.revalidate(); // cập nhật lại layout
        maiPanel.repaint();
    }

   


    public void formThemVe( View v){

        JDialog addDialog = new JDialog(v, "Thêm vé mới", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(v);
        
        // ==== THÔNG TIN KHÁCH HÀNG ====
        JPanel thongTinKH = new JPanel(new GridLayout(2, 2, 10, 10));
        thongTinKH.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        JTextField tenKhach = new JTextField();
        JTextField sdt = new JTextField();
        thongTinKH.add(new JLabel("Tên khách hàng:"));
        thongTinKH.add(tenKhach);
        thongTinKH.add(new JLabel("Số điện thoại:"));
        thongTinKH.add(sdt);
        
        // === Chọn phim, lịch chiếu, ghế ===
        JPanel luaChon = new JPanel();
        luaChon.setLayout(new BoxLayout(luaChon, BoxLayout.Y_AXIS));
        luaChon.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));
        JComboBox<String> phim = new JComboBox<>();
        phim.setPreferredSize(new Dimension(150, 25));
        JComboBox<String> lichChieu = new JComboBox<>();
        lichChieu.setPreferredSize(new Dimension(150, 25));
        JComboBox<String> ghe = new JComboBox<>();
        ghe.setPreferredSize(new Dimension(150, 25));
                
        // Load danh sách phim
        ArrayList<Phim> dsPhim = veBLL.dsPhim();
        if (dsPhim != null) {
            for (Phim p : dsPhim) {
                phim.addItem(p.getId() + ":" + p.getName());
            }
        }
        
        // Khi chọn phim => load lịch chiếu
        phim.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                lichChieu.removeAllItems();
                ghe.removeAllItems();
        
                String selectedPhim = (String) phim.getSelectedItem();
                if (selectedPhim != null) {
                    String[] arr = selectedPhim.split(":");
                    int phimId = Integer.parseInt(arr[0]);
                    ArrayList<LichChieu> dsLich = veBLL.dsLichChieu(phimId);
                    if (dsLich != null) {
                        for (LichChieu lc : dsLich) {
                            lichChieu.addItem(lc.getId() + ":" + lc.getTg() + ":" + lc.getTime());
                        }
                    }
                }
            }
        });
        // Khi chọn lịch chiếu => load ghế
        lichChieu.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ghe.removeAllItems();       
                String selectedLC = (String) lichChieu.getSelectedItem();
                if (selectedLC != null) {
                    String[] arr = selectedLC.split(":");
                    int lichChieuId = Integer.parseInt(arr[0]);
                    ArrayList<gheNgoi> dsGhe = veBLL.dsGheNgoi(lichChieuId);
                    if (dsGhe != null) {
                        for (gheNgoi g : dsGhe) {
                            ghe.addItem(g.getId() + ":" + g.getNumber_seat());
                        }
                    }
                }
            }
        });
                
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Chọn phim:"));
        phim.setPreferredSize(new Dimension(180, 25));
        row1.add(phim);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Chọn lịch chiếu:"));
        lichChieu.setPreferredSize(new Dimension(180, 25));
        row2.add(lichChieu);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("Chọn ghế:"));
        ghe.setPreferredSize(new Dimension(180, 25));
        row3.add(ghe);
        
        luaChon.add(row1);
        luaChon.add(row2);
        luaChon.add(row3);
                        
        // === Nút lưu & hủy ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnHuy);
        
        // Xử lý khi bấm Lưu
        btnLuu.addActionListener(e -> {
            String ten = tenKhach.getText().trim();
            String soDT = sdt.getText().trim();
            String gheChon = (String) ghe.getSelectedItem();
            String lichChieuChon = (String) lichChieu.getSelectedItem();
        
            if (ten.isEmpty() || soDT.isEmpty() || gheChon == null || lichChieuChon == null) {
                JOptionPane.showMessageDialog(addDialog, "Vui lòng nhập đầy đủ thông tin!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            String[] gheSplit = gheChon.split(":");
            String[] lichChieuSplit = lichChieuChon.split(":");
        
            Ve v1 = new Ve();
            v1.setNameUser(ten);
            v1.setNumberPhone(soDT);
            v1.setSeat_id(Integer.parseInt(gheSplit[0]));
            v1.setShowTime_id(Integer.parseInt(lichChieuSplit[0]));
            v1.setStatus("Đã thanh toán");
            v1.setPrice(45000);
        
            JOptionPane.showMessageDialog(null, veBLL.insertVe(v1), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            addDialog.dispose();
            showVe(v.mainPanel);
        });
        
        btnHuy.addActionListener(e -> addDialog.dispose());
        
        // Add vào dialog
        addDialog.add(thongTinKH, BorderLayout.NORTH);
        addDialog.add(luaChon, BorderLayout.CENTER);
        addDialog.add(buttonPanel, BorderLayout.SOUTH);
        addDialog.setVisible(true);
        
    }

    public void formSuaVe(View v){
             int row = tableVe.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin từ bảng
        int soVe = (int) tableVe.getValueAt(row, 0); // VD: "HD001"
        String trangThaiHienTai = (String) tableVe.getValueAt(row, 8);
        // Tạo dialog
        JDialog dialog = new JDialog((Frame) null, "Sửa trạng thái vé", true);
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
        btnXacNhan.setSize(50, 50);
        btnXacNhan.addActionListener(e -> {
            String trangThaiMoi = (String) comboBox.getSelectedItem();
            Ve ve = new Ve();
            ve.setId(soVe);
            ve.setStatus(trangThaiMoi);
            JOptionPane.showMessageDialog(null, veBLL.updateVe(ve), "thông báo", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
            showVe(v.mainPanel);
        });

        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(btnXacNhan, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    

    public void xoaVe(View v){
        int row = tableVe.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để xóa", "Thông báo ", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Ve ve = new Ve();
            ve.setId( (int) tableVe.getValueAt(row,0));
            JOptionPane.showMessageDialog(null, veBLL.deleteVe(ve), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            showVe(v.mainPanel);
        }
    }

}

