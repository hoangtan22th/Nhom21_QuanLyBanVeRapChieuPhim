package entity;

import java.util.Date;

public class TaiKhoan {
	private int id ;
	private String taiKhoan,matKhau;
	private Date ngayTao;
	private boolean isNhanVien;
	public TaiKhoan() {
		
	}
	public TaiKhoan(int id, String taiKhoan, String matKhau, Date ngayTao, boolean isNhanVien) {
		super();
		this.id = id;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.ngayTao = ngayTao;
		this.isNhanVien = isNhanVien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public boolean isNhanVien() {
		return isNhanVien;
	}
	public void setNhanVien(boolean isNhanVien) {
		this.isNhanVien = isNhanVien;
	}
	
}
