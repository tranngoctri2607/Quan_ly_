package Entity;

public class NhaCungCap {
 private String MaNCC;
 private String TenNCC;
 private String SDT;
 private String DiaChi;
 	public NhaCungCap() {

 	}
public NhaCungCap(String maNCC, String tenNCC, String sDT, String diaChi) {
	super();
	MaNCC = maNCC;
	TenNCC = tenNCC;
	SDT = sDT;
	DiaChi = diaChi;
}
public String getMaNCC() {
	return MaNCC;
}
public void setMaNCC(String maNCC) {
	MaNCC = maNCC;
}
public String getTenNCC() {
	return TenNCC;
}
public void setTenNCC(String tenNCC) {
	TenNCC = tenNCC;
}
public String getSDT() {
	return SDT;
}
public void setSDT(String sDT) {
	SDT = sDT;
}
public String getDiaChi() {
	return DiaChi;
}
public void setDiaChi(String diaChi) {
	DiaChi = diaChi;
}

}
