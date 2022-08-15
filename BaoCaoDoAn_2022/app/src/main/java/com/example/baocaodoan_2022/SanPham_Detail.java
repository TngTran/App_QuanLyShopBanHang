package com.example.baocaodoan_2022;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import DAO.LOPDAO;
import MODEL.SANPHAM;

public class SanPham_Detail extends AppCompatActivity {
    TextView tvMaSP,tvTenSP,tvMoTaSP,tvSoLuong,tvGiaCa;
    ImageView img_HinhDetail;
    LOPDAO lopdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham__detail);
        AnhXa();

        lopdao = new LOPDAO(SanPham_Detail.this);
        Intent intent = getIntent();
        String ma = intent.getStringExtra("maspdetail");
        String ten = intent.getStringExtra("tenspdetail");
        String mota = intent.getStringExtra("motadetail");
        String sl = String.valueOf(intent.getIntExtra("sldetail", 0));
        Double dg = intent.getDoubleExtra("dgdetail", 0);
        byte[] HinhAnh= intent.getByteArrayExtra("hinhanhdetail");
        // chuyển kiểu byte[]-> bitmap
        tvMaSP.setText(ma);
        tvTenSP.setText(ten);
        tvMoTaSP.setText("- " + mota);
        tvSoLuong.setText(sl);
        tvGiaCa.setText(String.format("%.0f VND",dg));
        /*tvMoTaSP.setText(mota);
        tvSoLuong.setText(sl);
        tvGiaCa.setText(dg);*/
        //chuyen byte[] -> bitmap

        Bitmap bitmap= BitmapFactory.decodeByteArray(HinhAnh,0,HinhAnh.length);
        img_HinhDetail.setImageBitmap(bitmap);
        /*Bitmap bitmap = (Bitmap) intent.getParcelableExtra("hinhanhdetail");
        img_HinhDetail.setImageBitmap(bitmap);*/

    }
    private  void AnhXa()
    {
        img_HinhDetail= findViewById(R.id.img_Hinh_Detail);
        tvMaSP= findViewById(R.id.textView_MaSP_Detail);
        tvTenSP= findViewById(R.id.textView_TenSP_Detail);
        tvMoTaSP=findViewById(R.id.textView_MoTaSP_Detail);
        tvSoLuong= findViewById(R.id.textView_SoLuong_Detail);
        tvGiaCa= findViewById(R.id.textView_GiaCa_Detail);
    }
}