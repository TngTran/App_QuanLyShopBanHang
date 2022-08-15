package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import ADAPTER.KhachHangAdapter;
import DAO.LOPDAO;
import MODEL.KHACHHANG;
import pl.droidsonroids.gif.GifImageView;

public class DanhSachKhacHang extends AppCompatActivity {
    ArrayList<KHACHHANG> data = new ArrayList<>();
    LOPDAO dao;
    KhachHangAdapter adapter;
    ListView lv;
    GifImageView btn_ThemKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khac_hang);
        getSupportActionBar().setTitle("Danh sách khách hàng");
        lv = findViewById(R.id.lv_dskh);
        btn_ThemKH = findViewById(R.id.btn_ThemKH);
        btn_ThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachKhacHang.this, InsertActivity.class);
                startActivity(intent);
            }
        });
        dao = new LOPDAO(DanhSachKhacHang.this);
        data = dao.readAll();
        adapter = new KhachHangAdapter(DanhSachKhacHang.this, data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhSachKhacHang.this, ChiTietKhachHang.class);
                intent.putExtra("chitietkhachhang", data.get(i));
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
        data.addAll(dao.readAll());
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }
}