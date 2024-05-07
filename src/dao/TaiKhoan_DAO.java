package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from TaiKhoan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String taiKhoan = rs.getString("taiKhoan");
				String pass = rs.getString("matKhau");
				Date ngayTao = rs.getDate("ngayTao");
				Integer isNhanVien = rs.getInt("isNhanVien");
				TaiKhoan tk = new TaiKhoan(id, taiKhoan, pass, ngayTao, isNhanVien == 1 ? true : false);
				dstk.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dstk;
	}
}
