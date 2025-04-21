package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import BLL.LichChieuBLL;
import DTO.LichChieu;
import DTO.Phim;
import DTO.PhongChieu;

public class LichChieuGUI {
    LamDepGUI lamDep = new LamDepGUI();
    LichChieuBLL l = new LichChieuBLL();
    public LichChieu selectedLichChieu = null;


    public void showLichChieu(JPanel main){
        JPanel showLichChieu = new JPanel(new GridLayout(0,5,15,15));
        showLichChieu.setBackground(new Color(51, 51, 51));
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
                    showLichChieu(main);
                }
                else showLichChieu(main,condition);
            }
            
        });
        main.removeAll();
        main.setLayout(new BorderLayout());
        ArrayList<LichChieu> ds = new ArrayList<>();
        ds = l.getArrayList();
        for(LichChieu l : ds){
            String url = l.getUrl();
            String tenPhim = l.getMovieName();
            String tenPhong = l.getRoomName();
            LocalDate ngay = l.getTg();
            LocalTime gio = l.getTime();
            ImageIcon i = lamDep.resizeImage(url,200 , 180);
            JPanel j = taoPanelLichChieu(i, tenPhim, tenPhong, ngay, gio, l);
            showLichChieu.add(j);

        }
        main.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(showLichChieu);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // không cuộn ngang
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn

        main.add(scrollPane, BorderLayout.CENTER);
        main.revalidate(); // cập nhật lại layout
        main.repaint();


    }



    public void showLichChieu(JPanel main, String condition) {
        JPanel showLichChieu = new JPanel(new GridLayout(0,5,15,15));
        showLichChieu.setBackground(new Color(51, 51, 51));
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
                    showLichChieu(main);
                }
                else showLichChieu(main,condition);
            }
            
        });
        main.removeAll();
        main.setLayout(new BorderLayout());
        ArrayList<LichChieu> ds = new ArrayList<>();
        ds = l.searchNamePhim(condition);
        for(LichChieu l : ds){
            String url = l.getUrl();
            String tenPhim = l.getMovieName();
            String tenPhong = l.getRoomName();
            LocalDate ngay = l.getTg();
            LocalTime gio = l.getTime();
            ImageIcon i = lamDep.resizeImage(url,200 , 180);
            JPanel j = taoPanelLichChieu(i, tenPhim, tenPhong, ngay, gio, l);
            showLichChieu.add(j);

        }
        if(ds.size()<11){
            for(int i = 1;i<=11-ds.size();i++)
            {
                JPanel j1 = new JPanel();
                j1.setVisible(false);
                showLichChieu.add(j1);
            }
        }
        main.add(tk,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(showLichChieu);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // không cuộn ngang
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // tăng tốc độ cuộn

        main.add(scrollPane, BorderLayout.CENTER);
        main.revalidate(); // cập nhật lại layout
        main.repaint();
       
    }



       public JPanel taoPanelLichChieu(ImageIcon anhPhim, String tenPhim, String tenPhong, LocalDate ngay , LocalTime gio ,LichChieu l) {

        JPanel panelLichChieu = new JPanel();
        panelLichChieu.setLayout(new BoxLayout(panelLichChieu, BoxLayout.Y_AXIS)); // dọc
        panelLichChieu.setPreferredSize(new Dimension(150, 250)); // cố định kích thước panel (nếu muốn)
    
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
        JLabel lblPhong = new JLabel("Phòng chiếu: " +tenPhong );
        lblPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPhong.setFont(new Font("Arial", Font.BOLD, 14));
        // lblTheLoai.setMaximumSize(new Dimension(200, 20));
    
        // Label thời lượng
        JLabel lblThoiGian = new JLabel("Thời gian: " +gio+" "+ngay );
        lblThoiGian.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblThoiGian.setFont(new Font("Arial", Font.BOLD, 14));
        // lblThoiLuong.setMaximumSize(new Dimension(200, 20));
    
        // Thêm các thành phần vào panel
        panelLichChieu.add(lblAnh);
        panelLichChieu.add(lblTen);
        panelLichChieu.add(lblPhong);
        panelLichChieu.add(lblThoiGian);
    
        // Viền cho đẹp
        panelLichChieu.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0),3));
        panelLichChieu.setBackground(new Color(247, 231, 206));
        panelLichChieu.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            selectedLichChieu = l;
            lamDep.highlightSelectedPanel(panelLichChieu);
        }

    });
        return panelLichChieu;
       }

       public void formThemLichChieu(View v){

        JDialog addDialog = new JDialog(v, "Thêm phim mới", true);
        addDialog.setSize(400, 300);
        addDialog.setLocationRelativeTo(v);
        addDialog.setLayout(new BorderLayout());
        JTextField tfId = new JTextField();
        tfId.setPreferredSize(new Dimension(200, 25));
        
        JComboBox<String> chonPhim = new JComboBox<>();
        chonPhim.setPreferredSize(new Dimension(200, 25));
        
        JComboBox<String> chonPhong = new JComboBox<>();
        chonPhong.setPreferredSize(new Dimension(200, 25));
        
        JTextField tfNgay = new JTextField();
        tfNgay.setPreferredSize(new Dimension(200, 25));
        
        JTextField tfGio = new JTextField();
        tfGio.setPreferredSize(new Dimension(200, 25));
        
        ArrayList<Phim>ds = new ArrayList<>();
        ds = l.dsPhim();
        for(Phim p : ds){
            String row = p.getId()+":"+p.getName();
            chonPhim.addItem(row);
        }
        ArrayList<PhongChieu>ds1 = new ArrayList<>();
        ds1 = l.dsPhongChieu();
        for(PhongChieu p : ds1){
            String row1 = p.getID()+":"+p.getName();
            chonPhong.addItem(row1);
        }

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("ID Lịch chiếu:"));
        row1.add(tfId);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("phim: "));
        row2.add(chonPhim);
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("phòng:"));
        row3.add(chonPhong);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNgay = new JLabel("Ngày tháng năm (yyyy-mm-dd):");
        row4.add(lblNgay);
        row4.add(tfNgay);

        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row5.add(new JLabel("Giờ(hh:mm):"));
        row5.add(tfGio);
        content.add(row1);
        content.add(row2);
        content.add(row3);
        content.add(row4);
        content.add(row5);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");
        buttons.add(btnLuu);
        buttons.add(btnHuy);
        addDialog.add(content,BorderLayout.CENTER);
        addDialog.add(buttons,BorderLayout.SOUTH);
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tfId.getText());
                String selectedPhim = (String) chonPhim.getSelectedItem();
                String array[] = selectedPhim.split(":");

                String selectedPhong = (String) chonPhong.getSelectedItem();
                String array1[] = selectedPhong.split(":");
             
                int id_movies =Integer.parseInt(array[0]) ;
                int id_room = Integer.parseInt(array1[0]);

                if (!isValidLocalDate(tfNgay.getText().trim())) {
                    JOptionPane.showMessageDialog(addDialog, "bạn nhập không đúng định dạng ngày vui lòng nhập lại!");
                    return;
                }
                LocalDate ngay = LocalDate.parse(tfNgay.getText().trim());
                if(!isValidLocalTime(tfGio.getText().trim())){
                    JOptionPane.showMessageDialog(addDialog, "bạn nhập không đúng định dạng giờ vui lòng nhập lại!");
                    return;
                }
                LocalTime gio = LocalTime.parse(tfGio.getText().trim());

                LichChieu lich = new LichChieu(id,id_movies,id_room,ngay,gio);
                JOptionPane.showMessageDialog(null, l.insertLichChieu(lich), "thông báo", JOptionPane.INFORMATION_MESSAGE);
                addDialog.dispose();
                showLichChieu(v.mainPanel);    
            }
            
        });
        btnHuy.addActionListener(e->addDialog.dispose());
        addDialog.setVisible(true);

}

