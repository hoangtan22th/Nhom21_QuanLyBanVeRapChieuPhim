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
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
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
import dao.NhanVien_DAO;
import entity.ChiTiet_HoaDon;
import entity.HoaDon;
import entity.NhanVien;

public class ThongKeNhanVien extends JPanel implements FocusListener, ActionListener {
	private JLabel lblTest;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JDateChooser tfFrom;
	private JDateChooser tfTo;
	private JButton btnThongKe;
	private JButton btnRefresh;
	private DefaultTableModel modelNV;
	private JTable tableNV;

//	public ThongKe1() {
//		lblTest = new JLabel("hi");
//		add(lblTest);
//	}
	private LocalDate now = LocalDate.now();
	@SuppressWarnings("deprecation")
	private Date dfNow = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth());
//	@SuppressWarnings("deprecation")
//	private Date dfFrom = new Date(now.getYear() - 1910, now.getMonthValue() - 1, now.getDayOfMonth());

	
	HoaDon_DAO hoaDonDAO;
	NhanVien_DAO nhanVienDAO;
	ChiTietHoaDon_DAO cthdDAO;
	
	/**
	 * 
	 * @author nguyentrunghau
	 */
	
	public ThongKeNhanVien() {
		
		try {
            ConnectDB.getIntance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		hoaDonDAO = new HoaDon_DAO();
		nhanVienDAO = new NhanVien_DAO();
		cthdDAO = new ChiTietHoaDon_DAO();
		
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
		JLabel lblTitle = new JLabel("THỐNG KÊ NHÂN VIÊN");
		lblTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		bTitle.add(lblTitle);

		Box bFilter = Box.createVerticalBox();
		Box bSearch1 = Box.createHorizontalBox();
		JLabel lblSearchName = new JLabel("Tìm kiếm theo tên: ");
		lblSearchName.setFont(new Font("Helvetica", Font.BOLD, 14));
		tfSearch = new JTextField(40);
		tfSearch.setPreferredSize(new Dimension(300, 33));
		tfSearch.setToolTipText("Tìm kiếm nhân viên theo tên");
		tfSearch.setText("Tìm kiếm nhân viên theo tên");
		tfSearch.setFont(new Font("Helvetica", Font.ITALIC, 15));
		tfSearch.setForeground(Color.LIGHT_GRAY);
		tfSearch.setBorder(new LineBorder(new Color(00, 153, 255), 2, true));
		btnSearch = new CustomButton(new ImageIcon("img//icons8-search-30.png"));
		btnSearch.setFocusPainted(false);
		btnSearch.setPreferredSize(new Dimension(100, 33));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnSearch.setBorder(new LineBorder(new Color(00, 153, 255), 2, true));
		btnSearch.setBackground(new Color(00, 153, 255));

		bSearch1.add(lblSearchName);
		bSearch1.add(Box.createHorizontalStrut(10));
		bSearch1.add(tfSearch);
		bSearch1.add(Box.createHorizontalStrut(20));
		bSearch1.add(btnSearch);

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

		bFilter.add(bSearch1);
		bFilter.add(Box.createVerticalStrut(10));
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
		String header[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Số lượng hóa đơn", "Doanh thu" };
		modelNV = new DefaultTableModel(header, 0);
		tableNV = new JTable(modelNV);
		tableNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableNV.setShowHorizontalLines(true);
		tableNV.setShowGrid(true);
		tableNV.setBackground(Color.WHITE);
		tableNV.setFont(new Font("Helvetica", Font.PLAIN, 14));
		tableNV.setSelectionBackground(new Color(00, 153, 255, 30));
		tableNV.setSelectionForeground(new Color(00, 153, 255));
		tableNV.setRowHeight(30);

		JTableHeader tableHeader = tableNV.getTableHeader();
		tableHeader.setBackground(new Color(00, 153, 255));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("Helvetica", Font.BOLD, 14));

		// thanh cuốn lên xuống
		JScrollPane scrollNV = new JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollNV.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
		scrollNV.setBackground(new Color(00, 153, 255));
		scrollNV.getHorizontalScrollBar();

		pnBottom.add(scrollNV);
		pnWrap.add(pnBottom, BorderLayout.CENTER);
		add(pnWrap, BorderLayout.CENTER);

		tfSearch.addFocusListener(this);
		btnThongKe.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnSearch.addActionListener(this);
		
		loadDataTable();
		
	}

	private void loadDataTable() {
		java.util.Date utilNgayBatDau = tfFrom.getDate();
		java.util.Date utilNgayKetThuc = tfTo.getDate();

		java.sql.Date ngayBatDau = new java.sql.Date(utilNgayBatDau.getTime());
		java.sql.Date ngayKetThuc = new java.sql.Date(utilNgayKetThuc.getTime());


	    if (ngayBatDau.before(ngayKetThuc) || ngayBatDau.equals(ngayKetThuc)) {
	        ArrayList<NhanVien> lstNV = nhanVienDAO.getAllNhanVien();

	        // Xóa dữ liệu cũ trong bảng
	        DefaultTableModel model = (DefaultTableModel) tableNV.getModel();
	        model.setRowCount(0);

	        int stt = 1;
	        for (NhanVien nv : lstNV) {
	            int soLuongHoaDon = cthdDAO.countHoaDonByNhanVien(nv.getMaNhanVien(), ngayBatDau, ngayKetThuc);
	            double doanhThu = cthdDAO.sumDoanhThuByNhanVien(nv.getMaNhanVien(), ngayBatDau, ngayKetThuc);
	            if (soLuongHoaDon > 0)
	            	model.addRow(new Object[] { stt++, nv.getMaNhanVien(), nv.getTenNhanVien(), 
	            		nv.getSoDienThoai(), soLuongHoaDon, doanhThu });
	        }
	        
	     // Sắp xếp dữ liệu theo cột "Doanh thu" giảm dần
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	        tableNV.setRowSorter(sorter);
	        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
	        int columnIndexToSort = 5; // Index của cột "Doanh thu" (đếm từ 0)
	        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING)); // Sắp xếp giảm dần
	        sorter.setSortKeys(sortKeys);
	    } else 
	    	JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
	}


	/**
	 * placeholder tìm kiếm
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (tfSearch.getText().equals("Tìm kiếm nhân viên theo tên")) {
			tfSearch.setFont(new Font("Helvetica", Font.PLAIN, 15));
			tfSearch.setForeground(Color.BLACK);
			tfSearch.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (tfSearch.getText().equals("")) {
			tfSearch.setFont(new Font("Helvetica", Font.ITALIC, 15));
			tfSearch.setForeground(Color.LIGHT_GRAY);
			tfSearch.setText("Tìm kiếm nhân viên theo tên");
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Thống kê nhân viên");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ThongKeNhanVien tk1 = new ThongKeNhanVien();
		frame.getContentPane().add(tk1);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // Hiển thị frame

	}
	
	private void loadEmployeesBySearchName(String tenNV) {
	    // Lấy trình sắp xếp của bảng
	    TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) tableNV.getRowSorter();
	    
	    // Tạo bộ lọc mới dựa trên giá trị tìm kiếm và cột "Tên nhân viên"
	    RowFilter<TableModel, Object> filter = RowFilter.regexFilter("(?i)" + tenNV, 2);
	    
	    // Thiết lập bộ lọc cho trình sắp xếp
	    sorter.setRowFilter(filter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			loadDataTable();
		} else if (o.equals(btnRefresh)) {
			tfSearch.setText("Tìm kiếm nhân viên theo tên");
			tfSearch.setFont(new Font("Helvetica", Font.ITALIC, 15));
			tfSearch.setForeground(Color.LIGHT_GRAY);
			tfFrom.setDate(dfNow);
			tfTo.setDate(dfNow);
			loadDataTable();
		} else if (o.equals(btnSearch)) {
			String tenNV = tfSearch.getText().trim();
	        if ((tenNV.length() > 0)) {
	            loadEmployeesBySearchName(tenNV);
	        } else {
	            loadDataTable();
	        }
		}
	}
}
