package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import BLL.KhachHangBLL;
import DTO.Nguoi;
import DTO.SanPham;
import DTO.Ve;


public class KhachHangGUI {
    public JTable tableKhachHang = new JTable();
    KhachHangBLL khachHangBLL = new KhachHangBLL();
    LamDepGUI lamDep = new LamDepGUI();
//    public Nguoi selectedKH = null;



    public void showKhachHang(JPanel mainPanel,View v){
        JPanel showKhachHang = new JPanel(new BorderLayout());
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        timKiem.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(lamDep.scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
        search.setPreferredSize(new Dimension(40, 32)); // Set kích thước nút nhỏ gọn
        search.setFocusPainted(false); // Bỏ viền focus
        search.setContentAreaFilled(false); // Nền trong suốt (tuỳ chọn)
        search.setBorderPainted(false); // Không viền (tuỳ chọn)

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        model.addColumn("ID");
        model.addColumn("Tên Khách hàng");
        model.addColumn("SĐT");

        tableKhachHang.setModel(model);
        tableKhachHang.setRowHeight(30);
        // tableKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
        // tableKhachHang.getColumnModel().getColumn(4).setPreferredWidth(300);
        tableKhachHang.setFont(new Font("Time new roman", Font.PLAIN, 15));
        JTableHeader header = tableKhachHang.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18));
        header.setBackground(new Color(173, 216, 230));

        // styleIconButton(search);
        tk.add(timKiem);
        tk.add(search);
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showKhachHang(mainPanel,v);
                }
                else showKhachHang(mainPanel,condition,v);
            }

        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<Nguoi> ds = new ArrayList<>();
        ds = khachHangBLL.getArrayList();
        for(Nguoi kh: ds){
            int maKh = kh.getId();
            String tenKh = kh.getName();
            String sđt = kh.getNumberPhone();

            Object []row = {maKh,tenKh,sđt};
            model.addRow(row);
        }
        JPanel chiTiet = new JPanel(new FlowLayout());
        JButton xemHoaDon = new JButton("Sản phẩm đã mua");
        JButton xemVe = new JButton("Vé đã đặt");
        chiTiet.add(xemHoaDon);
        chiTiet.add(xemVe);
        xemHoaDon.addActionListener(e->{
            int row = tableKhachHang.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null, "vui lòng chọn một khách hàng để xem", "thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            xemHoaDonDaMua(row, v);
        });
        xemVe.addActionListener(e->{
            int row = tableKhachHang.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null, "vui lòng chọn một khách hàng để xem", "thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            xemVeDaDat(row, v);
        });
        mainPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn
        showKhachHang.add(scrollPane,BorderLayout.CENTER);
        mainPanel.add(showKhachHang, BorderLayout.CENTER);
        mainPanel.add(chiTiet,BorderLayout.SOUTH);

        mainPanel.revalidate(); // cập nhật lại layout
        mainPanel.repaint();
    }

    public void showKhachHang(JPanel mainPanel,String condition,View v){

        JPanel showKhachHang = new JPanel(new BorderLayout());
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        timKiem.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(lamDep.scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
        search.setPreferredSize(new Dimension(40, 32)); // Set kích thước nút nhỏ gọn
        search.setFocusPainted(false); // Bỏ viền focus
        search.setContentAreaFilled(false); // Nền trong suốt (tuỳ chọn)
        search.setBorderPainted(false); // Không viền (tuỳ chọn)
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        model.addColumn("ID");
        model.addColumn("Tên Khách hàng");
        model.addColumn("SĐT");
        model.addColumn("Chi tiết");

        tableKhachHang.setModel(model);
        tableKhachHang.setRowHeight(30);
        tableKhachHang.setFont(new Font("Time new roman", Font.PLAIN, 15));
        JTableHeader header = tableKhachHang.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18));
        header.setBackground(new Color(173, 216, 230));

        // styleIconButton(search);
        tk.add(timKiem);
        tk.add(search);
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showKhachHang(mainPanel,v);
                }
                else showKhachHang(mainPanel,condition,v);
            }

        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<Nguoi> ds = new ArrayList<>();
        ds = khachHangBLL.searchName(condition);
        for(Nguoi kh:ds){
            int maKh = kh.getId();
            String tenKh = kh.getName();
            String sđt = kh.getNumberPhone();
            Object []row = {maKh,tenKh,sđt};
            model.addRow(row);
        }
        JPanel chiTiet = new JPanel(new FlowLayout());
        JButton xemHoaDon = new JButton("Sản phẩm đã mua");
        JButton xemVe = new JButton("Vé đã đặt");
        chiTiet.add(xemHoaDon);
        chiTiet.add(xemVe);
        xemHoaDon.addActionListener(e->{
            int row = tableKhachHang.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null, "vui lòng chọn một khách hàng để xem", "thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            xemHoaDonDaMua(row, v);
        });
        xemVe.addActionListener(e->{
            int row = tableKhachHang.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null, "vui lòng chọn một khách hàng để xem", "thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            xemVeDaDat(row, v);
        });
        mainPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn
        showKhachHang.add(scrollPane,BorderLayout.CENTER);
        mainPanel.add(showKhachHang, BorderLayout.CENTER);
        mainPanel.add(chiTiet,BorderLayout.SOUTH);

        mainPanel.revalidate(); // cập nhật lại layout
        mainPanel.repaint();

    }


