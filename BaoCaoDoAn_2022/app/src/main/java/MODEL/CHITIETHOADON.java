package MODEL;

import java.io.Serializable;

public class CHITIETHOADON implements Serializable {
    String MaHD, MaSP;
    Integer SoLuong;
    Double DonGia, ThanhTien;

    public CHITIETHOADON(String maHD, String maSP, Integer soLuong, Double donGia, Double thanhTien) {
        MaHD = maHD;
        MaSP = maSP;
        SoLuong = soLuong;
        DonGia = donGia;
        ThanhTien = thanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double donGia) {
        DonGia = donGia;
    }

    public Double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        ThanhTien = thanhTien;
    }
}
