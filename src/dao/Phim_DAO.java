package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.LoaiPhim;
import entity.Phim;
import entity.Phong;

public class Phim_DAO {
	public ArrayList<Phim> getAllPhim() {
		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Phim";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maPhim = rs.getString("maPhim");
				String tenPhim = rs.getString("tenPhim");
				Date ngayKhoiChieu = rs.getDate("ngayKhoiChieu");
				Double thoiLuong = rs.getDouble("thoiLuong");
				String ngonNgu = rs.getString("ngonNgu");
				Integer gioiHanTuoi = rs.getInt("gioiHanDoTuoi");
				Integer trangThai =  rs.getInt("trangThai");
				String poster = rs.getString("poster");
				Double giaTien = rs.getDouble("giaTien");
				LoaiPhim lp = new LoaiPhim(rs.getString("maLoaiPhim"));
				Phim p = new Phim(maPhim, tenPhim, ngonNgu, ngayKhoiChieu, thoiLuong, giaTien, gioiHanTuoi, trangThai == 1 ? true : false, poster, lp);
				dsPhim.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhim;
	}
	
	public String getMaTheoTenPhim(String tenPhim) { 
	    String maPhim = null;
	    ConnectDB.getIntance();
	    Connection con = ConnectDB.getConnection();
	    String sql = "SELECT maPhim FROM phim WHERE tenPhim like ?";
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + tenPhim + "%");
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            maPhim = rs.getString("maPhim");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maPhim;
	}

	
	public Phim getPhimTheoMa(String ma) { 
		Phim ph = new Phim();
		ConnectDB.getIntance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from [dbo].[Phim] where maPhim = '"+ma+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ph.setMaPhim(rs.getString(1));
				ph.setTenPhim(rs.getString(2));
				ph.setNgayKhoiChieu(rs.getDate(3));
				ph.setThoiLuong(rs.getDouble(4));
				ph.setNgonNgu(rs.getString(5));
				ph.setGioiHanDoTuoi(rs.getInt(6));
				ph.setTrangThai(rs.getBoolean(7));
				ph.setGiaTien(rs.getDouble(8));
				ph.setLoaiPhim(new LoaiPhim(rs.getString(9)));
				ph.setPoster(rs.getString(10));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ph;
	}
	
	public ArrayList<Phim> getPhimByTen(String ten) {
		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Phim where tenPhim like ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + ten + "%");
