package entity;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private Date ngayLapHoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    
    public HoaDon() {
        this.ngayLapHoaDon = new Date();
    }
    
    public HoaDon(KhachHang khachHang, NhanVien nhanVien) {
        this.ngayLapHoaDon = new Date();
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
    }
    
    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDon(String maHoaDon, Date ngayLapHoaDon, KhachHang khachHang, NhanVien nhanVien) {
        this.maHoaDon = maHoaDon;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
    }
    
    public String getMaHoaDon() {
        return maHoaDon;
    }
    
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    
    // Sửa phương thức setNgayLapHoaDon để tạo ngày lập hóa đơn là ngày hiện tại
    public void setNgayLapHoaDonToCurrentDate() {
        this.ngayLapHoaDon = new Date();
    }
    
    public KhachHang getKhachHang() {
        return khachHang;
    }
    
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
    
    public NhanVien getNhanVien() {
        return nhanVien;
    }
    
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
    
    public Date getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	// Override phương thức toString để hiển thị thông tin của đối tượng HoaDon
    @Override
    public String toString() {
        return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + ", khachHang=" + khachHang
                + ", nhanVien=" + nhanVien + "]";
    }
}
