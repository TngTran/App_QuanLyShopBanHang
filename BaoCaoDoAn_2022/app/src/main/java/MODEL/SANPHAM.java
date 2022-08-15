package MODEL;

import java.io.Serializable;

public class SANPHAM implements Serializable {
    private String maSP;
    private String tenSP;
    private String moTa;
    private Integer soLuong;
    private Double donGia;
    private byte[] hinhAnh;


    public SANPHAM(String maSP, String tenSP, String moTa, Integer soLuong, Double donGia,byte[] hinh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa= moTa;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.hinhAnh= hinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {return donGia;}

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
