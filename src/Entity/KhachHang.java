package Entity;

public class KhachHang {

	private String Sdt;
	private String HoTen;
	private String DiaChi;
	private String Email;
	public KhachHang(String sdt, String hoTen, String diaChi, String email) {
		super();
		Sdt = sdt;
		HoTen = hoTen;
		DiaChi = diaChi;
		Email = email;
	}
	public String getSdt() {
		return Sdt;
	}
	public void setSdt(String sdt) {
		Sdt = sdt;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	public KhachHang() {

	}

}
