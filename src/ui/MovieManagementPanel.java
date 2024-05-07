package ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovieManagementPanel extends JPanel {

	public MovieManagementPanel() {
		setLayout(new BorderLayout());
        add(new JLabel("Giao diện quản lý phim"), BorderLayout.CENTER);
		
	}
}
