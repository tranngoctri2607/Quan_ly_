package Entity;

public class KhuyenMai {
	String MaKhuyenMai;
    String TenKhuyenMai;
    String NgayBatDau;
    String NgayKetThuc;

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKhuyenMai, String TenKhuyenMai, String NgayBatDau, String NgayKetThuc) {
        this.MaKhuyenMai = MaKhuyenMai;
        this.TenKhuyenMai = TenKhuyenMai;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }

    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(String MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return TenKhuyenMai;
    }

    public void setTenKhuyenMai(String TenKhuyenMai) {
        this.TenKhuyenMai = TenKhuyenMai;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }
}
