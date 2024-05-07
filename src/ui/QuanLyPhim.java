package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.LoaiPhim_DAO;
import dao.Phim_DAO;
import entity.LoaiPhim;
import entity.Phim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author nguyentrunghau
 */

/**
 * mã màu: new Color(00, 153, 255)
 */

public class QuanLyPhim extends JPanel implements ActionListener, FocusListener {
	private JTextField tfSearchName;
	private JComboBox<String> comboCategory;
	private CustomButton btnSearch;
	private JDateChooser tfTo;
	private JDateChooser tfFrom;
	private CustomButton btnAdd;
	private CustomButton btnUpdate;
	private CustomButton btnRemove;
	private CustomButton btnRefresh;
	private DefaultTableModel modelPhim;
	private JTable tablePhim;
	private Phim_DAO movieDAO;
	private LoaiPhim_DAO categoryDAO;
	private CustomButton btnFilterDate;
	private LocalDate now = LocalDate.now(); 
	@SuppressWarnings("deprecation")
	private Date dfNow = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth());
	@SuppressWarnings("deprecation")
	private Date dfFrom = new Date(now.getYear() - 1910, now.getMonthValue() - 1, now.getDayOfMonth());

	public QuanLyPhim() {

		try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		movieDAO = new Phim_DAO();
		categoryDAO = new LoaiPhim_DAO();

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
//        add(new JLabel("Giao diện quản lý phim"), BorderLayout.CENTER);
		JPanel pnWrap = new JPanel(new BorderLayout());
		pnWrap.setBackground(Color.WHITE);
		pnWrap.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel pnTop = new JPanel();
//		pnTop.setBorder(BorderFactory.createLineBorder(new Color(00, 153, 255)));

		Box bTopContent = Box.createVerticalBox();

		Box bTitle = Box.createHorizontalBox();
		JLabel lblTitle = new JLabel("QUẢN LÝ PHIM");
		lblTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		bTitle.add(lblTitle);

		Box bFilter = Box.createVerticalBox();
		Box bSearch1 = Box.createHorizontalBox();
		JLabel lblSearchName = new JLabel("Tìm kiếm theo tên: ");
		lblSearchName.setFont(new Font("Helvetica", Font.BOLD, 14));
		tfSearchName = new JTextField(40);
		tfSearchName.setPreferredSize(new Dimension(300, 33));
		tfSearchName.setToolTipText("Tìm kiếm phim theo tên");
		tfSearchName.setText("Tìm kiếm phim theo tên");
		tfSearchName.setFont(new Font("Helvetica", Font.ITALIC, 15));
		tfSearchName.setForeground(Color.LIGHT_GRAY);
		tfSearchName.setBorder(new LineBorder(new Color(00, 153, 255), 2, true));
		btnSearch = new CustomButton(new ImageIcon("img//icons8-search-30.png"));
		btnSearch.setFocusPainted(false);
		btnSearch.setPreferredSize(new Dimension(100, 33));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnSearch.setBorder(new LineBorder(new Color(00, 153, 255), 2, true));
		btnSearch.setBackground(new Color(00, 153, 255));
		JLabel lblSearchCate = new JLabel("Lọc theo loại phim: ");
		lblSearchCate.setFont(new Font("Helvetica", Font.BOLD, 14));
		comboCategory = new JComboBox<String>();
		comboCategory.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
		comboCategory.addItem("Tất cả");
		loadCategoryToComboBox();

		bSearch1.add(lblSearchName);
		bSearch1.add(Box.createHorizontalStrut(10));
		bSearch1.add(tfSearchName);
		bSearch1.add(Box.createHorizontalStrut(20));
		bSearch1.add(btnSearch);
		bSearch1.add(Box.createHorizontalStrut(40));
		bSearch1.add(lblSearchCate);
		bSearch1.add(Box.createHorizontalStrut(10));
		bSearch1.add(comboCategory);

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
		tfFrom.setDate(dfFrom);
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

		bSearch2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white), "Tìm theo ngày công chiếu",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 14), Color.gray));
