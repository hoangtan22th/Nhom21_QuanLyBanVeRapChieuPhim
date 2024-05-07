package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTiet_HoaDon;
import entity.HoaDon;
import entity.Ve;

public class ChiTietHoaDon_DAO {
	public ArrayList<ChiTiet_HoaDon> getAllChiTietHoaDon() {
		ArrayList<ChiTiet_HoaDon> dsChiTietHoaDon = new ArrayList<ChiTiet_HoaDon>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Integer soLuong = rs.getInt("soLuong");
				Double tongTien = rs.getDouble("tongTien");
				HoaDon hd = new HoaDon(rs.getString("maHoaDon"));
				Ve v = new Ve(rs.getString("maVe"));
				ChiTiet_HoaDon cthd = new ChiTiet_HoaDon(soLuong, tongTien, hd, v);
				dsChiTietHoaDon.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public ArrayList<ChiTiet_HoaDon> getCTHDTheoMaHD(String maHD) {
		ArrayList<ChiTiet_HoaDon> lsCTHD = new ArrayList<ChiTiet_HoaDon>();
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM  ChiTietHoaDon where maHD = '" + maHD + "'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				ChiTiet_HoaDon cthd = new ChiTiet_HoaDon();

				cthd.setSoLuong(rs.getInt(1));
				cthd.setHoaDon(new HoaDon(rs.getString(2)));
				cthd.setVe(new Ve(rs.getString(3)));
				cthd.setTongTien(rs.getDouble(4));
				lsCTHD.add(cthd);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCTHD;
	}
	
	// Phương thức đếm số lượng hóa đơn theo mã nhân viên
		public int countHoaDonByNhanVien(String maNhanVien, java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc) {
		    int count = 0;
		    try {
		    	ConnectDB.getIntance();
				Connection con = ConnectDB.getConnection();
		        String query = "SELECT COUNT(*) AS count FROM ChiTietHoaDon cthd JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon "
		        		+ "WHERE maNhanVien = ? AND ngayLapHD BETWEEN ? AND ?";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, maNhanVien);
		        ps.setDate(2, ngayBatDau);
		        ps.setDate(3, ngayKetThuc);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            count = rs.getInt("count");
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return count;
		}


	    // Phương thức tính tổng doanh thu theo mã nhân viên
		public double sumDoanhThuByNhanVien(String maNhanVien, java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc) {
		    double sum = 0;
		    try {
		    	ConnectDB.getIntance();
				Connection con = ConnectDB.getConnection();
		        String query = "SELECT SUM(tongTien) AS sum FROM ChiTietHoaDon cthd JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon "
		        		+ "WHERE maNhanVien = ? AND ngayLapHD BETWEEN ? AND ?";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, maNhanVien);
		        ps.setDate(2, ngayBatDau);
		        ps.setDate(3, ngayKetThuc);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            sum = rs.getDouble("sum");
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return sum;
		}

}
