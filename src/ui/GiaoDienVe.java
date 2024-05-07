package ui;

import javax.swing.*;
import java.awt.*;

public class GiaoDienVe extends JFrame{

    private JLabel lblTenPhim;
	private JLabel lblTheLoai;
	private JLabel lblTenTheLoai;
	private JLabel lblThoiLuong;
	private JLabel lblTenThoiLuong;
	private JLabel lblNgayKhoiChieu;
	private JLabel lblTenNgayKhoiChieu;
	private JLabel lblGioChieu;
	private JLabel lblTenGioChieu;
	private JLabel lblPhongChieu;
	private JLabel lblTenPhongChieu;
	private JLabel lblGhe;
	private JLabel lblTenGhe;
	private JLabel lblTitle;
	private JLabel lblTenCty;
	private JTextField txtTenPhim;
	private JPanel pnTenPhim;
	private JTextField txtTenTheLoai;
	private JPanel pnTenTheLoai;
	private JTextField txtTenThoiLuong;
	private JPanel pnTenThoiLuong;
	private JTextField txtNgayKhoiChieu;
	private JPanel pnNgayKhoiChieu;
	private JTextField txtGioChieu;
	private JPanel pnGioChieu;
	private JTextField txtPhongChieu;
	private JTextField txtTenGhe;
	private JPanel pnPhongChieu;
	private JPanel pnTenGhe;
	private JPanel pnB;
	private JLabel lblMaVe;
	private JTextField txtMaVe;
	private JPanel pnMaVe;
	private JLabel lblMota;
	private JPanel pnMota;
	private JPanel pnMoTaVe;
	private JTextField txtMota;
	private JLabel lblCach;
	private JPanel pnCach;
	private JLabel lblTiensanpham;
	private JLabel lblTongThanhToan;
	private JTextField txtTiensanpham;
	private JTextField txtTongThanhToan; 
	private JPanel pnTiensanpham;
	private JPanel pnTongThanhToan;
 
