package com.example.baocaodoan_2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DAO.LOPDAO;
import MODEL.KHACHHANG;


public class UpdateActivity extends AppCompatActivity {
    EditText edt_maKH, edt_tenKH, edt_SDT, edt_Email;
    Button btn_sua;
    LOPDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edt_maKH = findViewById(R.id.edt_MaKHUD);
        edt_tenKH = findViewById(R.id.edt_TenKHUD);
        edt_SDT = findViewById(R.id.edt_SDTKHUD);
        edt_Email = findViewById(R.id.edt_EmailKHUD);
        btn_sua = findViewById(R.id.btn_Sua);
        dao = new LOPDAO(UpdateActivity.this);
        Intent intent = getIntent();
        KHACHHANG kh = (KHACHHANG) intent.getSerializableExtra("khachhang");
        edt_maKH.setText(kh.getMaKH());
        edt_tenKH.setText(kh.getTenKH());
        edt_SDT.setText(kh.getSDT());
        edt_Email.setText(kh.getEmail());
        //Gan su kien sua
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = edt_maKH.getText().toString();
                String ten = edt_tenKH.getText().toString();
                String sdt = edt_SDT.getText().toString();
                String email = edt_Email.getText().toString();
                if(dao.Update(ma, ten, sdt, email))
                {
                    Toast.makeText(UpdateActivity.this, "Sua thanh cong", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(UpdateActivity.this, "Sua khong thanh cong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}