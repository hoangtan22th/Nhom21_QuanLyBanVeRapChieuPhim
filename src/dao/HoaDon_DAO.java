package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import ui.DangNhap;

public class HoaDon_DAO {
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
        try {
            ConnectDB.getIntance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                Date ngapLapHD = rs.getDate("ngayLapHD");
                NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
                KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
                HoaDon hd = new HoaDon(maHD, ngapLapHD, kh, nv);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public ArrayList<HoaDon> getHDTheoNgay(Date ngayBatDau, Date ngayKetThuc) {

        ArrayList<HoaDon> lsHD = new ArrayList<HoaDon>();
        String ngayBD = (ngayBatDau.getYear() + 1900) + "/" + (ngayBatDau.getMonth() + 1) + "/" + ngayBatDau.getDate();
        String ngayKT = (ngayKetThuc.getYear() + 1900) + "/" + (ngayKetThuc.getMonth() + 1) + "/" + ngayKetThuc.getDate();

        ConnectDB.getIntance();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from QuanLyBanVe.[dbo].[HoaDon] where ngayLapHD between '" + ngayBD + "' and '" + ngayKT + "'";

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setNgayLapHoaDon(rs.getDate(2));
                hd.setNhanVien(new NhanVien(rs.getString(3)));
                hd.setKhachHang(new KhachHang(rs.getString(4)));
                lsHD.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lsHD;

    }

    public ArrayList<HoaDon> getAllHoaDonWithDetails() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            ConnectDB.getIntance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT hd.*, nv.*, kh.* " + "FROM HoaDon hd "
                    + "INNER JOIN NhanVien nv ON hd.maNhanVien = nv.maNhanVien "
                    + "INNER JOIN KhachHang kh ON hd.maKhachHang = kh.maKhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                Date ngapLapHD = rs.getDate("ngayLapHD");
                NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                HoaDon hd = new HoaDon(maHD, ngapLapHD, kh, nv);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public ArrayList<HoaDon> getHoaDonByDate(String fromDate, String toDate) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            ConnectDB.getIntance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT hd.*, nv.*, kh.* " + "FROM HoaDon hd "
                    + "INNER JOIN NhanVien nv ON hd.maNhanVien = nv.maNhanVien "
                    + "INNER JOIN KhachHang kh ON hd.maKhachHang = kh.maKhachHang " + "WHERE hd.ngayLapHD >= '"
                    + fromDate + "' AND hd.ngayLapHD <= '" + toDate + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                Date ngapLapHD = rs.getDate("ngayLapHD");
                NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                HoaDon hd = new HoaDon(maHD, ngapLapHD, kh, nv);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public ArrayList<HoaDon> getHoaDonByMaWithDetails(String maHoaDon) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            ConnectDB.getIntance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT hd.*, nv.*, kh.* " + "FROM HoaDon hd "
                    + "INNER JOIN NhanVien nv ON hd.maNhanVien = nv.maNhanVien "
                    + "INNER JOIN KhachHang kh ON hd.maKhachHang = kh.maKhachHang " + "WHERE hd.maHoaDon = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date ngayLapHD = rs.getDate("ngayLapHD");
                NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                HoaDon hd = new HoaDon(maHoaDon, ngayLapHD, kh, nv);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public void themHoaDonKhiCoTheThanhVien(HoaDon hoaDon) {
	    if (hoaDon.getNgayLapHoaDon() == null) {
	        System.out.println("Ngày lập hóa đơn không được null.");
	        return; // hoặc xử lý theo ý của bạn
	    }


	    String sqlSelectMaxMaHD = "SELECT MAX(maHoaDon) FROM HoaDon";
	    String sqlInsertHoaDon = "INSERT INTO HoaDon (maHoaDon, ngayLapHD, maNhanVien, maKhachHang) VALUES (?, ?, ?, ?)";
	    String maKhachHang = new KhachHang_DAO().timMaKhachHangTheoSDT(hoaDon.getKhachHang().getSoDienThoai());
	    try {
	        Connection conn = ConnectDB.getConnection();
	        PreparedStatement psSelectMaxMaHD = conn.prepareStatement(sqlSelectMaxMaHD);
	        ResultSet rsMaxMaHD = psSelectMaxMaHD.executeQuery();
	        String maHD = "HD00001"; // Giá trị mặc định
	        if (rsMaxMaHD.next()) {
	            String maxMaHoaDon = rsMaxMaHD.getString(1);
	            maHD = generateMaHoaDon(maxMaHoaDon);
	        }
//	        try (PreparedStatement psInsertHoaDon = conn.prepareStatement(sqlInsertHoaDon)) {
	        	PreparedStatement psInsertHoaDon = conn.prepareStatement(sqlInsertHoaDon);
	            psInsertHoaDon.setString(1, maHD);
	            psInsertHoaDon.setDate(2, new java.sql.Date(hoaDon.getNgayLapHoaDon().getTime()));
	            psInsertHoaDon.setString(3, DangNhap.maNhanVien);
	            psInsertHoaDon.setString(4, hoaDon.getKhachHang().getMaKhachHang());
	            psInsertHoaDon.executeUpdate();
//	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


    // Phương thức sinh mã hoá đơn mới
    private String generateMaHoaDon(String maxMaHoaDon) {
        String newMaHoaDon = "HD0001"; // Mã hoá đơn mặc định nếu không có hóa đơn nào trong cơ sở dữ liệu
        if (maxMaHoaDon != null) {
            // Tách số từ mã hoá đơn hiện tại và tăng giá trị lên 1
            int number = Integer.parseInt(maxMaHoaDon.trim().substring(2)) + 1;
            // Format lại chuỗi số với độ dài 4 ký tự và thêm vào "HD"
            newMaHoaDon = String.format("HD%05d", number);
        }
        return newMaHoaDon;
    }

    // Phương thức để lấy mã khách hàng cuối cùng từ cơ sở dữ liệu
    private String getLastMaKhachHang() {
        String maKhachHang = "";
        try {
            ConnectDB.getIntance();
            Connection con = ConnectDB.getConnection();

            // Tạo câu lệnh SQL để lấy mã khách hàng cuối cùng từ cơ sở dữ liệu
            String sqlSelectLastMaKH = "SELECT TOP 1 maKhachHang FROM KhachHang ORDER BY maKhachHang DESC";
            Statement statement = con.createStatement();
            ResultSet rsLastMaKH = statement.executeQuery(sqlSelectLastMaKH);

            if (rsLastMaKH.next()) {
                maKhachHang = rsLastMaKH.getString("maKhachHang");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maKhachHang;
    }

    public void themHoaDonVoiKhachHangCuoiCung(HoaDon hoaDon) {
        if (hoaDon.getNgayLapHoaDon() == null) {
            System.out.println("Ngày lập hóa đơn không được null.");
            return; // hoặc xử lý theo ý của bạn
        }

        String sqlSelectMaxMaHD = "SELECT MAX(maHoaDon) FROM HoaDon";
        String sqlInsertHoaDon = "INSERT INTO HoaDon (maHoaDon, ngayLapHD, maNhanVien, maKhachHang) VALUES (?, ?, ?, ?)";
        String maKhachHang = getLastMaKhachHang();
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement psSelectMaxMaHD = conn.prepareStatement(sqlSelectMaxMaHD);
            ResultSet rsMaxMaHD = psSelectMaxMaHD.executeQuery();
            String maHD = "HD00001"; // Giá trị mặc định
            if (rsMaxMaHD.next()) {
                String maxMaHoaDon = rsMaxMaHD.getString(1);
                maHD = generateMaHoaDon(maxMaHoaDon);
            }

            PreparedStatement psInsertHoaDon = conn.prepareStatement(sqlInsertHoaDon);
            psInsertHoaDon.setString(1, maHD);
            psInsertHoaDon.setDate(2, new java.sql.Date(hoaDon.getNgayLapHoaDon().getTime()));
            psInsertHoaDon.setString(3, DangNhap.maNhanVien);
            psInsertHoaDon.setString(4, getLastMaKhachHang());
            psInsertHoaDon.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HoaDon layHoaDonCuoiCung() {
        HoaDon hoaDon = null;
        String sqlSelectLastHoaDon = "SELECT TOP 1 * FROM HoaDon ORDER BY maHoaDon DESC";

        try {
            Connection conn = ConnectDB.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rsLastHoaDon = statement.executeQuery(sqlSelectLastHoaDon);

            if (rsLastHoaDon.next()) {
                String maHoaDon = rsLastHoaDon.getString("maHoaDon");
                Date ngayLapHoaDon = rsLastHoaDon.getDate("ngayLapHD");
                String maKhachHang = rsLastHoaDon.getString("maKhachHang");
                String maNhanVien = rsLastHoaDon.getString("maNhanVien");

                // Khởi tạo đối tượng hoá đơn
                hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, new KhachHang(maKhachHang), new NhanVien(maNhanVien));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    public HoaDon layHoaDonCuoiCungCoTen() {
        HoaDon hoaDon = null;
        try {
            ConnectDB.getIntance().connect(); // Mở kết nối đến cơ sở dữ liệu
            Connection con = ConnectDB.getConnection(); // Lấy kết nối từ ConnectDB
            String sql = "SELECT TOP 1 hd.maHoaDon, hd.ngayLapHD, nv.maNhanVien, nv.tenNhanVien, kh.maKhachHang, kh.tenKhachHang, kh.soDienThoai "
                    + "FROM HoaDon hd " + "INNER JOIN NhanVien nv " + "ON hd.maNhanVien = nv.maNhanVien "
                    + "INNER JOIN KhachHang kh " + "ON hd.maKhachHang = kh.maKhachHang " + "ORDER BY hd.maHoaDon DESC";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                Date ngayLapHD = rs.getDate("ngayLapHD");
                NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setSoDienThoai(rs.getString("soDienThoai"));
                hoaDon = new HoaDon(maHoaDon, ngayLapHD, kh, nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return hoaDon;

    }
    public String layMaKhachHangTuHoaDonCuoiCung() {
        String maKhachHang = null;
        try {
            ConnectDB.getIntance().connect(); 
            Connection con = ConnectDB.getConnection(); 
            String sql = "SELECT TOP 1 maKhachHang FROM HoaDon ORDER BY maHoaDon DESC";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                maKhachHang = rs.getString("maKhachHang");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maKhachHang;
    }
    public String getLastMaHoaDon() {
        String lastMaHoaDon = "";
        try {
            Connection connection = ConnectDB.getConnection();
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT TOP 1 maHoaDon FROM HoaDon ORDER BY maHoaDon DESC";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                lastMaHoaDon = resultSet.getString("maHoaDon");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastMaHoaDon;
    }
}


