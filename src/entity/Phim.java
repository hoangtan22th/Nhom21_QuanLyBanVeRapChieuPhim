package entity;

import java.util.Date;

public class Phim {
	private String maPhim,tenPhim,ngonNgu;
	private Date ngayKhoiChieu;
	private double thoiLuong,giaTien;
	private int gioiHanDoTuoi;
	private boolean trangThai;
	private String poster;
	private LoaiPhim loaiPhim;
	public Phim() {
		
	}
	
	public Phim(String maPhim) {
		this.maPhim = maPhim;
	}

	
	public Phim(String maPhim, String tenPhim, String ngonNgu, Date ngayKhoiChieu, double thoiLuong, double giaTien,
			int gioiHanDoTuoi, boolean trangThai, String poster, LoaiPhim loaiPhim) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.ngonNgu = ngonNgu;
		this.ngayKhoiChieu = ngayKhoiChieu;
		this.thoiLuong = thoiLuong;
		this.giaTien = giaTien;
		this.gioiHanDoTuoi = gioiHanDoTuoi;
		this.trangThai = trangThai;
		this.poster = poster;
		this.loaiPhim = loaiPhim;
	}

	public String getMaPhim() {
		return maPhim;
	}
	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}
	public String getTenPhim() {
		return tenPhim;
	}
	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}
	public String getNgonNgu() {
		return ngonNgu;
	}
	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}
	public Date getNgayKhoiChieu() {
		return ngayKhoiChieu;
	}
	public void setNgayKhoiChieu(Date ngayKhoiChieu) {
		this.ngayKhoiChieu = ngayKhoiChieu;
	}
	public double getThoiLuong() {
		return thoiLuong;
	}
	public void setThoiLuong(double thoiLuong) {
		this.thoiLuong = thoiLuong;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public int getGioiHanDoTuoi() {
		return gioiHanDoTuoi;
	}
	public void setGioiHanDoTuoi(int gioiHanDoTuoi) {
		this.gioiHanDoTuoi = gioiHanDoTuoi;
	}
	public boolean getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public LoaiPhim getLoaiPhim() {
		return loaiPhim;
	}
	public void setLoaiPhim(LoaiPhim loaiPhim) {
		this.loaiPhim = loaiPhim;
	}

	@Override
	public String toString() {
		return "Phim [maPhim=" + maPhim + ", tenPhim=" + tenPhim + ", ngonNgu=" + ngonNgu + ", ngayKhoiChieu="
				+ ngayKhoiChieu + ", thoiLuong=" + thoiLuong + ", giaTien=" + giaTien + ", gioiHanDoTuoi="
				+ gioiHanDoTuoi + ", trangThai=" + trangThai + ", poster=" + poster + ", loaiPhim=" + loaiPhim + "]";
	}
	
	
}
