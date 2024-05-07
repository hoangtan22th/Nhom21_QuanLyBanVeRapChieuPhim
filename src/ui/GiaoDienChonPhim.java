package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 
 * @author nguyentrunghau
 */

public class GiaoDienChonPhim extends JPanel implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JComboBox<String> comboCategory;
	private JList<Phim> movieList;
	private DefaultListModel<Phim> movieListModel;

	private Phim_DAO movieDAO;
	private LoaiPhim_DAO categoryDAO;
	private JPanel midPanel;

	// biến global để lưu trữ thông tin phim
	public static String maPhimVar;
	public static String posterPathVar;
	public static String tenPhimVar;
	public static String ngonNguVar;
	public static String maLoaiPhimVar;
	public static int gioiHanTuoiVar;
	public static double thoiLuongVar;
	public static double giaTienVar;
	public static boolean trangThaiVar;
	public static Date ngayKhoiChieuVar;

	/**
	 * 
	 * get set mã, tên, hình
	 */
	public String getTenPhim() {
		return tenPhimVar;
	}

	public void setTenPhim(String tenPhimVar) {
		GiaoDienChonPhim.tenPhimVar = tenPhimVar;
	}

	public String getMaPhim() {
		return maPhimVar;
	}

	public void setMaPhim(String maPhimVar) {
		GiaoDienChonPhim.maPhimVar = maPhimVar;
	}

	public String getPosterPath() {
		return posterPathVar;
	}

	public void setPosterPath(String posterPathVar) {
		GiaoDienChonPhim.posterPathVar = posterPathVar;
	}

	public String getNgonNgu() {
		return ngonNguVar;
	}

	public void setNgonNgu(String ngonNguVar) {
		GiaoDienChonPhim.ngonNguVar = ngonNguVar;
	}

	public String getMaLoaiPhim() {
		return maLoaiPhimVar;
	}

	public void setMaLoaiPhim(String maLoaiPhimVar) {
		GiaoDienChonPhim.maLoaiPhimVar = maLoaiPhimVar;
	}

	public int getGioiHanTuoi() {
		return gioiHanTuoiVar;
	}

	public void setGioiHanTuoi(int gioiHanTuoiVar) {
		GiaoDienChonPhim.gioiHanTuoiVar = gioiHanTuoiVar;
	}

	public double getThoiLuong() {
		return thoiLuongVar;
	}

	public void setThoiLuong(double thoiLuongVar) {
		GiaoDienChonPhim.thoiLuongVar = thoiLuongVar;
	}

	public double getGiaTien() {
		return giaTienVar;
	}

	public void setGiaTien(double giaTienVar) {
		GiaoDienChonPhim.giaTienVar = giaTienVar;
	}

	public Date getNgayKhoiChieu() {
		return ngayKhoiChieuVar;
	}

	public void setNgayKhoiChieu(Date ngayKhoiChieuVar) {
		GiaoDienChonPhim.ngayKhoiChieuVar = ngayKhoiChieuVar;
	}

	public boolean getTrangThai() {
		return trangThaiVar;
	}

	public void setTrangThai(boolean trangThaiVar) {
		GiaoDienChonPhim.trangThaiVar = trangThaiVar;
	}

	public GiaoDienChonPhim() {
        try {
            ConnectDB.getIntance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        movieDAO = new Phim_DAO();
        categoryDAO = new LoaiPhim_DAO();

        this.setLayout(new BorderLayout());
        JPanel wrapPanel = new JPanel();
        wrapPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        Box topContainer = Box.createVerticalBox();
        Box topTitle = Box.createHorizontalBox();
        Box topTools = Box.createHorizontalBox();

        JLabel lblTitle = new JLabel("CHỌN PHIM");
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 20));

        JLabel lblSearch = new JLabel("Tìm kiếm phim: ");
        lblSearch.setFont(new Font("Helvetica", Font.BOLD, 14));
        JLabel lblCategory = new JLabel("Thể loại: ");
        lblCategory.setFont(new Font("Helvetica", Font.BOLD, 14));

        tfSearch = new JTextField(30);
        tfSearch.setPreferredSize(new Dimension(300, 33));
        tfSearch.setToolTipText("Tìm kiếm phim");
        tfSearch.setText("Tìm kiếm phim theo tên");
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

        comboCategory = new JComboBox<String>();
        comboCategory.setBorder(new LineBorder(new Color(00, 153, 255), 2, getFocusTraversalKeysEnabled()));
        comboCategory.addItem("Tất cả");
        loadCategoryToComboBox();

        topTitle.add(lblTitle);
        topTools.add(lblSearch);
        topTools.add(Box.createHorizontalStrut(10));
        topTools.add(tfSearch);
        topTools.add(Box.createHorizontalStrut(20));
        topTools.add(btnSearch);
        topTools.add(Box.createHorizontalStrut(25));
        topTools.add(lblCategory);
        topTools.add(Box.createHorizontalStrut(10));
        topTools.add(comboCategory);

        topContainer.add(topTitle);
        topContainer.add(Box.createVerticalStrut(10));
        topContainer.add(topTools);
        topContainer.add(Box.createVerticalStrut(10));

        topPanel.add(topContainer);

        wrapPanel.add(topPanel, BorderLayout.NORTH);

        // Container phim
         midPanel = new JPanel();
        midPanel.setLayout(new BorderLayout());

        movieListModel = new DefaultListModel<>();
        movieList = new JList<>(movieListModel);
        movieList.setCellRenderer(new PhimListRenderer()); // Sử dụng renderer để hiển thị mỗi phim
        movieList.setLayoutOrientation(JList.HORIZONTAL_WRAP); // scroll dọc
