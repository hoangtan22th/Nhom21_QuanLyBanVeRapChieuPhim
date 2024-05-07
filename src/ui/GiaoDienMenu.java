package ui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GiaoDienMenu extends JPanel implements ActionListener, MouseListener {
	private GiaoDienChinh mainFrame;
	private JButton btnQuanLi;
	private JLabel imageLabel;
	private JPanel pnQuanliPhim;
	private JLabel lblQuanLi;
	private JPanel pnDatVe;
	private JLabel lblDatVe;
	private JPanel pnQLKhachHang;
	private JLabel lblQLKhachhang;
	private JPanel pnQLHoaDon;
	private JLabel lblQLHoadon;
	private JPanel pnDangxuat;
	private JLabel lblDangxuat;
	private JPanel pnDay;
	private JPanel pnUer;
	private JPanel pnUserName;
	private String nhanvien1;
	private JLabel lblUserName;
	private JButton btnDatVe;
	private JButton btnQLKhachhang;
	private JButton btnQLHoadon;
	private JButton btnDangxuat;
	private CustomButton btnThongKe;
	private CustomButton btnQLSuatChieu;

	public GiaoDienMenu(GiaoDienChinh mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(220, 600));

//      màu cam chủ đạo: 
	//	Color orangeDark = Color.orange;
		Color orangeDark = new Color(108, 126, 225);


//      pnImgUser
		pnUer = new JPanel();
		pnUer.setPreferredSize(new Dimension(210, 170));
		pnUer.setBackground(orangeDark);
		ImageIcon imgUser = new ImageIcon("img/account2-icon.png");
		Image scaledUser = scaleImage(imgUser.getImage(), 150, 130);
		ImageIcon imgScaled1 = new ImageIcon(scaledUser);
		JLabel lblImgScaled1 = new JLabel(imgScaled1);
		pnUer.add(lblImgScaled1);
		lblUserName = new JLabel("nhanvien1");
		pnUer.add(lblUserName);
		add(pnUer);

		// panel Quản lí Phim
		ImageIcon imgFilm = new ImageIcon("img/icons8-movie-32.png");
		Image scaledFilm = scaleImage(imgFilm.getImage(), 30, 30);
		ImageIcon imgScaled = new ImageIcon(scaledFilm);
		JLabel lblImgScaled = new JLabel(imgScaled);
		
		btnQuanLi = new CustomButton("Quản lý phim");
		btnQuanLi.setFocusPainted(false); // Tắt viền khi button được focus
		btnQuanLi.add(lblImgScaled);
		btnQuanLi.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnQuanLi.setBackground(Color.white);
		btnQuanLi.setBorder(new LineBorder(Color.white, 2, true));
		btnQuanLi.setForeground(Color.black);
		btnQuanLi.setPreferredSize(new Dimension(210, 50));
		((CustomButton) btnQuanLi).setBorderRadius(0);
		add(btnQuanLi);

//      panel Đặt vé
		ImageIcon imgTicket = new ImageIcon("img/ticket-icon.png");
		Image scaledTicket = scaleImage(imgTicket.getImage(), 30, 30);
		ImageIcon imgScaled2 = new ImageIcon(scaledTicket);
		JLabel lblImgScaled2 = new JLabel(imgScaled2);
		
		btnDatVe = new CustomButton("Quản lý đặt vé");
		btnDatVe.setFocusPainted(false); // Tắt viền khi button được focus
		btnDatVe.add(lblImgScaled2);
		btnDatVe.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDatVe.setBackground(Color.white);
		btnDatVe.setBorder(new LineBorder(Color.white, 2, true));
		btnDatVe.setForeground(Color.black);
		btnDatVe.setPreferredSize(new Dimension(210, 50));
		((CustomButton) btnDatVe).setBorderRadius(0);
		add(btnDatVe);

//      panel QL Khách hàng
		ImageIcon imgQLKhachhang = new ImageIcon("img/people-icon.png");
		Image scaledQLKhachhang = scaleImage(imgQLKhachhang.getImage(), 30, 30);
		ImageIcon imgScaled3 = new ImageIcon(scaledQLKhachhang);
		JLabel lblImgScaled3 = new JLabel(imgScaled3);
		
		btnQLKhachhang = new CustomButton("Quản lý khách hàng");
		btnQLKhachhang.setFocusPainted(false); // Tắt viền khi button được focus
		btnQLKhachhang.add(lblImgScaled3);
		btnQLKhachhang.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnQLKhachhang.setBackground(Color.white);
		btnQLKhachhang.setBorder(new LineBorder(Color.white, 2, true));
		btnQLKhachhang.setForeground(Color.black);
		btnQLKhachhang.setPreferredSize(new Dimension(210, 50));
		((CustomButton) btnQLKhachhang).setBorderRadius(0);
		add(btnQLKhachhang);

//      panel QL Hóa đơn
		ImageIcon imgQLHoadon = new ImageIcon("img/order-icon.png");
		Image scaledQLHoadon = scaleImage(imgQLHoadon.getImage(), 30, 30);
		ImageIcon imgScaled4 = new ImageIcon(scaledQLHoadon);
		JLabel lblImgScaled4 = new JLabel(imgScaled4);
		
		btnQLHoadon = new CustomButton("Quản lý hóa đơn");
		btnQLHoadon.setFocusPainted(false); // Tắt viền khi button được focus
		btnQLHoadon.add(lblImgScaled4);
		btnQLHoadon.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnQLHoadon.setBackground(Color.white);
		btnQLHoadon.setBorder(new LineBorder(Color.white, 2, true));
		btnQLHoadon.setForeground(Color.black);
		btnQLHoadon.setPreferredSize(new Dimension(210, 50));
		((CustomButton) btnQLHoadon).setBorderRadius(0);
		add(btnQLHoadon);
		
		// panel thống kê
		ImageIcon imgQLThongKe = new ImageIcon("img/icons8-statistic-32.png");
		Image scaledQLThongKe = scaleImage(imgQLThongKe.getImage(), 30, 30);
		ImageIcon imgScaled5 = new ImageIcon(scaledQLThongKe);
		JLabel lblImgScaled5= new JLabel(imgScaled5);

		btnThongKe = new CustomButton("Quản lý thống kê");
		btnThongKe.setFocusPainted(false);
		btnThongKe.add(lblImgScaled5);
		btnThongKe.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThongKe.setBackground(Color.white);
		btnThongKe.setBorder(new LineBorder(Color.white, 2, true));
		btnThongKe.setForeground(Color.black);
		btnThongKe.setPreferredSize(new Dimension(210, 50));
		((CustomButton) btnThongKe).setBorderRadius(0);
		add(btnThongKe);
		
		// qly suất chiêu
//		ImageIcon imgQLXuatChieu = new ImageIcon("img/quanlixuatchieu-icon.png");
//		Image scaledQLXuatChieu = scaleImage(imgQLXuatChieu.getImage(), 30, 30);
//		ImageIcon imgScaled6 = new ImageIcon(scaledQLXuatChieu);
//		JLabel lblImgScaled6= new JLabel(imgScaled6);
//		
//		btnQLSuatChieu = new CustomButton("Quản lý suất chiếu");
//		btnQLSuatChieu.setFocusPainted(false);
//				btnQLSuatChieu.add(lblImgScaled6);

		ImageIcon imgQLXuatChieu = new ImageIcon("img/quanlixuatchieu-icon.png");
		Image scaledQLXuatChieu = scaleImage(imgQLXuatChieu.getImage(), 30, 30);
		ImageIcon imgScaled7 = new ImageIcon(scaledQLXuatChieu);
		JLabel lblImgScaled7= new JLabel(imgScaled7);
		
		btnQLSuatChieu = new CustomButton("Quản lý suất chiếu");
		btnQLSuatChieu.setFocusPainted(false);
				btnQLSuatChieu.add(lblImgScaled7);

		ImageIcon imgQLSuatChieu = new ImageIcon("img/icons8-movie-theater-32.png");
		Image scaledQLSuatChieu = scaleImage(imgQLSuatChieu.getImage(), 30, 30);
		ImageIcon imgScaled6 = new ImageIcon(scaledQLSuatChieu);
		JLabel lblImgScaled6= new JLabel(imgScaled6);
		
		btnQLSuatChieu = new CustomButton("Quản lý suất chiếu");
		btnQLSuatChieu.setFocusPainted(false);
				btnQLSuatChieu.add(lblImgScaled6);

		btnQLSuatChieu.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnQLSuatChieu.setBackground(Color.white);
		btnQLSuatChieu.setBorder(new LineBorder(Color.white, 2, true));
		btnQLSuatChieu.setForeground(Color.black);
		btnQLSuatChieu.setPreferredSize(new Dimension(210, 50));
		((CustomButton)btnQLSuatChieu).setBorderRadius(0);
		add(btnQLSuatChieu);


//        panel đẩy
		pnDay = new JPanel();
		pnDay.setBackground(orangeDark);
		pnDay.setPreferredSize(new Dimension(210, 250));
		add(pnDay);

//      panel Đăng xuất
		
		ImageIcon imgDangxuat = new ImageIcon("img/sign-out-icon.png");
		Image scaledDangxuat = scaleImage(imgDangxuat.getImage(), 30, 30);
//		ImageIcon imgScaled7 = new ImageIcon(scaledDangxuat);
//		JLabel lblImgScaled7 = new JLabel(imgScaled7);
		ImageIcon imgScaled8 = new ImageIcon(scaledDangxuat);
		JLabel lblImgScaled8 = new JLabel(imgScaled6);
		
		btnDangxuat = new CustomButton("Đăng xuất");
		btnDangxuat.setFocusPainted(false); // Tắt viền khi button được focus
		btnDangxuat.add(lblImgScaled7);
		btnDangxuat.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDangxuat.setBackground(Color.white);
		btnDangxuat.setBorder(new LineBorder(Color.white, 2, true));
		btnDangxuat.setForeground(Color.black);
		btnDangxuat.setPreferredSize(new Dimension(210, 50));
		((CustomButton) btnDangxuat).setBorderRadius(0);
		add(btnDangxuat);

		setBackground(orangeDark); // Thay đổi màu nền thành màu cam
		btnDatVe.addActionListener(this);
		btnQLKhachhang.addActionListener(this);
		btnQuanLi.addActionListener(this);
		btnDangxuat.addActionListener(this);
		btnQLHoadon.addActionListener(this);;
		btnThongKe.addActionListener(this);
		btnQLSuatChieu.addActionListener(this);

	}

	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;  

	}

	// Phương thức để thêm nút chức năng vào thanh menu
	private void addButton(String text, final String functionName) {
		JButton button = new JButton(text);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.displayContent(functionName);
			}
		});
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(btnDatVe)) {
			mainFrame.displayContent("QlBanVe");
		} else if (source.equals(btnQLKhachhang)) {
			mainFrame.displayContent("QlKhachHang");
		} else if (source.equals(btnQuanLi)) {
			mainFrame.displayContent("QlPhim");
		}else if(source.equals(btnDangxuat)) {
			 mainFrame.hienThiGiaoDienDangNhap();
		}else if(source.equals(btnQLHoadon)) {
			 mainFrame.displayContent("QlHoaDon");
		}else if(source.equals(btnThongKe)) {
			 mainFrame.displayContent("ThongKe");
		} else if (source.equals(btnQLSuatChieu)) {
			mainFrame.displayContent("SuatChieu");
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}