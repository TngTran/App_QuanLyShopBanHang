package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertHoaDon extends AppCompatActivity {
    EditText edt_ngaylaphhd;
    Button btn_ThemHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_hoa_don);
        edt_ngaylaphhd = findViewById(R.id.edtNgayLapHD);
        edt_ngaylaphhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        btn_ThemHD = findViewById(R.id.btn_ThemHD);
        btn_ThemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(findViewById(R.id.layout_insertHD), edt_ngaylaphhd.getText().toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
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
                edt_ngaylaphhd.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}