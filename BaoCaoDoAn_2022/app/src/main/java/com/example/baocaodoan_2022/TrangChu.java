package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ADAPTER.ChucNangTKAdapter;
import MODEL.CHUCNANG;
import pl.droidsonroids.gif.GifImageView;

public class TrangChu extends AppCompatActivity {
    Dialog myDialog;
    TextView tenkh;
    ListView lsttk;
    ArrayList<CHUCNANG> chucNangTKS;
    ChucNangTKAdapter kadapter;
    GifImageView imgGif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        lsttk = findViewById(R.id.lsttaikhoan);
        chucNangTKS = new ArrayList<CHUCNANG>();
        imgGif = findViewById(R.id.imgGif);
        imgGif.bringToFront();
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_gif);
        imgGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim);
            }
        });
        myDialog = new Dialog(this);
        tenkh = findViewById(R.id.tktennv);
        Bundle bundle = getIntent().getBundleExtra("dulieu");
        String name = bundle.getString("ten nhan vien");
        tenkh.setText(name);
        chucNangTKS.add(new CHUCNANG(R.drawable.quanlikh2,"Quản lí khách hàng"));
        chucNangTKS.add(new CHUCNANG(R.drawable.quanlisp,"Quản lí sản phẩm"));
        chucNangTKS.add(new CHUCNANG(R.drawable.qlhoadon,"Quản lí hóa đơn"));
        chucNangTKS.add(new CHUCNANG(R.drawable.ic_baocaothongke,"Báo cáo thống kê"));
        chucNangTKS.add(new CHUCNANG(R.drawable.logout,"Đăng xuất"));
        kadapter = new ChucNangTKAdapter(TrangChu.this,R.layout.motchucnang,chucNangTKS);
        lsttk.setAdapter(kadapter);
        lsttk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0: {
                        Intent intent = new Intent(TrangChu.this, DanhSachKhacHang.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(TrangChu.this, DSSanPham.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(TrangChu.this, DanhSachHoaDon.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                        break;
                    }
                    case 3: {
                        Intent intent = new Intent(TrangChu.this, BaoCaoThongKe.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                        break;
                    }
                    case 4: {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TrangChu.this);
                        builder.setTitle("Xác nhận");
                        builder.setMessage("Bạn có muốn thoát?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(TrangChu.this, "Đăng xuất thành công!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(TrangChu.this, DangNhap.class);
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
                        break;
                    }
                }
            }
        });
    }
    public void showPopup(View view)
    {
        TextView tv_close, tv_TenNV, tv_SDT, tv_Email, tv_GT;
        Button btn_DoiMk, btn_DoiTT;
        myDialog.setContentView(R.layout.custompopup);
        tv_close = myDialog.findViewById(R.id.tv_close);
        btn_DoiMk = myDialog.findViewById(R.id.btn_DoiMK);
        btn_DoiTT = myDialog.findViewById(R.id.btn_DoiThongTin);
        tv_TenNV = myDialog.findViewById(R.id.menutennv);
        tv_SDT = myDialog.findViewById(R.id.menuSDT);
        tv_Email = myDialog.findViewById(R.id.menuemailnv);
        tv_GT = myDialog.findViewById(R.id.menugtnv);
        Bundle bundle = getIntent().getBundleExtra("dulieu");
        String manv = bundle.getString("ma nhan vien");
        String tennv = bundle.getString("ten nhan vien");
        String sdt = bundle.getString("sdt nhan vien");
        String email = bundle.getString("email nhan vien");
        String gt = bundle.getString("gioi tinh nhan vien");
        String tk = bundle.getString("tai khoan nhan vien");
        String mk = bundle.getString("mat khau nhan vien");
        tv_TenNV.setText(tennv);
        tv_SDT.setText(sdt);
        tv_Email.setText(email);
        tv_GT.setText(gt);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        btn_DoiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, DoiMatKhau.class);
                Bundle bundle = new Bundle();
                bundle.putString("manhanviendoimk", manv);
                bundle.putString("matkhaunhanviendoimk", mk);
                intent.putExtra("doimknhanvien", bundle);
                startActivity(intent);
            }
        });
        btn_DoiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, DoiTTNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putString("manhanviendoitt", manv);
                bundle.putString("tennhanviendoitt", tennv);
                bundle.putString("sdtnhanviendoitt", sdt);
                bundle.putString("emailnhanviendoitt", email);
                bundle.putString("gtnhanviendoitt", gt);
                bundle.putString("tknhanviendoitt", tk);
                bundle.putString("mknhanviendoitt", mk);
                intent.putExtra("doittnhanvien", bundle);
                startActivity(intent);
            }
        });
        myDialog.show();
    }
}