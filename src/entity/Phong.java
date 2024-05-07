package entity;

public class Phong {
	private String maPhong,tenPhong;
	private int soLuongGhe;
	public Phong() {
		
	}
	
	public Phong(String maPhong) {
		this.maPhong = maPhong;
	}

	public Phong(String maPhong, String tenPhong, int soLuongGhe) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.soLuongGhe = soLuongGhe;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public int getSoLuongGhe() {
		return soLuongGhe;
	}
	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", soLuongGhe=" + soLuongGhe + "]";
	}
	
}
