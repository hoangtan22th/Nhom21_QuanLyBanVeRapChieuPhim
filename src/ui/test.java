package ui;

import javax.swing.JFrame;

public class test extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Quản Lý Bán Hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChucNangDatVe());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
