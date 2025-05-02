package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LamDepGUI {
    private JPanel lastSelectedPanel = null;

    
        public ImageIcon resizeImage(String duongDanAnh,int w,int h) {
        ImageIcon iconGoc = new ImageIcon(duongDanAnh);
        Image anhGoc = iconGoc.getImage();
        Image anhResize = anhGoc.getScaledInstance(w,h, Image.SCALE_SMOOTH);
        return new ImageIcon(anhResize);
    }
    public ImageIcon scaleImageToFit(String path, int width, int height) {
        Image img = Toolkit.getDefaultToolkit().createImage(View.class.getResource(path));
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

     public void highlightSelectedPanel(JPanel panelPhim) {
            if (lastSelectedPanel != null) {
                lastSelectedPanel.setBackground(new Color(247, 231, 206)); // Bỏ màu panel trước đó
            }
            panelPhim.setBackground(Color.LIGHT_GRAY); // Highlight panel hiện tại
            lastSelectedPanel = panelPhim;
        }

        public void highlightSelectedPanel1(JPanel panelPhim) {
            if (lastSelectedPanel != null) {
                lastSelectedPanel.setBackground(Color.white); // Bỏ màu panel trước đó
            }
            panelPhim.setBackground(Color.LIGHT_GRAY); // Highlight panel hiện tại
            lastSelectedPanel = panelPhim;
        }
        public String formatVNDPlain(int soTien) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
        return nf.format(soTien) + " VNĐ";
}
public static boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("^0[3589]\\d{8}$");
}

}
