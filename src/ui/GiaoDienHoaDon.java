package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class GiaoDienHoaDon extends JFrame{
	private JLabel lblTitle;
	private JLabel lblDiachi;
	private JLabel lblTitle2;
	private JLabel lblMaHD;
	private JLabel lblThuNgan;
	private JLabel lblKH;
	private JTextField txtMaHD;
	private JTextField txtKH;
	private JPanel pnTitle;
	private JPanel pnTitle2;
	private JPanel pnMain;
	private JPanel pnDiaChi;
	private JPanel pnMaHD;
	private JLabel lblNgayLap;
	private JTextField txtNgayLap;
	private JPanel pnNgayLap;
	private JPanel pnThuNgan;
	private JPanel pnKH;
	private JTextField txtThuNgan;
	private JLabel lblThueGTGT;
	private JLabel lblTiensanpham;
	private JLabel lblTongThanhToan;
	private JLabel lblDiemDoi;
	private JTextField txtTiensanpham;
	private JTextField txtThueGTGT;
	private JTextField txtDiemDoi;
	private JTextField txtTongThanhToan;
	private JPanel pnTiensanpham;
	private JPanel pnThueGTGT;
	private JPanel pnDiemDoi;
	private JPanel pnTongThanhToan;
	private JLabel lblCach;
	private JLabel lblCamOn;
	private JPanel pnCach;
	private JPanel pnCamon;

	public GiaoDienHoaDon() {
		setSize(550, 640);
		setLocationRelativeTo(null);
		setResizable(false);
		
		lblTitle = new JLabel("Cinema ABC");
		lblDiachi = new JLabel("");
		lblTitle2 = new JLabel("HÓA ĐƠN");
		lblMaHD = new JLabel("Mã hóa đơn:");
		lblNgayLap = new JLabel("Ngày lập:");
		lblThuNgan = new JLabel("Thu ngân:");
		lblKH = new JLabel("Khách hàng - ĐT:");
		txtMaHD = new JTextField("HD123",20);
		txtNgayLap = new JTextField("04/05/2024");
		txtThuNgan = new JTextField("Nhân viên ẩn danh");
		txtKH =  new JTextField("Nguyen Van C");
		
//		set font 
		Font font = new Font(lblTitle.getFont().getName(), Font.PLAIN, 16);
		lblTitle.setFont(font);
		Font font3 = new Font(lblTitle2.getFont().getName(), Font.BOLD, 28);
		lblTitle2.setFont(font3);
		
		
//		set kh thể edit
		txtMaHD.setBorder(null); txtMaHD.setOpaque(false);txtMaHD.setEditable(false);txtMaHD.setFocusable(false);
		txtNgayLap.setBorder(null); txtNgayLap.setOpaque(false);txtNgayLap.setEditable(false);txtNgayLap.setFocusable(false);
		txtThuNgan.setBorder(null); txtThuNgan.setOpaque(false);txtThuNgan.setEditable(false);txtThuNgan.setFocusable(false);
		txtKH.setBorder(null); txtKH.setOpaque(false);txtKH.setEditable(false);txtKH.setFocusable(false);
		 
		pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,5)); 
		pnDiaChi = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,5));
		pnTitle2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		pnMaHD = new JPanel(new FlowLayout(FlowLayout.LEFT, 10 , 10));
		pnNgayLap = new JPanel(new FlowLayout(FlowLayout.LEFT, 10 , 10));
		pnThuNgan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10 , 10));
		pnKH = new JPanel(new FlowLayout(FlowLayout.LEFT, 10 , 10));
		pnTitle.add(lblTitle);
		pnDiaChi.add(lblDiachi); 
		pnTitle2.add(lblTitle2);
		pnMaHD.add(lblMaHD); pnMaHD.add(txtMaHD);
		pnNgayLap.add(lblNgayLap); pnNgayLap.add(txtNgayLap);
		pnThuNgan.add(lblThuNgan); pnThuNgan.add(txtThuNgan);
		pnKH.add(lblKH); pnKH.add(txtKH);
		
