package entity;

import java.time.LocalDateTime;
import java.util.Date;

public class ChiTietPhim {
	private LocalDateTime lichChieu;
	private Phim phim;
	private Phong phong;
	public ChiTietPhim() {
		
	}
	public ChiTietPhim(LocalDateTime lichChieu, Phim phim, Phong phong) {
		super();
		this.lichChieu = lichChieu;
		this.phim = phim;
		this.phong = phong;
	}
	public LocalDateTime getLichChieu() {
		return lichChieu;
	}
	public void setLichChieu(LocalDateTime lichChieu) {
		this.lichChieu = lichChieu;
	}
	public Phim getPhim() {
		return phim;
	}
	public void setPhim(Phim phim) {
		this.phim = phim;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	@Override
	public String toString() {
		return "ChiTietPhim [lichChieu=" + lichChieu + ", phim=" + phim + ", phong=" + phong + "]";
	}
	
}