public void formThemKH(View v) {
    JDialog addDialog = new JDialog(v, "Thêm khách hàng mới", true);
    addDialog.setSize(400, 300);
    addDialog.setLocationRelativeTo(v);
    addDialog.setLayout(new BorderLayout(10, 10));

    JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 70));
    formPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 20, 15));

    JTextField tfTen = new JTextField();
    JTextField tfSĐT = new JTextField(); // Renamed to avoid non-ASCII character

    formPanel.add(new JLabel("Tên KH:"));
    formPanel.add(tfTen);

    formPanel.add(new JLabel("SĐT:"));
    formPanel.add(tfSĐT);
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

    JButton btnLuu = new JButton("Lưu");
    JButton btnHuy = new JButton("Hủy");

    buttonPanel.add(btnLuu);
    buttonPanel.add(btnHuy);

    addDialog.add(formPanel, BorderLayout.CENTER);
    addDialog.add(buttonPanel, BorderLayout.SOUTH);

    JLabel titleLabel = new JLabel("THÔNG TIN KHÁCH HÀNG", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    addDialog.add(titleLabel, BorderLayout.NORTH);

    // Xử lý lưu
    btnLuu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
    
                String ten = tfTen.getText().trim();
            
                String sdt = tfSĐT.getText().trim();

                //  Kiểm tra trường trống
                if (ten.isEmpty() || sdt.isEmpty() ) {
                    JOptionPane.showMessageDialog(addDialog, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Kiểm tra số điện thoại
                if (!isValidVietnamesePhone(sdt)) {
                    JOptionPane.showMessageDialog(addDialog, "Số điện thoại không hợp lệ! Vui lòng nhập đúng định dạng số điện thoại Việt Nam.",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    tfSĐT.requestFocus();
                    return;
                }

                // Tạo và lưu đối tượng khách hàng
                Nguoi kh = new Nguoi();
                kh.setName(ten);
                kh.setNumberPhone(sdt);
                kh.setStatus("T");
                JOptionPane.showMessageDialog(addDialog, khachHangBLL.insertKhachHang(kh), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    addDialog.dispose();
                    showKhachHang(v.mainPanel,v);
                

        }
    });

    btnHuy.addActionListener(e -> addDialog.dispose());

    addDialog.getRootPane().setDefaultButton(btnLuu);

    addDialog.setVisible(true);
}

    public void formXoaKH(View v){
        int row = tableKhachHang.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để xóa", "Thông báo ", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn xóa khách hàng này: " ,
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Nguoi kh = new Nguoi();
                kh.setId( (int) tableKhachHang.getValueAt(row,0));
                JOptionPane.showMessageDialog(null,khachHangBLL.deleteKhachHang(kh),"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                showKhachHang(v.mainPanel,v); // Refresh lại danh sách
            }
        }


    }


    public void formSuaKH(View v) {
        int row = tableKhachHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tenKH = (String) tableKhachHang.getValueAt(row, 1);
        String sđtKH = (String) tableKhachHang.getValueAt(row, 2);

        JDialog editDialog = new JDialog(v, "Sửa thông tin khách hàng", true);
        editDialog.setSize(400, 300);
        editDialog.setLocationRelativeTo(v);
        editDialog.setLayout(new BorderLayout(10, 10));

        // Panel chứa form
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        // Tạo các field
        JTextField tfTen = new JTextField(tenKH);
        JTextField tfSĐT = new JTextField(sđtKH);

        // Thêm vào form panel
        formPanel.add(new JLabel("Tên KH:"));
        formPanel.add(tfTen);
        formPanel.add(new JLabel("SĐT:"));
        formPanel.add(tfSĐT);


        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");

        // Thêm nút vào panel
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnHuy);

        // Thêm panels vào dialog
        editDialog.add(formPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm tiêu đề
        JLabel titleLabel = new JLabel("SỬA THÔNG TIN KHÁCH HÀNG", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        editDialog.add(titleLabel, BorderLayout.NORTH);

        // Lưu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int maKH = (int ) tableKhachHang.getValueAt(row,0);
                    String ten = tfTen.getText().trim();
                    String sđt = tfSĐT.getText().trim();
                    // Kiểm tra trường hop trống
                    if (ten.isEmpty() || sđt.isEmpty() ) {
                        JOptionPane.showMessageDialog(editDialog, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Kiểm tra định dạng số điện thoại
                    if (!isValidVietnamesePhone(sđt)) {
                        JOptionPane.showMessageDialog(editDialog, "Số điện thoại không hợp lệ! Vui lòng nhập đúng định dạng số điện thoại Việt Nam.",
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                        tfSĐT.requestFocus();
                        return;
                    }
             
                    Nguoi kh = new Nguoi();
                    kh.setId(maKH);
                    kh.setName(ten);
                    kh.setNumberPhone(sđt);

                    JOptionPane.showMessageDialog(null, khachHangBLL.updateKhachHang(kh), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    editDialog.dispose();
                    showKhachHang(v.mainPanel,v);
               
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose();
            }
        });

        // Set default button
        editDialog.getRootPane().setDefaultButton(btnLuu);
        editDialog.setVisible(true);
    }


    public boolean isValidVietnamesePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        // Loại bỏ khoảng trắng, dấu gạch ngang và dấu chấm
        String phoneNumber = phone.replaceAll("\\s|-|\\.", "");

        // Kiểm tra định dạng +84xxxxxxxxx
        if (phoneNumber.startsWith("+84")) {
            return phoneNumber.length() == 12 && phoneNumber.substring(1).matches("\\d+");
        }

        // Kiểm tra định dạng 84xxxxxxxxx
        if (phoneNumber.startsWith("84")) {
            return phoneNumber.length() == 11 && phoneNumber.matches("\\d+");
        }

        // Kiểm tra định dạng 0xxxxxxxxx
        if (phoneNumber.startsWith("0")) {
            return phoneNumber.length() == 10 && phoneNumber.matches("\\d+");
        }

        return false;
    }


    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Pattern cơ bản cho email hợp lệ
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }

    public void xemHoaDonDaMua(int row,View v){
        ArrayList<SanPham> dsSanPhamDaMua = new ArrayList<>();
        int id= (int) tableKhachHang.getValueAt(row, 0);
        dsSanPhamDaMua = khachHangBLL.dsSanPhamDaMua(id);
        JDialog xemCT = new JDialog();
        xemCT.setSize(600, 300);
        xemCT.setLocationRelativeTo(v);
        xemCT.setLayout(new BorderLayout());
        JTable chiTiet = new JTable();
        chiTiet.setRowHeight(30);
        chiTiet.setFont(new Font("Time new roman", Font.BOLD, 14));
        DefaultTableModel modelCT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        modelCT.addColumn("Sản phẩm");
        modelCT.addColumn("SL");
        modelCT.addColumn("Đơn giá");
        modelCT.addColumn("Tổng tiền");
        JTableHeader h = chiTiet.getTableHeader();
      
        h.setFont(new Font("Time new roman", Font.BOLD, 18));
        h.setBackground(new Color(173, 216, 230));
        chiTiet.setModel(modelCT);
        int tc = 0;
        for(SanPham sp : dsSanPhamDaMua){
            tc += sp.getTongGia();
            Object [] roww = {sp.getName(),sp.getTongSL(),lamDep.formatVNDPlain(sp.getPrice()),lamDep.formatVNDPlain(sp.getTongGia())};
            modelCT.addRow(roww);
        }
        chiTiet.getColumnModel().getColumn(0).setPreferredWidth(200);
        chiTiet.getColumnModel().getColumn(1).setPreferredWidth(20);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel tongCong = new JLabel("Tổng cộng: "+lamDep.formatVNDPlain(tc));
        tongCong.setFont(new Font("Time new roman", Font.BOLD, 20));
        bottom.add(tongCong);

        JScrollPane j = new JScrollPane(chiTiet);
        xemCT.add(j,BorderLayout.CENTER);
        xemCT.add(tongCong,BorderLayout.SOUTH);
        xemCT.setVisible(true);

    }
    public void xemVeDaDat(int row,View v){
        ArrayList<Ve> dsVe = new ArrayList<>();
        int id= (int) tableKhachHang.getValueAt(row, 0);
        dsVe = khachHangBLL.dsVeDaDat(id);
        JDialog xemCT = new JDialog();
        xemCT.setSize(800, 300);
        xemCT.setLocationRelativeTo(v);
        xemCT.setLayout(new BorderLayout());
        JTable chiTiet = new JTable();
        chiTiet.setRowHeight(30);
        chiTiet.setFont(new Font("Time new roman", Font.BOLD, 14));
        DefaultTableModel modelCT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        modelCT.addColumn("Mã vé");
        modelCT.addColumn("Phim");
        modelCT.addColumn("Phòng");
        modelCT.addColumn("Số ghế");
        modelCT.addColumn("Ngày chiếu");
        modelCT.addColumn("Giờ chiếu");
        modelCT.addColumn("Giá vé");
        JTableHeader h = chiTiet.getTableHeader();
      
        h.setFont(new Font("Time new roman", Font.BOLD, 18));
        h.setBackground(new Color(173, 216, 230));
        chiTiet.setModel(modelCT);
        int tc = 0;
        for(Ve ve : dsVe){
            tc += ve.getPrice();
            Object [] roww = {ve.getId(),ve.getNameMovie(),ve.getNameRoom(),ve.getNameSeat(),ve.getDay(),ve.getTime(),lamDep.formatVNDPlain(ve.getPrice())};
            modelCT.addRow(roww);
        }
        chiTiet.getColumnModel().getColumn(0).setPreferredWidth(30);
        chiTiet.getColumnModel().getColumn(1).setPreferredWidth(150);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        JLabel tongCong = new JLabel("Tổng cộng: "+lamDep.formatVNDPlain(tc));
        tongCong.setFont(new Font("Time new roman", Font.BOLD, 20));
        bottom.add(tongCong);

        JScrollPane j = new JScrollPane(chiTiet);
        xemCT.add(j,BorderLayout.CENTER);
        xemCT.add(tongCong,BorderLayout.SOUTH);
        xemCT.setVisible(true);

    }

}

