package com.example.baocaodoan_2022;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import DAO.LOPDAO;
import MODEL.KHACHHANG;

public class InsertActivity extends AppCompatActivity {
    EditText edt_MaKH, edt_TenKH, edt_SDT, edt_Email;
    Button btn_Them;
    LOPDAO dao;
    KHACHHANG kh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        edt_MaKH = findViewById(R.id.edt_MaKHThem);
        edt_TenKH = findViewById(R.id.edt_TenKHThem);
        edt_SDT = findViewById(R.id.edt_SDTKHThem);
        edt_Email = findViewById(R.id.edt_EmailKHThem);
        btn_Them = findViewById(R.id.btn_Them);
        dao = new LOPDAO(InsertActivity.this);
        if(dao.DemKhachHang(kh)<10)
        {
            edt_MaKH.setText("KH00" + (dao.DemKhachHang(kh) + 1));
        }
        else
        {
            edt_MaKH.setText("KH0" + (dao.DemKhachHang(kh) + 1));
        }
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = edt_MaKH.getText().toString();
                String ten = edt_TenKH.getText().toString();
                String sdt = edt_SDT.getText().toString();
                String mail = edt_Email.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                KHACHHANG kh = new KHACHHANG(ma, ten, sdt, mail);
                if(TextUtils.isEmpty(ten)||TextUtils.isEmpty(sdt)||TextUtils.isEmpty(mail))
                {
                    Snackbar.make(findViewById(R.id.insertlayout), "Không được để trống thông tin!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(sdt.length()!=10 )
                {
                    Snackbar.make(findViewById(R.id.insertlayout), "Số điện thoại cần nhập đúng 10 số", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(!mail.matches(emailPattern))
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Email nhập sai định dạng!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else {
                    if (dao.Create(kh)) {

                        finish();
                    } else {
                        Snackbar.make(findViewById(R.id.insertlayout), "Thêm không thành công", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                    }
                }
            }
        });

    }
}