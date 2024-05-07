package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.ChiTietPhim_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhim_DAO;
import dao.NhanVien_DAO;
import dao.Phim_DAO;
import dao.Phong_DAO;
import entity.ChiTietPhim;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhim;
import entity.NhanVien;
import entity.Phim;
import entity.Phong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class QuanLySuatChieu extends JPanel implements ActionListener {
	private JComboBox<String> comboPhongSearch;
	private JComboBox<String> comboPhong;
	private JDateChooser tfTo;
	private JDateChooser tfFrom;
	private CustomButton btnAdd;
	private CustomButton btnUpdate;
	private CustomButton btnRemove;
	private CustomButton btnRefresh;
	private DefaultTableModel modelSuatChieu;
	private JTable tableSuatChieu;

	private Phim_DAO phimDAO;
	private Phong_DAO phongDAO;
	private ChiTietPhim_DAO ctpDAO;

	private CustomButton btnFilterDate;
	private LocalDate now = LocalDate.now();
	@SuppressWarnings("deprecation")
	private Date dfNow = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth());
	private JComboBox<String> comboPhim;
	private JDateChooser dcNgayChieu;
	private JComboBox<Integer> comboGio;
	private JComboBox<Integer> comboPhut;

	public QuanLySuatChieu() {

		try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		phimDAO = new Phim_DAO();
		phongDAO = new Phong_DAO();
		ctpDAO = new ChiTietPhim_DAO();

		setLayout(new BorderLayout());

		setBackground(Color.WHITE);
//	        add(new JLabel("Giao diện quản lý phim"), BorderLayout.CENTER);
		JPanel pnWrap = new JPanel(new BorderLayout());
		pnWrap.setBackground(Color.WHITE);
		pnWrap.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel pnTop = new JPanel();
//			pnTop.setBorder(BorderFactory.createLineBorder(new Color(00, 153, 255)));

		Box bTopContent = Box.createVerticalBox();

		Box bTitle = Box.createHorizontalBox();
		JLabel lblTitle = new JLabel("QUẢN LÝ SUẤT CHIẾU");
		lblTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		bTitle.add(lblTitle);

		Box bFilter = Box.createVerticalBox();
		Box bSearch1 = Box.createHorizontalBox();
		
		JLabel lblSearchPhong = new JLabel("Tìm suất chiếu theo phòng: ");
		lblSearchPhong.setFont(new Font("Helvetica", Font.BOLD, 14));
		comboPhongSearch = new JComboBox<String>();
		comboPhongSearch.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
		comboPhongSearch.addItem("Tất cả");




		bSearch1.add(lblSearchPhong);
		bSearch1.add(Box.createHorizontalStrut(10));
		bSearch1.add(comboPhongSearch);
		bSearch1.add(Box.createHorizontalStrut(300));

		Box bSearch2 = Box.createHorizontalBox();
//	        JLabel lblSearchDate = new JLabel("Tìm theo ngày công chiếu");
//	        lblSearchDate.setFont(new Font("Helvetica", Font.BOLD, 14));

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
		btnFilterDate = new CustomButton(new ImageIcon("img//icons8-filter-32.png"));
		btnFilterDate.setFocusPainted(false);
		btnFilterDate.setPreferredSize(new Dimension(50, 33));
		btnFilterDate.setForeground(Color.WHITE);
		btnFilterDate.setFont(new Font("Helvetica", Font.BOLD, 16));
		btnFilterDate.setBorder(new LineBorder(new Color(00, 153, 255), 2, true));
		btnFilterDate.setBackground(new Color(00, 153, 255, 200));

		bSearch2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white), "Tìm suất chiếu",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 14), Color.gray));
