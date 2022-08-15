package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ADAPTER.ChiTietHoaDonAdapter;
import ADAPTER.HoaDonAdapter;
import DAO.LOPDAO;
import MODEL.CHITIETHOADON;
import MODEL.HOADON;
import MODEL.KHACHHANG;

public class DanhSachCTHoaDon extends AppCompatActivity {
    ArrayList<CHITIETHOADON> data = new ArrayList<>();
    LOPDAO dao;
    ChiTietHoaDonAdapter adapter;
    ListView lv;
    TextView tv_TongTien, tv_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cthoa_don);
        lv = findViewById(R.id.lv_dscthd);
        dao = new LOPDAO(DanhSachCTHoaDon.this);
        tv_TongTien = findViewById(R.id.tv_TongTien);
        tv_close = findViewById(R.id.tv_closecthd);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        HOADON hd = (HOADON) intent.getSerializableExtra("chitiethoadon");
        data = dao.readAllCTHD(hd.getMaHD());
        adapter = new ChiTietHoaDonAdapter(DanhSachCTHoaDon.this, data);
        lv.setAdapter(adapter);
        tv_TongTien.setText(String.format("%.0f VND",dao.TinhTongTien(hd.getMaHD())));
    }
    protected void onResume()
    {
        super.onResume();
        Intent intent = getIntent();
        HOADON hd = (HOADON) intent.getSerializableExtra("chitiethoadon");
        data.clear();
        data.addAll(dao.readAllCTHD(hd.getMaHD()));
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }
}