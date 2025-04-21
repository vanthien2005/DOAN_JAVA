package GUI;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.MouseEvent;
import java.io.File;
import java.text.NumberFormat;


import BLL.SanPhamBLL;

import DTO.SanPham;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class SanPhamGUI {
    public SanPham selectedSanPham = null;
    SanPhamBLL sanPhamBLL  =new SanPhamBLL();
    LamDepGUI lamDep = new LamDepGUI();

    public void showSanPham(JPanel mainPanel){
        JPanel showSanPham = new JPanel(new GridLayout(0,4,20,20));
        showSanPham.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        timKiem.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(lamDep.scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
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
                    showSanPham(mainPanel);
                }
                else showSanPham(condition,mainPanel);
            }
            
        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<SanPham> ds = new ArrayList<>();
        ds = sanPhamBLL.getArrayList();
        for(SanPham p:ds){
            String url = p.getImage();
            String name = p.getName();
            String type = p.getType();
            int price = p.getPrice();
            JPanel j = new JPanel();
            ImageIcon i = lamDep.resizeImage(url,230,200);
            j = taoPanelPhim(i, name, type, price,p);
            showSanPham.add(j);
        }
        // tk.setBackground(new Color(173, 216, 230));
        mainPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(showSanPham);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // không cuộn ngang
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn

        mainPanel.add(scrollPane, BorderLayout.CENTER);


        mainPanel.revalidate(); // cập nhật lại layout
        mainPanel.repaint();


    }
    private void showSanPham(String condition, JPanel mainPanel) {
        JPanel showSanPham = new JPanel(new GridLayout(0,4,20,20));
        showSanPham.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        timKiem.setPreferredSize(new Dimension(250, 25));
        JButton search = new JButton();
        search.setIcon(lamDep.scaleImageToFit("/ICON/search.png", 22, 22)); // Giảm kích thước icon xuống
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
                    showSanPham(mainPanel);
                }
                else showSanPham(condition,mainPanel);
            }
            
        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<SanPham> ds = new ArrayList<>();
        ds = sanPhamBLL.searchName(condition);
        for(SanPham p:ds){
            String url = p.getImage();
            String name = p.getName();
            String type = p.getType();
            int price = p.getPrice();
            JPanel j = new JPanel();
            ImageIcon i = lamDep.resizeImage(url,230,200);
            j = taoPanelPhim(i, name, type, price,p);
            showSanPham.add(j);
        }
        if(ds.size()<4){
            for(int i  = 1;i<6-ds.size();i++){
                JPanel j1 = new JPanel();
                j1.setVisible(false);
                showSanPham.add(j1);
            }
        }
        // tk.setBackground(new Color(173, 216, 230));
        mainPanel.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(showSanPham);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // không cuộn ngang
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn

        mainPanel.add(scrollPane, BorderLayout.CENTER);


        mainPanel.revalidate(); // cập nhật lại layout
        mainPanel.repaint();
    }
    public void formThemSanPham(View v){
         JTextField txtTenSP;
         JComboBox<String> cmbLoai;
         JTextField txtGia;
         JTextField txtAnh;
         JButton btnChonAnh;
         JButton btnLuu,btnHuy;
         JDialog addDialog = new JDialog(v, "Thêm sản phẩm mới", true);
         addDialog.setSize(400, 300);
         addDialog.setLocationRelativeTo(v);
         addDialog.setLayout(new GridLayout(0,2,20,20));

        // Tên sản phẩm

        addDialog.add(new JLabel("Tên sản phẩm:"));
        txtTenSP = new JTextField(20);
        addDialog.add(txtTenSP);

        addDialog.add(new Label("Loại: "));
        cmbLoai = new JComboBox<>(new String[] {"Nước", "Đồ ăn"});
        addDialog.add(cmbLoai);

        addDialog.add(new JLabel("Giá:"));
        txtGia = new JTextField(20);
        addDialog.add(txtGia);


        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setSize(50,50);
        addDialog.add(btnChonAnh);
        txtAnh = new JTextField(15);
        addDialog.add(txtAnh);
        txtAnh.setEditable(false);


        btnLuu = new JButton("Lưu");
        addDialog.add(btnLuu);

        btnHuy = new JButton("Hủy");
        addDialog.add(btnHuy);
        // Xử lý chọn ảnh
        btnChonAnh.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = chooser.showOpenDialog(addDialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                txtAnh.setText(file.getAbsolutePath());
            }
        });

        // Xử lý lưu sản phẩm
        btnLuu.addActionListener(e -> {
            String tenSP = txtTenSP.getText();
            String loai = (String) cmbLoai.getSelectedItem();
            String giaText = txtGia.getText();
            String anh = txtAnh.getText();
            if (tenSP.isEmpty() || giaText.isEmpty() || anh.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
                return;
            }
            try {
                int gia = Integer.parseInt(giaText);
                SanPham p = new SanPham();
                p.setName(tenSP);
                p.setType(loai);
                p.setPrice(gia);
                p.setStatus("1");
                p.setImage(anh);
                JOptionPane.showMessageDialog(null,sanPhamBLL.insertSanPham(p),"Thông báo",JOptionPane.INFORMATION_MESSAGE );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Giá không hợp lệ.");
            }
            addDialog.dispose();
            showSanPham(v.mainPanel);
        });
       btnHuy.addActionListener(e ->{
        addDialog.dispose();
       });
         addDialog.setVisible(true);
    }

    public void xoaSanPham(SanPham p ,View v){
        if(selectedSanPham==null){
            JOptionPane.showMessageDialog(null,"Vui lòng chọn 1 sản phẩm để xóa", null, 0);
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc muốn xóa sản phẩm: " + selectedSanPham.getName() + "?",
        "Xác nhận xóa",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(null,sanPhamBLL.deleteSanPham(p),"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        selectedSanPham = null;
        showSanPham(v.mainPanel); // Refresh lại danh sách
    }
    }

    public void formSuaSanPham(SanPham p,View v){
        JTextField txtTenSP;
        JComboBox<String> cmbLoai;
        JTextField txtGia;
        JTextField txtAnh;
        JButton btnChonAnh;
        JButton btnLuu,btnHuy;
        JDialog addDialog = new JDialog(v, "Sửa sản phẩm", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(v);
        addDialog.setLayout(new GridLayout(0,2,20,20));

       // Tên sản phẩm

       addDialog.add(new JLabel("Tên sản phẩm:"));
       txtTenSP = new JTextField();
       txtTenSP.setSize(100, 30);
       txtTenSP.setText(p.getName());
       addDialog.add(txtTenSP);

       addDialog.add(new Label("Loại: "));
       cmbLoai = new JComboBox<>(new String[] {"Nước", "Đồ ăn"});
       cmbLoai.setSelectedItem(p.getType());
       addDialog.add(cmbLoai);

       addDialog.add(new JLabel("Giá:"));
       txtGia = new JTextField();
       txtGia.setText(p.getPrice()+"");
       addDialog.add(txtGia);


       btnChonAnh = new JButton("Chọn ảnh");
       btnChonAnh.setSize(50,50);
       addDialog.add(btnChonAnh);
       txtAnh = new JTextField();
       txtAnh.setText(p.getImage());
       addDialog.add(txtAnh);
       txtAnh.setEditable(false);


       btnLuu = new JButton("Lưu");
       addDialog.add(btnLuu);

       btnHuy = new JButton("Hủy");
       addDialog.add(btnHuy);
       // Xử lý chọn ảnh
       btnChonAnh.addActionListener(e -> {
           JFileChooser chooser = new JFileChooser();
           chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
           int result = chooser.showOpenDialog(addDialog);
           if (result == JFileChooser.APPROVE_OPTION) {
               File file = chooser.getSelectedFile();
               txtAnh.setText(file.getAbsolutePath());
           }
       });

       btnLuu.addActionListener(e->{
        p.setName(txtTenSP.getText());
        p.setType((String) cmbLoai.getSelectedItem());
        p.setImage(txtAnh.getText());
        p.setPrice(Integer.parseInt(txtGia.getText()));

        JOptionPane.showMessageDialog(null, sanPhamBLL.updateSanPham(p), "thông báo", JOptionPane.INFORMATION_MESSAGE);
        addDialog.dispose();
        showSanPham(v.mainPanel);
       });

       btnHuy.addActionListener(e->{
        addDialog.dispose();
       });

       addDialog.setVisible(true);
    }


    public JPanel taoPanelPhim(ImageIcon anhSanPham, String tenSanPham, String loaiSanPham, int giaSanPham,SanPham sp) {
        JPanel panelSanPham = new JPanel();
        panelSanPham.setLayout(new BoxLayout(panelSanPham, BoxLayout.Y_AXIS)); // dọc
        panelSanPham.setPreferredSize(new Dimension(150, 250)); // cố định kích thước panel (nếu muốn)
    
        // Label hiển thị ảnh phim
        JLabel lblAnh = new JLabel(anhSanPham);
        lblAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
        // lblAnh.setPreferredSize(new Dimension(120, 180)); // đảm bảo không chiếm quá nhiều
        // lblAnh.setMaximumSize(new Dimension(120, 180));   // ép ảnh nằm trong kích thước
        lblAnh.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
    
        // Label tên phim
        JLabel lblTen = new JLabel("Tên sản phẩm: " + tenSanPham);
        lblTen.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTen.setFont(new Font("Arial", Font.BOLD, 14));
        // lblTen.setMaximumSize(new Dimension(200, 20));
        
        // Label thể loại
        JLabel lblTheLoai = new JLabel("Loại sản phẩm: " + loaiSanPham);
        lblTheLoai.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTheLoai.setFont(new Font("Arial", Font.BOLD, 14));
        // lblTheLoai.setMaximumSize(new Dimension(200, 20));
    
        // Label thời lượng
        JLabel lblThoiLuong = new JLabel("Giá: "+lamDep.formatVNDPlain(giaSanPham) );
        lblThoiLuong.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblThoiLuong.setFont(new Font("Arial", Font.BOLD, 14));
        // lblThoiLuong.setMaximumSize(new Dimension(200, 20));
    
        // Thêm các thành phần vào panel
        panelSanPham.add(lblAnh);
        panelSanPham.add(lblTen);
        panelSanPham.add(lblTheLoai);
        panelSanPham.add(lblThoiLuong);
    
        // Viền cho đẹp
        panelSanPham.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0),3));
        panelSanPham.setBackground(new Color(247, 231, 206));
        panelSanPham.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            selectedSanPham = sp;
            lamDep.highlightSelectedPanel(panelSanPham);
        }

    });
        return panelSanPham;
    }


}
