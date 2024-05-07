package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiaoDienChinh extends JFrame {
    private GiaoDienMenu menuPanel;
    private ThemGiaoDienMenuVaoCard contentPanel;

    public GiaoDienChinh() {
        setTitle("Quản lý bán vé xem phim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1470, 800);
        setLocationRelativeTo(null);

        // Khởi tạo thanh menu và phần nội dung
        menuPanel = new GiaoDienMenu(this);
        contentPanel = new ThemGiaoDienMenuVaoCard();

        // Thêm thanh menu và phần nội dung vào cửa sổ chính
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER); 
    }

    // Phương thức để hiển thị nội dung tương ứng với chức năng
    public void displayContent(String functionName) {
        contentPanel.showContent(functionName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GiaoDienChinh frame = new GiaoDienChinh();
                frame.setVisible(true);
               
            }
        });
    }

	public void hienThiGiaoDienDangNhap() {
		// Hiển thị giao diện đăng nhập
	     DangNhap dangNhapUI = new DangNhap();
	     dangNhapUI.setVisible(true);
	    // Sau khi đăng nhập thành công, bạn cần ẩn giao diện hiện tại.
	     setVisible(false);
		
	}
}

