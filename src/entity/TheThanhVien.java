package entity;

import java.util.Date;

public class TheThanhVien {
	private String maTheThanhVien,loai;
	private Date ngayDangKy;
	private double diemTichLuy;
	
	private KhachHang khachHang;

	public TheThanhVien(String maTheThanhVien, String loai, Date ngayDangKy, double diemTichLuy, KhachHang khachHang) {
		super();
		this.maTheThanhVien = maTheThanhVien;
		this.loai = loai;
		this.ngayDangKy = ngayDangKy;
		this.diemTichLuy = diemTichLuy;
		this.khachHang = khachHang;
	}

	public String getMaTheThanhVien() {
		return maTheThanhVien;
	}

	public void setMaTheThanhVien(String maTheThanhVien) {
		this.maTheThanhVien = maTheThanhVien;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public Date getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public double getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(double diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public String toString() {
		return "TheThanhVien [maTheThanhVien=" + maTheThanhVien + ", loai=" + loai + ", ngayDangKy=" + ngayDangKy
				+ ", diemTichLuy=" + diemTichLuy + ", khachHang=" + khachHang + "]";
	}
	
	
}	
