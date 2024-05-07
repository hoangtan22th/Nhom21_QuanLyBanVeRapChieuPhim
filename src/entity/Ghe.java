package entity;

public class Ghe {
	private String maGhe,tenGhe;
	private boolean trangThai;
	private Phong phong;
	public Ghe() {
		
	}
	
	public Ghe(String maGhe) {
		this.maGhe = maGhe;
	}

	public Ghe(String maGhe, String tenGhe, boolean trangThai, Phong phong) {
		super();
		this.maGhe = maGhe;
		this.tenGhe = tenGhe;
		this.trangThai = trangThai;
		this.phong = phong;
	}
	public String getMaGhe() {
		return maGhe;
	}
	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}
	public String getTenGhe() {
		return tenGhe;
	}
	public void setTenGhe(String tenGhe) {
		this.tenGhe = tenGhe;
	}
	public boolean getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + ", tenGhe=" + tenGhe + ", trangThai=" + trangThai + ", phong=" + phong + "]";
	}
	
}
