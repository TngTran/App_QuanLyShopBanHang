package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(@Nullable Context context)
    {
        super(context,"Android", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE KHACHHANG(MaKH Text primary key not null, TenKH Text, SDT Text, Email Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH001','Nguyễn Văn A', '0988804317', 'bennumber186@gmail.com')");
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH002','Nguyễn Văn B', '0906983430', 'cobekhocnhe@gmail.com')");
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH003','Nguyễn Văn C', '0903495392', 'tuilahaune@gmail.com')");
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH004','Nguyễn Văn D', '0324239583', 'imhau271@gmail.com')");
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH005','Nguyễn Văn E', '0944583201', 'tuilahau@gmail.com')");
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH006','Nguyễn Văn F', '0231498274', 'haulatui@gmail.com')");
        db.execSQL("INSERT INTO KHACHHANG VALUES ('KH007','Nguyễn Văn H', '0234239583', 'latuihau@gmail.com')");
        sql = "CREATE TABLE USER(Username Text primary key not null, Password Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO USER VALUES('HauNguyen','HauNguyen271')");
        db.execSQL("INSERT INTO USER VALUES('tung','tung123')");
        sql = "CREATE TABLE NHANVIEN(MaNV Text primary key not null, TenNV Text, SDTNV Text, EmailNV Text, GioiTinhNV Text, TaiKhoanNV Text, MatKhauNV Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO NHANVIEN VALUES ('NV001','Nguyễn Lê Phước Hậu', '0988804317', 'bennumber186@gmail.com', 'Nam', 'HauNguyen', 'HauNguyen271')");
        db.execSQL("INSERT INTO NHANVIEN VALUES ('NV002','Ngô Hồng Phúc', '0908204317', 'phucngo@gmail.com', 'Nam', 'admin', '123456')");
        db.execSQL("INSERT INTO NHANVIEN VALUES ('NV003','Lê Nguyễn Phương Khánh', '0234432123', 'cobedangiu1010@gmail.com', 'Nữ', 'PhuongKhanh', 'PhuongKhanh1010')");
        sql="CREATE TABLE PRODUCT(MaSP  String primary key not null, TenSP Text,MoTa Text, SoLuong Integer, GiaCa Real,HinhAnh BLOB)";
        db.execSQL(sql);
        sql="CREATE TABLE HOADON(MaHD Text primary key not null, NgayLap Text, ThanhTien Double)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO HOADON VALUES('HD01','27-01-2020', 1100000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD02','27-03-2021', 300000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD03','17-02-2022', 500000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD04','17-03-2022', 650000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD05','25-04-2022', 100000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD06','1-05-2022', 350000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD07','14-06-2022', 200000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD08','17-06-2022', 150000)");
        db.execSQL("INSERT INTO HOADON VALUES('HD09','18-06-2022', 750000)");
        sql="CREATE TABLE CTHOADON(MaHD Text , MaSP Text , SoLuong int, DonGia Double, ThanhTien Double)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO CTHOADON VALUES('HD01','J001', 1, 200000, 200000)");
        db.execSQL("INSERT INTO CTHOADON VALUES('HD01','S002', 2, 450000, 900000)");
        db.execSQL("INSERT INTO CTHOADON VALUES('HD02','J002',1, 300000, 300000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS KHACHANG");
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
        db.execSQL("DROP TABLE IF EXISTS PRODUCT");
        db.execSQL("DROP TABLE IF EXISTS HOADON");
        db.execSQL("DROP TABLE IF EXISTS CTHOADON");
    }
}
