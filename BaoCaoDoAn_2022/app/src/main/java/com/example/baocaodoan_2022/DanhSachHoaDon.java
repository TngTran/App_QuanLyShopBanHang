package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ADAPTER.ChiTietHoaDonAdapter;
import ADAPTER.HoaDonAdapter;
import ADAPTER.KhachHangAdapter;
import DAO.LOPDAO;
import MODEL.CHITIETHOADON;
import MODEL.HOADON;
import MODEL.KHACHHANG;
import pl.droidsonroids.gif.GifImageView;

public class DanhSachHoaDon extends AppCompatActivity {
    ArrayList<HOADON> data = new ArrayList<>();
    LOPDAO dao;
    HoaDonAdapter adapter;
    ListView lv1;
    GifImageView btn_ThemHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoa_don);
        getSupportActionBar().setTitle("Danh sách hóa đơn");
        lv1 = findViewById(R.id.lv_dshd);
        btn_ThemHD = findViewById(R.id.btn_ThemHD);
        btn_ThemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachHoaDon.this, InsertHoaDon.class);
                startActivity(intent);
            }
        });
        dao = new LOPDAO(DanhSachHoaDon.this);
        data = dao.readAllHD();
        adapter = new HoaDonAdapter(DanhSachHoaDon.this, data);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhSachHoaDon.this, DanhSachCTHoaDon.class);
                intent.putExtra("chitiethoadon", data.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.menu_Search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText.trim());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    protected void onResume()
    {
        super.onResume();
        data.clear();
        data.addAll(dao.readAllHD());
        adapter.notifyDataSetChanged();
        lv1.setAdapter(adapter);
    }

}