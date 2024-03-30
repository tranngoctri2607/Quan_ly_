package Entity;

public class LoaiSP {
    String MaLoai;
    String TenLoai;

    public LoaiSP() {
    }

    public LoaiSP(String MaLoai, String TenLoai) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return TenLoai;
    }

}
