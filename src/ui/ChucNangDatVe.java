package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChucNangDatVe extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton nextButton;
    private JButton prevButton;
    private JPanel panelButton;
	private GiaoDienChonPhim giaoDienChonPhim;
	private GiaoDienChonThoiGian giaoDienChonThoiGian;
	private boolean flag1 = false;
	private boolean flag2 = false;

    public ChucNangDatVe() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Tạo các bước trong quá trình đặt vé và thêm chúng vào CardLayout
        GiaoDienChonPhim step1Panel = new GiaoDienChonPhim();
        GiaoDienChonThoiGian step2Panel = new GiaoDienChonThoiGian();
        GiaoDienChonGhe step3Panel = new GiaoDienChonGhe();
        GiaoDienThanhToan step4Panel = new GiaoDienThanhToan();
        GiaoDienThanhToan2 step5Panel = new GiaoDienThanhToan2();

        cardPanel.add(step1Panel, "step1");
        cardPanel.add(step2Panel, "step2");
        cardPanel.add(step3Panel, "step3");
        cardPanel.add(step4Panel, "step4");
        cardPanel.add(step5Panel, "step5");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        nextButton = new JButton("Tiếp tục");
        prevButton = new JButton("Trở về");
        
        nextButton.setBackground(new Color(108, 126, 225));
        prevButton.setBackground(new Color(108, 126, 225));
        
        nextButton.setPreferredSize(new Dimension(200, 40)); // Thay width và height bằng kích thước mong muốn
        prevButton.setPreferredSize(new Dimension(200, 40)); // Thay width và height bằng kích thước mong muốn
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(this);
        prevButton.addActionListener(this);

        panelButton = new JPanel();
        panelButton.add(prevButton);
        panelButton.add(nextButton);
        add(panelButton, BorderLayout.SOUTH);
    }
    public void nextCard() {
        cardLayout.next(cardPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (e.getSource() == nextButton) {
        	int i = getCurrentIndex();
        	System.out.println(i);
            if (i != 3) {
            	if(giaoDienChonPhim.tenPhimVar!= null  && giaoDienChonPhim.maPhimVar!= null && giaoDienChonPhim.posterPathVar !=null ) {           	      
            		String tenPhim = giaoDienChonPhim.tenPhimVar;    
            		String duongDanHinh = giaoDienChonPhim.posterPathVar;
            		String maPhim = giaoDienChonPhim.maPhimVar.trim();
            		giaoDienChonThoiGian.getNgayChieu(maPhim);
            		GiaoDienChonThoiGian.layThongTinPhim("PHIM: "+tenPhim);
            		GiaoDienChonThoiGian.capNhatHinhAnh(duongDanHinh);                  
            		cardLayout.next(cardPanel);
            		
            		
            		
            		
            	}
            	else {
            		JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN PHIM!!!");
            		
            	}      
            	
            } else {
            	if (giaoDienChonPhim.tenPhimVar!= null  && giaoDienChonPhim.maPhimVar!= null && giaoDienChonPhim.posterPathVar !=null ) {
            		String tenPhim = giaoDienChonPhim.tenPhimVar; 
            		String[] newData = {"1",tenPhim, String.valueOf(giaoDienChonPhim.giaTienVar), String.valueOf("1"), String.valueOf(giaoDienChonPhim.giaTienVar)};
        			GiaoDienThanhToan2.capNhatThongTinDongDauTienTrongTable(newData);
        			cardLayout.next(cardPanel);
        		}
            }
            if(giaoDienChonThoiGian.thoiGian != null && giaoDienChonThoiGian.suatChieu != null && giaoDienChonThoiGian.soPhong!=null) {
            	String ten = giaoDienChonPhim.tenPhimVar;
            	String rap = "Galaxy";
            	String soPhong = giaoDienChonThoiGian.soPhong;
            	String xuatChieu = giaoDienChonThoiGian.suatChieu;
            	String ghe = "H100";            	
            	double thoiLuong = GiaoDienChonPhim.thoiLuongVar;
            	String thoiLuong1 = String.valueOf(thoiLuong)+" Phút"; 
            	String theLoai = giaoDienChonThoiGian.thoiGian;
            	GiaoDienThanhToan.setThongTinPhim(ten, rap, soPhong, xuatChieu, ghe, thoiLuong1, theLoai);
            	String duongDanHinh = giaoDienChonPhim.posterPathVar; 
            	GiaoDienThanhToan.capNhatHinhAnhPhim(duongDanHinh);   
            	
	
            	
            }          
            else {
            	JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN THỜI GIAN");
            	return;
            	
            } 
            
           
            
        }
        
//        if (o.equals(nextButton)) {
//            int currentIndex = getCurrentIndex();
//            if (currentIndex < cardPanel.getComponentCount() - 1) { // Kiểm tra xem có phải là giao diện cuối cùng không
//                cardLayout.next(cardPanel);
//            }
//        }
        if (o.equals(prevButton)) {
            int currentIndex = getCurrentIndex();
            if (currentIndex > 0) { // Kiểm tra xem có phải là giao diện đầu tiên không
                cardLayout.previous(cardPanel);
            }
        }
    }

    // Phương thức này để lấy chỉ mục của giao diện hiện tại trong cardPanel
    private int getCurrentIndex() {
        for (int i = 0; i < cardPanel.getComponentCount(); i++) {
            Component component = cardPanel.getComponent(i);
            if (component.isVisible()) {
                return i;
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy chỉ mục nào
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ChucNangDatVe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ChucNangDatVe());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
