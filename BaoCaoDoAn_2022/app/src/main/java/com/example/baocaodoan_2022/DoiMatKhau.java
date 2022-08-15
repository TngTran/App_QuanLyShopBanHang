package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import DAO.LOPDAO;
import MODEL.KHACHHANG;
import MODEL.NHANVIEN;

public class DoiMatKhau extends AppCompatActivity {
    EditText edt_mkc, edt_mkm, edt_xnmk;
    Button btn_doimk;
    LOPDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edt_mkc = findViewById(R.id.edtMKC);
        edt_mkm = findViewById(R.id.edtMKMdk);
        edt_xnmk = findViewById(R.id.edtXNMKM);
        btn_doimk = findViewById(R.id.btnDoiMK);

        Bundle bundle = getIntent().getBundleExtra("doimknhanvien");
        String manv = bundle.getString("manhanviendoimk");
        String mknv = bundle.getString("matkhaunhanviendoimk");
        btn_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mkc = edt_mkc.getText().toString();
                String mkm = edt_mkm.getText().toString();
                String xnmk = edt_xnmk.getText().toString();
                if(TextUtils.isEmpty(mkc)||TextUtils.isEmpty(mkm)||TextUtils.isEmpty(xnmk))
                {
                    Snackbar.make(findViewById(R.id.layout_doimk), "Thông tin không được để trống!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(!mkm.equals(xnmk))
                {
                    Snackbar.make(findViewById(R.id.layout_doimk), "Mật khẩu xác nhận không trùng khớp!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else
                {
                    if(mkc.equals(mknv))
                    {

                            AlertDialog.Builder builder = new AlertDialog.Builder(DoiMatKhau.this);
                            builder.setTitle("Xác nhận");
                            builder.setMessage("Bạn có muốn đổi mật khẩu không");
                            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dao.DoiMKNV(manv, mkm);
                                    Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thành công!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(DoiMatKhau.this, DangNhap.class);
                                    startActivity(intent);

                                }
                            });
                            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.cancel();
                                }
                            });
                            AlertDialog a=builder.create();
                            a.show();
                    }
                    else
                    {
                        Snackbar.make(findViewById(R.id.layout_doimk), "Mật khẩu hiện tại không chính xác!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                    }
                }
            }
        });

    }
}