package Entity;

public class ThuongHieu {
	private String MaTH;
	private String TenTH;
	private String Email;

	public ThuongHieu() {
	}

	public ThuongHieu(String maTH, String tenTH, String email) {
		this.MaTH = maTH;
		this.TenTH = tenTH;
		this.Email = email;
	}

	public String getMaTH() {
		return MaTH;
	}

	public void setMaTH(String maTH) {
		MaTH = maTH;
	}

	public String getTenTH() {
		return TenTH;
	}

	public void setTenTH(String tenTH) {
		TenTH = tenTH;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
