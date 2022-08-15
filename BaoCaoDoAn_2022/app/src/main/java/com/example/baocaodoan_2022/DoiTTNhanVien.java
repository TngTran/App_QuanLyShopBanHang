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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import DAO.LOPDAO;

public class DoiTTNhanVien extends AppCompatActivity {
    EditText edt_TenNV, edt_SDT, edt_Email;
    Button btn_doitt;
    LOPDAO dao;
    RadioGroup rdoGioiTinh;
    String gt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_ttnhan_vien);
        AnhXa();
        Bundle bundle = getIntent().getBundleExtra("doittnhanvien");
        String manv = bundle.getString("manhanviendoitt");
        String tk = bundle.getString("tknhanviendoitt");
        String mk = bundle.getString("mknhanviendoitt");
        edt_TenNV.setText(bundle.getString("tennhanviendoitt"));
        edt_SDT.setText(bundle.getString("sdtnhanviendoitt"));
        edt_Email.setText(bundle.getString("emailnhanviendoitt"));
        btn_doitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edt_TenNV.getText().toString();
                String SDT = edt_SDT.getText().toString();
                String email = edt_Email.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                rdoGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i)
                        {
                            case R.id.rdo_NamDTT: {
                                gt = "Male";
                                break;
                            }
                            case R.id.rdo_NuDTT: {
                                gt = "Female";
                                break;
                            }

                        }
                    }
                });
                if(TextUtils.isEmpty(ten)||TextUtils.isEmpty(SDT)||TextUtils.isEmpty(email))
                {
                    Snackbar.make(findViewById(R.id.layout_doitt), manv.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(SDT.length()!=10)
                {
                    Snackbar.make(findViewById(R.id.layout_doitt), manv + ten + SDT + email + gt, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else if(!email.matches(emailPattern))
                {
                    Snackbar.make(findViewById(R.id.layout_dangky), "Email nhập sai định dạng!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else
                {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DoiTTNhanVien.this);
                        builder.setTitle("Xác nhận");
                        builder.setMessage("Bạn có muốn đổi thông tin không");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dao.SuaTTNV(manv, ten, SDT, email, gt))
                                {
                                    Toast.makeText(DoiTTNhanVien.this, "Đổi thông tin thành công!", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Snackbar.make(findViewById(R.id.layout_doitt), "Đổi thông tin không thành công!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                                }



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

                }

        });
    }
    protected void AnhXa()
    {
        edt_TenNV = findViewById(R.id.edtTenNVdtt);
        edt_SDT = findViewById(R.id.edtSDTdtt);
        edt_Email = findViewById(R.id.edtEmaildtt);
        rdoGioiTinh = findViewById(R.id.rdoGTDTT);
        btn_doitt = findViewById(R.id.btnDoiTT);
    }
}