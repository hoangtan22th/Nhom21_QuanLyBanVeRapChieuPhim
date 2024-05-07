package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Ghe extends JFrame {
	private JPanel p;
	private JButton datVe_button;
	Font fontnews;

	public Ghe() {
		setTitle("Chọn ghế");
		setSize(1000, 700);
//		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		p = new JPanel();
		p.setBorder(new EmptyBorder(10,10,10,10));
		p.setLayout(new BorderLayout());
		
		HangGhe();
		DatVe();
		add(p);
	}

	public static void main(String[] args) {
		new Ghe().setVisible(true);

	}
	
	public void HangGhe() {
		int kichThuocKhungNgang = 920;
		int kichThuocKhungDoc = 600;
		
	    JPanel center = new JPanel();
	    center.setPreferredSize(new Dimension(kichThuocKhungNgang,kichThuocKhungDoc));
	    center.setLayout(new GridLayout(14, 1));
	    int gapSize = 10;
	    center.setBorder(new EmptyBorder(gapSize, gapSize, gapSize, gapSize));
	    JLabel textManHinh = new JLabel("Màn Hình");
	    Font customFont = new Font(textManHinh.getFont().getName(), Font.BOLD, 20);
	    textManHinh.setFont(customFont);
	    textManHinh.setHorizontalAlignment(JLabel.CENTER);
	    center.add(textManHinh);
	    
	    for (char row = 'A'; row <= 'I'; row++) {
	        JPanel rowPanel = new JPanel(new GridLayout(1, 10));
	        for (int i = 1; i <= 16; i++) {
	            JButton button = new JButton(row + "" + i);
	            button.setForeground(Color.WHITE);
	            button.setBackground(new Color(192,192,192));  
	            if(row >= 'E' && row <= 'J') {
	            	button.setBackground(new Color(213, 44, 99));
	            }
	            rowPanel.add(button);
	        }            
	        center.add(rowPanel);
	    }
	    JPanel totalPanel = new JPanel(new GridLayout(1, 6));
	    for (int i = 1; i <= 8; i++) {
	        JButton button = new JButton("K" + i);
	        button.setForeground(Color.WHITE);
	        button.setBackground(new Color(237, 90, 179));
	        totalPanel.add(button);
	    }
	    center.add(totalPanel);
	 
	    center.add(Box.createRigidArea(new Dimension(5, 0)));
	    
	    JPanel dangChon = new JPanel();
		    JButton dangChon_button = new JButton();
		    dangChon_button.setEnabled(false);
		    dangChon_button.setPreferredSize(new Dimension(25,25));
		    dangChon_button.setBackground(Color.red);
		    dangChon_button.setBorderPainted(false);
		    JLabel dangChon_lable = new JLabel("Đang chọn");
		    
		    JButton daDat_button = new JButton();
		    daDat_button.setEnabled(false);
		    daDat_button.setPreferredSize(new Dimension(25,25));
		    daDat_button.setBackground(Color.yellow);
		    daDat_button.setBorderPainted(false);
		    JLabel daDat_lable = new JLabel("Đã đặt");
		    
		    JButton thuong_button = new JButton();
		    thuong_button.setEnabled(false);
		    thuong_button.setPreferredSize(new Dimension(25,25));
		    thuong_button.setBackground(new Color(192,192,192));
		    thuong_button.setBorderPainted(false);
		    JLabel thuong_lable = new JLabel("Thường");
		    
		dangChon.add(dangChon_button);
		dangChon.add(dangChon_lable);
		dangChon.add(daDat_button);
		dangChon.add(daDat_lable);
		dangChon.add(thuong_button);
		dangChon.add(thuong_lable);

	    center.add(dangChon);
	    
	    JPanel dangChon1 = new JPanel();
		    JButton gheDoi_button = new JButton();
		    gheDoi_button.setEnabled(false);
		    gheDoi_button.setPreferredSize(new Dimension(25,25));
		    gheDoi_button.setBackground(new Color(237, 90, 179));
		    gheDoi_button.setBorderPainted(false);
		    JLabel gheDoi_lable = new JLabel("Ghế đôi");
		    
		    JButton vIP_button = new JButton();
		    vIP_button.setEnabled(false);
		    vIP_button.setPreferredSize(new Dimension(25,25));
		    vIP_button.setBackground(new Color(213, 44, 99));
		    vIP_button.setBorderPainted(false);
		    JLabel vIP_lable = new JLabel("VIP");
		    
		dangChon1.add(gheDoi_button);
		dangChon1.add(gheDoi_lable);
		dangChon1.add(vIP_button);
		dangChon1.add(vIP_lable);

	    center.add(dangChon1);
	    p.add(center, BorderLayout.CENTER);
	}


public void DatVe() {
	 JPanel south = new JPanel(new BorderLayout());
	 south.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 10));
	 	
	 	Box x = Box.createVerticalBox();
       Label info_wrapper1 = new Label("Lật Mặt 6");
       info_wrapper1.setFont(new Font("Arial", Font.BOLD, 23));
       Label info_wrapper2 = new Label("2D Phụ Đề Anh");
       
	 	Box y = Box.createHorizontalBox();
       Label total = new Label("Tổng Tiền:");
       total.setFont(new Font("Arial", Font.BOLD, 23));
       Label order_Total = new Label("000.000đ");
       order_Total.setFont(new Font("Arial", Font.BOLD, 23));

       JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
       

       x.add(info_wrapper1);
       x.add(info_wrapper2);
       y.add(total);
       y.add(order_Total);
       x.add(y);
       labelsPanel.add(x);
       south.add(labelsPanel, BorderLayout.WEST);
       
       JPanel datVe_panel = new JPanel();
       datVe_panel.setLayout(new BorderLayout());
       datVe_button = new JButton("Đặt Vé");
       datVe_button.setBackground(Color.red);
       datVe_button.setForeground(Color.white);
       datVe_button.setFont(new Font("Arial", Font.BOLD, 18));
       datVe_button.setPreferredSize(new Dimension(100,40));
       datVe_button.setBorderPainted(false);
       datVe_panel.add(datVe_button, BorderLayout.SOUTH);
       south.add(datVe_panel, BorderLayout.EAST);
       
       JPanel center = new JPanel();
       center.setBorder(new EmptyBorder(0,30,0,0));
       center.setLayout(new BorderLayout());
       JLabel block_seats = new JLabel("Thường A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13");
       block_seats.setFont(new Font("Arial", Font.BOLD, 21));

       center.add(block_seats, BorderLayout.NORTH);
       south.add(center);
       
       p.add(south, BorderLayout.SOUTH);
}

}
