package ui;

import javax.swing.*;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class DangNhap extends JFrame implements ActionListener {

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel imageLabel;
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblPass;
    private JTextField txtPass;
    private JButton loginBtn;
    private JPanel pnLogin;
	private JLabel lblTiTle;
	private JPanel pnTitle;
	private Font Font;
	private JPanel pnName;
	private JPanel pnPass;
	private JPanel pnBtn;
	private JPanel pnName1;
	private JPanel pnName2;
	private JPanel pnPass1;
	private JPanel pnPass2;
	private TaiKhoan_DAO TK_DAO;
	private NhanVien_DAO NV_DAO;
	public static String maNhanVien;
	public static String tenNhanVien;

    public DangNhap() {
        setTitle("Đăng nhập");
        setSize(600, 340);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
//        connectDB
        try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        TK_DAO = new TaiKhoan_DAO();

        leftPanel = new JPanel();
        rightPanel = new JPanel();
        JPanel phim1 = new JPanel();
//        ImageIcon img7 = new ImageIcon("img/logo4.jpg");
        ImageIcon img7 = new ImageIcon("img/login1.jpg");
        Image scaled7 = scaleImage(img7.getImage(), 320, 295); //chiều ngang, chiều cao
		ImageIcon imgScaled7 = new ImageIcon(scaled7);
		JLabel hinh7 = new JLabel(imgScaled7);
        leftPanel.add(hinh7);
        leftPanel.setBackground(Color.white);
		
//		infor
//        title
        pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        lblTiTle = new JLabel("ĐĂNG NHẬP");
        Font = new Font("Arial", Font.BOLD, 20);
        lblTiTle.setFont(Font);
        Color orangeDark = Color.orange; // Tạo màu cam tùy chỉnh
        lblTiTle.setForeground(orangeDark);
        pnTitle.add(lblTiTle);
        
//        ten dang nhap
        Box BoxInfor = Box.createVerticalBox();
        lblName = new JLabel("Tên đăng nhập:");
        txtName = new JTextField(20); //tùy chỉnh chiều rộng
        txtName.setPreferredSize(new Dimension(25, 25)); 
        pnName1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));
        pnName1.add(lblName);
        pnName2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));
        pnName2.add(txtName);
        
//        matkhau
        lblPass = new JLabel("Mật khẩu:");
        txtPass = new JPasswordField(20); //tùy chỉnh chiều rộng
        txtPass.setPreferredSize(new Dimension(25, 25)); 
        pnPass1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));
        pnPass1.add(lblPass);
        pnPass2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));
        pnPass2.add(txtPass);
//        button
        loginBtn = new JButton("Đăng nhập");
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(orangeDark);
        loginBtn.setPreferredSize(new Dimension(200, 34));
        pnBtn = new JPanel(new FlowLayout(FlowLayout.LEFT,0,20));
        pnBtn.add(loginBtn);
        
        BoxInfor.add(pnName1);
        BoxInfor.add(pnName2);
        BoxInfor.add(pnPass1);
        BoxInfor.add(pnPass2);
   
        rightPanel.add(pnTitle, BorderLayout.SOUTH);
        rightPanel.add(BoxInfor, BorderLayout.CENTER);
        rightPanel.add(pnBtn, BorderLayout.NORTH);

       
        add(leftPanel,BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
      
        
        loginBtn.addActionListener(this);
    }

    private Image scaleImage(Image image, int w, int h) {
    	Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
	}

	public static void main(String[] args) {
        new DangNhap().setVisible(true);
    }

    /**
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String username = txtName.getText().trim();
		String password = txtPass.getText().trim();
		
		ArrayList<TaiKhoan> dsTK = TK_DAO.getAllTaiKhoan();
		
		boolean flag = false;
		
		for(TaiKhoan tk : dsTK) {
			if(username.equals(tk.getTaiKhoan().trim()) && password.equals(tk.getMatKhau().trim())) {
				JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
	// HoangTan fix			
				maNhanVien = tk.getTaiKhoan();
				NV_DAO = new NhanVien_DAO();
				tenNhanVien = NV_DAO.getNVTheoMa(maNhanVien).getTenNhanVien();
				setVisible(false);
				new GiaoDienChinh().setVisible(true);
				flag = true;
				
			} 
		}
			if(!flag) {
				if (username.isEmpty() || password.isEmpty()){
					JOptionPane.showMessageDialog(null, "Không được để trống!");
				} else
					JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu!");
					txtName.setText("");
					txtPass.setText("");
					txtName.requestFocus();
			}
		
		
	}
}