//        movieList.setLayoutOrientation(JList.VERTICAL_WRAP); // scroll ngang
        movieList.setVisibleRowCount(-1); // hiển thị càng nhiều hàng càng tốt
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // chỉ cho phép chọn một phim
        JScrollPane scrollPane = new JScrollPane(movieList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        midPanel.add(scrollPane, BorderLayout.CENTER);	
        
        wrapPanel.add(midPanel, BorderLayout.CENTER);

        this.add(wrapPanel, BorderLayout.CENTER);
        
        movieList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Phim selectedMovie = movieList.getSelectedValue();
                    if (selectedMovie != null) {
//                        maPhimVar = selectedMovie.getMaPhim();
                    	setMaPhim(selectedMovie.getMaPhim());
//                        posterPathVar = selectedMovie.getPoster();
                    	setPosterPath(selectedMovie.getPoster());
//                        tenPhimVar = selectedMovie.getTenPhim();
                    	setTenPhim(selectedMovie.getTenPhim());
                    	setThoiLuong(selectedMovie.getThoiLuong());
                    	setGiaTien(selectedMovie.getGiaTien());
                    	setGioiHanTuoi(selectedMovie.getGioiHanDoTuoi());
                    	setNgonNgu(selectedMovie.getNgonNgu());
                    	setMaLoaiPhim(selectedMovie.getLoaiPhim().getMaLoaiPhim());
                    	setNgonNgu(selectedMovie.getNgonNgu());
                    	setTrangThai(selectedMovie.getTrangThai());
                    	setNgayKhoiChieu(selectedMovie.getNgayKhoiChieu());
                    	
                    	JOptionPane.showMessageDialog(null, "Bạn đã chọn phim: " + tenPhimVar
                                + "\nMã: " + maPhimVar);

//                        JOptionPane.showMessageDialog(null, "Bạn đã chọn phim: " + tenPhimVar
//                            + "\nMã: " + maPhimVar
//                            + "\nThoi luong: " + thoiLuongVar
//                            + "\nGia: " + giaTienVar
//                            + "\nGHTuoi: " + gioiHanTuoiVar
//                            + "\nNgon: " + ngonNguVar
//                            + "\nmaLoai: " + maLoaiPhimVar
//                            + "\ntrang: " + trangThaiVar
//                            + "\nNgay: " + ngayKhoiChieuVar);
                    }
                }
            }
        });
        
        // hover
        movieList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change cursor to hand when mouse enters the area of the list cell
                movieList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Change cursor back to default when mouse exits the area of the list cell
                movieList.setCursor(Cursor.getDefaultCursor());
            }
        });

        tfSearch.addFocusListener(this);
        btnSearch.addActionListener(this);
        comboCategory.addActionListener(this);

        loadMovieFromDatabase();
    }
