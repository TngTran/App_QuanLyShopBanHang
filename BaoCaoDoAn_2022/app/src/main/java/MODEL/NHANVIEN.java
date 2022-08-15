package MODEL;

import java.io.Serializable;

public class NHANVIEN implements Serializable {
    String maNV;
    String tenNV;
    String sdtNV;
    String emailNV;
    String gtNV;
    String tkNV;
    String mkNV;
    public NHANVIEN(String maNV, String tenNV, String sdtNV, String emailNV, String gtNV, String tkNV, String mkNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdtNV = sdtNV;
        this.emailNV = emailNV;
        this.gtNV = gtNV;
        this.tkNV = tkNV;
        this.mkNV = mkNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    public String getGtNV() {
        return gtNV;
    }

    public void setGtNV(String gtNV) {
        this.gtNV = gtNV;
    }

    public String getTkNV() {
        return tkNV;
    }

    public void setTkNV(String tkNV) {
        this.tkNV = tkNV;
    }

    public String getMkNV() {
        return mkNV;
    }

    public void setMkNV(String mkNV) {
        this.mkNV = mkNV;
    }
}
