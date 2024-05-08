package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	private String generateMaVe() {
	    String maVe = "";
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT MAX(maVe) FROM Ve";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        rs.next();
	        String maxMaVe = rs.getString(1);
	        if (maxMaVe == null) {
	            return "VE00001";
	        } else {
	            maxMaVe = maxMaVe.trim();
	            int num = Integer.parseInt(maxMaVe.substring(2)) + 1;
	            maVe = String.format("VE%05d", num);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maVe;
	}
	public boolean taoVe(String moTa, double giaTien, String maPhim, String maGhe) {
	    try {
	        ConnectDB.getIntance();
	        Connection con = ConnectDB.getConnection();
	        
	        String sqlInsertVe = "INSERT INTO Ve (maVe, moTa, giaTien, maPhim, maGhe) VALUES (?, ?, ?, ?, ?)";
	        String maVeXemPhim = generateMaVe();

	        PreparedStatement preparedStatement = con.prepareStatement(sqlInsertVe);        
	 
	        preparedStatement.setString(1, maVeXemPhim);
	        preparedStatement.setString(2, moTa);
	        preparedStatement.setDouble(3, giaTien);
	        preparedStatement.setString(4, maPhim);
	        preparedStatement.setString(5, maGhe);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return false;
	}
	public String layMaVeCuoiCung() {
        String maVe = "";
        try {
            ConnectDB.getIntance();
            Connection con = ConnectDB.getConnection();       
            String sqlSelectLastMaVe = "SELECT TOP 1 maVe FROM Ve ORDER BY maVe DESC";
            Statement statement = con.createStatement();
            ResultSet rsLastMaVe = statement.executeQuery(sqlSelectLastMaVe);

            if (rsLastMaVe.next()) {
            	maVe = rsLastMaVe.getString("maVe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maVe;
    }

}
