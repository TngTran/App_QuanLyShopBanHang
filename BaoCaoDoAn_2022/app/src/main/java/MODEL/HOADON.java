package MODEL;

import java.io.Serializable;
import java.util.Date;

public class HOADON implements Serializable {
    String MaHD;
    String NgayLap;
    Double ThanhTien;

    public HOADON(String maHD, String ngayLap, Double thanhTien) {
        MaHD = maHD;
        NgayLap = ngayLap;
        ThanhTien = thanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public Double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        ThanhTien = thanhTien;
    }
}