public void formSuaLichChieu(LichChieu p,View v){

    JDialog editDialog = new JDialog(v, "Sửa thông tin phim", true);
    editDialog.setSize(400, 300);
    editDialog.setLocationRelativeTo(v);
    editDialog.setLayout(new GridLayout(5, 2, 10, 10));

    // Tạo các field
    JTextField tfIdPhim = new JTextField(p.getIdPhim()+"");
    JTextField tfIdPhong = new JTextField(p.getIdPhong()+"");
    JTextField tfNgay = new JTextField(p.getTg().toString());
    JTextField tfGio = new JTextField(p.getTime().toString());

    // Thêm vào dialog
    editDialog.add(new JLabel("ID phim:"));
    editDialog.add(tfIdPhim);
    editDialog.add(new JLabel("ID phòng:"));
    editDialog.add(tfIdPhong);
    editDialog.add(new JLabel("Ngày tháng năm:"));
    editDialog.add(tfNgay);
    editDialog.add(new JLabel("Giờ:"));
    editDialog.add(tfGio);

    JButton btnLuu = new JButton("Lưu");
    JButton btnHuy = new JButton("Hủy");

    editDialog.add(btnLuu);
    editDialog.add(btnHuy);

    btnLuu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            p.setIdPhim(Integer.parseInt(tfIdPhim.getText()));
            p.setId_phong(Integer.parseInt(tfIdPhong.getText().trim()));
            if (!isValidLocalDate(tfNgay.getText().trim())) {
                JOptionPane.showMessageDialog(editDialog, "bạn nhập không đúng định dạng ngày vui lòng nhập lại!");
                return;
            }
            p.setTg(LocalDate.parse(tfNgay.getText().trim()));
            if(!isValidLocalTime(tfGio.getText().trim())){
                JOptionPane.showMessageDialog(editDialog, "bạn nhập không đúng định dạng giờ vui lòng nhập lại!");
                return;
            }
            p.setTime(LocalTime.parse(tfGio.getText().trim()));
            JOptionPane.showMessageDialog(null, l.updateLichChieu(p),"Thông báo", JOptionPane.INFORMATION_MESSAGE);
            editDialog.dispose();
            showLichChieu(v.mainPanel); 
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

    public void xoaLichChieu(View v){
        if (selectedLichChieu == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một lịch chiếu để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "Bạn có chắc muốn xóa Lịch chiếu này chứ"+ "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,l.deleteLichChieu(selectedLichChieu),"Thông báo",JOptionPane.INFORMATION_MESSAGE);
            selectedLichChieu = null;
            showLichChieu(v.mainPanel); // Refresh lại danh sách
        }
    }


public boolean isValidLocalDate(String input) {
    try {
        LocalDate.parse(input); // định dạng mặc định yyyy-MM-dd
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}

public boolean isValidLocalTime(String input) {
    try {
        LocalTime.parse(input); // định dạng mặc định HH:mm hoặc HH:mm:ss
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}
    
}
