package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.Date;

import Database.MyDatabase;
import MODEL.CHITIETHOADON;
import MODEL.HOADON;
import MODEL.KHACHHANG;
import MODEL.NHANVIEN;
import MODEL.SANPHAM;
import MODEL.USER;

public class LOPDAO {
    MyDatabase mydata;
    KHACHHANG kh;
    private static final String TABLE_NHANVIEN = "NHANVIEN";
    private static final String TABLE_PRODUCT = "PRODUCT";
    private static final String SEARCH_TK = "TaiKhoanNV";
    public LOPDAO(Context context) {
        this.mydata = new MyDatabase(context);



    }
    public boolean KiemTraDN(NHANVIEN nhanvien)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from NHANVIEN where TaiKhoanNV=? and MatKhauNV=?",new String[]{nhanvien.getTkNV(), nhanvien.getMkNV()});
        if(cs.getCount()<=0)
        {
            return false;
        }
        return true;
    }
    public long DemKhachHang(KHACHHANG kh)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "KHACHHANG");
        db.close();
        return count;
    }

    public NHANVIEN TraVeNV(String tk)
    {
        String manv = "";
        String tennv = "";
        String sdtnv = "";
        String emailnv = "";
        String gtnv = "";
        String pass = "";
        SQLiteDatabase db = mydata.getReadableDatabase();
        String where = SEARCH_TK + " = ?";
        String[] whereArgs = {tk};
        Cursor cursor = db.query(TABLE_NHANVIEN, null, where, whereArgs, null, null, null);
        NHANVIEN obj = null;

        try {
            if (cursor.moveToFirst()) {
                        //now You got the entry and can get all informations You want
                manv = cursor.getString(0);
                tennv = cursor.getString(1);
                sdtnv = cursor.getString(2);
                emailnv = cursor.getString(3);
                gtnv = cursor.getString(4);
                pass = cursor.getString(6);
                obj = new NHANVIEN(manv, tennv, sdtnv, emailnv, gtnv, tk, pass);
            }
        } finally {
            cursor.close();
        }
        return obj;


    }
    public boolean SuaTTNV(String ma, String tennv, String sdt, String email, String gt)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenNV", tennv);
        values.put("SDTNV", sdt);
        values.put("EmailNV", email);
        values.put("GioiTinhNV", gt);
        int row = db.update("NHANVIEN",values, "MaNV=?", new String[]{ma});
        return row > 0;
    }
    public void DoiMKNV(String ma, String mk)
    {
        SQLiteDatabase db = mydata.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MatKhauNV", mk);
        db.update("NHANVIEN",values, "MaNV=?", new String[]{ma});
        db.close();
    }
    // Tao phuong thuc khoi tao (them)
    public boolean Create(KHACHHANG kh)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaKH", kh.getMaKH());
        values.put("TenKH", kh.getTenKH());
        values.put("SDT", kh.getSDT());
        values.put("Email", kh.getEmail());
        long row = db.insert("KHACHHANG", null, values);
        return (row > 0);
    }
    //Tao phuong thuc chinh sua
    public boolean Update(String ma, String tenkh, String sdt, String email)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenKH", tenkh);
        values.put("SDT", sdt);
        values.put("Email", email);
        int row = db.update("KHACHHANG",values, "MaKH=?", new String[]{ma});
        return row > 0;
    }
    /*public boolean Xoa(String ma, String tenkh, String sdt, String email)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenKH", tenkh);
        values.put("SDT", sdt);
        values.put("Email", email);
        int row = db.update("KHACHHANG",values, "MaKH=?", new String[]{ma});
        return row > 0;
    }*/
    //Tao phuong thuc xoa
    public boolean Delete(String ma)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        int row = db.delete("KHACHHANG", "MaKH=?", new String[]{ma});
        return row > 0;
    }
    public ArrayList<KHACHHANG> readAll()
    {
        ArrayList<KHACHHANG> data = new ArrayList<>();
        SQLiteDatabase db = mydata.getReadableDatabase();
        //Tao con tro de lay du lieu
        Cursor cs = db.rawQuery("select * from KHACHHANG", null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            String masp = cs.getString(0);
            String tensp = cs.getString(1);
            String SDT = cs.getString(2);
            String email = cs.getString(3);
            data.add(new KHACHHANG(masp, tensp, SDT, email));
            cs.moveToNext();
        }

        cs.close();
        return data;
    }
    public boolean CreateNV(NHANVIEN nv)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaNV", nv.getMaNV());
        values.put("TenNV", nv.getTenNV());
        values.put("SDTNV", nv.getSdtNV());
        values.put("EmailNV", nv.getEmailNV());
        values.put("GioiTinhNV", nv.getGtNV());
        values.put("TaiKhoanNV", nv.getTkNV());
        values.put("MatKhauNV", nv.getMkNV());
        long row = db.insert("NHANVIEN", null, values);
        return (row > 0);
    }
    public long DemNhanVien(NHANVIEN nv)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "NHANVIEN");
        db.close();
        return count;
    }
    /*public int DemKH(KHACHHANG kh)
    {
        ArrayList<KHACHHANG> data = new ArrayList<>();
        SQLiteDatabase db = mydata.getReadableDatabase();
        //Tao con tro de lay du lieu
        Cursor cs = db.rawQuery("select * from KHACHHANG", null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            cs.moveToNext();
        }

        String ktc = cs.getString(0).substring(3,2);
        int dem = Integer.parseInt(ktc);
        cs.close();
        return dem;
    }*/
    public int LayThongTinNhanVien(String tk, String pass)
    {
        ArrayList<NHANVIEN> data = new ArrayList<>();
        SQLiteDatabase db = mydata.getReadableDatabase();
        //Tao con tro de lay du lieu
        Cursor cs = db.rawQuery("select * from NHANVIEN where TaiKhoanNV=? and MatKhauNV=?",new String[]{tk, pass});
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            cs.moveToNext();
        }


        cs.close();
        return cs.getPosition();
    }
    public ArrayList<SANPHAM>readAllSP()
    {
        ArrayList<SANPHAM> data= new ArrayList<>();
        SQLiteDatabase db= mydata.getReadableDatabase();

        Cursor cs =db.rawQuery("select * from PRODUCT",null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            String masp = cs.getString(0);
            String tensp = cs.getString(1);
            String mota = cs.getString(2);
            Integer soluong = cs.getInt(3);
            Double giaca= cs.getDouble(4);
            byte[] hinh= cs.getBlob(5);
            data.add(new SANPHAM(masp, tensp, mota, soluong,giaca,hinh));
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    // Tao phuong thuc dem san pham
    public long CountSP(SANPHAM sp)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "PRODUCT");
        db.close();
        return count;
    }
    public  void CreateSP(String ma, String ten,String moTa,Integer soL,Double donG, byte[] hinh)
    {
        SQLiteDatabase database= mydata.getWritableDatabase();
        String sql="INSERT INTO PRODUCT VALUES(?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.clearBindings();
        statement.bindString(1, ma);
        statement.bindString(2, ten);
        statement.bindString(3,moTa);
        statement.bindLong(4,soL);//Interger
        statement.bindDouble(5,donG);
        statement.bindBlob(6,hinh);
        statement.executeInsert();
    }

    //tao phuong thuc update
    public  boolean UpdateSP(SANPHAM sp)
    {
        SQLiteDatabase db= mydata.getReadableDatabase();
        ContentValues values= new ContentValues();

        values.put("TenSP",sp.getTenSP());
        values.put("MoTa",sp.getMoTa());
        values.put("SoLuong",sp.getSoLuong());
        values.put("GiaCa",sp.getDonGia());
        values.put("HinhAnh",sp.getHinhAnh());

        int row= db.update("PRODUCT",values,"MaSP=?",new String[]{String.valueOf(sp.getMaSP())});
        return  row>0;
    }

    //tao phuong thuc xoa
    public  boolean DeleteSP(String maSP)
    {
        SQLiteDatabase db= mydata.getReadableDatabase();

        int row= db.delete("PRODUCT","MaSP=?",new String[]{maSP});
        return row>0;
    }
    public ArrayList<HOADON>readAllHD()
    {
        ArrayList<HOADON> data= new ArrayList<>();
        SQLiteDatabase db= mydata.getReadableDatabase();

        Cursor cs =db.rawQuery("select * from HOADON",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){

            String MaHD= cs.getString(0);
            String NgayLap=cs.getString(1);
            Double ThanhTien = cs.getDouble(2);


            data.add(new HOADON(MaHD,NgayLap,ThanhTien));
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public boolean CreateHD(HOADON hd)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaHD", hd.getMaHD());
        values.put("NgayLap", hd.getNgayLap());
        values.put("ThanhTien", hd.getThanhTien());
        long row = db.insert("HOADON", null, values);
        return (row > 0);
    }
    //Tao phuong thuc chinh sua
    public boolean Update(String ma, String NgayLap, Double ThanhTien)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("NgayLap", NgayLap);
        values.put("ThanhTien", ThanhTien);
        int row = db.update("HOADON",values, "MaHD=?", new String[]{ma});
        return row > 0;
    }
    public boolean DeleteHD(String ma)
    {
        SQLiteDatabase db = mydata.getReadableDatabase();
        int row = db.delete("HOADON", "MaHD=?", new String[]{ma});
        return row > 0;
    }
    public ArrayList<CHITIETHOADON>readAllCTHD(String ma)
    {
        ArrayList<CHITIETHOADON> data= new ArrayList<>();
        SQLiteDatabase db= mydata.getReadableDatabase();

        Cursor cs =db.rawQuery("select * from CTHOADON where MaHD=?",new String[]{ma});
        cs.moveToFirst();
        while (!cs.isAfterLast()){

            String MaHD= cs.getString(0);
            String MaSP=cs.getString(1);
            Integer SoLuong= cs.getInt(2);
            Double GiaCa= cs.getDouble(3);
            Double ThanhTien = cs.getDouble(4);

            data.add(new CHITIETHOADON(MaHD,MaSP,SoLuong,GiaCa, ThanhTien));
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public Double TinhTongTien(String ma)
    {
        ArrayList<CHITIETHOADON> data= new ArrayList<>();
        SQLiteDatabase db= mydata.getReadableDatabase();
        Double Tong = 0.0;
        Cursor cs =db.rawQuery("select * from CTHOADON where MaHD=?",new String[]{ma});
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Tong = Tong + cs.getDouble(4);
            cs.moveToNext();
        }
        cs.close();
        return Tong;
    }
    public Double TinhTongDoanhThu(String ngay)
    {
        ArrayList<HOADON> data= new ArrayList<>();
        SQLiteDatabase db= mydata.getReadableDatabase();
        Double Tong = 0.0;
        Cursor cs =db.rawQuery("select * from HOADON where NgayLap=?",new String[]{ngay});
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Tong = Tong + cs.getDouble(2);
            cs.moveToNext();
        }
        cs.close();
        return Tong;
    }
    public int TinhSLTonKho()
    {
        ArrayList<HOADON> data= new ArrayList<>();
        SQLiteDatabase db= mydata.getReadableDatabase();
        Integer Tong = 0;
        Cursor cs =db.query(TABLE_PRODUCT, null, null, null, null, null, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Tong = Tong + cs.getInt(3);
            cs.moveToNext();
        }
        cs.close();
        return Tong;
    }
}
