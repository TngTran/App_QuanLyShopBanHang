package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import DAO.LOPDAO;

public class BaoCaoThongKe extends AppCompatActivity {
    TextView tv_tongDoanhThu, tv_chonngay, tv_SoLuong;
    Button btn_xemTongDT, btn_SL;
    LOPDAO dao;
    ImageButton img_calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_thong_ke);
        dao = new LOPDAO(BaoCaoThongKe.this);
        tv_chonngay = findViewById(R.id.tvChonNgay);
        tv_SoLuong = findViewById(R.id.tv_SLTonKho);
        btn_xemTongDT = findViewById(R.id.btn_XemTongDT);
        tv_tongDoanhThu = findViewById(R.id.tv_TongDoanhThu);
        img_calendar = findViewById(R.id.img_Calendar);
        btn_SL = findViewById(R.id.btn_XemSL);
        img_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        btn_xemTongDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TongDoanhThu = String.format("%.0f VND",dao.TinhTongDoanhThu(tv_chonngay.getText().toString()));
                tv_tongDoanhThu.setText("Tổng doanh thu: " + TongDoanhThu);
            }
        });
        btn_SL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_SoLuong.setText("Số lượng tồn kho: "+dao.TinhSLTonKho());
            }
        });
    }
    private void ChonNgay()
    {
        Calendar calendar = Calendar.getInstance();

        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                tv_chonngay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}