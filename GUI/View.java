package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.MouseEvent;


import BLL.PhimBLL;
import DTO.Phim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class View extends JFrame implements ActionListener {
    JTable tableVe, tablePhong, tableKhachHang, tableNhanVien;
    JButton home, phim, lichChieu, phongChieu, ve, nhanVien, khachHang, thongKeDoanhThu;
    JButton them , sua, xoa;
    JPanel leftMenu,mainPanel,bottom;
    private String currentMenu = "Home";
    private Phim selectedPhim = null; 
    private JPanel lastSelectedPanel = null;

    private PhimBLL phimBLL = new PhimBLL();
    java.util.List<JButton> menuButtons;

    View() {
        init();
        mainPanel = new JPanel();
        showHome();                  // hiển thị trang chủ
        setActiveMenuButton(home);
        mainPanel.setBackground(new Color(245, 245, 245)); // hoặc bất kỳ màu nào bạn muốn
        this.add(mainPanel, BorderLayout.CENTER); // thêm vào giữa
        setVisible(true);
    }

    void init() {
        setTitle("Quản lí rạp chiếu phim");
        // setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        leftMenu = new JPanel(new GridLayout(0, 1, 10, 10));
        leftMenu.setBackground(Color.white);
        leftMenu.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 20));
        home = new JButton("Trang chủ");
        home.setIcon(scaleIcon("/ICON/Home.png", 32, 32));
        home.addActionListener(this);
        styleButton(home);
        leftMenu.add(home);

        phim = new JButton("Phim");
        phim.setIcon(scaleIcon("/ICON/movies.png", 32, 32));
        phim.addActionListener(this);
        styleButton(phim);
        leftMenu.add(phim);

        lichChieu = new JButton("Lịch chiếu");
        lichChieu.setIcon(scaleIcon("/ICON/showtime.png", 32, 32));
        lichChieu.addActionListener(this);
        styleButton(lichChieu);
        leftMenu.add(lichChieu);

        phongChieu = new JButton("Phòng chiếu");
        phongChieu.setIcon(scaleIcon("/ICON/room.png", 32, 32));
        phongChieu.addActionListener(this);
        styleButton(phongChieu);
        leftMenu.add(phongChieu);

        ve = new JButton("Vé");
        ve.setIcon(scaleIcon("/ICON/Tickets.png", 32, 32));
        ve.addActionListener(this);
        styleButton(ve);
        leftMenu.add(ve);

        nhanVien = new JButton("Nhân viên");
        nhanVien.setIcon(scaleIcon("/ICON/employee.png", 32, 32));
        nhanVien.addActionListener(this);
        styleButton(nhanVien);
        leftMenu.add(nhanVien);

        khachHang = new JButton("Khách hàng");
        khachHang.setIcon(scaleIcon("/ICON/customer.png", 32, 32));
        khachHang.addActionListener(this);
        styleButton(khachHang);
        leftMenu.add(khachHang);

        thongKeDoanhThu = new JButton("Doanh thu");
        thongKeDoanhThu.setIcon(scaleIcon("/ICON/revenue.png", 32, 32));
        thongKeDoanhThu.addActionListener(this);
        styleButton(thongKeDoanhThu);
        leftMenu.add(thongKeDoanhThu);

        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // khoảng cách giữa nút: 20px
        bottom.setBackground(new Color(245, 245, 245)); // màu trắng nhạt
        
        them = new JButton();
        them.setIcon(scaleIcon("/ICON/them.png", 32, 32));
        styleIconButton(them); // áp dụng style giống nhau
        them.addActionListener(this);
        bottom.add(them);
        
        sua = new JButton();
        sua.setIcon(scaleIcon("/ICON/sua.png", 32, 32));
        sua.addActionListener(this);
        styleIconButton(sua);
        bottom.add(sua);
        
        xoa = new JButton();
        xoa.setIcon(scaleIcon("/ICON/xoa.png", 32, 32));
        styleIconButton(xoa);
        xoa.addActionListener(this);
        bottom.add(xoa);

        menuButtons = new java.util.ArrayList<>();
        menuButtons.add(home);
        menuButtons.add(phim);
        menuButtons.add(lichChieu);
        menuButtons.add(phongChieu);
        menuButtons.add(ve);
        menuButtons.add(nhanVien);
        menuButtons.add(khachHang);
        menuButtons.add(thongKeDoanhThu);

        // leftMenu.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 235),3));
        this.add(leftMenu,BorderLayout.WEST);
        this.add(bottom, BorderLayout.SOUTH);
    }

    // Hàm scale icon về cùng kích thước
    public ImageIcon scaleIcon(String path, int width, int height) {
        Image img = Toolkit.getDefaultToolkit().createImage(View.class.getResource(path));
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public void setActiveMenuButton(JButton activeButton) {
        for (JButton btn : menuButtons) {
            if (btn == activeButton) {
                btn.setBackground(new Color(173, 216, 230)); // Màu xanh dương đậm
                btn.setForeground(Color.blue);  // Chữ trắng
                btn.setOpaque(true); // Cho phép hiển thị nền
            } else {
                btn.setBackground(Color.white); // Màu gốc
                btn.setForeground(Color.BLACK);              // Chữ đen
                btn.setOpaque(true);
            }
        }
    }
    
    // Hàm căn giữa icon/text trong JButton
    public void styleButton(JButton btn) {
        btn.setFocusPainted(false);
         btn.setBorderPainted(false);     // ẩn viền
         btn.setContentAreaFilled(false); // nếu muốn nền trong suốt
        btn.setHorizontalAlignment(SwingConstants.LEFT); // icon + text nằm bên trái
        btn.setIconTextGap(10); // khoảng cách giữa icon và text
        btn.setFont(new Font("Arial", Font.BOLD, 16));
    }

    public void styleIconButton(JButton btn) {
        btn.setFocusPainted(false);
        // btn.setContentAreaFilled(false); // nếu muốn nền trong suốt
        // btn.setBorderPainted(false);     // ẩn viền
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // đổi con trỏ khi hover
    }

    public ImageIcon scaleImageToFit(String path, int width, int height) {
        Image img = Toolkit.getDefaultToolkit().createImage(View.class.getResource(path));
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
          Object src = e.getSource();

    // Xử lý sự kiện của menu
    if (src instanceof JButton && menuButtons.contains(src)) {
        setActiveMenuButton((JButton) src);
        
        // Cập nhật trạng thái menu hiện tại
        if (src == home) {
            currentMenu = "Home";
            showHome();  // Hiển thị trang chủ
        } else if (src == phim) {
            currentMenu = "Phim";
            showPhim();  // Hiển thị danh sách phim
        } else if (src == lichChieu) {
            currentMenu = "LichChieu";
            showLichChieu(); // Hiển thị danh sách lịch chiếu
        } else if (src == phongChieu) {
            currentMenu = "PhongChieu";
            showPhongChieu(); // Hiển thị danh sách phòng chiếu
        } else if (src == ve) {
            currentMenu = "Ve";
            showVe();  // Hiển thị danh sách vé
        } else if (src == nhanVien) {
            currentMenu = "NhanVien";
            showNhanVien(); // Hiển thị danh sách nhân viên
        } else if (src == khachHang) {
            currentMenu = "KhachHang";
            showKhachHang(); // Hiển thị danh sách khách hàng
        } else if (src == thongKeDoanhThu) {
            currentMenu = "DoanhThu";
            showThongKe();  // Hiển thị thống kê doanh thu
        }
    }

    // Xử lý các nút Thêm, Sửa, Xóa sau khi đã chọn menu
    if (src == them) {
        handleAddAction();
    } else if (src == sua) {
        handleEditAction();
    } else if (src == xoa) {
        handleDeleteAction();
    }

    }
    

    ///////////     CÁC HÀM XỬ LÍ THÊM SỬA XÓA      ////////////////////////
    
    private void handleDeleteAction() {
        switch (currentMenu) {
            case "Phim":
                if (selectedPhim == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phim để xóa.");
                    return;
                }
    
                int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn xóa phim: " + selectedPhim.getName() + "?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
                );
    
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this,phimBLL.deletePhim(selectedPhim),"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    selectedPhim = null;
                    showPhim(); // Refresh lại danh sách
                }
            break;
            case "LichChieu":


            break;
                
        }
    }

    private void handleEditAction() {
        switch (currentMenu) {
            case "Phim":
                if (selectedPhim == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phim để sửa");
                    return;
                }
                    formSuaPhim(selectedPhim);
            break;
            case "LichChieu":

            break;

            default:
                break;
        }
    }

    private void handleAddAction() {
        switch (currentMenu) {
            case "Phim":
                formThemPhim();
            break;
               
    }
}

    ////            CÁC HÀM HIỂN THỊ CÁC DANH SÁCH LEFT MENU       ///////////////////////////////

    public void showThongKe() {
        mainPanel.removeAll();
    }

    public void showKhachHang() {
        mainPanel.removeAll();
    }

    public void showNhanVien() {
        mainPanel.removeAll();
    }

    public void showVe() {
        mainPanel.removeAll();
        
    }

    public void showPhongChieu() {
        mainPanel.removeAll();
    }

    public void showLichChieu() {
        mainPanel.removeAll();
    }

    public void showPhim() {
        JPanel showPhim = new JPanel(new GridLayout(0,5,15,15));
        showPhim.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        timKiem.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
        search.setPreferredSize(new Dimension(40, 32)); // Set kích thước nút nhỏ gọn
        search.setFocusPainted(false); // Bỏ viền focus
        search.setContentAreaFilled(false); // Nền trong suốt (tuỳ chọn)
        search.setBorderPainted(false); // Không viền (tuỳ chọn)
        // styleIconButton(search);
        tk.add(timKiem);
        tk.add(search);
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showPhim();
                }
                else showPhim(condition);
            }
            
        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<Phim> ds = new ArrayList<>();
        ds = phimBLL.getArrayList();
        for(Phim p:ds){
            String url = p.getUrl();
            String name = p.getName();
            String type = p.getType();
            String duration = p.getDuration();
            JPanel j = new JPanel();
            ImageIcon i = resizeImage(url);
            j = taoPanelPhim(i, name, type, duration,p);
            showPhim.add(j);
        }
        // tk.setBackground(new Color(173, 216, 230));
        mainPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(showPhim);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // không cuộn ngang
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn

        mainPanel.add(scrollPane, BorderLayout.CENTER);


        mainPanel.revalidate(); // cập nhật lại layout
        mainPanel.repaint();

    }
    public void showPhim(String condition) {
        JPanel showPhim = new JPanel(new GridLayout(0,5,15,15));
        showPhim.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        timKiem.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
        search.setPreferredSize(new Dimension(40, 32)); // Set kích thước nút nhỏ gọn
        search.setFocusPainted(false); // Bỏ viền focus
        search.setContentAreaFilled(false); // Nền trong suốt (tuỳ chọn)
        search.setBorderPainted(false); // Không viền (tuỳ chọn)
        // styleIconButton(search);
        tk.add(timKiem);
        tk.add(search);
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showPhim();
                }
                else showPhim(condition);
            }
            
        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<Phim> ds = new ArrayList<>();
        ds = phimBLL.searchName(condition);
        for(Phim p:ds){
            String url = p.getUrl();
            String name = p.getName();
            String type = p.getType();
            String duration = p.getDuration();
            JPanel j = new JPanel();
            ImageIcon i = resizeImage(url);
            j = taoPanelPhim(i, name, type, duration,p);
            showPhim.add(j);
        }
        if(ds.size()<11){
            for(int i = 1;i<=11-ds.size();i++)
            {
                JPanel j1 = new JPanel();
                j1.setVisible(false);
                showPhim.add(j1);
            }
        }
        // tk.setBackground(new Color(173, 216, 230));
        mainPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(showPhim);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // không cuộn ngang
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn

        mainPanel.add(scrollPane, BorderLayout.CENTER);


        mainPanel.revalidate(); // cập nhật lại layout
        mainPanel.repaint();

    }
    

    public void showHome(){
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        // Lấy kích thước của mainPanel
        JLabel title = new JLabel("Quản lí rạp chiếu phim", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        // title.setBorder(BorderFactory.createLineBorder(Color.gray,2));

        int panelWidth = mainPanel.getWidth();
        int panelHeight = mainPanel.getHeight() - 70;

        // Nếu panel chưa có kích thước do chưa render lần đầu
        if (panelWidth == 0 || panelHeight == 0) {
            panelWidth = 800;
            panelHeight = 600;
        }

        ImageIcon scaledImage = scaleImageToFit("/ICON/anhHome.jpg", panelWidth, panelHeight);
        JLabel imageLabel = new JLabel(scaledImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(title,BorderLayout.NORTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // ///  hàm tạo 1 bộ phim //// 

    public JPanel taoPanelPhim(ImageIcon anhPhim, String tenPhim, String theLoai, String thoiLuong,Phim phim) {
        JPanel panelPhim = new JPanel();
        panelPhim.setLayout(new BoxLayout(panelPhim, BoxLayout.Y_AXIS)); // dọc
        panelPhim.setPreferredSize(new Dimension(150, 250)); // cố định kích thước panel (nếu muốn)
    
        // Label hiển thị ảnh phim
        JLabel lblAnh = new JLabel(anhPhim);
        lblAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
        // lblAnh.setPreferredSize(new Dimension(120, 180)); // đảm bảo không chiếm quá nhiều
        // lblAnh.setMaximumSize(new Dimension(120, 180));   // ép ảnh nằm trong kích thước
        lblAnh.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
    
        // Label tên phim
        JLabel lblTen = new JLabel("Tên phim: " + tenPhim);
        lblTen.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTen.setFont(new Font("Arial", Font.BOLD, 14));
        // lblTen.setMaximumSize(new Dimension(200, 20));
        
        // Label thể loại
        JLabel lblTheLoai = new JLabel("Thể loại: " + theLoai);
        lblTheLoai.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTheLoai.setFont(new Font("Arial", Font.BOLD, 14));
        // lblTheLoai.setMaximumSize(new Dimension(200, 20));
    
        // Label thời lượng
        JLabel lblThoiLuong = new JLabel("Thời lượng: " + thoiLuong+"p");
        lblThoiLuong.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblThoiLuong.setFont(new Font("Arial", Font.BOLD, 14));
        // lblThoiLuong.setMaximumSize(new Dimension(200, 20));
    
        // Thêm các thành phần vào panel
        panelPhim.add(lblAnh);
        panelPhim.add(lblTen);
        panelPhim.add(lblTheLoai);
        panelPhim.add(lblThoiLuong);
    
        // Viền cho đẹp
        panelPhim.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0),3));
        panelPhim.setBackground(new Color(247, 231, 206));
        panelPhim.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            selectedPhim = phim;
            highlightSelectedPanel(panelPhim);
        }


        private void highlightSelectedPanel(JPanel panelPhim) {
            if (lastSelectedPanel != null) {
                lastSelectedPanel.setBackground(new Color(247, 231, 206)); // Bỏ màu panel trước đó
            }
            panelPhim.setBackground(Color.LIGHT_GRAY); // Highlight panel hiện tại
            lastSelectedPanel = panelPhim;
        }
    });
        return panelPhim;
    }
    
    // hàm resize ảnh cho bộ phim
    public ImageIcon resizeImage(String duongDanAnh) {
        ImageIcon iconGoc = new ImageIcon(duongDanAnh);
        Image anhGoc = iconGoc.getImage();
        Image anhResize = anhGoc.getScaledInstance(200,180, Image.SCALE_SMOOTH);
        return new ImageIcon(anhResize);
    }

    ////  CÁC HÀM HIỂN THỊ FORM SỬA THÔNG TIN ///
     
    public void formSuaPhim(Phim p){

    JDialog editDialog = new JDialog(this, "Sửa thông tin phim", true);
    editDialog.setSize(400, 300);
    editDialog.setLocationRelativeTo(this);
    editDialog.setLayout(new GridLayout(5, 2, 10, 10));

    // Tạo các field
    JTextField tfTen = new JTextField(p.getName());
    JTextField tfTheLoai = new JTextField(p.getType());
    JTextField tfThoiLuong = new JTextField(p.getDuration());
    JTextField tfUrl = new JTextField(p.getUrl());

    // Thêm vào dialog
    editDialog.add(new JLabel("Tên phim:"));
    editDialog.add(tfTen);
    editDialog.add(new JLabel("Thể loại:"));
    editDialog.add(tfTheLoai);
    editDialog.add(new JLabel("Thời lượng:"));
    editDialog.add(tfThoiLuong);
    editDialog.add(new JLabel("URL ảnh:"));
    editDialog.add(tfUrl);

    JButton btnLuu = new JButton("Lưu");
    JButton btnHuy = new JButton("Hủy");

    editDialog.add(btnLuu);
    editDialog.add(btnHuy);

    btnLuu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            p.setName(tfTen.getText());
            p.setType(tfTheLoai.getText());
            p.setDuration(tfThoiLuong.getText());
            p.setUrl(tfUrl.getText());
            JOptionPane.showMessageDialog(null, phimBLL.updatePhim(p),"Thông báo", JOptionPane.INFORMATION_MESSAGE);
            editDialog.dispose();
            showPhim(); 
        }      
    });
    btnHuy.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            editDialog.dispose();
        } 
    });
        editDialog.setVisible(true);
    }

    //    form thêm đối tượng    //
    public void formThemPhim(){
        JDialog addDialog = new JDialog(this, "Thêm phim mới", true);
        addDialog.setSize(400, 300);
        addDialog.setLocationRelativeTo(this);
        addDialog.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField tfId = new JTextField();
        JTextField tfTen = new JTextField();
        JTextField tfTheLoai = new JTextField();
        JTextField tfThoiLuong = new JTextField();
        JTextField tfUrl = new JTextField();

        addDialog.add(new JLabel("ID phim:"));
        addDialog.add(tfId);
        addDialog.add(new JLabel("Tên phim:"));
        addDialog.add(tfTen);
        addDialog.add(new JLabel("Thể loại:"));
        addDialog.add(tfTheLoai);
        addDialog.add(new JLabel("Thời lượng:"));
        addDialog.add(tfThoiLuong);
        addDialog.add(new JLabel("URL ảnh:"));
        addDialog.add(tfUrl);

        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");

        addDialog.add(btnLuu);
        addDialog.add(btnHuy);
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tfId.getText());
                String ten = tfTen.getText().trim();
                String theLoai = tfTheLoai.getText().trim();
                String thoiLuong = tfThoiLuong.getText().trim();
                String url = tfUrl.getText().trim();

                if (ten.isEmpty() || theLoai.isEmpty() || thoiLuong.isEmpty()) {
                    JOptionPane.showMessageDialog(addDialog, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                Phim p = new Phim(ten, theLoai, thoiLuong, url);
                p.setId(id);
                JOptionPane.showMessageDialog(null, phimBLL.insertPhim(p), "thông báo", JOptionPane.INFORMATION_MESSAGE);
                addDialog.dispose();
                showPhim();    
            }
            
        });
        btnHuy.addActionListener(e->addDialog.dispose());
        addDialog.setVisible(true);

}

}


