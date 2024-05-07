package entity;

public class LoaiPhim {
	private String maLoaiPhim,tenLoaiPhim,moTa;

	
	public LoaiPhim(String maLoaiPhim) {
		this.maLoaiPhim = maLoaiPhim;
	}

	public LoaiPhim(String maLoaiPhim, String tenLoaiPhim, String moTa) {
		super();
		this.maLoaiPhim = maLoaiPhim;
		this.tenLoaiPhim = tenLoaiPhim;
		this.moTa = moTa;
	}

	public String getMaLoaiPhim() {
		return maLoaiPhim;
	}

	public void setMaLoaiPhim(String maLoaiPhim) {
		this.maLoaiPhim = maLoaiPhim;
	}

	public String getTenLoaiPhim() {
		return tenLoaiPhim;
	}

	public void setTenLoaiPhim(String tenLoaiPhim) {
		this.tenLoaiPhim = tenLoaiPhim;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public String toString() {
		return "LoaiPhim [maLoaiPhim=" + maLoaiPhim + ", tenLoaiPhim=" + tenLoaiPhim + ", moTa=" + moTa + "]";
	}
	
}
