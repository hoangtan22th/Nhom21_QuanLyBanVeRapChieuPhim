package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.TheThanhVien_DAO;
import entity.KhachHang;
import entity.TheThanhVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class QuanLyKhachHang extends JPanel {
    private JTextField txtTuoi;
    private DefaultTableModel tableModel;
    private JTable table;
    private KhachHang_DAO KH_DAO;
	private JPanel searchPN;
	private JLabel searchLbl;
	private JTextField searchTxt;
	private JPanel pnTen;
	private JPanel pnMa;
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblTen;
	private JTextField txtTen;
	private JPanel pnTuoi;
	private JLabel lblTuoi;
	private JPanel pnDT;
	private JLabel lblDT;
	private JTextField txtDT;
	private JPanel pnTTV;
	private JLabel lblTTV;
	private JCheckBox ckcTTV;
	private JButton capNhatBtn;
	private JButton xoaBtn;
	private JPanel btns;
	private JButton searchBtn;
	private JButton lamMoiBtn;
	private TheThanhVien_DAO TTV_DAO;
    private String maTTV;
	private String loaiTTV;
	private double diemTL;
	private String ngayDk;

    public QuanLyKhachHang() {
        //    	ket noi DB
        try {
            ConnectDB.getIntance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        KH_DAO = new KhachHang_DAO();
        TTV_DAO = new TheThanhVien_DAO();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm border trống xung quanh panel
        setBackground(Color.WHITE); // Đặt màu nền cho giao diện là màu trắng

        // Panel nhập liệu
//        JPanel inputPanel = new JPanel(); // Sử dụng GridLayout để phân bố các thành phần theo hàng và cột
        Box B = Box.createVerticalBox();
        
        searchPN = new JPanel();
        searchPN.setBackground(Color.WHITE);
        searchLbl = new JLabel("Tìm kiếm khách hàng theo tên:");
        searchTxt = new JTextField(40);
        searchTxt.setPreferredSize(new Dimension(30, 30));
        searchBtn = new CustomButton("Tìm kiếm");
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchBtn.setPreferredSize(new Dimension(100, 33));
        searchBtn.setBorderPainted(false);
        searchBtn.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
        searchBtn.setBackground(new Color(57, 210, 247));
        searchBtn.setBounds(178, 184, 145, 38);
        searchBtn.setBorder(getBorder());
        searchPN.add(searchLbl);
        searchPN.add(searchTxt);
        searchPN.add(searchBtn);
        
        Box input1 = Box.createHorizontalBox();
        Box input2 = Box.createHorizontalBox();
        Box input3 = Box.createHorizontalBox();
        
        pnMa = new JPanel();
        pnMa.setBackground(Color.WHITE);
        lblMa = new JLabel("Mã khách hàng");
        txtMa = new JTextField(30);
        txtMa.setEditable(false);
        txtMa.setPreferredSize(new Dimension(30, 30));
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        
        pnTen = new JPanel();
        pnTen.setBackground(Color.WHITE);
        lblTen = new JLabel("Tên khách hàng");
        txtTen = new JTextField(30);
        txtTen.setPreferredSize(new Dimension(30, 30));
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        
        pnTuoi = new JPanel();
        pnTuoi.setBackground(Color.WHITE);
        lblTuoi = new JLabel("Tuổi");
        txtTuoi = new JTextField(30);
        txtTuoi.setPreferredSize(new Dimension(30, 30));
        pnTuoi.add(lblTuoi);
        pnTuoi.add(txtTuoi);
        
        pnDT = new JPanel();
        pnDT.setBackground(Color.WHITE);
        lblDT = new JLabel("Số điện thoại");
        txtDT = new JTextField(30);
        txtDT.setPreferredSize(new Dimension(30, 30));
        pnDT.add(lblDT);
        pnDT.add(txtDT);
        
        pnTTV = new JPanel();
        pnTTV.setBackground(Color.WHITE);
        lblTTV = new JLabel("Thẻ thành viên");
        ckcTTV = new JCheckBox();
        pnTTV.add(lblTTV);
        pnTTV.add(ckcTTV);

        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblTuoi.setPreferredSize(lblTen.getPreferredSize());
        lblDT.setPreferredSize(lblTen.getPreferredSize());
        
        input1.add(pnMa);
        input1.add(pnTen);
        input2.add(pnTuoi);
        input2.add(pnDT);
        input3.add(pnTTV);
        
        btns = new JPanel();
        btns.setBackground(Color.WHITE);
        
        capNhatBtn = new CustomButton("Cập nhật");
        capNhatBtn.setForeground(Color.WHITE);
        capNhatBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        capNhatBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        capNhatBtn.setPreferredSize(new Dimension(100, 33));
        capNhatBtn.setBorderPainted(false);
        capNhatBtn.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
        capNhatBtn.setBackground(new Color(133, 217, 191));
        
        xoaBtn = new CustomButton("Xoá");
        xoaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        xoaBtn.setForeground(Color.WHITE);
        xoaBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        xoaBtn.setPreferredSize(new Dimension(100, 33));
        xoaBtn.setBorderPainted(false);
        xoaBtn.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
        xoaBtn.setBackground(new Color(0xE91940));
        
        lamMoiBtn = new CustomButton("Làm mới");
        lamMoiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lamMoiBtn.setForeground(Color.WHITE);
        lamMoiBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        lamMoiBtn.setPreferredSize(new Dimension(100, 33));
        lamMoiBtn.setBorderPainted(false);
        lamMoiBtn.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
        lamMoiBtn.setBackground(new Color(0, 153, 255));
        
        btns.add(lamMoiBtn);
        btns.add(Box.createHorizontalStrut(40));
        btns.add(capNhatBtn);
        btns.add(Box.createHorizontalStrut(40));
        btns.add(xoaBtn);
        
        B.add(searchPN);
        B.add(input1);
        B.add(input2);
        B.add(input3);
        B.add(btns);
//        add(inputPanel, BorderLayout.NORTH);
        add(B, BorderLayout.NORTH);

        // Tạo bảng danh sách khách hàng
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa trực tiếp trên bảng
            }
        };
        table = new JTable(tableModel);

        // Thêm cột vào bảng theo thứ tự mong muốn: Mã KH, Tên KH, Tuổi, Số điện thoại, Thẻ thành viên
        tableModel.addColumn("Mã KH");
        tableModel.addColumn("Tên KH");
        tableModel.addColumn("Tuổi");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Thẻ thành viên");
        tableModel.addColumn("Mã thẻ thành viên");
        tableModel.addColumn("Loại thành viên");
        tableModel.addColumn("Điểm tích luỹ");
        tableModel.addColumn("Ngày đăng ký");

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Chỉ cho phép chọn một dòng tại một thời điểm
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table.setShowHorizontalLines(true);
        table.setShowGrid(true);
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Helvetica", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(00, 153, 255, 30));
        table.setSelectionForeground(new Color(00, 153, 255));
        table.setRowHeight(30);
        JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(00, 153, 255));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("Helvetica", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(new Color(00, 153, 255), 1, true));
        scrollPane.setBackground(new Color(00, 153, 255));
        scrollPane.getHorizontalScrollBar();

        docDuLieuVaoTable();

        add(scrollPane, BorderLayout.CENTER);

     // Tạo ListSelectionListener cho bảng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Đảm bảo chỉ xử lý sự kiện sau khi lựa chọn hoàn thành
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { // Nếu có dòng được chọn
                        // Lấy thông tin từ dòng được chọn và hiển thị lên các textfield tương ứng
                        txtMa.setText(tableModel.getValueAt(selectedRow, 0).toString());
                        txtTen.setText(tableModel.getValueAt(selectedRow, 1).toString());
                        txtTuoi.setText(tableModel.getValueAt(selectedRow, 2).toString());
                        txtDT.setText(tableModel.getValueAt(selectedRow, 3).toString());
                        ckcTTV.setSelected(tableModel.getValueAt(selectedRow, 4).toString().equals("Có"));
                        maTTV = tableModel.getValueAt(selectedRow, 5).toString();
                        loaiTTV = tableModel.getValueAt(selectedRow, 6).toString();
                        diemTL = Double.parseDouble(tableModel.getValueAt(selectedRow, 7).toString());
                        ngayDk = (tableModel.getValueAt(selectedRow, 8).toString());
                    }
                }
            }
        });
        
        capNhatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                String maKH = txtMa.getText().trim();
