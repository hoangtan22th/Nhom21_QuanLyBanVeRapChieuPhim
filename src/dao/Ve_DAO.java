package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ghe;
import entity.Phim;
import entity.Ve;

public class Ve_DAO {
	public ArrayList<Ve> getAllVe() {
		ArrayList<Ve> dsVe = new ArrayList<Ve>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Ve";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maVe = rs.getString("maVe");
				String moTa = rs.getString("moTa");
				Double giaTien = rs.getDouble("giaTien");
				Phim p = new Phim(rs.getString("maPhim"));
				Ghe g = new Ghe(rs.getString("maGhe"));
				Ve v = new Ve(maVe, moTa, giaTien, p, g);
				dsVe.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVe;
	}
}