//        bSearch2.add(lblSearchDate);
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

		bTopContent.add(bTitle);
		bTopContent.add(Box.createVerticalStrut(10));
		bTopContent.add(bFilter);
		bTopContent.add(Box.createVerticalStrut(15));
		bTopContent.add(pnTools);
		bTopContent.add(Box.createVerticalStrut(10));

		pnTop.add(bTopContent);
		pnTop.setBackground(Color.WHITE);
		pnWrap.add(pnTop, BorderLayout.NORTH);

		// Table
		JPanel pnBottom = new JPanel(new BorderLayout());
		String header[] = { "STT", "Mã phim", "Tên phim", "Ngày khởi chiếu", "Thời lượng", "Ngôn ngữ", "Giới hạn tuổi",
				"Trạng thái", "Giá tiền", "Loại phim", "Poster" };
		modelPhim = new DefaultTableModel(header, 0);
		tablePhim = new JTable(modelPhim);
		tablePhim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablePhim.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablePhim.setShowHorizontalLines(true);
		tablePhim.setShowGrid(true);
		tablePhim.setBackground(Color.WHITE);
		tablePhim.setFont(new Font("Helvetica", Font.PLAIN, 14));
		tablePhim.setSelectionBackground(new Color(00, 153, 255, 30));
		tablePhim.setSelectionForeground(new Color(00, 153, 255));
		tablePhim.setRowHeight(30);

		JTableHeader tableHeader = tablePhim.getTableHeader();
		tableHeader.setBackground(new Color(00, 153, 255));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("Helvetica", Font.BOLD, 14));

		// thanh cuốn lên xuống
		JScrollPane scrollPhim = new JScrollPane(tablePhim, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPhim.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		scrollPhim.setBackground(new Color(00, 153, 255));
		scrollPhim.getHorizontalScrollBar();

		pnBottom.add(scrollPhim);
		pnWrap.add(pnBottom, BorderLayout.CENTER);
		add(pnWrap, BorderLayout.CENTER);

		tfSearchName.addFocusListener(this);
		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRemove.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnSearch.addActionListener(this);
		comboCategory.addActionListener(this);
		btnFilterDate.addActionListener(this);
		loadAllMovieToTable();

	}

	private void loadCategoryToComboBox() {
		List<LoaiPhim> categoryList = categoryDAO.getAllLoaiPhim();
		for (LoaiPhim loaiPhim : categoryList) {
			comboCategory.addItem(loaiPhim.getTenLoaiPhim());
		}
	}

	public void removeTableData() {
		DefaultTableModel dtm = (DefaultTableModel) tablePhim.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void loadAllMovieToTable() {
		removeTableData();
		List<Phim> movieList = movieDAO.getAllPhim();
		List<LoaiPhim> cateList = categoryDAO.getAllLoaiPhim();
		int i = 0;
		for (Phim phim : movieList) {
			for (LoaiPhim loaiPhim : cateList) {
				if (phim.getLoaiPhim().getMaLoaiPhim().equals(loaiPhim.getMaLoaiPhim()))
					modelPhim.addRow(new Object[] { ++i, phim.getMaPhim(), phim.getTenPhim(), phim.getNgayKhoiChieu(),
							phim.getThoiLuong(), phim.getNgonNgu(), phim.getGioiHanDoTuoi(),
							phim.getTrangThai() ? "Đang chiếu" : "Ngừng chiếu", phim.getGiaTien(),
							loaiPhim.getTenLoaiPhim(), phim.getPoster() });
			}
		}
	}

	private void loadMoviesBySearchName(String tenPhim) {
		removeTableData();
		List<Phim> movieList = movieDAO.getPhimByTen(tenPhim);
		List<LoaiPhim> cateList = categoryDAO.getAllLoaiPhim();
		int i = 0;
		for (Phim phim : movieList) {
			for (LoaiPhim loaiPhim : cateList) {
				if (phim.getLoaiPhim().getMaLoaiPhim().equals(loaiPhim.getMaLoaiPhim()))
					modelPhim.addRow(new Object[] { ++i, phim.getMaPhim(), phim.getTenPhim(), phim.getNgayKhoiChieu(),
							phim.getThoiLuong(), phim.getNgonNgu(), phim.getGioiHanDoTuoi(),
							phim.getTrangThai() ? "Đang chiếu" : "Ngừng chiếu", phim.getGiaTien(),
							loaiPhim.getTenLoaiPhim(), phim.getPoster() });
			}
		}
	}

	private void loadMoviesBySearchByCategory(String cate) {
		removeTableData();
		if (cate.equals("Tất cả")) {
			loadAllMovieToTable();
		} else {
			List<Phim> movieList = movieDAO.getPhimByLoaiPhim(cate);
			List<LoaiPhim> cateList = categoryDAO.getAllLoaiPhim();
			int i = 0;
			for (Phim phim : movieList) {
				for (LoaiPhim loaiPhim : cateList) {
					if (phim.getLoaiPhim().getMaLoaiPhim().equals(loaiPhim.getMaLoaiPhim()))
						modelPhim.addRow(new Object[] { ++i, phim.getMaPhim(), phim.getTenPhim(),
								phim.getNgayKhoiChieu(), phim.getThoiLuong(), phim.getNgonNgu(),
								phim.getGioiHanDoTuoi(), phim.getTrangThai() ? "Đang chiếu" : "Ngừng chiếu",
								phim.getGiaTien(), loaiPhim.getTenLoaiPhim(), phim.getPoster() });
				}
			}
		}
	}

	public void loadMoviesByDate(Date from, Date to) {
		removeTableData();
		List<Phim> movieList = movieDAO.getPhimByNgayCongChieu(from, to);
		List<LoaiPhim> cateList = categoryDAO.getAllLoaiPhim();
		int i = 0;
		for (Phim phim : movieList) {
			for (LoaiPhim loaiPhim : cateList) {
				if (phim.getLoaiPhim().getMaLoaiPhim().equals(loaiPhim.getMaLoaiPhim()))
					modelPhim.addRow(new Object[] { ++i, phim.getMaPhim(), phim.getTenPhim(), phim.getNgayKhoiChieu(),
							phim.getThoiLuong(), phim.getNgonNgu(), phim.getGioiHanDoTuoi(),
							phim.getTrangThai() ? "Đang chiếu" : "Ngừng chiếu", phim.getGiaTien(),
							loaiPhim.getTenLoaiPhim(), phim.getPoster() });
			}
		}
	}

	/**
	 * placeholder tìm kiếm
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (tfSearchName.getText().equals("Tìm kiếm phim theo tên")) {
			tfSearchName.setFont(new Font("Helvetica", Font.PLAIN, 15));
			tfSearchName.setForeground(Color.BLACK);
			tfSearchName.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (tfSearchName.getText().equals("")) {
			tfSearchName.setFont(new Font("Helvetica", Font.ITALIC, 15));
			tfSearchName.setForeground(Color.LIGHT_GRAY);
			tfSearchName.setText("Tìm kiếm phim theo tên");
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Quản Lý Phim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		QuanLyPhim quanLyPhim = new QuanLyPhim();
		frame.getContentPane().add(quanLyPhim);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // Hiển thị frame

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnAdd) {
			// Hiển thị dialog Thêm phim
			AddEditMovieDialog addEditDialog = new AddEditMovieDialog((JFrame) SwingUtilities.getWindowAncestor(this),
					"Thêm Phim", true);
			addEditDialog.setVisible(true);
			// cập nhật bảng
			loadAllMovieToTable();
		} else if (o == btnUpdate) {
			int selectedRow = tablePhim.getSelectedRow() > tablePhim.getRowCount() ? -1 : tablePhim.getSelectedRow();
			if (selectedRow != -1) {
				String maPhim = tablePhim.getValueAt(selectedRow, 1).toString();
				String tenPhim = tablePhim.getValueAt(selectedRow, 2).toString();
				String ngayKhoiChieu = tablePhim.getValueAt(selectedRow, 3).toString();
				String thoiLuong = tablePhim.getValueAt(selectedRow, 4).toString();
				String ngonNgu = tablePhim.getValueAt(selectedRow, 5).toString().trim();
				String gioiHanTuoi = tablePhim.getValueAt(selectedRow, 6).toString();
				String trangThai = tablePhim.getValueAt(selectedRow, 7).toString();
				String giaTien = tablePhim.getValueAt(selectedRow, 8).toString();
				String loaiPhim = tablePhim.getValueAt(selectedRow, 9).toString();
				String poster = tablePhim.getValueAt(selectedRow, 10).toString();
				// Hiển thị dialog Sửa phim
				AddEditMovieDialog addEditDialog = new AddEditMovieDialog(
						(JFrame) SwingUtilities.getWindowAncestor(this), "Cập Nhật Phim", true);
				// Thiết lập thông tin của phim lên dialog
				addEditDialog.setMovieInfo(maPhim, tenPhim, ngayKhoiChieu, thoiLuong, ngonNgu, gioiHanTuoi, trangThai,
						giaTien, loaiPhim, poster);
				addEditDialog.setVisible(true);
				loadAllMovieToTable();
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phim cần sửa");
			}
		} else if (o.equals(btnRemove)) {
			int selectedRow = tablePhim.getSelectedRow() > tablePhim.getRowCount() ? -1 : tablePhim.getSelectedRow();
			if (selectedRow != -1) {
				if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa ?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maPhim = modelPhim.getValueAt(selectedRow, 1).toString();
					String posterImg = (String) modelPhim.getValueAt(selectedRow, 10);
					if (movieDAO.remove(maPhim)) {
						// xóa poster trong folder img
						Path posterPath = Paths.get("img//" + posterImg);
						try {
							Files.deleteIfExists(posterPath);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						loadAllMovieToTable();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phim cần xóa");
			}
		} else if (o.equals(btnSearch)) {
			String tenPhim = tfSearchName.getText().trim();
			if ((tenPhim.length() > 0)) {
				loadMoviesBySearchName(tenPhim);
				if (tenPhim.equals("Tìm kiếm phim theo tên")) {
					loadAllMovieToTable();
				}
			} else {
				loadAllMovieToTable();
			}
		} else if (o.equals(comboCategory)) {
			String cate = (String) comboCategory.getSelectedItem();
			loadMoviesBySearchByCategory(cate);
		} else if (o.equals(btnFilterDate)) {
			Date from = tfFrom.getDate();
			Date to = tfTo.getDate();
			loadMoviesByDate(from, to);
		} else if (o.equals(btnRefresh)) {
			tfSearchName.setText("Tìm kiếm phim theo tên");
			tfSearchName.setFont(new Font("Helvetica", Font.ITALIC, 15));
			tfSearchName.setForeground(Color.LIGHT_GRAY);
			comboCategory.setSelectedIndex(0);
			tfFrom.setDate(dfFrom);
			tfTo.setDate(dfNow);
			loadAllMovieToTable();
		}
	}
}
