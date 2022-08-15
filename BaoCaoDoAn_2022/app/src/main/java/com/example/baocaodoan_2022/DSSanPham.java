package com.example.baocaodoan_2022;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ADAPTER.SanPhamAdapter;
import DAO.LOPDAO;

import MODEL.SANPHAM;
import pl.droidsonroids.gif.GifImageView;

public class DSSanPham extends AppCompatActivity {
    ArrayList<SANPHAM> data = new ArrayList<>();
    LOPDAO dao;
    SanPhamAdapter sanPhamAdapter;
    ListView lv_sp;
    GifImageView btn_ThemSP;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_s_san_pham);
        lv_sp= findViewById(R.id.lv_dsSP);
        btn_ThemSP= findViewById(R.id.btn_ThemSP);
        getSupportActionBar().setTitle("Danh sách sản phẩm");
        btn_ThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DSSanPham.this,SanPham_Insert.class);
                startActivity(intent);
            }
        });
        //hiển thị data
        dao= new LOPDAO(DSSanPham.this);
        data= dao.readAllSP();

        sanPhamAdapter= new SanPhamAdapter(DSSanPham.this,data);
        lv_sp.setAdapter(sanPhamAdapter);
        lv_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DSSanPham.this,SanPham_Detail.class);
                Bitmap bitmap= BitmapFactory.decodeByteArray(data.get(i).getHinhAnh(),0,data.get(i).getHinhAnh().length);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                byte[] byteArray = bytes.toByteArray();
                /*bundle.putString("maspdetail", data.get(i).getMaSP());
                bundle.putString("tenspdetail", data.get(i).getTenSP());
                bundle.putString("motaspdetail", data.get(i).getMoTa());
                bundle.putInt("slspdetail", data.get(i).getSoLuong());
                bundle.putDouble("dgspdetail", data.get(i).getDonGia());
                bundle.putByteArray("maspdetail", data.get(i).getHinhAnh());
                intent.putExtra("dulieudetail", bundle);*/
                intent.putExtra("maspdetail", data.get(i).getMaSP());
                intent.putExtra("tenspdetail", data.get(i).getTenSP());
                intent.putExtra("motadetail", data.get(i).getMoTa());
                intent.putExtra("sldetail", data.get(i).getSoLuong());
                intent.putExtra("dgdetail", data.get(i).getDonGia());
                intent.putExtra("hinhanhdetail",byteArray);
                /*intent.putExtra("hinhanhdetail", data.get(i).getHinhAnh());*/
                /*Bundle extras = intent.getExtras();
                extras.putByteArray("hinhanhdetail", data.get(i).getHinhAnh());*/
                /*Bitmap bmp= BitmapFactory.decodeByteArray(data.get(i).getHinhAnh(),0,data.get(i).getHinhAnh().length);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("hinhanhdetail", byteArray);*/
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
                sanPhamAdapter.filter(newText.trim());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        data.addAll(dao.readAllSP());
        sanPhamAdapter.notifyDataSetChanged();
        lv_sp.setAdapter(sanPhamAdapter);
    }
}