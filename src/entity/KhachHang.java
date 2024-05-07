package entity;

public class KhachHang {
	private String maKhachHang,tenKhachHang,soDienThoai;
	private int tuoi;
	private boolean hasTheThanhVien;
	public KhachHang() {
		
	}
	
	public KhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, int tuoi, boolean hasTheThanhVien) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.tuoi = tuoi;
		this.hasTheThanhVien = hasTheThanhVien;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public boolean hasTheThanhVien() {
		return hasTheThanhVien;
	}
	public void setHasTheThanhVien(boolean hasTheThanhVien) {
		this.hasTheThanhVien = hasTheThanhVien;
	}
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", soDienThoai="
				+ soDienThoai + ", tuoi=" + tuoi + ", hasTheThanhVien=" + hasTheThanhVien + "]";
	}
	
}
