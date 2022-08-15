package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import DAO.LOPDAO;
import MODEL.NHANVIEN;

public class DangKy extends AppCompatActivity {
    TextView tv_haveAccount;
    Button btnDangKy;
    EditText edtTenNV, edtSDT, edtEmail, edtUN, edtPW, edtRPW;
    RadioGroup rdoGioiTinh;
    String gt, makh;
    LOPDAO dao;
    NHANVIEN nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
        dao = new LOPDAO(DangKy.this);
        if(dao.DemNhanVien(nv)<10)
        {
            makh = "KH00" + (dao.DemNhanVien(nv) + 1);
        }
        else
        {
            makh = "KH0" + (dao.DemNhanVien(nv) + 1);
        }
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTenNV.getText().toString();
                String sdt = edtSDT.getText().toString();
                String email = edtEmail.getText().toString();
                String un = edtUN.getText().toString();
                String pw = edtPW.getText().toString();
                String rpw = edtRPW.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                rdoGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i)
                        {
                            case R.id.rdo_Nam: {
                                gt = "Male";
                                break;
                            }
                            case R.id.rdo_Nu: {
                                gt = "Female";
                                break;
                            }

                        }
                    }
                });
                NHANVIEN nv = new NHANVIEN(makh, ten, sdt, email, gt, un, pw);
                if(TextUtils.isEmpty(ten)||TextUtils.isEmpty(sdt)||TextUtils.isEmpty(email)||TextUtils.isEmpty(un)||TextUtils.isEmpty(pw)||TextUtils.isEmpty(rpw)||gt==null)
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Thông tin không được để trống!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(sdt.length()!=10)
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Số điện thoại cần nhập đúng 10 số!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(pw.length()<6||pw.length()>18)
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Mật khẩu chứa 8 - 16 kí tự", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(!pw.equals(rpw))
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Mật khẩu xác nhận không trùng khớp!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(!email.matches(emailPattern))
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Email nhập sai định dạng!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else
                {
                    if (dao.CreateNV(nv)) {
                        Intent i = new Intent();
                        i.putExtra("username", un);
                        setResult(RESULT_OK, i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(R.id.layout_dangky), "Đăng ký không thành công", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                    }
                }
            }
        });
        tv_haveAccount = findViewById(R.id.tvHaveAccount);
        tv_haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangKy.this, DangNhap.class);
                startActivity(i);
            }
        });
    }
    protected void AnhXa()
    {
        edtTenNV = findViewById(R.id.edtTenNVdk);
        edtSDT = findViewById(R.id.edtSDTdk);
        edtEmail = findViewById(R.id.edtEmaildk);
        edtUN = findViewById(R.id.edtAccountdk);
        edtPW = findViewById(R.id.edtPassworddk);
        edtRPW = findViewById(R.id.edtPasswordXNdk);
        rdoGioiTinh = findViewById(R.id.rdoGT);
        btnDangKy = findViewById(R.id.btnDangKy);
    }
}