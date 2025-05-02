package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.NhanVienBLL;
import DTO.NhanVien;

public class NhanVienGUI {
    NhanVienBLL nhanVienBLL = new NhanVienBLL();
    JTable tableNhanVien = new JTable();
    LamDepGUI lamDep = new LamDepGUI();

    public void showNhanVien(JPanel mainPanel){
        JPanel content = new JPanel(new BorderLayout());
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
        search.addActionListener(e->{
            String condition = timKiem.getText().trim();
            if(condition.equals("")){
                showNhanVien(mainPanel);
            }
            showNhanVien(mainPanel,condition);

        });
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        model.addColumn("Mã NV");
        model.addColumn("Họ và tên");
        model.addColumn("Tuổi");
        model.addColumn("Số điện thoại");
        model.addColumn("Chức vụ");
        tableNhanVien.setModel(model);
        tableNhanVien.setRowHeight(35);
        tableNhanVien.setFont(new Font("Time new roman", Font.PLAIN, 16));

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18));
        header.setBackground(new Color(173, 216, 230));
        ArrayList<NhanVien>ds = new ArrayList<>();
        ds = nhanVienBLL.getArrayList();
        for(NhanVien n : ds){
            Object []row = {n.getId(),n.getName(),n.getAge(),n.getNumberPhone(),n.getPosition()};
            model.addRow(row);
        }
        JScrollPane js = new JScrollPane(tableNhanVien);
        content.add(js,BorderLayout.CENTER);
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tk,BorderLayout.NORTH);
        mainPanel.add(content,BorderLayout.CENTER);


        mainPanel.revalidate();
        mainPanel.repaint();
        
    }

    private void showNhanVien(JPanel mainPanel, String condition) {
        JPanel content = new JPanel(new BorderLayout());
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
        search.addActionListener(e->{
            String searchString = timKiem.getText().trim();
            if(searchString.equals("")){
                showNhanVien(mainPanel);
            }
            showNhanVien(mainPanel,searchString);

        });
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được sửa
            }
        };
        model.addColumn("Mã NV");
        model.addColumn("Họ và tên");
        model.addColumn("Tuổi");
        model.addColumn("Số điện thoại");
        model.addColumn("Chức vụ");
        tableNhanVien.setModel(model);
        tableNhanVien.setRowHeight(35);
        tableNhanVien.setFont(new Font("Time new roman", Font.PLAIN, 16));

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setFont(new Font("Time new roman", Font.BOLD, 18));
        header.setBackground(new Color(173, 216, 230));
        ArrayList<NhanVien>ds = new ArrayList<>();
        ds = nhanVienBLL.searchName(condition);
        for(NhanVien n : ds){
            Object []row = {n.getId(),n.getName(),n.getAge(),n.getNumberPhone(),n.getPosition()};
            model.addRow(row);
        }
        JScrollPane js = new JScrollPane(tableNhanVien);
        content.add(js,BorderLayout.CENTER);
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tk,BorderLayout.NORTH);
        mainPanel.add(content,BorderLayout.CENTER);


        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void formThemNhanVien(View v){
        JDialog addDialog = new JDialog(v, "Thêm nhân viên mới", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(v);
        JLabel formThem = new JLabel("Form thêm nhân viên");
        formThem.setFont(new Font("Time new roman", Font.BOLD, 20));
        formThem.setBorder(BorderFactory.createEmptyBorder(30, 160, 30, 50));
        JPanel content = new JPanel(new GridLayout(0,2,5,30));
        content.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 5));
        content.add(new JLabel("Nhập tên"));
        JTextField ten = new JTextField();
        content.add(ten);
        content.add(new JLabel("Nhập tuổi"));
        JTextField tuoi = new JTextField();
        content.add(tuoi);
        content.add(new JLabel("Nhập số điện thoại"));
        JTextField sdt = new JTextField();
        content.add(sdt);
        content.add(new JLabel("chọn chức vụ "));
        JComboBox<String> chonCV = new JComboBox<>(new String[]{"Nhân viên bán vé","Nhân viên kỹ thuật","Nhân viên bảo vệ","Nhân viên kiểm soát","Nhân viên bán vé","Nhân viên quầy bắp nước","Nhân viên tạp vụ","Quản lý"} );
        content.add(chonCV);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JButton luu = new JButton("Lưu");
        JButton huy = new JButton("Hủy");
        buttons.add(luu);
        buttons.add(huy);
        luu.addActionListener(e->{
            if(ten.getText().isEmpty() || tuoi.getText().isEmpty() || sdt.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String name = ten.getText().trim();
                int age = Integer.parseInt(tuoi.getText());
                if(!lamDep.isValidPhoneNumber(sdt.getText())){
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String number = sdt.getText();
                String cv = (String) chonCV.getSelectedItem();
                NhanVien nv = new NhanVien();
                nv.setName(name);
                nv.setAge(age);
                nv.setNumberPhone(number);
                nv.setPosition(cv);
                nv.setStatus("T");
                JOptionPane.showMessageDialog(null, nhanVienBLL.insertNhanVien(nv), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                addDialog.dispose();
                showNhanVien(v.mainPanel);

            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "tuổi phải là số nguyên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        huy.addActionListener(e->{
            addDialog.dispose();
        });


        addDialog.setLayout(new BorderLayout());
        addDialog.add(formThem,BorderLayout.NORTH);
        addDialog.add(content,BorderLayout.CENTER);
        addDialog.add(buttons,BorderLayout.SOUTH);
        addDialog.setVisible(true);
        
    }

    public void xoaNhanVien(View v){
        int row = tableNhanVien.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa", "thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        NhanVien nv = new NhanVien();
        nv.setId( (int) tableNhanVien.getValueAt(row, 0));
        JOptionPane.showMessageDialog(null, nhanVienBLL.deleteNhanVien(nv), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        showNhanVien(v.mainPanel);
    }
    public void formSuaNhanVien(View v){

        int row = tableNhanVien.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa", "thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JDialog addDialog = new JDialog(v, "Sửa nhân viên ", true);
        addDialog.setSize(500, 400);
        addDialog.setLocationRelativeTo(v);
        JLabel formThem = new JLabel("Form sửa nhân viên");
        formThem.setFont(new Font("Time new roman", Font.BOLD, 20));
        formThem.setBorder(BorderFactory.createEmptyBorder(30, 180, 30, 50));
        JPanel content = new JPanel(new GridLayout(0,2,5,30));
        content.add(new JLabel("Sửa tên"));
        JTextField ten = new JTextField();
        ten.setText(tableNhanVien.getValueAt(row, 1).toString());
        content.add(ten);
        content.add(new JLabel("Sửa tuổi"));
        JTextField tuoi = new JTextField();
        tuoi.setText( tableNhanVien.getValueAt(row, 2).toString());
        content.add(tuoi);
        content.add(new JLabel("Sửa số điện thoại"));
        JTextField sdt = new JTextField();
        sdt.setText(tableNhanVien.getValueAt(row, 3).toString());
        content.add(sdt);
        content.add(new JLabel("chọn chức vụ "));
        JComboBox<String> chonCV = new JComboBox<>(new String[]{"Nhân viên bán vé","Nhân viên kỹ thuật","Nhân viên bảo vệ","Nhân viên kiểm soát","Nhân viên bán vé","Nhân viên quầy bắp nước","Nhân viên tạp vụ","Quản lý"} );
        
        String chucVuHienTai = (String) tableNhanVien.getValueAt(row, 4); // Lấy chức vụ từ bảng
        chonCV.setSelectedItem(chucVuHienTai); // Set mặc định
        content.add(chonCV);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JButton luu = new JButton("Lưu");
        JButton huy = new JButton("Hủy");
        buttons.add(luu);
        buttons.add(huy);
        luu.addActionListener(e->{
            if(ten.getText().isEmpty() || tuoi.getText().isEmpty() || sdt.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String name = ten.getText().trim();
                int age = Integer.parseInt(tuoi.getText());
                if(!lamDep.isValidPhoneNumber(sdt.getText())){
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String number = sdt.getText();
                String cv = (String) chonCV.getSelectedItem();
                NhanVien nv = new NhanVien();
                nv.setId((int) tableNhanVien.getValueAt(row, 0));
                nv.setName(name);
                nv.setAge(age);
                nv.setNumberPhone(number);
                nv.setPosition(cv);
                JOptionPane.showMessageDialog(null, nhanVienBLL.updateNhanVien(nv), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                addDialog.dispose();
                showNhanVien(v.mainPanel);

            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "tuổi phải là số nguyên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        huy.addActionListener(e->{
            addDialog.dispose();
        });


        addDialog.setLayout(new BorderLayout());
        addDialog.add(formThem,BorderLayout.NORTH);
        addDialog.add(content,BorderLayout.CENTER);
        addDialog.add(buttons,BorderLayout.SOUTH);
        addDialog.setVisible(true);
    }
}
