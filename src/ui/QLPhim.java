package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.RenderingHints;
import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QLPhim extends JFrame {
	
	private Object height;

	public QLPhim() {
		setTitle("Quản lý phim");
		setSize(1000, 700);
//		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel chonPhim = new JPanel();
		JLabel lblChonPhim = new JLabel("Chọn tên phim:");
		JComboBox cboPhim = new JComboBox();
		cboPhim.setEditable(true);
		cboPhim.setModel(new DefaultComboBoxModel(new String[] {"Lật mặt 7", "Người vợ cuối cùng", "Quật mộ trùng ma", "Cái giá của hạnh phúc",
				"Biệt đội săn ma", "Tu viện máu","Godzilla x Kong" , "Thanh xuân 18x2" }));
		
		JPanel listPhim = new JPanel();
		listPhim.setLayout(new GridLayout(0, 4));
		
		JPanel phim1 = new JPanel();
		JLabel tenPhim1 = new JLabel("Lật mặt 7");
		ImageIcon img1 = new ImageIcon("img/lat-mat-7.jpg");
//		h = w * 1.5
		Image scaled1 = scaleImage(img1.getImage(), 190, 285);
		ImageIcon imgScaled1 = new ImageIcon(scaled1);
		JLabel hinh1 = new JLabel(imgScaled1);
		
		JPanel phim2 = new JPanel();
		JLabel tenPhim2 = new JLabel("Người vợ cuối cùng");
		ImageIcon img2 = new ImageIcon("img/nguoi-vo-cuoi-cung.jpg");
		Image scaled2 = scaleImage(img2.getImage(), 190, 285);
		ImageIcon imgScaled2 = new ImageIcon(scaled2);
		JLabel hinh2 = new JLabel(imgScaled2);
		
		JPanel phim3 = new JPanel();
		JLabel tenPhim3 = new JLabel("Quật mộ trùng ma");
		ImageIcon img3 = new ImageIcon("img/quat-mo-trung-ma.jpg");
		Image scaled3 = scaleImage(img3.getImage(), 190, 285);
		ImageIcon imgScaled3 = new ImageIcon(scaled3);
		JLabel hinh3 = new JLabel(imgScaled3);
		
		JPanel phim4 = new JPanel();
		JLabel tenPhim4 = new JLabel("Cái giá của hạnh phúc");
		ImageIcon img4 = new ImageIcon("img/cai-gia-cua-hanh-phuc.jpg");
		Image scaled4 = scaleImage(img4.getImage(), 190, 285);
		ImageIcon imgScaled4 = new ImageIcon(scaled4);
		JLabel hinh4 = new JLabel(imgScaled4);
		
		JPanel phim5 = new JPanel();
		JLabel tenPhim5 = new JLabel("Biệt đội săn ma");
		ImageIcon img5 = new ImageIcon("img/biet-doi-san-ma.jpg");
		Image scaled5 = scaleImage(img5.getImage(), 190, 285);
		ImageIcon imgScaled5 = new ImageIcon(scaled5);
		JLabel hinh5 = new JLabel(imgScaled5);
		
		JPanel phim6 = new JPanel();
		JLabel tenPhim6 = new JLabel("Tu viện máu");
		ImageIcon img6 = new ImageIcon("img/tu-vien-mau.jpg");
		Image scaled6 = scaleImage(img6.getImage(), 190, 285);
		ImageIcon imgScaled6 = new ImageIcon(scaled6);
		JLabel hinh6 = new JLabel(imgScaled6);
		
		JPanel phim7 = new JPanel();
		JLabel tenPhim7 = new JLabel("Godzilla x Kong");
		ImageIcon img7 = new ImageIcon("img/god-x-kong.jpg");
		Image scaled7 = scaleImage(img7.getImage(), 190, 285);
		ImageIcon imgScaled7 = new ImageIcon(scaled7);
		JLabel hinh7 = new JLabel(imgScaled7);
		
		JPanel phim8 = new JPanel();
		JLabel tenPhim8 = new JLabel("Thanh xuân 18x2");
		ImageIcon img8 = new ImageIcon("img/thanh-xuan-18-x-2.jpg");
		Image scaled8 = scaleImage(img8.getImage(), 190, 285);
		ImageIcon imgScaled8 = new ImageIcon(scaled8);
		JLabel hinh8 = new JLabel(imgScaled8);
		
		JPanel btnPanel = new JPanel();
		Box b = Box.createHorizontalBox();
		JButton backBtn = new JButton("Trở lại");
		backBtn.setForeground(Color.blue);
		backBtn.setBackground(Color.WHITE);
		JButton nextBtn = new JButton("Tiếp tục");
		nextBtn.setForeground(Color.blue);
		nextBtn.setBackground(Color.WHITE);
		b.add(backBtn);
		b.add(Box.createHorizontalStrut(700));
		b.add(nextBtn);
		
		phim1.add(tenPhim1);
		phim1.add(hinh1);
		phim2.add(tenPhim2);
		phim2.add(hinh2);
		phim3.add(tenPhim3);
		phim3.add(hinh3);
		phim4.add(tenPhim4);
		phim4.add(hinh4);
		phim5.add(tenPhim5);
		phim5.add(hinh5);
		phim6.add(tenPhim6);
		phim6.add(hinh6);
		phim7.add(tenPhim7);
		phim7.add(hinh7);
		phim8.add(tenPhim8);
		phim8.add(hinh8);		
		listPhim.add(phim1);
		listPhim.add(phim2);
		listPhim.add(phim3);
		listPhim.add(phim4);
		listPhim.add(phim5);
		listPhim.add(phim6);
		listPhim.add(phim7);
		listPhim.add(phim8);
		
		chonPhim.add(lblChonPhim);
		chonPhim.add(cboPhim);
		btnPanel.add(b);
//		btnPanel.add(nextBtn);
		add(chonPhim, BorderLayout.NORTH);
		add(listPhim, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new QLPhim().setVisible(true);
	}

	private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
		 
    }
}
