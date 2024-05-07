package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.TheThanhVien_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GiaoDienThanhToan extends JPanel implements ActionListener{
    private JLabel lblTitle1;
	private JLabel lblTien;
	private JPanel pnThongTin;
	private JCheckBox checkBox;
	private JLabel lblSDT;
	private JTextField txtSDT;
	private JLabel lblHoTen;
	private JTextField txtHoTen;
	private JLabel lblGhiChu;
	private JTextArea txtGhiChu;
	private JButton btnThanhtoan;
	private JPanel pnSDT;
	private JPanel pnHoTen;
	private JPanel pnGhiChu;
	private JCheckBox checkBoxDaCoThe;
	private JCheckBox checkBoxLamThe;
	private JCheckBox checkBoxKhongThe;
	private JRadioButton radioButtonDaCoThe;
	private JRadioButton radioButtonLamThe;
	private JRadioButton radioButtonKhongThe;
	private JPanel pnTitle;
	private JLabel lblTuoi;
	private JTextField txtTuoi;
	private JPanel pnTuoi;
	private JPanel pnTien;
	private JPanel pnLeft;
	private ImageIcon imgQLKhachhang;
	private JPanel pnThongTinPhim;
	private JLabel lblTen;
	private JLabel lblRap;
	private JLabel lblSuatChieu;
	private JLabel lblPhong;
	private JLabel lblGhe;
	private JLabel lblThoiLuong;
	private JLabel lblTheLoai;
	private JLabel lblTongTien;
	private static JLabel lblImgScaled;
	private static JTextField txtTen;
	private static JTextField txtRap;
	private static JTextField txtPhong;
	private static JTextField txtSuatChieu;
	private static JTextField txtGhe;
	private static JTextField txtThoiLuong;
	private static JTextField txtTongTien;
	private static JTextField txtTheLoai;

    public GiaoDienThanhToan() {
        setLayout(new BorderLayout());
        lblTitle1 = new JLabel("Chọn bắp/nước");
        pnTitle = new JPanel(new FlowLayout(FlowLayout.LEFT,20,30));
        pnTitle.add(lblTitle1);
        add(pnTitle, BorderLayout.NORTH);
        
//      connectDB
      try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
//      màu xanh chủ đạo: 
		Color blueDark = new Color(0, 153, 255);

       
        String[] columnNames = {"Combo", "Số lượng", "Đơn giá(VNĐ)", "Tổng(VNĐ)"};

        Object[][] data = {
                {"Bắp + Pepsi vị chanh lớn", 0, 60000, 0},
                {"Bắp + Pepsi vị chanh vừa", 0, 80000, 0},
                {"Bắp + Pepsi vị chanh lớn", 0, 100000, 0},
                {"Bắp rang bơ vị phô mai nhỏ", 0, 80000, 0},
                {"Bắp rang bơ vị phô mai vừa", 0, 100000,0},
                {"Bắp rang bơ vị phô mai lớn", 0, 12000,0}
        	};

        //BẢNG
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(600, 150));
        table.setGridColor(Color.WHITE); // Set màu của đường biên
        table.setBorder(BorderFactory.createEmptyBorder()); // Đặt đường biên trống
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font chữ
        table.setRowHeight(90); // Set chiều cao của mỗi dòng
        
        JScrollPane scrollPane = new JScrollPane(table);
        // Custom renderer cho cột "Combo"
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageTextRenderer());
        // Tạo editor cho cột "Số lượng"
        TableColumn quantityColumn = table.getColumnModel().getColumn(1);
        quantityColumn.setCellEditor(new QuantityEditor());
        TableColumn quantityColumnSoluong = table.getColumnModel().getColumn(0); // Lấy cột "combo"
        quantityColumnSoluong.setPreferredWidth(200); // Đặt chiều rộng mong muốn

        // Set renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa dữ liệu
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Căn giữa cột "Đơn giá(VNĐ)"
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Căn giữa cột "Tổng(VNĐ)"

        // Set renderer cho tiêu đề cột
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD)); // In đậm tiêu đề
        //  HẾT BANG
        
        lblTien = new JLabel("Tiền sản phẩm:");
        pnTien = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
        pnTien.add(lblTien);
        
        Box box = Box.createVerticalBox();
        box.add(scrollPane);
        //box.add(pnTien);
    
        
        // THÔNG TIN KHÁCH HÀNG, THANH TOÁN
        // ẢNH PHIM
		ImageIcon imgPhim = new ImageIcon("img/quat-mo-trung-ma.jpg");
		Image scaledPhim = scaleImage(imgPhim.getImage(), 200, 280);
		ImageIcon imgScaled = new ImageIcon(scaledPhim); 
		lblImgScaled = new JLabel(imgScaled);
