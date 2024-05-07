package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.Phong;

public class Phong_DAO {
	public ArrayList<Phong> getAllPhong() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getIntance();
			Connection con  = ConnectDB.getConnection();
			String sql = "select * from Phong";
			Statement statement = con .createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maPhong = rs.getString("maPhong");
				Integer soLuong = rs.getInt("soLuongGhe");
				String tenPhong = rs.getString("tenPhong");
				Phong p = new Phong(maPhong, tenPhong, soLuong);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public String getMaTheoTenPhong(String tenPhong) { 
	    String maPhong = null;
	    ConnectDB.getIntance();
	    Connection con = ConnectDB.getConnection();
	    String sql = "SELECT maPhong FROM Phong WHERE tenPhong = ?";
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, tenPhong);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	maPhong = rs.getString("maPhong");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maPhong;
	}
	
	public Phong getPhongTheoMa(String ma) { 
		Phong ph = new Phong();
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[Phong] where maPhong = '"+ma+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ph.setMaPhong(rs.getString(1));
				ph.setSoLuongGhe(rs.getInt(2));
				ph.setTenPhong(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ph;
	}
	
}
