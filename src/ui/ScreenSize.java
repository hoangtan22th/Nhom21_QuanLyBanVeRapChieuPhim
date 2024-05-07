package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScreenSize extends JFrame implements ActionListener{
    private JButton test;
	private JFrame frame;

	public ScreenSize() {
		frame = new JFrame("Quản Lý Bán Hàng");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test = new JButton("test");
        ((JFrame) frame).getContentPane().add(test);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        test.addActionListener(this);
	}
	
	public static void main(String[] args) {
        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // In ra chiều rộng và chiều cao của màn hình
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println("Chiều rộng màn hình: " + width + " pixels");
        System.out.println("Chiều cao màn hình: " + height + " pixels");
        
     // Lấy kích thước của JFrame
         new ScreenSize().setVisible(true);

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(test)) {
			Dimension size = frame.getSize();
	        int widthF = size.width;
	        int heightF = size.height;
	        System.out.println("Chiều rộng màn hình: " + widthF + " pixels");
	        System.out.println("Chiều cao màn hình: " + heightF + " pixels");
		}
	}
}