//	        bSearch2.add(lblSearchDate);
		bSearch2.add(lblFrom);
		bSearch2.add(Box.createHorizontalStrut(10));
		bSearch2.add(tfFrom);
		bSearch2.add(Box.createHorizontalStrut(25));
		bSearch2.add(lblTo);
		bSearch2.add(Box.createHorizontalStrut(10));
		bSearch2.add(tfTo);
		bSearch2.add(Box.createHorizontalStrut(25));
		bSearch2.add(btnFilterDate);

		bFilter.add(bSearch1);
		bFilter.add(Box.createVerticalStrut(10));
		bFilter.add(bSearch2);

		JPanel pnTools = new JPanel();
		// nút thêm
		btnAdd = new CustomButton("Thêm");
		btnAdd.setFocusPainted(false);
		btnAdd.setPreferredSize(new Dimension(100, 33));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBackground(new Color(57, 210, 247));
		// nút sửa
		btnUpdate = new CustomButton("Sửa");
		btnUpdate.setFocusPainted(false);
		btnUpdate.setPreferredSize(new Dimension(100, 33));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(133, 217, 191));
		// nút xóa
		btnRemove = new CustomButton("Xóa");
		btnRemove.setFocusPainted(false);
		btnRemove.setPreferredSize(new Dimension(100, 33));
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnRemove.setBackground(new Color(0xE91940));
		// nút reset
		btnRefresh = new CustomButton("Làm mới");
		btnRefresh.setFocusPainted(false);
		btnRefresh.setPreferredSize(new Dimension(100, 33));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnRefresh.setBackground(new Color(114, 23, 153));
		pnTools.add(btnAdd);
		pnTools.add(Box.createHorizontalStrut(30));
		pnTools.add(btnUpdate);
		pnTools.add(Box.createHorizontalStrut(30));
		pnTools.add(btnRemove);
		pnTools.add(Box.createHorizontalStrut(30));
		pnTools.add(btnRefresh);
		pnTools.setBackground(Color.WHITE);

		Box bInputWrap = Box.createVerticalBox();
		Box bInput1 = Box.createHorizontalBox();
		Box bInput2 = Box.createHorizontalBox();
		JLabel lblPhim = new JLabel("Chọn phim: ");
		comboPhim = new JComboBox<String>();
		comboPhim.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
		loadPhimToComboBox();
		JLabel lblPhong = new JLabel("Chọn phòng: ");
		comboPhong = new JComboBox<String>();
		comboPhong.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
		loadPhongToComboBox();
		bInput1.add(lblPhim);
		bInput1.add(Box.createHorizontalStrut(10));
		bInput1.add(comboPhim);
		bInput1.add(Box.createHorizontalStrut(50));
		bInput1.add(lblPhong);
		bInput1.add(Box.createHorizontalStrut(10));
		bInput1.add(comboPhong);
		JLabel lblNgayChieu = new JLabel("Ngày chiếu: ");
		dcNgayChieu = new JDateChooser();
		dcNgayChieu.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcNgayChieu.getCalendarButton().setPreferredSize(new Dimension(33, 33));
		dcNgayChieu.getCalendarButton().setBackground(new Color(00, 153, 255));
		dcNgayChieu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dcNgayChieu.setDateFormatString("dd/MM/yyyy");
		dcNgayChieu.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		dcNgayChieu.setDate(dfNow);
		JLabel lblGioChieu = new JLabel("Giờ chiếu: ");
		comboGio = new JComboBox<Integer>();
		for (int i = 0; i < 24; i++) {
			comboGio.addItem(i);
		}
		comboGio.setPreferredSize(new Dimension(70, 0));
		comboGio.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
		JLabel lblHaiCham = new JLabel(":");
		comboPhut = new JComboBox<Integer>();
		for (int i = 0; i < 60; i++) {
			comboPhut.addItem(i);
		}
		comboPhut.setPreferredSize(new Dimension(70, 0));
		comboPhut.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
		bInput2.add(lblNgayChieu);
		bInput2.add(Box.createHorizontalStrut(10));
		bInput2.add(dcNgayChieu);
		bInput2.add(Box.createHorizontalStrut(30));
		bInput2.add(lblGioChieu);
		bInput2.add(Box.createHorizontalStrut(10));
		bInput2.add(comboGio);
		bInput2.add(Box.createHorizontalStrut(10));
		bInput2.add(lblHaiCham);
		bInput2.add(Box.createHorizontalStrut(10));
		bInput2.add(comboPhut);
		bInput2.add(Box.createHorizontalStrut(100));
		bInputWrap.add(bInput1);
		bInputWrap.add(Box.createVerticalStrut(20));
		bInputWrap.add(bInput2);
		bInputWrap.setBorder(BorderFactory.createTitledBorder("Nhập dữ liệu"));

		bTopContent.add(bTitle);
		bTopContent.add(Box.createVerticalStrut(10));
		bTopContent.add(bFilter);
		bTopContent.add(Box.createVerticalStrut(15));
		bTopContent.add(bInputWrap);
		bTopContent.add(Box.createVerticalStrut(15));
		bTopContent.add(pnTools);
		bTopContent.add(Box.createVerticalStrut(10));

		pnTop.add(bTopContent);
		pnTop.setBackground(Color.WHITE);
		pnWrap.add(pnTop, BorderLayout.NORTH);

		// Table
		JPanel pnBottom = new JPanel(new BorderLayout());
		String header[] = { "STT", "Mã phim", "Tên phim", "Mã phòng", "Tên phòng", "Ngày chiếu", "Giờ bắt đầu chiếu",
				"Giờ kết thúc" };
		modelSuatChieu = new DefaultTableModel(header, 0);
		tableSuatChieu = new JTable(modelSuatChieu);
		tableSuatChieu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableSuatChieu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableSuatChieu.setShowHorizontalLines(true);
		tableSuatChieu.setShowGrid(true);
		tableSuatChieu.setBackground(Color.WHITE);
		tableSuatChieu.setFont(new Font("Helvetica", Font.PLAIN, 14));
		tableSuatChieu.setSelectionBackground(new Color(00, 153, 255, 30));
		tableSuatChieu.setSelectionForeground(new Color(00, 153, 255));
		tableSuatChieu.setRowHeight(30);

		JTableHeader tableHeader = tableSuatChieu.getTableHeader();
		tableHeader.setBackground(new Color(00, 153, 255));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("Helvetica", Font.BOLD, 14));

		// thanh cuốn lên xuống
		JScrollPane scrollPhim = new JScrollPane(tableSuatChieu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPhim.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		scrollPhim.setBackground(new Color(00, 153, 255));
		scrollPhim.getHorizontalScrollBar();

		pnBottom.add(scrollPhim);
		pnWrap.add(pnBottom, BorderLayout.CENTER);
		add(pnWrap, BorderLayout.CENTER);

		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRemove.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnFilterDate.addActionListener(this);
		comboPhongSearch.addActionListener(this);

		loadDataToTable();

	}

	public void loadPhimToComboBox() {
		List<Phim> phimList = phimDAO.getPhimDangChieu();
		for (Phim phim : phimList) {
			comboPhim.addItem(phim.getTenPhim());
		}
	}

	public void loadPhongToComboBox() {
		List<Phong> phongList = phongDAO.getAllPhong();
		for (Phong phong : phongList) {
			comboPhong.addItem(phong.getTenPhong());
			comboPhongSearch.addItem(phong.getTenPhong());
		}
	}

	public void removeTableData() {
		DefaultTableModel dtm = (DefaultTableModel) tableSuatChieu.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void loadDataToTable() {
		removeTableData();
		List<ChiTietPhim> ctpList = ctpDAO.getAllChiTietPhim();
		int i = 0;

		for (ChiTietPhim chiTietPhim : ctpList) {
			Phim phim = phimDAO.getPhimTheoMa(chiTietPhim.getPhim().getMaPhim());
			Phong phong = phongDAO.getPhongTheoMa(chiTietPhim.getPhong().getMaPhong());

			LocalDateTime lichChieu = chiTietPhim.getLichChieu();
			LocalDateTime gioKetThuc = lichChieu.plusMinutes((long) phim.getThoiLuong());

			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

			String ngayChieu = dateFormat.format(lichChieu);
			String gioChieu = timeFormat.format(lichChieu);
			String gioKetThucStr = timeFormat.format(gioKetThuc);

			modelSuatChieu.addRow(new Object[] { ++i, chiTietPhim.getPhim().getMaPhim(), phim.getTenPhim(),
					chiTietPhim.getPhong().getMaPhong(), phong.getTenPhong(), ngayChieu, gioChieu, gioKetThucStr });

		}
	}
	
	public void searchByPhong(String tenPhong) {
		removeTableData();
		List<ChiTietPhim> ctpList = ctpDAO.getChiTietPhimByMaPhong(phongDAO.getMaTheoTenPhong(tenPhong));
		int i = 0;

		for (ChiTietPhim chiTietPhim : ctpList) {
			Phim phim = phimDAO.getPhimTheoMa(chiTietPhim.getPhim().getMaPhim());
			Phong phong = phongDAO.getPhongTheoMa(chiTietPhim.getPhong().getMaPhong());

			LocalDateTime lichChieu = chiTietPhim.getLichChieu();
			LocalDateTime gioKetThuc = lichChieu.plusMinutes((long) phim.getThoiLuong());

			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

			String ngayChieu = dateFormat.format(lichChieu);
			String gioChieu = timeFormat.format(lichChieu);
			String gioKetThucStr = timeFormat.format(gioKetThuc);

			modelSuatChieu.addRow(new Object[] { ++i, chiTietPhim.getPhim().getMaPhim(), phim.getTenPhim(),
					chiTietPhim.getPhong().getMaPhong(), phong.getTenPhong(), ngayChieu, gioChieu, gioKetThucStr });

		}
	}
	
	public void filterDate(Date from, Date to) {
		removeTableData();
		List<ChiTietPhim> ctpList = ctpDAO.getChiTietPhimByNgay(from, to);
		int i = 0;

		for (ChiTietPhim chiTietPhim : ctpList) {
			Phim phim = phimDAO.getPhimTheoMa(chiTietPhim.getPhim().getMaPhim());
			Phong phong = phongDAO.getPhongTheoMa(chiTietPhim.getPhong().getMaPhong());

			LocalDateTime lichChieu = chiTietPhim.getLichChieu();
			LocalDateTime gioKetThuc = lichChieu.plusMinutes((long) phim.getThoiLuong());

			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

			String ngayChieu = dateFormat.format(lichChieu);
			String gioChieu = timeFormat.format(lichChieu);
			String gioKetThucStr = timeFormat.format(gioKetThuc);

			modelSuatChieu.addRow(new Object[] { ++i, chiTietPhim.getPhim().getMaPhim(), phim.getTenPhim(),
					chiTietPhim.getPhong().getMaPhong(), phong.getTenPhong(), ngayChieu, gioChieu, gioKetThucStr });

		}
	}


	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			String tenPhim = (String) comboPhim.getSelectedItem();
			String tenPhong = (String) comboPhong.getSelectedItem();
			Date ngayChieu = dcNgayChieu.getDate();
			int gioChieu = (int) comboGio.getSelectedItem();
			int phutChieu = (int) comboPhut.getSelectedItem();

			LocalDateTime lichChieu = LocalDateTime.ofInstant(ngayChieu.toInstant(), ZoneId.systemDefault())
					.withHour(gioChieu).withMinute(phutChieu);
			
			if (lichChieu.isBefore(LocalDateTime.now())) {
		        JOptionPane.showMessageDialog(null, "Không thể thêm suất chiếu với ngày hoặc giờ trước hiện tại");
		        return;
		    }
			
			Phim phim = phimDAO.getPhimTheoMa(phimDAO.getMaTheoTenPhim(tenPhim));
			Phong phong = phongDAO.getPhongTheoMa(phongDAO.getMaTheoTenPhong(tenPhong));
			ChiTietPhim ctp = new ChiTietPhim(lichChieu, phim, phong);

			if (ctpDAO.insert(ctp)) {
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				loadDataToTable();
			} else {
				JOptionPane.showMessageDialog(null, "Suất chiếu bị trùng");
			}
		} else if (o.equals(btnRemove)) {
			int selectedRow = tableSuatChieu.getSelectedRow();
			if (selectedRow != -1) {
				String maPhim = (String) tableSuatChieu.getValueAt(selectedRow, 1);
				String maPhong = (String) tableSuatChieu.getValueAt(selectedRow, 3);
				String ngayChieuStr = (String) tableSuatChieu.getValueAt(selectedRow, 5);
				String gioChieuStr = (String) tableSuatChieu.getValueAt(selectedRow, 6);

				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

				LocalDateTime lichChieu = LocalDateTime.parse(ngayChieuStr + " " + gioChieuStr,
						DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

				int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa suất chiếu này?",
						"Xác nhận xóa", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					if (ctpDAO.delete(maPhim, maPhong, lichChieu)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						loadDataToTable();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn suất chiếu cần xóa");
			}
		} else if (o.equals(btnUpdate)) {

		} else if (o.equals(btnRefresh)) {
			ctpDAO.xoaSuatChieuHetHan();
			clearInputFields();
		} else if (o.equals(comboPhongSearch)) {
			String tenPhong = (String) comboPhongSearch.getSelectedItem();
			if (tenPhong == "Tất cả")
				loadDataToTable();
			else
				searchByPhong(tenPhong);
		} else if (o.equals(btnFilterDate)) {
			Date from = tfFrom.getDate();
			Date to = tfTo.getDate();
			filterDate(from, to);
		}
	}

	private void clearInputFields() {
		comboPhim.setSelectedIndex(0);
		comboPhong.setSelectedIndex(0);
		dcNgayChieu.setDate(dfNow);
		comboGio.setSelectedIndex(0);
		comboPhut.setSelectedIndex(0);
		loadDataToTable();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Quản Lý Suất Chiếu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		QuanLySuatChieu quanLySuatCieu = new QuanLySuatChieu();
		frame.getContentPane().add(quanLySuatCieu);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // Hiển thị frame

	}

}
