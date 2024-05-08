package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class ThongKeHoaDon extends JPanel implements ActionListener {
	private JLabel lblTest;
//	private JTextField tfSearch;
//	private JButton btnSearch;
	private JDateChooser tfFrom;
	private JDateChooser tfTo;
	private JButton btnThongKe;
	private JButton btnRefresh;
	private DefaultTableModel modelHD;
	private JTable tableTK;

//	public ThongKe1() {
//		lblTest = new JLabel("hi");
//		add(lblTest);
//	}
	private LocalDate now = LocalDate.now();
	@SuppressWarnings("deprecation")
	private Date dfNow = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth());
	private CustomButton btnTongDoanhThu;
	private NhanVien_DAO nhanVienDAO;
	private HoaDon_DAO hoaDonDAO;
	private ChiTietHoaDon_DAO cthdDAO;
	private KhachHang_DAO khachHangDAO;

	public ThongKeHoaDon() {

		try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		nhanVienDAO = new NhanVien_DAO();
		hoaDonDAO = new HoaDon_DAO();
		cthdDAO = new ChiTietHoaDon_DAO();
		khachHangDAO = new KhachHang_DAO();

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
//        add(new JLabel("Giao diện quản lý phim"), BorderLayout.CENTER);
		JPanel pnWrap = new JPanel(new BorderLayout());
		pnWrap.setBackground(Color.WHITE);
		pnWrap.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel pnTop = new JPanel(new BorderLayout());
//		pnTop.setBorder(BorderFactory.createLineBorder(new Color(00, 153, 255)));

		Box bTopLeftContent = Box.createVerticalBox();

		Box bTitle = Box.createHorizontalBox();
		JLabel lblTitle = new JLabel("THỐNG KÊ HÓA ĐƠN");
		lblTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		bTitle.add(lblTitle);

		Box bFilter = Box.createVerticalBox();
		

		

		Box bSearch2 = Box.createHorizontalBox();
//        JLabel lblSearchDate = new JLabel("Tìm theo ngày công chiếu");
//        lblSearchDate.setFont(new Font("Helvetica", Font.BOLD, 14));

		JLabel lblFrom = new JLabel("Từ ngày: ");
		lblFrom.setFont(new Font("Helvetica", Font.BOLD, 14));
		tfFrom = new JDateChooser();
		tfFrom.getCalendarButton().setToolTipText("Từ ngày");
		tfFrom.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tfFrom.getCalendarButton().setPreferredSize(new Dimension(33, 33));
		tfFrom.getCalendarButton().setBackground(new Color(00, 153, 255));
		tfFrom.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfFrom.setDateFormatString("dd/MM/yyyy");
		tfFrom.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		tfFrom.setDate(dfNow);
		JLabel lblTo = new JLabel("Đến ngày: ");
		lblTo.setFont(new Font("Helvetica", Font.BOLD, 14));
		tfTo = new JDateChooser();
		tfTo.getCalendarButton().setToolTipText("Đến ngày");
		tfTo.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tfTo.getCalendarButton().setPreferredSize(new Dimension(33, 33));
		tfTo.getCalendarButton().setBackground(new Color(00, 153, 255));
		tfTo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfTo.setDateFormatString("dd/MM/yyyy");
		tfTo.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		tfTo.setDate(dfNow);

		bSearch2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white), "Lọc theo ngày",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 14), Color.gray));
//        bSearch2.add(lblSearchDate);
		bSearch2.add(lblFrom);
		bSearch2.add(Box.createHorizontalStrut(10));
		bSearch2.add(tfFrom);
		bSearch2.add(Box.createHorizontalStrut(25));
		bSearch2.add(lblTo);
		bSearch2.add(Box.createHorizontalStrut(10));
		bSearch2.add(tfTo);

		
		bFilter.add(bSearch2);

		JPanel pnTools = new JPanel();
		// nút thêm
		btnThongKe = new CustomButton("Thống kê");
		btnThongKe.setFocusPainted(false);
		btnThongKe.setPreferredSize(new Dimension(150, 33));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThongKe.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnThongKe.setBackground(new Color(57, 210, 247));

		// nút reset
		btnRefresh = new CustomButton("Làm mới");
		btnRefresh.setFocusPainted(false);
		btnRefresh.setPreferredSize(new Dimension(150, 33));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnRefresh.setBackground(new Color(114, 23, 153));
		pnTools.add(btnThongKe);
		pnTools.add(Box.createHorizontalStrut(30));
		pnTools.add(btnRefresh);
		pnTools.setBackground(Color.WHITE);

//		bTopLeftContent.add(bTitle);
//		bTopLeftContent.add(Box.createVerticalStrut(10));
		bTopLeftContent.add(bFilter);
		bTopLeftContent.add(Box.createVerticalStrut(15));
		bTopLeftContent.add(pnTools);
		bTopLeftContent.add(Box.createVerticalStrut(10));

//		bTopLeftContent.setBorder(BorderFactory.createLineBorder(Color.blue));

		JPanel bTopRightContent = new JPanel(new BorderLayout());
		bTopRightContent.setBackground(Color.white);