	public GiaoDienVe() {
		setSize(450, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ImageIcon imgQLThongKe = new ImageIcon("img/movi-icon.png");
		Image scaledQLThongKe = scaleImage(imgQLThongKe.getImage(), 80, 80);
		ImageIcon imgScaled5 = new ImageIcon(scaledQLThongKe);
		JLabel lblImgScaled5= new JLabel(imgScaled5);
		
		lblTitle = new JLabel("VÉ  XEM  PHIM");
		lblTenCty = new JLabel("               Cinema ABC");
		Font font = new Font(lblTitle.getFont().getName(), Font.BOLD, 28);
		lblTitle.setFont(font);
		
		Box boxTileCty = Box.createVerticalBox();
		boxTileCty.add(lblTitle);
		boxTileCty.add(lblTenCty);
		
		
		Box boxTitle = Box.createHorizontalBox();
		boxTitle.add(Box.createHorizontalStrut(40));
		boxTitle.add(lblImgScaled5);
		boxTitle.add(Box.createHorizontalStrut(40));
		boxTitle.add(boxTileCty); 
		
        lblTenPhim = new JLabel("Tên phim: ");
        lblTenPhim.setFont(new Font("Arial", Font.BOLD, 14));
        txtTenPhim = new JTextField("Quật mộ trùng ma", 20);
        txtTenPhim.setFont(new Font("Arial", Font.BOLD, 15));
        txtTenPhim.setForeground(Color.BLUE);
        pnTenPhim = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnTenPhim.setBackground(Color.WHITE);
        pnTenPhim.add(lblTenPhim); pnTenPhim.add(txtTenPhim);
        
        
        lblTheLoai = new JLabel("Thể loại:");
        lblTheLoai.setFont(new Font("Arial", Font.BOLD, 14));
        lblTheLoai.setPreferredSize(new Dimension(140, 30));
        txtTenTheLoai = new JTextField("Kinh dị");
        txtTenTheLoai.setFont(new Font("Arial", Font.BOLD, 15));
        txtTenTheLoai.setPreferredSize(new Dimension(140, 30));
        txtTenTheLoai.setHorizontalAlignment(SwingConstants.CENTER);
        pnTenTheLoai = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnTenTheLoai.setBackground(Color.WHITE);
        pnTenTheLoai.add(lblTheLoai); pnTenTheLoai.add(txtTenTheLoai);
        
        lblThoiLuong = new JLabel("Thời lượng:");
        lblThoiLuong.setFont(new Font("Arial", Font.BOLD, 14));
        lblThoiLuong.setPreferredSize(new Dimension(140, 30));
        txtTenThoiLuong = new JTextField("90 phút");
        txtTenThoiLuong.setFont(new Font("Arial", Font.BOLD, 15));
        txtTenThoiLuong.setPreferredSize(new Dimension(140, 30));
        txtTenThoiLuong.setHorizontalAlignment(SwingConstants.CENTER);
        pnTenThoiLuong = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnTenThoiLuong.setBackground(Color.WHITE);
        pnTenThoiLuong.add(lblThoiLuong); pnTenThoiLuong.add(txtTenThoiLuong);
        
        lblNgayKhoiChieu = new JLabel("Ngày khởi chiếu:");
        lblNgayKhoiChieu.setFont(new Font("Arial", Font.BOLD, 14));
        lblNgayKhoiChieu.setPreferredSize(new Dimension(140, 30));
        txtNgayKhoiChieu = new JTextField("04/05/2024");
        txtNgayKhoiChieu.setFont(new Font("Arial", Font.BOLD, 15));
        txtNgayKhoiChieu.setPreferredSize(new Dimension(140, 30));
        txtNgayKhoiChieu.setHorizontalAlignment(SwingConstants.CENTER);
        pnNgayKhoiChieu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnNgayKhoiChieu.setBackground(Color.WHITE);
        pnNgayKhoiChieu.add(lblNgayKhoiChieu); pnNgayKhoiChieu.add(txtNgayKhoiChieu);
        
        lblGioChieu = new JLabel("Giờ chiếu:");
        lblGioChieu.setFont(new Font("Arial", Font.BOLD, 14));
        lblGioChieu.setPreferredSize(new Dimension(140, 30));
        txtGioChieu = new JTextField("22:30");
        txtGioChieu.setFont(new Font("Arial", Font.BOLD, 15));
        txtGioChieu.setPreferredSize(new Dimension(140, 30));
        txtGioChieu.setHorizontalAlignment(SwingConstants.CENTER);
        pnGioChieu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnGioChieu.setBackground(Color.WHITE);
        pnGioChieu.add(lblGioChieu); pnGioChieu.add(txtGioChieu);
        
        lblPhongChieu = new JLabel("Phòng chiếu:");
        lblPhongChieu.setFont(new Font("Arial", Font.BOLD, 14));
        lblPhongChieu.setPreferredSize(new Dimension(140, 30));
        txtPhongChieu = new JTextField("Phong001");
        txtPhongChieu.setFont(new Font("Arial", Font.BOLD, 15));
        txtPhongChieu.setPreferredSize(new Dimension(140, 30));
        txtPhongChieu.setHorizontalAlignment(SwingConstants.CENTER);
        pnPhongChieu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnPhongChieu.setBackground(Color.WHITE);
        pnPhongChieu.add(lblPhongChieu); pnPhongChieu.add(txtPhongChieu);
        
        lblGhe = new JLabel("Ghế ngồi:");
        lblGhe.setFont(new Font("Arial", Font.BOLD, 14));
        lblGhe.setPreferredSize(new Dimension(140, 30));
        txtTenGhe = new JTextField("G5");
        txtTenGhe.setFont(new Font("Arial", Font.BOLD, 15));
        txtTenGhe.setPreferredSize(new Dimension(140, 30));
        txtTenGhe.setHorizontalAlignment(SwingConstants.CENTER);
        pnTenGhe = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnTenGhe.setBackground(Color.WHITE);
        pnTenGhe.add(lblGhe); pnTenGhe.add(txtTenGhe);
        
        txtTenPhim.setBorder(null); txtTenPhim.setOpaque(false);txtTenPhim.setEditable(false);txtTenPhim.setFocusable(false);
        txtTenTheLoai.setBorder(null); txtTenTheLoai.setOpaque(false);txtTenTheLoai.setEditable(false);txtTenTheLoai.setFocusable(false);
        txtTenThoiLuong.setBorder(null); txtTenThoiLuong.setOpaque(false);txtTenThoiLuong.setEditable(false);txtTenThoiLuong.setFocusable(false);
        txtNgayKhoiChieu.setBorder(null); txtNgayKhoiChieu.setOpaque(false);txtNgayKhoiChieu.setEditable(false);txtNgayKhoiChieu.setFocusable(false);
        txtGioChieu.setBorder(null); txtGioChieu.setOpaque(false);txtGioChieu.setEditable(false);txtGioChieu.setFocusable(false);
        txtPhongChieu.setBorder(null);txtPhongChieu.setOpaque(false);txtPhongChieu.setEditable(false);txtPhongChieu.setFocusable(false);
        txtTenGhe.setBorder(null);txtTenGhe.setOpaque(false);txtTenGhe.setEditable(false);txtTenGhe.setFocusable(false);
        
        lblCach = new JLabel("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  ");
        pnCach = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnCach.setBackground(Color.WHITE);
        pnCach.add(lblCach); 
        
//        thanh toán
//      thành tiền
     
      lblTiensanpham = new JLabel("Thành tiền:"); //là thành tiền
      lblTiensanpham.setFont(new Font("Arial", Font.BOLD, 14));
      lblTiensanpham.setPreferredSize(new Dimension(140, 30));
      txtTiensanpham = new JTextField("160.000");
      txtTiensanpham.setFont(new Font("Arial", Font.BOLD, 15));
      txtTiensanpham.setPreferredSize(new Dimension(140, 30));
      txtTiensanpham.setHorizontalAlignment(SwingConstants.CENTER); 
      pnTiensanpham = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
      pnTiensanpham.setBackground(Color.WHITE);
      pnTiensanpham.add(lblTiensanpham); pnTiensanpham.add(txtTiensanpham);
      
      lblTongThanhToan = new JLabel("Tổng thanh toán:"); 
      lblTongThanhToan.setFont(new Font("Arial", Font.BOLD, 14));
      lblTongThanhToan.setPreferredSize(new Dimension(140, 30));
      txtTongThanhToan = new JTextField("143,60");
      txtTongThanhToan.setFont(new Font("Arial", Font.BOLD, 15));
      txtTongThanhToan.setPreferredSize(new Dimension(80, 30));
      txtTongThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
      pnTongThanhToan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
      pnTongThanhToan.setBackground(Color.WHITE);
      pnTongThanhToan.add(lblTongThanhToan); pnTongThanhToan.add(txtTongThanhToan);
      
      txtTiensanpham.setBorder(null); txtTiensanpham.setOpaque(false);txtTiensanpham.setEditable(false);txtTiensanpham.setFocusable(false);
      txtTongThanhToan.setBorder(null); txtTongThanhToan.setOpaque(false);txtTongThanhToan.setEditable(false);txtTongThanhToan.setFocusable(false);
 
        
//        thông tin chung về vé
        lblMaVe = new JLabel("Mã vé:");
        lblMaVe.setFont(new Font("Arial", Font.BOLD, 15));
        lblMaVe.setPreferredSize(new Dimension(80, 30));
        txtMaVe = new JTextField("V12345",15);
        txtMaVe.setFont(new Font("Arial", Font.BOLD, 15));
        txtMaVe.setPreferredSize(new Dimension(140, 30));
        txtMaVe.setHorizontalAlignment(SwingConstants.CENTER);
        pnMaVe = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnMaVe.setBackground(Color.WHITE);
        pnMaVe.add(lblMaVe); pnMaVe.add(txtMaVe);
        
        lblMota = new JLabel("Mô tả:");
        lblMota.setFont(new Font("Arial", Font.BOLD, 15));
        lblMota.setPreferredSize(new Dimension(80, 30));
        txtMota = new JTextField("Cty Cinema ABC chi nhánh HCM",22);
        txtMota.setFont(new Font("Arial", Font.BOLD, 15));
        txtMota.setPreferredSize(new Dimension(140, 30));
        txtMota.setHorizontalAlignment(SwingConstants.CENTER);
        pnMota = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnMota.setBackground(Color.WHITE);
        pnMota.add(lblMota); pnMota.add(txtMota);
        
        txtMaVe.setBorder(null);txtMaVe.setOpaque(false);txtMaVe.setEditable(false);txtMaVe.setFocusable(false);
        txtMota.setBorder(null);txtMota.setOpaque(false);txtMota.setEditable(false);txtMota.setFocusable(false);
        
        Box b = Box.createVerticalBox();
        b.add(pnTenPhim);
        b.add(pnTenTheLoai);
        b.add(pnTenThoiLuong);
        b.add(pnNgayKhoiChieu);
        b.add(pnGioChieu);
        b.add(pnPhongChieu);
        b.add(pnTenGhe);
        b.add(pnCach);
        b.add(pnTiensanpham);
        b.add(pnTongThanhToan);
        
        
        Box bMotave = Box.createVerticalBox();
        bMotave.add(pnMaVe);
        bMotave.add(pnMota);
        
        
        pnB = new JPanel();
        pnB.add(b);
        pnMoTaVe = new JPanel();
        pnMoTaVe.add(bMotave);
     
        add(boxTitle, BorderLayout.NORTH);
        add(pnMoTaVe, BorderLayout.CENTER);
        add(pnB, BorderLayout.SOUTH);
        
        
        setBackground(Color.WHITE);
    }

    private Image scaleImage(Image image, int i, int j) {
    	Image scaled = image.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return scaled;
	}

	// Phương thức để thiết lập giá trị cho các trường thông tin
    public void setThongTinVe() {
        
    }

    public static void main(String[] args) {


        // Hiển thị JFrame
        new GiaoDienVe().setVisible(true);
    }
}
