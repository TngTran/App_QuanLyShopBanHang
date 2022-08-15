package com.example.baocaodoan_2022;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import DAO.LOPDAO;
import MODEL.SANPHAM;

public class SanPham_Insert extends AppCompatActivity {
    Button btn_ThemSP;
    EditText editT_MaSP, editT_TenSP,editT_MoTaSP,editT_SL,editT_GiaCa;
    ImageView img_Hinh;
    ImageButton btn_Camera, btn_Folder;
    SANPHAM sp;
    int REQUEST_CODE_CAMERA= 123;
    int REQUEST_CODE_FOLDER= 456;
    LOPDAO lopdao;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham__insert);
        AnhXa();
        lopdao = new LOPDAO(SanPham_Insert.this);
        if(lopdao.CountSP(sp)<10)
        {
            a = "SP00" + (lopdao.CountSP(sp) + 1);
        }
        else
        {
            a = "SP0" + (lopdao.CountSP(sp) + 1);
        }
        btn_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);

            }
        });
        btn_Folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
        btn_ThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển data imageView-->byte[]
                BitmapDrawable bitmapDrawable=(BitmapDrawable) img_Hinh.getDrawable();
                Bitmap bitmap= bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinh= byteArrayOutputStream.toByteArray();

                //thêm sản phẩm vào database
                //a= Integer.parseInt(editT_MaSP.getText().toString().trim());
                String b= editT_TenSP.getText().toString().trim();
                String c= editT_MoTaSP.getText().toString().trim();
                Integer d=Integer.parseInt(editT_SL.getText().toString());
                Double e= Double.parseDouble(editT_GiaCa.getText().toString());
                byte[] f=hinh;
                Toast.makeText(SanPham_Insert.this,"Đã thêm",Toast.LENGTH_LONG).show();
                startActivity(new Intent(SanPham_Insert.this,DSSanPham.class));
                if(TextUtils.isEmpty(b)||TextUtils.isEmpty(c)||TextUtils.isEmpty(d.toString())||TextUtils.isEmpty(d.toString()))
                {
                    Snackbar.make(findViewById(R.id.layout_insertSP), "Không được để trống thông tin!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
                }
                else {
                    lopdao.CreateSP(a, b,c,d,e,f);
                    Toast.makeText(SanPham_Insert.this,"Đã thêm",Toast.LENGTH_LONG).show();
                    finish();
                }
//                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_CODE_CAMERA && resultCode== RESULT_OK && data!=null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_Hinh.setImageBitmap(bitmap);
        }
        if(requestCode==REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data!= null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                img_Hinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private  void AnhXa()
    {
        editT_MaSP = findViewById(R.id.ed_MaSP);
        editT_TenSP= findViewById(R.id.ed_TenSP);
        editT_MoTaSP= findViewById(R.id.ed_MoTaSP);
        editT_SL= findViewById(R.id.ed_SoLuong);
        editT_GiaCa= findViewById(R.id.ed_GiaCa);
        img_Hinh= findViewById(R.id.imgHinh);

        btn_Camera= findViewById(R.id.ibtn_Camera);
        btn_Folder= findViewById(R.id.ibtn_Folder);
        btn_ThemSP= findViewById(R.id.btn_ThemSP);

    }
}