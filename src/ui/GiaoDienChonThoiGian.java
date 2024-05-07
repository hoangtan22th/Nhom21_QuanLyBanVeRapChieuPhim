package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import connectDB.ConnectDB;
import dao.ChiTietPhim_DAO;
import dao.Phim_DAO;
import dao.Phong_DAO;
import entity.ChiTietPhim;
import entity.Phim;
import entity.Phong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GiaoDienChonThoiGian extends JPanel implements ActionListener {
	public static String soPhong;
	public static String thoiGian;
	public static String suatChieu;

	private static int widthImg = 380;
	private static int heightImg = 500;
	static JButton currenNgaytBtn = null;
	static JButton currentPhongBtn = null;
	static JButton currentSuatChieuBtn = null;
	private static JLabel lblTitle;
	private ImageIcon phimDoremon;
	private Object scaled7;
	private static JLabel lblPhimDoremon;

	private static JPanel pnSuatChieu;

	private JPanel pnThongTin;
	private JLabel lblSoPhong;
	private JLabel lblSoSuatChieu;
	private JLabel lblSoThoiGian;
	private static JTextField txtSoPhong;
	private static JTextField txtSoSuatChieu;
	private static JTextField txtNgay;

	private static GiaoDienChonPhim gdChonPhim;
	private static String duongDanHinhAnh;

	private static Phim_DAO phimDAO;
	private static Phong_DAO phongDAO;
	private static ChiTietPhim_DAO ctpDAO;

	JButton btnChonPhong[];
	JButton btnChonNgay[];
	JButton btnChonSuat[];

	private static Set<String> ngChieuSet;
//	private Set<String> tenPhongSet;
//	private Set<String> giChieuSet;
	private static JPanel pnPhong;
	private static JPanel pnNgay;
	private Container pnThoiGian;
	private static String ngayText;

	public GiaoDienChonThoiGian() {

		try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		gdChonPhim = new GiaoDienChonPhim();

		phimDAO = new Phim_DAO();
		phongDAO = new Phong_DAO();
		ctpDAO = new ChiTietPhim_DAO();

		setLayout(new BorderLayout());

		lblTitle = new JLabel("Phim: ");
//		lblMaPhim = new JLabel("PH001");

		Font font = lblTitle.getFont();
		Font newFont = font.deriveFont(Font.BOLD, 22);
		lblTitle.setFont(newFont);

		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(lblTitle);

		duongDanHinhAnh = "img/3_hiepsimongmo.jpg";

		ImageIcon imageIcon = new ImageIcon(duongDanHinhAnh);
		lblPhimDoremon = new JLabel(imageIcon);
		lblPhimDoremon.setIcon(resizeImageIcon(imageIcon, widthImg, heightImg));

		JPanel imagePanel = new JPanel();
		imagePanel.add(lblPhimDoremon);
		add(imagePanel, BorderLayout.WEST);

		pnNgay = new JPanel();

		pnPhong = new JPanel();

		pnSuatChieu = new JPanel();

		pnThongTin = new JPanel(new GridLayout(3, 2));
		lblSoThoiGian = new JLabel("Ngày đã chọn");
		txtNgay = new JTextField(30);

		lblSoPhong = new JLabel("Phòng đã chọn");
		txtSoPhong = new JTextField(30);

		lblSoSuatChieu = new JLabel("Suất chiếu đã chọn");
		txtSoSuatChieu = new JTextField(30);

		// Đặt font cho các nhãn và ô nhập liệu
		Font font1 = new Font("Arial", Font.BOLD, 15);
		lblSoPhong.setFont(font1);
		lblSoSuatChieu.setFont(font1);
		lblSoThoiGian.setFont(font1);
		txtSoPhong.setFont(font1);
		txtSoSuatChieu.setFont(font1);
		txtNgay.setFont(font1);

		// Ẩn viền và background cho các ô nhập liệu
		txtSoPhong.setBorder(null);
		txtSoSuatChieu.setBorder(null);
		txtNgay.setBorder(null);
		txtSoPhong.setOpaque(false);
		txtSoSuatChieu.setOpaque(false);
		txtNgay.setOpaque(false);


		// Không cho phép người dùng chỉnh sửa nội dung của các ô nhập liệu
		txtSoPhong.setEditable(false);
		txtSoSuatChieu.setEditable(false);
		txtNgay.setEditable(false);
		pnThongTin.add(lblSoThoiGian);
		pnThongTin.add(txtNgay);
		pnThongTin.add(lblSoPhong);
		pnThongTin.add(txtSoPhong);
		pnThongTin.add(lblSoSuatChieu);
		pnThongTin.add(txtSoSuatChieu);

		// Không cho phép focus vào các ô nhập liệu
		txtSoPhong.setFocusable(false);
		txtSoSuatChieu.setFocusable(false);
		txtNgay.setFocusable(false);

		pnThoiGian = new JPanel();

		pnThoiGian.add(pnPhong);
		pnThoiGian.add(pnNgay);
		pnThoiGian.add(pnSuatChieu);
		pnThoiGian.add(pnThongTin);
//        pnThoiGian.add(btnTest);

		pnPhong.setBorder(BorderFactory.createTitledBorder("Chọn Phòng"));
		pnSuatChieu.setBorder(BorderFactory.createTitledBorder("Chọn Suất Chiếu"));
		pnNgay.setBorder(BorderFactory.createTitledBorder("Chọn Ngày"));

		Box pnWrap = Box.createVerticalBox();
		pnWrap.add(titlePanel);
		pnWrap.add(pnNgay);
		pnWrap.add(pnPhong);
		pnWrap.add(pnSuatChieu);
		pnWrap.add(pnThoiGian);
		add(pnWrap, BorderLayout.CENTER);

	}

	public static void getNgayChieu(String maPhim) {
		phimDAO = new Phim_DAO();
		phongDAO = new Phong_DAO();
		ctpDAO = new ChiTietPhim_DAO();
		ngChieuSet = new HashSet<>();

		pnNgay.removeAll();
		pnPhong.removeAll();
		pnSuatChieu.removeAll();
		// get suất chiếu
		List<ChiTietPhim> ctpList = ctpDAO.getChiTietPhimByMaPhim(maPhim);
//				List<ChiTietPhim> ctpList = ctpDAO.getChiTietPhimByMaPhim("PH003");
		for (ChiTietPhim chiTietPhim : ctpList) {
			LocalDateTime lichChieu = chiTietPhim.getLichChieu();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String ngayChieu = dateFormat.format(lichChieu);
			ngChieuSet.add(ngayChieu);
		}

		// Tạo JPanel cho ngày
		for (String ngChieu : ngChieuSet) {
			JButton btnNgay = new JButton(ngChieu);
			btnNgay.setPreferredSize(new Dimension(120, 50));
			btnNgay.setBackground(Color.ORANGE);
			btnNgay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object o = e.getSource();
					if (o instanceof JButton) {
						JButton clickNgayBtn = (JButton) o;
						JButton clickPhongBtn = (JButton) o;
						JButton clickSuatChieuBtn = (JButton) o;
						// sự kiện click ngày
						if (clickNgayBtn.getParent() == pnNgay) {
							if (currenNgaytBtn != null) {
								currenNgaytBtn.setBackground(Color.ORANGE);
								txtSoPhong.setText("");
								soPhong = null;
								txtSoSuatChieu.setText("");
								suatChieu = null;
							}
							currenNgaytBtn = clickNgayBtn;
							clickNgayBtn.setBackground(Color.GREEN);
							// Hiển thị ngày
							txtNgay.setText(clickNgayBtn.getText());
							thoiGian = clickNgayBtn.getText();

							ngayText = clickNgayBtn.getText();
							SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
							try {
								Date ngay = sdfInput.parse(ngayText);

								ArrayList<Phong> dsPhong = ctpDAO.getPhongByNgayChieuVaMaPhim(ngay,
										gdChonPhim.getMaPhim());
								if (dsPhong.isEmpty()) {
									pnPhong.removeAll();
									pnPhong.revalidate();
									pnPhong.repaint();
									pnSuatChieu.removeAll();
									pnSuatChieu.revalidate();
									pnSuatChieu.repaint();
								} else {
									pnPhong.removeAll();
									pnSuatChieu.removeAll();
									for (Phong phong : dsPhong) {
										JButton btnPhong = new JButton(phong.getTenPhong());
										btnPhong.setPreferredSize(new Dimension(120, 50));
										btnPhong.setBackground(Color.ORANGE);
										btnPhong.addActionListener(this);
										pnPhong.add(btnPhong);
									}
									pnPhong.revalidate();
									pnPhong.repaint();
								}

							} catch (ParseException ex) {
								ex.printStackTrace();
							}
							// sự kiện click phòng
						} else if (clickPhongBtn.getParent() == pnPhong) {
							if (currentPhongBtn != null) {
								currentPhongBtn.setBackground(Color.ORANGE);
								txtSoSuatChieu.setText("");
								suatChieu = null;
							}
							currentPhongBtn = clickPhongBtn;
							clickPhongBtn.setBackground(Color.GREEN);
							// Hiển thị phòng
							txtSoPhong.setText(clickPhongBtn.getText());
							soPhong = clickNgayBtn.getText();

							SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
							try {
								Date ngay = sdfInput.parse(ngayText);
								String maPhong = phongDAO.getMaTheoTenPhong(clickPhongBtn.getText());

								ArrayList<ChiTietPhim> dsCtp = ctpDAO.getChiTietPhimByNgayMaPhimMaPhong(ngay,
										gdChonPhim.getMaPhim(), maPhong);
								if (dsCtp.isEmpty()) {
									pnSuatChieu.removeAll();
									pnSuatChieu.revalidate();
									pnSuatChieu.repaint();
								} else {
									pnSuatChieu.removeAll();
									for (ChiTietPhim ctp : dsCtp) {
										Phim phim = phimDAO.getPhimTheoMa(ctp.getPhim().getMaPhim());
										LocalDateTime lichChieu = ctp.getLichChieu();
										LocalDateTime gioKetThuc = lichChieu.plusMinutes((long) phim.getThoiLuong());
										DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
										String gioChieu = timeFormat.format(lichChieu);
										String gioKetThucStr = timeFormat.format(gioKetThuc);

										JButton btnSuatChieu = new JButton(gioChieu + " - " + gioKetThucStr);
										btnSuatChieu.setPreferredSize(new Dimension(120, 50));
										btnSuatChieu.setBackground(Color.ORANGE);
										btnSuatChieu.addActionListener(this);
										pnSuatChieu.add(btnSuatChieu);
									}
									pnPhong.revalidate();
									pnPhong.repaint();
								}

							} catch (ParseException ex) {
								ex.printStackTrace();
							}
							// sự kiện lick suất chiếu
						} else if (clickSuatChieuBtn.getParent() == pnSuatChieu) {
							if (currentSuatChieuBtn != null) {
								currentSuatChieuBtn.setBackground(Color.ORANGE);
							}
							currentSuatChieuBtn = clickSuatChieuBtn;
							clickSuatChieuBtn.setBackground(Color.GREEN);
							// Hiển thị suất chiếu
							txtSoSuatChieu.setText(clickSuatChieuBtn.getText());
							suatChieu = clickSuatChieuBtn.getText();
						}
					}
				}
			});
			pnNgay.add(btnNgay);
		}

		pnNgay.revalidate();
		pnNgay.repaint();
		pnPhong.revalidate();
		pnPhong.repaint();
		pnSuatChieu.revalidate();
		pnSuatChieu.repaint();
	}

	private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(newImage);
	}

	public static void capNhatHinhAnh(String duongDanMoi) {
		if (!duongDanHinhAnh.equals(duongDanMoi)) {
			duongDanHinhAnh = duongDanMoi;
			ImageIcon imageIconMoi = new ImageIcon(duongDanHinhAnh);
			lblPhimDoremon.setIcon(imageIconMoi);
			lblPhimDoremon.setIcon(resizeImageIcon(imageIconMoi, widthImg, heightImg));

			duongDanMoi = "img/" + gdChonPhim.getPosterPath();
			capNhatHinhAnh(duongDanMoi);
		}
	}

	public static void layThongTinPhim(String tenPhim) {
		lblTitle.setText(tenPhim);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Chọn thời gian");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GiaoDienChonThoiGian chontime = new GiaoDienChonThoiGian();
		frame.getContentPane().add(chontime);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // Hiển thị frame
	}
}
