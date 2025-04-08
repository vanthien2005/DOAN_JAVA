package GUI;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import DAO.DAOPhongChieu;
import DAO.DAOPhongChieuPhim;
import DAO.DAOVe;
import DAO.DAOLichChieu;
import DAO.DAOPhim;
import DTO.LichChieu;
import DTO.Phim;
import DTO.PhongChieu;
import DTO.PhongChieuPhim;
import DTO.Ve;

import java.awt.Color;
// import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class View extends JFrame implements ActionListener {
    JPanel j = new JPanel();
    View()
    {
        // p = new PhongChieu(1,34,"E.304");
        setLayout(null);
        init();
        setVisible(true);
    }
    void init()
    {
        this.setTitle("Rap chieu phim");
        this.setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JButton b = new JButton("in");
        b.setBounds(100, 100, 50, 50);
        b.addActionListener(this);
        this.add(b);
        j.setBounds(100,200,200,200);
        j.setBackground(Color.red);
        add(j);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
        // JLabel l  = new JLabel("ID phong chieu: "+p.getID());
        // JLabel l1 = new JLabel("Ten phong chieu: "+p.getName());
        // JLabel l3 = new JLabel("So ghe ngoi: "+p.getSoGhe());
        // JLabel l4 = new JLabel("ten phim: "+p.getIdPhim()+"");
        // j.add(l);
        // j.add(l1);
        // j.add(l3);
        // j.add(l4);
        // j.revalidate();
        // j.repaint();

    }
  
}
