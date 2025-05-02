package GUI;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.MouseEvent;
import java.io.File;

import BLL.PhimBLL;
import DTO.Phim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class PhimGUI {
    public Phim selectedPhim = null; 
    public PhimBLL phimBLL = new PhimBLL();
    LamDepGUI lamDep = new LamDepGUI();

    public void showPhim(JPanel mainPanel) {
        JPanel showPhim = new JPanel(new GridLayout(0,5,15,15));
        showPhim.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        JComboBox<String>chonTheLoai = new JComboBox<>();
        ArrayList<Phim>dsTheLoai = new ArrayList<>();
        dsTheLoai = phimBLL.dsTheLoai();
        chonTheLoai.addItem("Tất cả");
        for(Phim p : dsTheLoai){
            chonTheLoai.addItem(p.getType());
        }
        JButton loc = new JButton("Lọc");
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
        tk.add(chonTheLoai);
        tk.add(loc);
        loc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) chonTheLoai.getSelectedItem();
                if(selectedType.equals("Tất cả")){
                    showPhim(mainPanel);
                }
                else searchType(mainPanel,selectedType);
            }
            
        });

        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showPhim(mainPanel);
                }
                else showPhim(condition,mainPanel);
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
            ImageIcon i = lamDep.resizeImage(url,200,180);
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
    public void showPhim(String condition,JPanel mainPanel) {
        JPanel showPhim = new JPanel(new GridLayout(0,5,15,15));
        showPhim.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        JComboBox<String>chonTheLoai = new JComboBox<>();
        ArrayList<Phim>dsTheLoai = new ArrayList<>();
        dsTheLoai = phimBLL.dsTheLoai();
        chonTheLoai.addItem("Tất cả");
        for(Phim p : dsTheLoai){
            chonTheLoai.addItem(p.getType());
        }
        JButton loc = new JButton("Lọc");
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
        tk.add(chonTheLoai);
        tk.add(loc);
        loc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) chonTheLoai.getSelectedItem();
                if(selectedType.equals("Tất cả")){
                    showPhim(mainPanel);
                }
                else searchType(mainPanel,selectedType);
            }
            
        });
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showPhim(mainPanel);
                }
                else showPhim(condition,mainPanel);
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
            ImageIcon i = lamDep.resizeImage(url,200,180);
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

    public void searchType(JPanel mainPanel,String condition){
        JPanel showPhim = new JPanel(new GridLayout(0,5,15,15));
        showPhim.setBackground(new Color(51, 51, 51));
        JPanel tk = new JPanel(new FlowLayout());
        JTextField timKiem = new JTextField();
        JComboBox<String>chonTheLoai = new JComboBox<>();
        ArrayList<Phim>dsTheLoai = new ArrayList<>();
        dsTheLoai = phimBLL.dsTheLoai();
        chonTheLoai.addItem("Tất cả");
        for(Phim p : dsTheLoai){
            chonTheLoai.addItem(p.getType());
        }
        JButton loc = new JButton("Lọc");
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
        tk.add(chonTheLoai);
        tk.add(loc);
        loc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) chonTheLoai.getSelectedItem();
                if(selectedType.equals("Tất cả")){
                    showPhim(mainPanel);
                }
                else searchType(mainPanel,selectedType);
            }
            
        });

        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String condition = timKiem.getText();
                if(condition.equals("")){
                    showPhim(mainPanel);
                }
                else showPhim(condition,mainPanel);
            }
            
        });
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ArrayList<Phim> ds = new ArrayList<>();
        ds = phimBLL.searchType(condition);
        for(Phim p:ds){
            String url = p.getUrl();
            String name = p.getName();
            String type = p.getType();
            String duration = p.getDuration();
            JPanel j = new JPanel();
            ImageIcon i = lamDep.resizeImage(url,200,180);
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
            lamDep.highlightSelectedPanel(panelPhim);
        }

    });
        return panelPhim;
    }

    public void formSuaPhim(Phim p,View v){
        JButton btnChonAnh = new JButton("chọn ảnh");

        JDialog editDialog = new JDialog(v, "Sửa thông tin phim", true);
        editDialog.setSize(400, 500);
        editDialog.setLocationRelativeTo(v);
        editDialog.setLayout(new BorderLayout());
        JLabel formSua = new JLabel("Form sửa phim");
        formSua.setFont(new Font("Time new roman", Font.BOLD, 20));
        formSua.setBorder(BorderFactory.createEmptyBorder(30, 120, 20, 50));

        JPanel content = new JPanel(new GridLayout(0,2,5,60));
        content.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 20));
        // Tạo các field
        JTextField tfTen = new JTextField(p.getName());
        JTextField tfTheLoai = new JTextField(p.getType());
        JTextField tfThoiLuong = new JTextField(p.getDuration());
        JTextField tfUrl = new JTextField(p.getUrl());
        tfUrl.setEditable(false);
    
        // Thêm vào dialog
        content.add(new JLabel("Tên phim:"));
        content.add(tfTen);
        content.add(new JLabel("Thể loại:"));
        content.add(tfTheLoai);
        content.add(new JLabel("Thời lượng:"));
        content.add(tfThoiLuong);
        content.add(btnChonAnh);
        content.add(tfUrl);

        btnChonAnh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Chỉ ảnh JPG, PNG", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(editDialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                tfUrl.setText(selectedFile.getAbsolutePath()); // lưu đường dẫn ảnh vào tfUrl
            }
            }
            
        });
        JPanel buttons = new JPanel(new FlowLayout());
    
        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");
    
        buttons.add(btnLuu);
        buttons.add(btnHuy);
    
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setName(tfTen.getText());
                p.setType(tfTheLoai.getText());
                p.setDuration(tfThoiLuong.getText());
                p.setUrl(tfUrl.getText());
                JOptionPane.showMessageDialog(null, phimBLL.updatePhim(selectedPhim),"Thông báo", JOptionPane.INFORMATION_MESSAGE);
                editDialog.dispose();
                showPhim(v.mainPanel); 
            }      
        });
        btnHuy.addActionListener(new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose();
            } 
        });
        editDialog.add(formSua,BorderLayout.NORTH);
        editDialog.add(content,BorderLayout.CENTER);
        editDialog.add(buttons,BorderLayout.SOUTH);
        editDialog.setVisible(true);
        }
    
        //    form thêm đối tượng    //
    public void formThemPhim(View v) {

        JDialog addDialog = new JDialog(v, "Thêm phim mới", true);
        addDialog.setSize(400, 500);
        addDialog.setLocationRelativeTo(v);
        addDialog.setLayout(new BorderLayout());
        JLabel formThem = new JLabel("Form thêm phim");
        formThem.setFont(new Font("Time new roman", Font.BOLD, 20));
        formThem.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 40));

        JTextField tfId = new JTextField();
        JTextField tfTen = new JTextField();
        JTextField tfTheLoai = new JTextField();
        JTextField tfThoiLuong = new JTextField();
        JTextField tfUrl = new JTextField(); // dùng để lưu đường dẫn ảnh (ẩn hoặc không cho nhập tay)
        tfUrl.setEditable(false); // không cho người dùng gõ tay

        JButton btnChonAnh = new JButton("Chọn ảnh...");
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0,2,5,50));
        content.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 20));
        content.add(new JLabel("ID phim:"));
        content.add(tfId);
        content.add(new JLabel("Tên phim:"));
        content.add(tfTen);
        content.add(new JLabel("Thể loại:"));
        content.add(tfTheLoai);
        content.add(new JLabel("Thời lượng:"));
        content.add(tfThoiLuong);
        content.add(btnChonAnh);
        content.add(tfUrl);
         // dùng nút chọn ảnh thay vì nhập tay
         JPanel bottom = new JPanel();
         bottom.setLayout(new FlowLayout());

        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");

        bottom.add(btnLuu);
        bottom.add(btnHuy);
        addDialog.add(formThem,BorderLayout.NORTH);
        addDialog.add(content,BorderLayout.CENTER);
        addDialog.add(bottom,BorderLayout.SOUTH);
        // Xử lý chọn ảnh
        btnChonAnh.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Chỉ ảnh JPG, PNG", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(addDialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                tfUrl.setText(selectedFile.getAbsolutePath()); // lưu đường dẫn ảnh vào tfUrl
            }
        });

        // Xử lý lưu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(tfId.getText());
                    String ten = tfTen.getText().trim();
                    String theLoai = tfTheLoai.getText().trim();
                    String thoiLuong = tfThoiLuong.getText().trim();
                    String url = tfUrl.getText().trim();

                    if (ten.isEmpty() || theLoai.isEmpty() || thoiLuong.isEmpty() || url.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                        return;
                    }

                    Phim p = new Phim(ten, theLoai, thoiLuong, url);
                    p.setId(id);
                    JOptionPane.showMessageDialog(null, phimBLL.insertPhim(p), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    addDialog.dispose();
                    showPhim(v.mainPanel);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    btnHuy.addActionListener(e -> addDialog.dispose());
    addDialog.setVisible(true);
}

    
    public void xoaPhim(View v){
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
            JOptionPane.showMessageDialog(null,phimBLL.deletePhim(selectedPhim),"Thông báo",JOptionPane.INFORMATION_MESSAGE);
            selectedPhim = null;
            showPhim(v.mainPanel); // Refresh lại danh sách
        }
    }

}
