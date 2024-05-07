package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietPhim;
import entity.LoaiPhim;
import entity.Phim;
import entity.Phong;

public class ChiTietPhim_DAO {
	public ArrayList<ChiTietPhim> getAllChiTietPhim() {
		ArrayList<ChiTietPhim> dsChiTietPhim = new ArrayList<>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietPhim";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Timestamp lichChieuTimestamp = rs.getTimestamp("lichChieu");
				LocalDateTime lichChieu = lichChieuTimestamp.toLocalDateTime();
				Phim phim = new Phim(rs.getString("maPhim"));
				Phong phong = new Phong(rs.getString("maPhong"));
				ChiTietPhim ctp = new ChiTietPhim(lichChieu, phim, phong);
				dsChiTietPhim.add(ctp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhim;
	}
	
	public ArrayList<Phong> getPhongByNgayChieuVaMaPhim(Date date, String maPhim) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT p.maPhong, p.soLuongGhe, p.tenPhong " +
                    "FROM ChiTietPhim ct " +
                    "JOIN Phong p ON ct.maPhong = p.maPhong " +
                    "WHERE CONVERT(DATE, ct.lichChieu) = ? AND ct.maPhim = ?";
	        statement = con.prepareStatement(sql);
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	        statement.setDate(1, sqlDate);
	        statement.setString(2, maPhim);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maPhong = rs.getString("maPhong");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String tenPhong = rs.getString("tenPhong");

                Phong phong = new Phong(maPhong, tenPhong, soLuongGhe);
                dsPhong.add(phong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public ArrayList<ChiTietPhim> getChiTietPhimByNgayMaPhimMaPhong(Date date, String maPhim, String maPhong) {
		ArrayList<ChiTietPhim> dsChiTietPhim = new ArrayList<ChiTietPhim>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM ChiTietPhim WHERE maPhim = ? AND maPhong = ? AND CONVERT(date, lichChieu) = ?";
	        statement = con.prepareStatement(sql);
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	        statement.setString(1, maPhim);
	        statement.setString(2, maPhong);
	        statement.setDate(3, sqlDate);
	        
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Timestamp lichChieuTimestamp = rs.getTimestamp("lichChieu");
				LocalDateTime lichChieu = lichChieuTimestamp.toLocalDateTime();
				Phim phim = new Phim(rs.getString("maPhim"));
				Phong phong = new Phong(rs.getString("maPhong"));
				ChiTietPhim ctp = new ChiTietPhim(lichChieu, phim, phong);
				dsChiTietPhim.add(ctp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhim;
	}
	
	public ArrayList<ChiTietPhim> getChiTietPhimByMaPhong(String maPhong) { 
		ArrayList<ChiTietPhim> dsChiTietPhim = new ArrayList<>();
		String sql = "select * from [dbo].[ChiTietPhim] where maPhong = '"+maPhong+"'";
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Timestamp lichChieuTimestamp = rs.getTimestamp("lichChieu");
				LocalDateTime lichChieu = lichChieuTimestamp.toLocalDateTime();
				Phim phim = new Phim(rs.getString("maPhim"));
				Phong phong = new Phong(rs.getString("maPhong"));
				ChiTietPhim ctp = new ChiTietPhim(lichChieu, phim, phong);
				dsChiTietPhim.add(ctp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhim;
	}
	
	public ArrayList<ChiTietPhim> getChiTietPhimByMaPhim(String maPhim) { 
		ArrayList<ChiTietPhim> dsChiTietPhim = new ArrayList<>();
		String sql = "select * from [dbo].[ChiTietPhim] where maPhim = '"+maPhim+"'";
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Timestamp lichChieuTimestamp = rs.getTimestamp("lichChieu");
				LocalDateTime lichChieu = lichChieuTimestamp.toLocalDateTime();
				Phim phim = new Phim(rs.getString("maPhim"));
				Phong phong = new Phong(rs.getString("maPhong"));
				ChiTietPhim ctp = new ChiTietPhim(lichChieu, phim, phong);
				dsChiTietPhim.add(ctp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhim;
	}
	
	public ArrayList<ChiTietPhim> getChiTietPhimByNgay(Date from, Date to) {
		ArrayList<ChiTietPhim> dsChiTietPhim = new ArrayList<ChiTietPhim>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM ChiTietPhim WHERE CONVERT(DATE, lichChieu) BETWEEN ? AND ?";
	        statement = con.prepareStatement(sql);
	        java.sql.Date fromDate = new java.sql.Date(from.getTime());
	        java.sql.Date toDate = new java.sql.Date(to.getTime());
	        
	        statement.setDate(1, fromDate);
	        statement.setDate(2, toDate);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Timestamp lichChieuTimestamp = rs.getTimestamp("lichChieu");
				LocalDateTime lichChieu = lichChieuTimestamp.toLocalDateTime();
				Phim phim = new Phim(rs.getString("maPhim"));
				Phong phong = new Phong(rs.getString("maPhong"));
				ChiTietPhim ctp = new ChiTietPhim(lichChieu, phim, phong);
				dsChiTietPhim.add(ctp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhim;
	}

	public boolean insert(ChiTietPhim chiTietPhim) {
		String check = "SELECT COUNT(*) FROM ChiTietPhim ctp JOIN Phim p ON ctp.maPhim = p.maPhim "
				+ "WHERE maPhong = ? AND ((? BETWEEN lichChieu AND "
				+ "DATEADD(MINUTE, p.thoiLuong, lichChieu)) OR "
				+ "(? BETWEEN lichChieu AND DATEADD(MINUTE, p.thoiLuong, lichChieu)))";
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement checkStmt = con.prepareStatement(check);
			checkStmt.setString(1, chiTietPhim.getPhong().getMaPhong());
	        checkStmt.setTimestamp(2, Timestamp.valueOf(chiTietPhim.getLichChieu()));
	        checkStmt.setTimestamp(3, Timestamp.valueOf(chiTietPhim.getLichChieu().plusMinutes((long) chiTietPhim.getPhim().getThoiLuong())));

			try (ResultSet rs = checkStmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					if (count > 0) {
						return false;
					}
				}
			}

			String insertQuery = "INSERT INTO ChiTietPhim (maPhim, maPhong, lichChieu) VALUES (?, ?, ?)";
			try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
				insertStmt.setString(1, chiTietPhim.getPhim().getMaPhim());
				insertStmt.setString(2, chiTietPhim.getPhong().getMaPhong());
				insertStmt.setTimestamp(3, Timestamp.valueOf(chiTietPhim.getLichChieu()));

				int n = insertStmt.executeUpdate();
				return n > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String maPhim, String maPhong, LocalDateTime lichChieu) {
		String sql = "DELETE FROM ChiTietPhim WHERE maPhim = ? AND maPhong = ? AND lichChieu = ?";
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maPhim);
			pst.setString(2, maPhong);
			pst.setTimestamp(3, Timestamp.valueOf(lichChieu));
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void xoaSuatChieuHetHan() {
	    LocalDateTime now = LocalDateTime.now();
	    String sql = "DELETE FROM ChiTietPhim WHERE "
	    		+ "DATEADD(MINUTE, (SELECT thoiLuong FROM Phim WHERE ChiTietPhim.maPhim = Phim.maPhim), lichChieu) <= ?";

	    try {
	        Connection con = ConnectDB.getConnection();
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setTimestamp(1, Timestamp.valueOf(now));
	        int n = pst.executeUpdate();
//	        System.out.println(n + " suất chiếu đã bị xóa.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