//		 bảng
        String[] columnNames = {"Sản phẩm","Đơn giá", "SL", "Thành tiền"};
        Object[][] data = {
                { "Phim Quật mộ trùng ma", 80000 , 1 , 80000},
                {"Bắp + Pepsi vị chanh vừa", 80000,1, 80000}
        	};
        
      //BẢNG
        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(430, 150));
        table.setGridColor(Color.WHITE); // Set màu của đường biên
        table.setBorder(BorderFactory.createEmptyBorder()); // Đặt đường biên trống
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font chữ
        table.setRowHeight(40); // Set chiều cao của mỗi dòng
        JScrollPane scrollPane = new JScrollPane(table);
		
        // Thiết lập chiều rộng cho từng cột
        table.getColumnModel().getColumn(1).setPreferredWidth(40); // Đơn giá
        table.getColumnModel().getColumn(2).setPreferredWidth(10); // Số lượng
        table.getColumnModel().getColumn(3).setPreferredWidth(30); // Thành tiền

        // Set renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa dữ liệu
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Căn giữa cột
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Căn giữa cột 

        // Set renderer cho tiêu đề cột
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD)); // In đậm tiêu đề
//		HẾT BẢNG
        
//        thành tiền
        lblTiensanpham = new JLabel("Tổng tiền sản phẩm:");
        lblThueGTGT = new JLabel("Thuế GTGT 10%: ");
        lblDiemDoi = new JLabel("Điểm:");
        lblTongThanhToan = new JLabel("Tổng thanh toán:");
        txtTiensanpham = new JTextField("160.000");
        txtThueGTGT = new JTextField("16.000");
        txtDiemDoi = new JTextField("400");
        txtTongThanhToan = new JTextField("143,600");
        
        txtTiensanpham.setBorder(null); txtTiensanpham.setOpaque(false);txtTiensanpham.setEditable(false);txtTiensanpham.setFocusable(false);
        txtThueGTGT.setBorder(null); txtThueGTGT.setOpaque(false);txtThueGTGT.setEditable(false);txtThueGTGT.setFocusable(false);
        txtDiemDoi.setBorder(null); txtDiemDoi.setOpaque(false);txtDiemDoi.setEditable(false);txtDiemDoi.setFocusable(false);
        txtTongThanhToan.setBorder(null); txtTongThanhToan.setOpaque(false);txtTongThanhToan.setEditable(false);txtTongThanhToan.setFocusable(false);
        
        pnTiensanpham = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,5));
        pnTiensanpham.add(lblTiensanpham); pnTiensanpham.add(txtTiensanpham);
        pnThueGTGT = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,5));
        pnThueGTGT.add(lblThueGTGT); pnThueGTGT.add(txtThueGTGT);
        pnDiemDoi = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,5));
        pnDiemDoi.add(lblDiemDoi); pnDiemDoi.add(txtDiemDoi);
        pnTongThanhToan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,5));
        pnTongThanhToan.add(lblTongThanhToan); pnTongThanhToan.add(txtTongThanhToan);
        
//        font
        lblTiensanpham.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font chữ
        lblThueGTGT.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDiemDoi.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTongThanhToan.setFont(new Font("Arial", Font.PLAIN, 14));
        
//        hẹn gặp
        lblCach = new JLabel("************************************");
        lblCamOn = new JLabel("Cảm ơn quý khách hẹn gặp lại!!");
        Font fontItalic = new Font(lblCamOn.getFont().getName(), Font.ITALIC, lblCamOn.getFont().getSize());
        lblCamOn.setFont(fontItalic);
        
        pnCach = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,5));
        pnCamon = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,5));
        pnCach.add(lblCach);
        pnCamon.add(lblCamOn);

		
		Box box = Box.createVerticalBox();
		box.add(pnTitle);
		box.add(lblDiachi);
		box.add(pnTitle2);
		box.add(pnMaHD);
		box.add(pnNgayLap);
		box.add(pnThuNgan);
		box.add(pnKH);
		Box boxThanhTien = Box.createVerticalBox();
		boxThanhTien.add(pnTiensanpham); 
		boxThanhTien.add(pnThueGTGT); 
		boxThanhTien.add(pnDiemDoi); 
		boxThanhTien.add(pnTongThanhToan); 
		boxThanhTien.add(pnCach);
		boxThanhTien.add(pnCamon);
	
		
		
		
		
		pnMain = new JPanel();
		pnMain = new JPanel(new BorderLayout()); // Sử dụng BorderLayout cho pnMain
		pnMain.add(box, BorderLayout.NORTH);
		pnMain.add(scrollPane, BorderLayout.CENTER);
		pnMain.add(boxThanhTien, BorderLayout.SOUTH);
		
//		add
		add(pnMain, BorderLayout.NORTH);
//		add(scrollPane, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new GiaoDienHoaDon().setVisible(true);
	}
}
