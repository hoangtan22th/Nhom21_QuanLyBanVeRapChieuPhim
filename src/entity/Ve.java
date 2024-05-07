package entity;

public class Ve {
	private String maVe,moTa;
	private double giaTien;
	private Phim phim;
	
	private Ghe ghe;
	public Ve() {
		
	}
	
	public Ve(String maVe) {
		this.maVe = maVe;
	}

	public Ve(String maVe, String moTa, double giaTien, Phim phim, Ghe ghe) {
		super();
		this.maVe = maVe;
		this.moTa = moTa;
		this.giaTien = giaTien;
		this.phim = phim;
		this.ghe = ghe;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public Phim getPhim() {
		return phim;
	}
	public void setPhim(Phim phim) {
		this.phim = phim;
	}
	public Ghe getGhe() {
		return ghe;
	}
	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}
	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", moTa=" + moTa + ", giaTien=" + giaTien + ", phim=" + phim + ", ghe=" + ghe + "]";
	}
	
	
}