//    public void chonPhimMoi(String tenPhimMoi) {  
//        GiaoDienChonThoiGian.capNhatTenPhim(tenPhimMoi);
//    }

	private void loadCategoryToComboBox() {
		List<LoaiPhim> categoryList = categoryDAO.getAllLoaiPhim();
		for (LoaiPhim loaiPhim : categoryList) {
			comboCategory.addItem(loaiPhim.getTenLoaiPhim());
		}
	}

	private void loadMovieFromDatabase() {
		List<Phim> movieListData = movieDAO.getPhimDangChieu();
		List<LoaiPhim> categoryList = categoryDAO.getAllLoaiPhim();
		for (Phim movie : movieListData) {
			for (LoaiPhim loaiPhim : categoryList) {
				if (movie.getLoaiPhim().getMaLoaiPhim().equals(loaiPhim.getMaLoaiPhim())) {
					movieListModel.addElement(movie);
				}
			}
		}
	}

	private class PhimListRenderer extends JPanel implements ListCellRenderer<Phim> {
		private static final long serialVersionUID = 1L;

		private JLabel posterLabel = new JLabel();
		private JLabel titleLabel = new JLabel();
		private JLabel genreLabel = new JLabel();
		private JLabel lengthLabel = new JLabel();

		public PhimListRenderer() {
			setLayout(new BorderLayout());
			// Lấy kích thước của màn hình
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension screenSize = midPanel.getSize();
			int screenWidth = (int) screenSize.getWidth();
			int screenHeight = (int) screenSize.getHeight();

			// Thiết lập kích thước dựa trên kích thước của màn hình
			int preferredWidth = (screenWidth - 20) / 4;
			float preferredHeight = (float) (screenHeight / 1.5);
			setPreferredSize(new Dimension(preferredWidth, (int) preferredHeight));

			add(posterLabel, BorderLayout.NORTH);
			Box bMovieDesc = Box.createVerticalBox();
			titleLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
			bMovieDesc.add(titleLabel);
			genreLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
			bMovieDesc.add(genreLabel);
			lengthLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
			bMovieDesc.add(lengthLabel);

			add(bMovieDesc, BorderLayout.CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends Phim> list, Phim value, int index,
				boolean isSelected, boolean cellHasFocus) {
			ImageIcon poster = new ImageIcon("img//" + value.getPoster());
			Dimension screenSize = midPanel.getSize();
			int screenWidth = (int) screenSize.getWidth();
			int screenHeight = (int) screenSize.getHeight();
			int preferredWidth = (screenWidth - 20) / 4;
			float preferredHeight = (float) (screenHeight / 1.5);
			setPreferredSize(new Dimension(preferredWidth, (int) preferredHeight));
			Image scaled = scaleImage(poster.getImage(), preferredWidth, (int) preferredHeight - 100);

			posterLabel.setIcon(new ImageIcon(scaled));
			titleLabel.setText(value.getTenPhim());
			String loai = "";
			List<LoaiPhim> categoryList = categoryDAO.getAllLoaiPhim();
			for (LoaiPhim loaiPhim : categoryList) {
				if (value.getLoaiPhim().getMaLoaiPhim().equals(loaiPhim.getMaLoaiPhim())) {
					loai = loaiPhim.getTenLoaiPhim();
				}
			}
			genreLabel.setText("Thể loại: " + loai);
			lengthLabel.setText("Thời lượng: " + value.getThoiLuong() + " phút");

			setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

			return this;
		}

		private Image scaleImage(Image image, int w, int h) {
			Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return scaled;
		}
	}

	/**
	 * placeholder tìm kiếm
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (tfSearch.getText().equals("Tìm kiếm phim theo tên")) {
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
			tfSearch.setText("Tìm kiếm phim theo tên");
		}
	}

	/**
	 * event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(comboCategory)) {
			String cate = (String) comboCategory.getSelectedItem();
			loadMoviesByCategoryFromDatabase(cate);
		} else if (o.equals(btnSearch)) {
//            System.out.println(getMaPhim());
			String tenPhim = tfSearch.getText();
			if ((tenPhim.length() > 0)) {
				loadMoviesByNameFromDatabase(tenPhim);
				if (tenPhim.equals("Tìm kiếm phim theo tên")) {
					movieListModel.clear();
					loadMovieFromDatabase();
				}
			} else {
				movieListModel.clear();
				loadMovieFromDatabase();
			}
		}
	}

	private void loadMoviesByNameFromDatabase(String tenPhim) {
		movieListModel.clear();
		List<Phim> movieListData = movieDAO.getPhimByTen(tenPhim);
		for (Phim movie : movieListData) {
			movieListModel.addElement(movie);
		}
	}

	private void loadMoviesByCategoryFromDatabase(String cate) {
		movieListModel.clear();
		if (cate.equals("Tất cả")) {
			loadMovieFromDatabase();
		} else {
			List<Phim> movieListData = movieDAO.getPhimByLoaiPhim(cate);
			for (Phim movie : movieListData) {
				movieListModel.addElement(movie);
			}
		}
	}

}