package ui;

import javax.swing.*;
import java.awt.*;

public class ThemGiaoDienMenuVaoCard extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ThemGiaoDienMenuVaoCard() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Tạo các giao diện cho từng chức năng và thêm chúng vào CardLayout
        QuanLyPhim movieManagementPanel = new QuanLyPhim();
        ChucNangDatVe ticketBookingPanel = new ChucNangDatVe();
        QuanLyKhachHang customerManagementPanel = new QuanLyKhachHang();
        QuanLyHoaDon qlhd = new QuanLyHoaDon();
        ThongKe qlttv = new ThongKe();
        QuanLySuatChieu qlsc = new QuanLySuatChieu();
        
        
        
        cardPanel.add(movieManagementPanel, "QlPhim");
        cardPanel.add(ticketBookingPanel, "QlBanVe");
        cardPanel.add(customerManagementPanel, "QlKhachHang");
        cardPanel.add(qlhd, "QlHoaDon");
        cardPanel.add(qlttv, "ThongKe");
        cardPanel.add(qlsc, "SuatChieu");
        

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
    }

    // Phương thức để hiển thị nội dung tương ứng với chức năng
    public void showContent(String functionName) {
        cardLayout.show(cardPanel, functionName);
    }
}
