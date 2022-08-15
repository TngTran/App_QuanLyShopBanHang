package MODEL;

import java.io.Serializable;

public class KHACHHANG implements Serializable {
    String MaKH;
    String TenKH;
    String SDT;
    String Email;

    public KHACHHANG(String maKH, String tenKH, String SDT, String email) {
        MaKH = maKH;
        TenKH = tenKH;
        this.SDT = SDT;
        Email = email;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
