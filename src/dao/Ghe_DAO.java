package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Phong;
import ui.Ghe;

public class Ghe_DAO {
	public ArrayList<entity.Ghe> getAllGhe() {
		
		ArrayList<entity.Ghe> dsGhe = new ArrayList<entity.Ghe>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			
			
			Statement statement = con.createStatement();
			String sql = "select * from Ghe";
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maGhe = rs.getString("maGhe");
				Integer trangThai = rs.getInt("trangThai");
				String tenGhe = rs.getString("tenGhe");
				Phong p = new Phong(rs.getString("maPhong"));
				entity.Ghe g = new entity.Ghe(maGhe, tenGhe, trangThai == 1 ? true : false, p);
				dsGhe.add(new entity.Ghe(maGhe, tenGhe, trangThai == 1 ? true : false, p));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsGhe;
	}
	
	
}