//		bTopRightContent.setBorder(BorderFactory.createLineBorder(Color.blue));

		JLabel lblDollar = new JLabel("$");
		lblDollar.setFont(new Font("SansSerif", Font.BOLD, 60));
		lblDollar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDollar.setForeground(Color.ORANGE);

		btnTongDoanhThu = new CustomButton("55.000.000.000 VND");
		btnTongDoanhThu.setFocusPainted(false);
		btnTongDoanhThu.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnTongDoanhThu.setForeground(Color.BLACK);
		btnTongDoanhThu.setBackground(Color.WHITE);

		JLabel lblTDT = new JLabel("Tổng doanh thu");
		lblTDT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTDT.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblTDT.setForeground(new Color(00, 153, 255));

		bTopRightContent.add(lblDollar, BorderLayout.NORTH);
		bTopRightContent.add(btnTongDoanhThu, BorderLayout.CENTER);
		bTopRightContent.add(lblTDT, BorderLayout.SOUTH);

		pnTop.add(bTitle, BorderLayout.NORTH);
		pnTop.add(bTopLeftContent, BorderLayout.CENTER);
		pnTop.add(bTopRightContent, BorderLayout.EAST);
		pnTop.setBackground(Color.WHITE);
		pnWrap.add(pnTop, BorderLayout.NORTH);

		// Table
		JPanel pnBottom = new JPanel(new BorderLayout());
		String header[] = { "STT", "Mã hóa đơn", "Mã nhân viên", "Tên nhân viên", "Mã khách hàng", "Tên khách hàng",
				"Ngày lập", "Tổng thu" };
		modelHD = new DefaultTableModel(header, 0);
		tableTK = new JTable(modelHD);
		tableTK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableTK.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableTK.setShowHorizontalLines(true);
		tableTK.setShowGrid(true);
		tableTK.setBackground(Color.WHITE);
		tableTK.setFont(new Font("Helvetica", Font.PLAIN, 14));
		tableTK.setSelectionBackground(new Color(00, 153, 255, 30));
		tableTK.setSelectionForeground(new Color(00, 153, 255));
		tableTK.setRowHeight(30);

		JTableHeader tableHeader = tableTK.getTableHeader();
		tableHeader.setBackground(new Color(00, 153, 255));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("Helvetica", Font.BOLD, 14));

		// thanh cuốn lên xuống
		JScrollPane scrollHD = new JScrollPane(tableTK, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollHD.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		scrollHD.setBackground(new Color(00, 153, 255));
		scrollHD.getHorizontalScrollBar();

		pnBottom.add(scrollHD);
		pnWrap.add(pnBottom, BorderLayout.CENTER);
		add(pnWrap, BorderLayout.CENTER);

		
		btnThongKe.addActionListener(this);
		btnRefresh.addActionListener(this);

		loadDataTable();
	}

	

	private void loadDataTable() {
		java.util.Date utilNgayBatDau = tfFrom.getDate();
		java.util.Date utilNgayKetThuc = tfTo.getDate();

		java.sql.Date ngayBatDau = new java.sql.Date(utilNgayBatDau.getTime());
		java.sql.Date ngayKetThuc = new java.sql.Date(utilNgayKetThuc.getTime());

		if (ngayBatDau.before(ngayKetThuc) || ngayBatDau.equals(ngayKetThuc)) {
			ArrayList<HoaDon> lstHD = hoaDonDAO.getAllHoaDon();

			DefaultTableModel model = (DefaultTableModel) tableTK.getModel();
			model.setRowCount(0);

			int stt = 1;
			double tongDoanhThu = 0;
			for (HoaDon hd : lstHD) {
				String tenNV = nhanVienDAO.getTenNVTheoMa(hd.getNhanVien().getMaNhanVien());
				String tenKH = khachHangDAO.getTenKHTheoMa(hd.getKhachHang().getMaKhachHang());
				double tongThu = cthdDAO.getTongThu(hd.getMaHoaDon(), ngayBatDau, ngayKetThuc);
				if (tongThu > 0) {
					tongDoanhThu += tongThu;
					model.addRow(new Object[] { stt++, hd.getMaHoaDon(), hd.getNhanVien().getMaNhanVien(), tenNV,
							hd.getKhachHang().getMaKhachHang(), tenKH, hd.getNgayLapHoaDon(), tongThu});					
				}
			}
			DecimalFormat decimalFormat = new DecimalFormat("###,###");
	        String formattedTongDoanhThu = decimalFormat.format(tongDoanhThu);
	        btnTongDoanhThu.setText(formattedTongDoanhThu + " VND");
		} else
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			loadDataTable();
		} else if (o.equals(btnRefresh)) {
			tfFrom.setDate(dfNow);
			tfTo.setDate(dfNow);
			loadDataTable();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Thống kê hóa đơn");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ThongKeHoaDon tk1 = new ThongKeHoaDon();
		frame.getContentPane().add(tk1);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // Hiển thị frame

	}
}
