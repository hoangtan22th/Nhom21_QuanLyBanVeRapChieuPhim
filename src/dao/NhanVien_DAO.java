package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_DAO {
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString("maNhanVien");
				Date ngayVaoLam = rs.getDate("ngayVaoLam");
				Double luong = rs.getDouble("luong");
				String tenNV = rs.getString("tenNhanVien");
				Integer tuoi = rs.getInt("tuoi");
				String soDT = rs.getString("soDienThoai");
				NhanVien nv = new NhanVien(maNV, tenNV, soDT, ngayVaoLam, luong, tuoi);
				dsNhanVien.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	
	public NhanVien getNVTheoMa(String ma) { 
		NhanVien nv = new NhanVien();
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[NhanVien] where maNhanVien = '"+ma+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				nv.setMaNhanVien(rs.getString(1));
				nv.setNgayVaoLam(rs.getDate(2));
				nv.setLuong(rs.getDouble(3));
				nv.setTenNhanVien(rs.getString(4));
				nv.setTuoi(rs.getInt(5));
				nv.setSoDienThoai(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
}
