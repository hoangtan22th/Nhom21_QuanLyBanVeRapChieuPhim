package entity;

public class ChiTiet_HoaDon {
	private int soLuong;
	private double tongTien;
	private HoaDon hoaDon;
	private Ve ve;
	public ChiTiet_HoaDon() {
		
	}

	public ChiTiet_HoaDon(int soLuong, double tongTien, HoaDon hoaDon, Ve ve) {
		super();
		this.soLuong = soLuong;
		this.tongTien = tongTien;
		this.hoaDon = hoaDon;
		this.ve = ve;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	
	public double tongGiaTien() { 
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "ChiTiet_HoaDon [soLuong=" + soLuong + ", tongTien=" + tongTien + ", hoaDon=" + hoaDon + ", ve=" + ve
				+ "]";
	}

}
