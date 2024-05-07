package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.TheThanhVien;

public class TheThanhVien_DAO {
	public ArrayList<TheThanhVien> getAllTheThanhVien() {
		ArrayList<TheThanhVien> dsTTV = new ArrayList<TheThanhVien>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from TheThanhVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maTTV = rs.getString("maTTV");
				Date ngayDK = rs.getDate("ngayDangKy");
				String loai = rs.getString("loai");
				Double diemTL = rs.getDouble("diemTichLuy");
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				TheThanhVien ttv = new TheThanhVien(maTTV, loai, ngayDK, diemTL, kh);
				dsTTV.add(ttv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTTV;
	}
	public boolean taoTheThanhVienVsKhachHangCuoiCung() {
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Tạo câu lệnh SQL để chèn dữ liệu vào bảng TheThanhVien
	        String sqlInsertTTV = "INSERT INTO TheThanhVien (maTTV, ngayDangKy, loai, diemTichLuy, maKhachHang) VALUES (?, ?, ?, ?, ?)";
	        
	        // Tạo mã thẻ thành viên tự động
	        String maTTV = generateMaTTV();
	        
	        // Lấy mã khách hàng cuối cùng từ cơ sở dữ liệu
	        String maKhachHang = getLastMaKhachHang();
	        
	        // Ngày đăng ký là ngày hiện tại
	        Date ngayDangKy = new Date();
	        
	        // Loại thẻ là "Standard"
	        String loai = "Standard";
	        
	        // Điểm tích luỹ ban đầu là 0
	        double diemTichLuy = 0;
	        
	        // Tạo PreparedStatement để thực thi câu lệnh SQL và tránh lỗi SQL Injection
	        PreparedStatement preparedStatement = con.prepareStatement(sqlInsertTTV);
	        
	        // Thiết lập các tham số cho câu lệnh SQL
	        preparedStatement.setString(1, maTTV);
	        preparedStatement.setDate(2, new java.sql.Date(ngayDangKy.getTime()));
	        preparedStatement.setString(3, loai);
	        preparedStatement.setDouble(4, diemTichLuy);
	        preparedStatement.setString(5, maKhachHang);
	        
	        // Thực thi câu lệnh SQL
	        int rowsAffected = preparedStatement.executeUpdate();
	        
	        // Nếu có ít nhất một dòng bị ảnh hưởng (chèn thành công), trả về true
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    // Nếu không thành công hoặc có lỗi, trả về false
	    return false;
	}

	// Phương thức sinh mã thẻ thành viên mới
	private String generateMaTTV() {
	    String newMaTTV = "TTV001"; // Mã thẻ thành viên mặc định nếu không có thẻ nào trong cơ sở dữ liệu
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Tạo câu lệnh SQL để lấy mã thẻ thành viên lớn nhất trong cơ sở dữ liệu
	        String sqlSelectMaxMaTTV = "SELECT MAX(maTTV) FROM TheThanhVien";
	        PreparedStatement psSelectMaxMaTTV = con.prepareStatement(sqlSelectMaxMaTTV);
	        ResultSet rsMaxMaTTV = psSelectMaxMaTTV.executeQuery();
	        
	        if (rsMaxMaTTV.next()) {
	            String maxMaTTV = rsMaxMaTTV.getString(1);
	            // Tách số từ mã thẻ thành viên hiện tại và tăng giá trị lên 1
	            int number = Integer.parseInt(maxMaTTV.trim().substring(3)) + 1;
	            // Format lại chuỗi số với độ dài 3 ký tự và thêm vào "TTV"
	            newMaTTV = String.format("TTV%03d", number);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return newMaTTV;
	}


	// Phương thức để lấy mã khách hàng cuối cùng từ cơ sở dữ liệu
	private String getLastMaKhachHang() {
	    String maKhachHang = "";
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Tạo câu lệnh SQL để lấy mã khách hàng cuối cùng từ cơ sở dữ liệu
	        String sqlSelectLastMaKH = "SELECT TOP 1 maKhachHang FROM KhachHang ORDER BY maKhachHang DESC";
	        Statement statement = con.createStatement();
	        ResultSet rsLastMaKH = statement.executeQuery(sqlSelectLastMaKH);
	        
	        if (rsLastMaKH.next()) {
	            maKhachHang = rsLastMaKH.getString("maKhachHang");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maKhachHang;
	}
	public void insertTheThanhVien(TheThanhVien ttv) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
          
            con = ConnectDB.getIntance().getConnection();
            
        
            String sql = "INSERT INTO TheThanhVien (maTTV, ngayDangKy, loai, diemTichLuy, maKhachHang) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            
         
            pstmt.setString(1, ttv.getMaTheThanhVien());
            pstmt.setDate(2, new java.sql.Date(ttv.getNgayDangKy().getTime()));
            pstmt.setString(3, ttv.getLoai());
            pstmt.setDouble(4, ttv.getDiemTichLuy());
            pstmt.setString(5, ttv.getKhachHang().getMaKhachHang());
            
           
            pstmt.executeUpdate();
            
            System.out.println("Thêm thẻ thành viên thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm thẻ thành viên: " + e.getMessage());
        } finally { 
         
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	 public TheThanhVien layTheThanhVienTheoMaKhachHang(String maKhachHang) {
	        TheThanhVien theThanhVien = null;
	        try {
	            ConnectDB.getIntance();
	            Connection con = ConnectDB.getConnection();
	            String sql = "SELECT maTTV, ngayDangky, loai, diemTichLuy "
	                       + "FROM TheThanhVien "
	                       + "WHERE maKhachHang = ?";
	            PreparedStatement statement = con.prepareStatement(sql);
	            statement.setString(1, maKhachHang);
	            ResultSet rs = statement.executeQuery();
	            if (rs.next()) {
	                String maTheThanhVien = rs.getString("maTTV");
	                String loai = rs.getString("loai");
	                Date ngayDangKy = rs.getDate("ngayDangky");
	                double diemTichLuy = rs.getDouble("diemTichLuy");	                
	                KhachHang khachHang = new KhachHang(maKhachHang);
	                theThanhVien = new TheThanhVien(maTheThanhVien, loai, ngayDangKy, diemTichLuy, khachHang);
	            }
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return theThanhVien;
	    }
	 public void capNhatDiemTichLuy(String maKhachHang, double diemMoi) {
	        try {
	            ConnectDB.getIntance().connect(); // Mở kết nối đến cơ sở dữ liệu
	            Connection con = ConnectDB.getConnection(); // Lấy kết nối từ ConnectDB
	            String sql = "UPDATE TheThanhVien SET diemTichLuy = ? WHERE maKhachHang = ?";
	            PreparedStatement statement = con.prepareStatement(sql);
	            statement.setDouble(1, diemMoi);
	            statement.setString(2, maKhachHang);
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Cập nhật số điểm tích lũy thành công.");
	            } else {
	                System.out.println("Không có dòng nào được cập nhật.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	    }



}
