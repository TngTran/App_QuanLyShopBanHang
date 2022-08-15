package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import DAO.LOPDAO;
import MODEL.KHACHHANG;

public class ChiTietKhachHang extends AppCompatActivity {
    TextView tv_maKH, tv_tenKH, tv_SDT, tv_Email;
    LOPDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khach_hang);
        tv_maKH = findViewById(R.id.tv_CTMaKH);
        tv_tenKH = findViewById(R.id.tv_CTTenKH);
        tv_SDT = findViewById(R.id.tv_CTSDT);
        tv_Email = findViewById(R.id.tv_CTEmail);
        dao = new LOPDAO(ChiTietKhachHang.this);
        Intent intent = getIntent();
        KHACHHANG kh = (KHACHHANG) intent.getSerializableExtra("chitietkhachhang");
        tv_maKH.setText(kh.getMaKH());
        tv_tenKH.setText(kh.getTenKH());
        tv_SDT.setText(kh.getSDT());
        tv_Email.setText(kh.getEmail());
    }
}