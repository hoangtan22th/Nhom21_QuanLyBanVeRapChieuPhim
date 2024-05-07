package ui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GioXem extends JFrame {
	public GioXem() {
		setTitle("Chọn giờ xem");
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel btnPanel = new JPanel();
		Box b = Box.createHorizontalBox();
		JButton backBtn = new JButton("Trở lại");
		JButton nextBtn = new JButton("Tiếp tục");
		b.add(backBtn);
		b.add(Box.createHorizontalStrut(700));
		b.add(nextBtn);
		
		btnPanel.add(b);

		add(btnPanel, BorderLayout.SOUTH);
		
	}

	public static void main(String[] args) {
		new GioXem().setVisible(true);

	}

}
