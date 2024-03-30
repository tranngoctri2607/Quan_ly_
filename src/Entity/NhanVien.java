package Entity;

public class NhanVien {
	private String MaNV;
	private String HoTen;
	private String MatKhau;
	private String Sdt;
	private boolean VaiTro;
	private boolean TrangThai;
	private String Email;

	public NhanVien() {

	}

	public NhanVien(String maNV, String hoTen, String matKhau, String sdt, boolean vaiTro, boolean trangThai,
			String email) {
		super();
		MaNV = maNV;
		HoTen = hoTen;
		MatKhau = matKhau;
		Sdt = sdt;
		VaiTro = vaiTro;
		TrangThai = trangThai;
		Email = email;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public String getSdt() {
		return Sdt;
	}

	public void setSdt(String sdt) {
		Sdt = sdt;
	}

	public boolean isVaiTro() {
		return VaiTro;
	}

	public void setVaiTro(boolean vaiTro) {
		VaiTro = vaiTro;
	}

	public boolean getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(boolean trangThai) {
		TrangThai = trangThai;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
