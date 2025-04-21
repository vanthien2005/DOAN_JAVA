package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import BLL.SanPhamBLL;

import java.awt.event.MouseEvent;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class View extends JFrame implements ActionListener {
    public JButton home, phim, lichChieu, phongChieu, ve, nhanVien, khachHang, thongKeDoanhThu,sanPham,hoaDon;
    public JButton them , sua, xoa;
    public JPanel leftMenu,mainPanel,bottom;
    SanPhamBLL sanPhamBLL = new SanPhamBLL();
    LamDepGUI lamDep = new LamDepGUI();
    PhimGUI phimGUI = new PhimGUI();
    VeGUI veGUI = new VeGUI();
    SanPhamGUI sanPhamGUI = new SanPhamGUI();
    LichChieuGUI lichGUI = new LichChieuGUI();
    HoaDonGUI hoaDonGUI = new HoaDonGUI();
    DoanhThuGUI doanhThuGUI = new DoanhThuGUI();

    private String currentMenu = "Home";
    
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
        // setLocationRelativeTo(null);
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

        sanPham  =new JButton("Sản Phẩm");
        sanPham.setIcon(scaleIcon("/ICON/product.png", 32, 32));
        sanPham.addActionListener(this);
        styleButton(sanPham);
        leftMenu.add(sanPham);

        hoaDon  =new JButton("Hóa Đơn");
        hoaDon.setIcon(scaleIcon("/ICON/order.png", 32, 32));
        hoaDon.addActionListener(this);
        styleButton(hoaDon);
        leftMenu.add(hoaDon);

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
        leftMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
        menuButtons.add(sanPham);
        menuButtons.add(hoaDon);
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
        btn.setFont(new Font("Arial", Font.BOLD, 18));
    }

    public void styleIconButton(JButton btn) {
        btn.setFocusPainted(false);
        // btn.setContentAreaFilled(false); // nếu muốn nền trong suốt
        // btn.setBorderPainted(false);     // ẩn viền
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // đổi con trỏ khi hover
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
            phimGUI.showPhim(mainPanel);  // Hiển thị danh sách phim
        } else if (src == lichChieu) {
            currentMenu = "LichChieu";
            lichGUI.showLichChieu(mainPanel); // Hiển thị danh sách lịch chiếu
        } else if (src == phongChieu) {
            currentMenu = "PhongChieu";
             // Hiển thị danh sách phòng chiếu
        } else if(src==sanPham){
            currentMenu = "SanPham";
            sanPhamGUI.showSanPham(mainPanel);
        }else if(src==hoaDon){
            currentMenu  = "HoaDon";
            hoaDonGUI.showHoaDon(mainPanel);
        }
        else if (src == ve) {
            currentMenu = "Ve";
            veGUI.showVe(mainPanel);  // Hiển thị danh sách vé
        } else if (src == nhanVien) {
            currentMenu = "NhanVien";
          // Hiển thị danh sách nhân viên
        } else if (src == khachHang) {
            currentMenu = "KhachHang";
            // Hiển thị danh sách khách hàng
        } else if (src == thongKeDoanhThu) {
            currentMenu = "DoanhThu";
            doanhThuGUI.showDoanhThu(mainPanel);  // Hiển thị thống kê doanh thu
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
                phimGUI.xoaPhim(this);
            break;
            case "LichChieu":
                lichGUI.xoaLichChieu(this);
            break;

            case "Phong":

            break;
            case "SanPham":
          
                sanPhamGUI.xoaSanPham(sanPhamGUI.selectedSanPham, this);
            break;

            case "HoaDon":
            hoaDonGUI.XoaHoaDon(this);
            

            break;
            case "Ve":
                veGUI.xoaVe(this);

            break;
            case "NhanVien":

            break;
            case "KhachHang":

            break;

            case "DoanhThu":
            
            break;
      
        }
    }

    private void handleEditAction() {
        switch (currentMenu) {
            case "Phim":
                if (phimGUI.selectedPhim == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phim để sửa");
                    return;
                }
                    phimGUI.formSuaPhim(phimGUI.selectedPhim,this);
            break;
            case "LichChieu":
                if(lichGUI.selectedLichChieu==null){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một lịch chiếu để sửa");
                    return;
                }
                lichGUI.formSuaLichChieu(lichGUI.selectedLichChieu, this);
            break;
            case "Phong":

            break;
            case "SanPham":
            if(sanPhamGUI.selectedSanPham==null){
                JOptionPane.showMessageDialog(null, "vui lòng chọn 1 sản phẩm để sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            sanPhamGUI.formSuaSanPham(sanPhamGUI.selectedSanPham, this);
            break;
            case "HoaDon":
            hoaDonGUI.formSuaTrangThai(this);

            break;
            case "Ve":
            veGUI.formSuaVe(this);
           
            break;
            case "NhanVien":

            break;
            case "KhachHang":

            break;

            case "DoanhThu":
            break;

            default:
                break;
        }
    }

    private void handleAddAction() {
        switch (currentMenu) {
            case "Phim":
                phimGUI.formThemPhim(this);
            break;
            case "LichChieu":
                lichGUI.formThemLichChieu(this);  
            break;
            case "Phong":

            break;
            case "SanPham":
            sanPhamGUI.formThemSanPham(this);
            break;

            case "HoaDon":

            hoaDonGUI.formThemHoaDon(this, sanPhamBLL.getArrayList());
            
            break;
            case "Ve":
                veGUI.formThemVe(this);
            break;
            case "NhanVien":

            break;
            case "KhachHang":

            break;

            case "DoanhThu":
            break;
    }
}

    ////            CÁC HÀM HIỂN THỊ CÁC DANH SÁCH LEFT MENU       ///////////////////////////////

    
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

        ImageIcon scaledImage = lamDep.scaleImageToFit("/ICON/anhHome.jpg", panelWidth, panelHeight);
        JLabel imageLabel = new JLabel(scaledImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(title,BorderLayout.NORTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
}


