package com.example.baocaodoan_2022;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import DAO.LOPDAO;
import MODEL.NHANVIEN;
import MODEL.USER;

public class DangNhap extends AppCompatActivity {
    EditText edtuser,edtpassword;
    Button btndangnhap,btnthoat;
    TextView tvdangky;
    String makh, tenkh, sdt, email, gt;
    LOPDAO dao;
    public static NHANVIEN Nhanvien = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        dao = new LOPDAO(DangNhap.this);
        Anhxa();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un= edtuser.getText().toString();
                String pw = edtpassword.getText().toString();
                NHANVIEN nv = new NHANVIEN(makh, tenkh, sdt, email, gt, un, pw);
                if(dao.KiemTraDN(nv))
                {
                    Nhanvien = dao.TraVeNV(un);
                    Intent intent = new Intent(DangNhap.this, TrangChu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma nhan vien", Nhanvien.getMaNV());
                    bundle.putString("ten nhan vien", Nhanvien.getTenNV());
                    bundle.putString("email nhan vien", Nhanvien.getEmailNV());
                    bundle.putString("sdt nhan vien", Nhanvien.getSdtNV());
                    bundle.putString("gioi tinh nhan vien", Nhanvien.getGtNV());
                    bundle.putString("tai khoan nhan vien", Nhanvien.getTkNV());
                    bundle.putString("mat khau nhan vien", Nhanvien.getMkNV());
                    intent.putExtra("dulieu", bundle);
                    startActivity(intent);

                }
                else
                {
                    Snackbar.make(findViewById(R.id.layout_dangnhap), "Nhập sai gòi bạn ơi", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
            }
        });
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivityForResult(intent, 999);
            }
        });
    }
    private void Anhxa()
    {
        edtuser=(EditText) findViewById(R.id.edittextuser);
        edtpassword=(EditText) findViewById(R.id.edittextpassword);
        btndangnhap=(Button) findViewById(R.id.buttondangnhap);
        tvdangky=(TextView) findViewById(R.id.tv_dangky);
    }
//    private void LuuTT(String un, String pw, boolean check)
//    {
//        SharedPreferences pref = getSharedPreferences("thongtin.dat", MODE_PRIVATE);
//        SharedPreferences.Editor editor= pref.edit();
//        if(check)
//        {
//            editor.putString("username", un);
//            editor.putString("password", pw);
//            editor.putBoolean("check", check);
//        }
//        else
//        {
//            editor.clear();
//        }
//        editor.commit();
//    }
//    private void loadData()
//    {
//        SharedPreferences pref = getSharedPreferences("thongtin.dat", MODE_PRIVATE);
//        boolean check = pref.getBoolean("check", false);
//        if(check)
//        {
//            edtuser.setText(pref.getString("username", ""));
//            edtpassword.setText(pref.getString("password", ""));
//            chkSave.setChecked(check);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999 && resultCode==RESULT_OK)
        {
            String un = data.getStringExtra("username");
            edtuser.setText(un);
        }
    }
}