//			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maPhim = rs.getString("maPhim");
				String tenPhim = rs.getString("tenPhim");
				Date ngayKhoiChieu = rs.getDate("ngayKhoiChieu");
				Double thoiLuong = rs.getDouble("thoiLuong");
				String ngonNgu = rs.getString("ngonNgu");
				Integer gioiHanTuoi = rs.getInt("gioiHanDoTuoi");
				Integer trangThai =  rs.getInt("trangThai");
				String poster = rs.getString("poster");
				Double giaTien = rs.getDouble("giaTien");
				LoaiPhim lp = new LoaiPhim(rs.getString("maLoaiPhim"));
				Phim p = new Phim(maPhim, tenPhim, ngonNgu, ngayKhoiChieu, thoiLuong, giaTien, gioiHanTuoi, trangThai == 1 ? true : false, poster, lp);
				dsPhim.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhim;
	}
	
	public ArrayList<Phim> getPhimByLoaiPhim(String loaiPhim) {
		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select p.* from Phim p join LoaiPhim lp on p.maLoaiPhim = lp.maLoaiPhim "
					+ "where lp.tenLoaiPhim = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, loaiPhim);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maPhim = rs.getString("maPhim");
				String tenPhim = rs.getString("tenPhim");
				Date ngayKhoiChieu = rs.getDate("ngayKhoiChieu");
				Double thoiLuong = rs.getDouble("thoiLuong");
				String ngonNgu = rs.getString("ngonNgu");
				Integer gioiHanTuoi = rs.getInt("gioiHanDoTuoi");
				Integer trangThai =  rs.getInt("trangThai");
				String poster = rs.getString("poster");
				Double giaTien = rs.getDouble("giaTien");
				LoaiPhim lp = new LoaiPhim(rs.getString("maLoaiPhim"));
				Phim p = new Phim(maPhim, tenPhim, ngonNgu, ngayKhoiChieu, thoiLuong, giaTien, gioiHanTuoi, trangThai == 1 ? true : false, poster, lp);
				dsPhim.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhim;
	}
	//select * from Phim where ngayKhoiChieu >= '' and ngayKhoiChieu <= ''
	public ArrayList<Phim> getPhimByNgayCongChieu(Date from, Date to) {
		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Phim where ngayKhoiChieu between ? and ?";
	        statement = con.prepareStatement(sql);
	        java.sql.Date fromDate = new java.sql.Date(from.getTime());
	        java.sql.Date toDate = new java.sql.Date(to.getTime());
	        
	        statement.setDate(1, fromDate);
	        statement.setDate(2, toDate);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maPhim = rs.getString("maPhim");
				String tenPhim = rs.getString("tenPhim");
				Date ngayKhoiChieu = rs.getDate("ngayKhoiChieu");
				Double thoiLuong = rs.getDouble("thoiLuong");
				String ngonNgu = rs.getString("ngonNgu");
				Integer gioiHanTuoi = rs.getInt("gioiHanDoTuoi");
				Integer trangThai =  rs.getInt("trangThai");
				String poster = rs.getString("poster");
				Double giaTien = rs.getDouble("giaTien");
				LoaiPhim lp = new LoaiPhim(rs.getString("maLoaiPhim"));
				Phim p = new Phim(maPhim, tenPhim, ngonNgu, ngayKhoiChieu, thoiLuong, giaTien, gioiHanTuoi, trangThai == 1 ? true : false, poster, lp);
				dsPhim.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhim;
	}
	
	public ArrayList<Phim> getPhimDangChieu() {
		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		PreparedStatement statement = null;
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Phim where trangThai = 1";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maPhim = rs.getString("maPhim");
				String tenPhim = rs.getString("tenPhim");
				Date ngayKhoiChieu = rs.getDate("ngayKhoiChieu");
				Double thoiLuong = rs.getDouble("thoiLuong");
				String ngonNgu = rs.getString("ngonNgu");
				Integer gioiHanTuoi = rs.getInt("gioiHanDoTuoi");
				Integer trangThai =  rs.getInt("trangThai");
				String poster = rs.getString("poster");
				Double giaTien = rs.getDouble("giaTien");
				LoaiPhim lp = new LoaiPhim(rs.getString("maLoaiPhim"));
				Phim p = new Phim(maPhim, tenPhim, ngonNgu, ngayKhoiChieu, thoiLuong, giaTien, gioiHanTuoi, trangThai == 1 ? true : false, poster, lp);
				dsPhim.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhim;
	}
	
	public boolean insert(Phim movie) {
        try{
        	ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
            String sql = "insert into Phim values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, generateId());
            pst.setString(2, movie.getTenPhim());
            java.util.Date utilDate = movie.getNgayKhoiChieu();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pst.setDate(3, sqlDate);
            pst.setDouble(4, movie.getThoiLuong());
            pst.setString(5, movie.getNgonNgu());
            pst.setInt(6, movie.getGioiHanDoTuoi());
            pst.setBoolean(7, movie.getTrangThai());
            pst.setDouble(8, movie.getGiaTien());
            pst.setString(9, movie.getLoaiPhim().getMaLoaiPhim());
            pst.setString(10, movie.getPoster());
            return pst.executeUpdate() > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
	
	public boolean update(Phim movie) {
        try{
        	ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
            String sql = "update Phim set tenPhim = ?, ngayKhoiChieu = ?, thoiLuong = ?, ngonNgu = ?, "
                    + "gioiHanDoTuoi = ?, trangThai = ?, giaTien = ?, maLoaiPhim = ?, poster = ? "
                    + "where maPhim = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, movie.getTenPhim());
            java.util.Date utilDate = movie.getNgayKhoiChieu();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pst.setDate(2, sqlDate);
            pst.setDouble(3, movie.getThoiLuong());
            pst.setString(4, movie.getNgonNgu());
            pst.setInt(5, movie.getGioiHanDoTuoi());
            pst.setBoolean(6, movie.getTrangThai());
            pst.setDouble(7, movie.getGiaTien());
            pst.setString(8, movie.getLoaiPhim().getMaLoaiPhim());
            pst.setString(9, movie.getPoster());
            pst.setString(10, movie.getMaPhim());
            return pst.executeUpdate() > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
	
	public boolean remove(String maPhim) {
		try {
			ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from Phim where maPhim = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maPhim);
	
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String generateId() {
        String id = "";
        try{
        	ConnectDB.getIntance();
			Connection con = ConnectDB.getConnection();
            String sql = "select max(maPhim) from Phim";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String maxMaPhim = rs.getString(1);
            if(maxMaPhim == null){
                return "PH001";
            }
            else{
            	maxMaPhim = maxMaPhim.trim();
                int num = Integer.parseInt(maxMaPhim.substring(2)) + 1;
                id = String.format("PH%03d", num);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return id;
    }
		
}