//		pnThongTinPhim = new JPanel(new BorderLayout());
		Box boxThongTinVe = Box.createHorizontalBox();
		boxThongTinVe.add(lblImgScaled, BorderLayout.WEST);
		
		
        //thông tin phim
		lblTen = new JLabel("Tên:  ");
		lblRap = new JLabel("Rạp:  ");
		lblPhong = new JLabel("Phòng:  ");
		lblSuatChieu = new JLabel("Xuất chiếu:  ");
		lblGhe = new JLabel("Ghế:  ");
		lblThoiLuong = new JLabel("Thời lượng:  "); 
		lblTheLoai = new JLabel("Ngày:  ");
		//lblTongTien = new JLabel("Tổng tiền:  ");
		txtTen = new JTextField("Quật mộ trùng ma");
		txtRap = new JTextField("Galaxy");
		txtPhong = new JTextField("1");
		txtSuatChieu = new JTextField("21:00");
		txtGhe = new JTextField("H5");
		txtThoiLuong = new JTextField("1 giờ"); 
		txtTheLoai = new JTextField("10 tg 1");
		//txtTongTien = new JTextField("24000");
		
		txtTen.setBorder(null); txtTen.setOpaque(false);txtTen.setEditable(false);txtTen.setFocusable(false);
		txtRap.setBorder(null); txtRap.setOpaque(false);txtRap.setEditable(false);txtRap.setFocusable(false);
		txtPhong.setBorder(null); txtPhong.setOpaque(false);txtPhong.setEditable(false);txtPhong.setFocusable(false);
		txtSuatChieu.setBorder(null); txtSuatChieu.setOpaque(false);txtSuatChieu.setEditable(false);txtSuatChieu.setFocusable(false);
		txtGhe.setBorder(null); txtGhe.setOpaque(false);txtGhe.setEditable(false);txtGhe.setFocusable(false);
		txtThoiLuong.setBorder(null); txtThoiLuong.setOpaque(false);txtThoiLuong.setEditable(false);txtThoiLuong.setFocusable(false);
		txtTheLoai.setBorder(null); txtTheLoai.setOpaque(false);txtTheLoai.setEditable(false);txtTheLoai.setFocusable(false);
		//txtTongTien.setBorder(null); txtTongTien.setOpaque(false);txtTongTien.setEditable(false);txtTongTien.setFocusable(false);
		
		
		// Định nghĩa màu trong suốt
		Color transparentColor = new Color(255, 255, 255, 100); // 100 là độ mờ, giá trị nằm trong khoảng từ 0 đến 255
		Color systemColor = new Color(0xF0F0F0); 
		
		// Đặt màu nền trong suốt cho các JTextField
		txtTen.setBackground(systemColor);
		txtRap.setBackground(systemColor);
		txtPhong.setBackground(systemColor);
		txtSuatChieu.setBackground(systemColor);
		txtGhe.setBackground(systemColor);
		txtThoiLuong.setBackground(systemColor);
		txtTheLoai.setBackground(systemColor);
		//txtTongTien.setBackground(systemColor);
		
		// Đặt border cho JTextField
        
		Border border = BorderFactory.createLineBorder(systemColor);
		txtTen.setBorder(border);
		txtRap.setBorder(border);
		txtPhong.setBorder(border);
		txtSuatChieu.setBorder(border);
		txtGhe.setBorder(border);
		txtThoiLuong.setBorder(border);
		txtTheLoai.setBorder(border);
		//txtTongTien.setBorder(border);
	        
		
		Box boxTen = Box.createHorizontalBox();
		Box boxRap = Box.createHorizontalBox();
		Box boxPhong = Box.createHorizontalBox();
		Box boxSuatChieu = Box.createHorizontalBox();
		Box boxGhe = Box.createHorizontalBox();
		Box boxThoiLuong = Box.createHorizontalBox();
		Box boxTheLoai = Box.createHorizontalBox();
		//Box boxTongTien = Box.createHorizontalBox();
		
		boxTen.add(lblTen); boxTen.add(txtTen);
		boxRap.add(lblRap); boxRap.add(txtRap);
		boxPhong.add(lblPhong); boxPhong.add(txtPhong);
		boxSuatChieu.add(lblSuatChieu); boxSuatChieu.add(txtSuatChieu);
		boxGhe.add(lblGhe); boxGhe.add(txtGhe);
		boxThoiLuong.add(lblThoiLuong); boxThoiLuong.add(txtThoiLuong);
		boxTheLoai.add(lblTheLoai); boxTheLoai.add(txtTheLoai);
		//boxTongTien.add(lblTongTien); boxTongTien.add(txtTongTien);
		
		Box boxThongTinPhim = Box.createVerticalBox();
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxTen);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxRap);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxPhong);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxSuatChieu);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxGhe);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxThoiLuong);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		boxThongTinPhim.add(boxTheLoai);
		boxThongTinPhim.add(Box.createVerticalStrut(12));
		//boxThongTinPhim.add(boxTongTien);
		// Đặt khoảng cách từ lề trái
		boxThongTinPhim.setAlignmentX(Component.LEFT_ALIGNMENT);
		boxThongTinPhim.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0)); // Cách trái 50px
		boxThongTinVe.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0)); // Cách trái 15px
		boxThongTinVe.add(boxThongTinPhim, BorderLayout.CENTER);
		
        pnThongTin = new  JPanel();
        radioButtonDaCoThe = new JRadioButton("Đã có thẻ thành viên");
        radioButtonLamThe = new JRadioButton("Làm thẻ thành viên");
        radioButtonKhongThe = new JRadioButton("Không có thẻ thành viên");
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,20));
        radioPanel.add(radioButtonDaCoThe);
        radioPanel.add(radioButtonLamThe);
        radioPanel.add(radioButtonKhongThe);
        
        
        lblSDT = new JLabel("Số điện thoại:");
        txtSDT = new JTextField(40);
        lblHoTen = new JLabel("Họ và tên:");
        txtHoTen = new JTextField(40);
        lblTuoi = new JLabel("Tuổi");
        txtTuoi = new JTextField(40);
        lblGhiChu = new JLabel("Ghi chú:");
        txtGhiChu = new JTextArea(2,40);
        JScrollPane scrollPaneGhiChu = new JScrollPane(txtGhiChu);
        btnThanhtoan = new JButton("XÁC NHẬN");
        btnThanhtoan.setBackground(blueDark);
        txtSDT.setPreferredSize(new Dimension(40, 30));
        txtHoTen.setPreferredSize(new Dimension(40, 30));
        txtTuoi.setPreferredSize(new Dimension(40,30));
      
        pnSDT = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));
        pnSDT.add(lblSDT); pnSDT.add(txtSDT);
        pnHoTen = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,8));
        pnHoTen.add(lblHoTen); pnHoTen.add(txtHoTen);
        pnTuoi = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,10));
        pnTuoi.add(lblTuoi); pnTuoi.add(txtTuoi);
        pnGhiChu = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,10));
        pnGhiChu.add(lblGhiChu); pnGhiChu.add(scrollPaneGhiChu);
        
        Box boxThongTin = Box.createVerticalBox();
        boxThongTin.add(boxThongTinVe);
        boxThongTin.add(radioPanel);
        boxThongTin.add(pnSDT);
        boxThongTin.add(pnHoTen);
        boxThongTin.add(pnTuoi);
        boxThongTin.add(pnGhiChu);
        boxThongTin.add(btnThanhtoan);
        
  
     // Tạo một nhóm để chỉ cho phép chọn một radio button trong nhóm
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonDaCoThe);
        buttonGroup.add(radioButtonLamThe);
        buttonGroup.add(radioButtonKhongThe);
        
        add(box, BorderLayout.WEST);
        add(boxThongTin, BorderLayout.CENTER);
        
        
        btnThanhtoan.addActionListener(this);
        radioButtonDaCoThe.addActionListener(this);
        radioButtonKhongThe.addActionListener(this);
        radioButtonLamThe.addActionListener(this);
        
    }

	private static Image scaleImage(Image image, int i, int j) {
		Image scaled = image.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return scaled;
	}

	// Định nghĩa phương thức để cập nhật hình ảnh cho nhãn
	public static void capNhatHinhAnhPhim(String duongDan) {
	    ImageIcon imgPhim = new ImageIcon("img/"+duongDan);
	    Image scaledPhim = scaleImage(imgPhim.getImage(), 200, 280);
	    ImageIcon imgScaled = new ImageIcon(scaledPhim);
	    lblImgScaled.setIcon(imgScaled);
	}
	
	
	 
	// Renderer để chèn hình ảnh vào bên trái của văn bản
    static class ImageTextRenderer extends DefaultTableCellRenderer {
        private JLabel label = new JLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Thay đổi đường dẫn tới hình ảnh của bạn
            ImageIcon imageIcon = new ImageIcon("img/bap-nuoc.png");
            Image scaledImage = scaleImage(imageIcon.getImage(), 60, 60);
            label.setIcon(new ImageIcon(scaledImage));
            label.setText(value.toString());
            return label;
        }

        private Image scaleImage(Image image, int w, int h) {
            return image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        }
    }

    // Editor cho cột "Số lượng"
    static class QuantityEditor extends AbstractCellEditor implements TableCellEditor {
        private JComboBox<Integer> comboBox;

        public QuantityEditor() {
            comboBox = new JComboBox<>();
            for (int i = 0; i <= 10; i++) {
                comboBox.addItem(i);
            }

            // Thêm sự kiện cho JComboBox
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped(); // Khi giá trị được chọn thay đổi, chạy sự kiện fireEditingStopped
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return comboBox.getSelectedItem(); // Trả về giá trị đã chọn trong JComboBox
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            comboBox.setSelectedItem(value); // Đặt giá trị mặc định cho JComboBox
            return comboBox; // Trả về JComboBox làm editor cho ô cụ thể
        }
    }
    public static void setThongTinPhim(String ten,String rap,String phong,String suatChieu,String ghe,String thoiLuong,String theLoai) {
    	txtTen.setText(ten);
    	txtRap.setText(rap);
    	txtPhong.setText(phong);
    	txtSuatChieu.setText(suatChieu);
    	txtGhe.setText(ghe);
    	txtThoiLuong.setText(thoiLuong);
    	txtTheLoai.setText(theLoai); // đã đổi thành thời gian
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(radioButtonDaCoThe)) {
			// Hiển thị input yêu cầu nhập số điện thoại
            lblSDT.setVisible(true);
            txtSDT.setVisible(true);
            lblGhiChu.setVisible(true);
            txtGhiChu.setVisible(true);
            // Ẩn input nhập họ tên 
            lblHoTen.setVisible(false);
            txtHoTen.setVisible(false);
            lblTuoi.setVisible(false);
            txtTuoi.setVisible(false);
			
			
			
		}
		else if(o.equals(radioButtonLamThe)) {
			// Xử lý sự kiện khi radio button Làm thẻ thành viên được chọn
            // Hiển thị input yêu cầu nhập số điện thoại và họ tên
            lblSDT.setVisible(true);
            txtSDT.setVisible(true);
            lblHoTen.setVisible(true);
            txtHoTen.setVisible(true);
            lblTuoi.setVisible(true);
            txtTuoi.setVisible(true);		
            lblGhiChu.setVisible(true);
            txtGhiChu.setVisible(true);
		}
		else if(o.equals(radioButtonKhongThe)) {
			// Xử lý sự kiện khi radio button Không có thẻ thành viên được chọn
            // Hiển thị input yêu cầu nhập ghi chú
            lblGhiChu.setVisible(true);
            txtGhiChu.setVisible(true);
            // Ẩn input nhập số điện thoại và họ tên
            lblSDT.setVisible(false);
            txtSDT.setVisible(false);
            lblHoTen.setVisible(false);
            txtHoTen.setVisible(false);
            lblTuoi.setVisible(true);
            txtTuoi.setVisible(true);
		}
		
		else if (o.equals(btnThanhtoan)) 
		{
		    if (radioButtonDaCoThe.isSelected()) 
		    {	    	
		        String sdt = txtSDT.getText().trim();
		        HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
		        KhachHang_DAO khachHangDAO = new KhachHang_DAO();
		        String maKhachHang = khachHangDAO.timMaKhachHangTheoSDT(sdt);
		        		        
		        if (maKhachHang != null) {
		        	 HoaDon hoaDon = new HoaDon();
		             hoaDon.setKhachHang(new KhachHang(maKhachHang));
		             hoaDon.setNhanVien(new NhanVien(DangNhap.maNhanVien));
		             hoaDonDAO.themHoaDonKhiCoTheThanhVien(hoaDon);
		            JOptionPane.showMessageDialog(this, "Đã tạo hoá đơn thành công.");
		        } 
		        else {
		            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại này.");
		        }
		        
		     
		    }
		    if (radioButtonLamThe.isSelected()) {
		        if (txtSDT != null && txtHoTen != null && txtTuoi != null) {
		            String sdt = txtSDT.getText().trim();
		            String hoTen = txtHoTen.getText();
		            String tuoi = txtTuoi.getText().trim();
		            int tuoi2 = Integer.parseInt(tuoi);
		            
		            KhachHang_DAO khachHangDAO = new KhachHang_DAO();
		            boolean khachHangInserted = khachHangDAO.insertKhachHang(hoTen, sdt, tuoi2, true);
		            
		            if (khachHangInserted) {
		                
		                TheThanhVien_DAO ttvDAO = new TheThanhVien_DAO();
		                boolean theThanhVienInserted = ttvDAO.taoTheThanhVienVsKhachHangCuoiCung();
		                
		                if (theThanhVienInserted) {
		                    JOptionPane.showMessageDialog(this, "Đã tạo khách hàng và thẻ thành viên thành công");
		                } else {
		                    JOptionPane.showMessageDialog(this, "Tạo khách hàng thành công nhưng không thể tạo thẻ thành viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(this, "Không thành công khi tạo khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		    if (radioButtonKhongThe.isSelected()) {
		    	 KhachHang_DAO khachHangDAO = new KhachHang_DAO();

		            String tuoi = txtTuoi.getText().trim();
		            if(tuoi.equals("")) {
		            	JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		            else {
		            	boolean khachHangInserted = khachHangDAO.insertKhachHang("Khách vãng lai", "", Integer.parseInt(tuoi), false);
		            	if (khachHangInserted) {
			            	HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
			 		        HoaDon hoaDon = new HoaDon();		 		
			 		        hoaDon.setNgayLapHoaDon(new Date());		        
			 		        hoaDon.setNhanVien(new NhanVien(DangNhap.maNhanVien)); 		        		     
			 		        hoaDonDAO.themHoaDonVoiKhachHangCuoiCung(hoaDon);		        
			 		        JOptionPane.showMessageDialog(this, "Đã tạo hoá đơn thành công.");
			            }
			            
				           
		            }
		            
		             
		            
		       
		        
		    }


		    GiaoDienThanhToan2.capNhatThongTinTrangThanhToan();
		    }
		
		
		}

}




