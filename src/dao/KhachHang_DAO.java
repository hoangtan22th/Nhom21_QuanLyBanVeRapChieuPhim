package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	public ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maKH = rs.getString("maKhachHang");
				Integer hasTTV = rs.getInt("hasTheThanhVien");
				String tenKH = rs.getString("tenKhachHang");
				Integer tuoi = rs.getInt("tuoi");
				String soDT = rs.getString("soDienThoai");
				KhachHang kh = new KhachHang(maKH, tenKH, soDT, tuoi, hasTTV == 1 ? true : false);
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}
	
	public boolean update(KhachHang kh) {
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update KhachHang set hasTheThanhVien = ?, tenKhachHang = ?, tuoi = ?, soDienThoai = ? where maKhachHang = ?");
			stmt.setInt(1, kh.hasTheThanhVien() ? 1 : 0);
			stmt.setString(2, kh.getTenKhachHang());
			stmt.setInt(3, kh.getTuoi());
			stmt.setString(4, kh.getSoDienThoai());
			stmt.setString(5, kh.getMaKhachHang());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<KhachHang> timKiemKhachHangTheoTen(String ten) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from KhachHang where tenKhachHang like ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + ten + "%");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String ma = rs.getString("maKhachHang");
				String tenKH = rs.getString("tenKhachHang");
				Integer tuoi = rs.getInt("tuoi");
				String dt = rs.getString("soDienThoai");
				Integer ttv = rs.getInt("hasTheThanhVien");
				KhachHang kh = new KhachHang(ma, tenKH, dt, tuoi, ttv == 1 ? true : false);
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<KhachHang> timKiemKhachHangTheoSDT(String soDT) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from KhachHang where soDienThoai = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, soDT);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String ma = rs.getString("maKhachHang");
				String tenKH = rs.getString("tenKhachHang");
				Integer tuoi = rs.getInt("tuoi");
				String dt = rs.getString("soDienThoai");
				Integer ttv = rs.getInt("hasTheThanhVien");
				KhachHang kh = new KhachHang(ma, tenKH, dt, tuoi, ttv == 1 ? true : false);
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public String timMaKhachHangTheoSDT(String soDienThoai) {
        String maKhachHang = null;
        try {Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT maKhachHang FROM KhachHang WHERE soDienThoai = ?"); 
            ps.setString(1, soDienThoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maKhachHang = rs.getString("maKhachHang");
            } else {
            	 maKhachHang = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maKhachHang;
    }
	public boolean insertKhachHang(String tenKH, String soDT, int tuoi, boolean hasTTV) {
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Tạo câu lệnh SQL để chèn dữ liệu vào bảng KhachHang
	        String sql = "INSERT INTO KhachHang (maKhachHang, tenKhachHang, soDienThoai, tuoi, hasTheThanhVien) VALUES (?, ?, ?, ?, ?)";
	        
	        // Tạo mã khách hàng tự động
	        String maKH = generateMaKhachHang();
	        
	        // Tạo PreparedStatement để thực thi câu lệnh SQL và tránh lỗi SQL Injection
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        
	        // Thiết lập các tham số cho câu lệnh SQL
	        preparedStatement.setString(1, maKH);
	        preparedStatement.setString(2, tenKH);
	        preparedStatement.setString(3, soDT);
	        preparedStatement.setInt(4, tuoi);
	        preparedStatement.setInt(5, hasTTV ? 1 : 0);
	        
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

	// Phương thức sinh mã khách hàng mới
	private String generateMaKhachHang() {
	    String newMaKhachHang = "KH001"; // Mã khách hàng mặc định nếu không có khách hàng nào trong cơ sở dữ liệu
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Tạo câu lệnh SQL để lấy mã khách hàng lớn nhất trong cơ sở dữ liệu
	        String sqlSelectMaxMaKH = "SELECT MAX(maKhachHang) FROM KhachHang";
	        PreparedStatement psSelectMaxMaKH = con.prepareStatement(sqlSelectMaxMaKH);
	        ResultSet rsMaxMaKH = psSelectMaxMaKH.executeQuery();
	        
	        if (rsMaxMaKH.next()) {
	            String maxMaKhachHang = rsMaxMaKH.getString(1);
	            // Kiểm tra xem có mã khách hàng lớn nhất hay không
	            if (maxMaKhachHang != null) {
	                // Tách số từ mã khách hàng hiện tại và tăng giá trị lên 1
	                int number = Integer.parseInt(maxMaKhachHang.trim().substring(2)) + 1;
	                // Format lại chuỗi số với độ dài 3 ký tự và thêm vào "KH"
	                newMaKhachHang = String.format("KH%03d", number);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return newMaKhachHang;
	}
	



	
}