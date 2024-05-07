package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhim;

public class LoaiPhim_DAO {
	public ArrayList<LoaiPhim> getAllLoaiPhim() {
		ArrayList<LoaiPhim> dsLoaiPhim = new ArrayList<LoaiPhim>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from LoaiPhim";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maLP = rs.getString("maLoaiPhim");
				String tenLP = rs.getString("tenLoaiPhim");
				String moTa = rs.getString("moTa");
				LoaiPhim lp = new LoaiPhim(maLP, tenLP, moTa);
				dsLoaiPhim.add(lp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiPhim;
	}
	

	public static void main(String[] args) {
		try {
			ConnectDB.getIntance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoaiPhim_DAO dao = new LoaiPhim_DAO();
		ArrayList<LoaiPhim> list = dao.getAllLoaiPhim();
		for(LoaiPhim lp : list) {
			System.out.println(lp.getMaLoaiPhim());
			System.out.println(lp.getTenLoaiPhim());;
			System.out.println(lp.getMoTa());
		}
	}
}