//                String tenKH = txtTen.getText().trim();
//                int tuoi = Integer.parseInt(txtTuoi.getText().trim());
//                String soDienThoai = txtDT.getText().trim();
//                boolean coTheThanhVien = ckcTTV.isSelected();

                suaKhachHang();
                
            }
        });

        xoaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaKhachHang();
            }
        });

        // Thêm ListSelectionListener cho bảng để hiển thị thông tin khi người dùng chọn một dòng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin từ dòng được chọn và hiển thị lên các ô TextField
                    txtMa.setText(table.getValueAt(selectedRow, 0).toString());
                    ckcTTV.setSelected(table.getValueAt(selectedRow, 4).toString().equals("Có"));
                    txtTen.setText(table.getValueAt(selectedRow, 1).toString());
                    txtTuoi.setText(table.getValueAt(selectedRow, 2).toString());
                    txtDT.setText(table.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        // Thêm ActionListener cho ô tìm kiếm
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String name = searchTxt.getText().trim();
            	xoaHetDuLieuTrenModal();
            	timKiemKhachHang();
            	
//            	ArrayList<KhachHang> ds = KH_DAO.timKiemKhachHangTheoTen(name);
//            	
//            	for (KhachHang kh : ds) {
//                    tableModel.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getTuoi(), kh.getSoDienThoai(), kh.hasTheThanhVien() ? "Có" : "Không"});
//                }
            }
        });
        
        lamMoiBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Xóa nội dung trong các ô TextField sau khi sửa
	            txtMa.setText("");
	            txtTen.setText("");
	            txtTuoi.setText("");
	            txtDT.setText("");
	            ckcTTV.setSelected(false);
	            
	            xoaHetDuLieuTrenModal();
	            docDuLieuVaoTable();
				
			}
		});
    }

    // Phương thức sửa thông tin khách hàng
    private void suaKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
        	
          String maKH = txtMa.getText().trim();
          String tenKH = txtTen.getText().trim();
          String tuoi = txtTuoi.getText().trim();
          String soDienThoai = txtDT.getText().trim();
          boolean coTheThanhVien = ckcTTV.isSelected();

            // Kiểm tra tính hợp lệ của dữ liệu nhập vào
            if (tenKH.isEmpty() || tuoi.isEmpty() || soDienThoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int tuoiInt = Integer.parseInt(tuoi);
                // Kiểm tra tuổi phải là số nguyên dương
                if (tuoiInt <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tuổi phải là một số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra số điện thoại phải là số và có đúng 10 ký tự
            if (!soDienThoai.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! (Phải là số và có đúng 10 ký tự)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Nếu dữ liệu hợp lệ, cập nhật thông tin của khách hàng
//            boolean coTheThanhVien = ckcTTV.isSelected();
            tableModel.setValueAt(coTheThanhVien ? "Có" : "Không", selectedRow, 4);
            tableModel.setValueAt(tenKH, selectedRow, 1);
            tableModel.setValueAt(tuoi, selectedRow, 2);
            tableModel.setValueAt(soDienThoai, selectedRow, 3);
            tableModel.setValueAt(maTTV, selectedRow, 5);
            tableModel.setValueAt(loaiTTV, selectedRow, 6);
            tableModel.setValueAt(diemTL, selectedRow, 7);
            tableModel.setValueAt(ngayDk, selectedRow, 8);

            // Xóa nội dung trong các ô TextField sau khi sửa
            txtMa.setText("");
            txtTen.setText("");
            txtTuoi.setText("");
            txtDT.setText("");
            ckcTTV.setSelected(false);
            
            KhachHang kh = new KhachHang(maKH, tenKH, soDienThoai, Integer.parseInt(tuoi), coTheThanhVien);
            
            if(KH_DAO.update(kh)) {
            	JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            } else {
            	JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Phương thức xóa khách hàng khỏi bảng
    private void xoaKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        }
    }

    // Phương thức tìm kiếm khách hàng theo tên
    private void timKiemKhachHang() {
        String tenKHCanTim = searchTxt.getText().trim();
        if (!tenKHCanTim.isEmpty()) {
            // Xóa dữ liệu hiện có trên bảng
            tableModel.setRowCount(0);

            // Lấy danh sách khách hàng từ cơ sở dữ liệu
            ArrayList<KhachHang> list = KH_DAO.timKiemKhachHangTheoTen(tenKHCanTim);
            if(list.isEmpty()) {
            	list = KH_DAO.timKiemKhachHangTheoSDT(tenKHCanTim);
            }
            ArrayList<TheThanhVien> listTTV = TTV_DAO.getAllTheThanhVien();

            // Hiển thị danh sách khách hàng tìm được lên bảng
            for (KhachHang kh : list) {
            	boolean check = true;
            	for(TheThanhVien ttv : listTTV) {
            		if(kh.getMaKhachHang().equalsIgnoreCase(ttv.getKhachHang().getMaKhachHang())) {
            			tableModel.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getTuoi(), kh.getSoDienThoai(), kh.hasTheThanhVien() ? "Có" : "Không", ttv.getMaTheThanhVien(), ttv.getLoai(), ttv.getDiemTichLuy(), ttv.getNgayDangKy()});
            			check = false;
            		} 
            	}
        		if(check) {
        			tableModel.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getTuoi(), kh.getSoDienThoai(), kh.hasTheThanhVien() ? "Có" : "Không", "", "", "", ""});       
        		}
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng cần tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void docDuLieuVaoTable() {
        ArrayList<KhachHang> list = KH_DAO.getAllKhachHang();
        ArrayList<TheThanhVien> listTTV = TTV_DAO.getAllTheThanhVien();

        for (KhachHang kh : list) {
        	boolean check = true;
        	for(TheThanhVien ttv : listTTV) {
        		if(kh.getMaKhachHang().equalsIgnoreCase(ttv.getKhachHang().getMaKhachHang())) {
        			tableModel.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getTuoi(), kh.getSoDienThoai(), kh.hasTheThanhVien() ? "Có" : "Không", ttv.getMaTheThanhVien(), ttv.getLoai(), ttv.getDiemTichLuy(), ttv.getNgayDangKy()});
        			check = false;
        		} 
        	}
    		if(check) {
    			tableModel.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getTuoi(), kh.getSoDienThoai(), kh.hasTheThanhVien() ? "Có" : "Không", "", "", "", ""});       
    		}
        }
    }
    
    private void xoaHetDuLieuTrenModal() {
    	DefaultTableModel dm = (DefaultTableModel) table.getModel();
    	dm.setRowCount(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản Lý Bán Hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new QuanLyKhachHang());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
