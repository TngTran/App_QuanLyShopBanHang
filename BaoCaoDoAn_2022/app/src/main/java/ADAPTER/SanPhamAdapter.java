package ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocaodoan_2022.R;
import com.example.baocaodoan_2022.SanPham_Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import DAO.LOPDAO;
import MODEL.KHACHHANG;
import MODEL.SANPHAM;

import static java.lang.String.*;

public class SanPhamAdapter extends BaseAdapter {
    Context context;
    List<SANPHAM> data;
    ArrayList<SANPHAM> listSP;
    LOPDAO dao;

    public SanPhamAdapter(Context context, ArrayList<SANPHAM> data) {
        this.context = context;
        this.data = data;
        this.listSP= new ArrayList<SANPHAM>();
        this.listSP.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup viewGroup) {
        ViewHolder holder;//class
        if(ConvertView ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater= ((Activity)context).getLayoutInflater();
            ConvertView= inflater.inflate(R.layout.sanpham_item,null);
            holder.img_HinhAnh= ConvertView.findViewById(R.id.img_HinhSPCustom);
            holder.textView_MaSP= ConvertView.findViewById((R.id.textView_MaSPCustom));
            holder.textView_TenSP= ConvertView.findViewById((R.id.textView_TenSPCustom));
            holder.textView_GiaCa = ConvertView.findViewById(R.id.textView_GiaSPCustom);
            holder.iv_edit=ConvertView.findViewById((R.id.imgView_Edit));
            holder.iv_delete=ConvertView.findViewById((R.id.imgView_Delete));
            ConvertView.setTag(holder);
        }
        else {
            holder=(ViewHolder) ConvertView.getTag();
        }
        holder.iv_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, SanPham_Update.class);
                intent.putExtra("PRODUCT",data.get(position));//i? position:i
                ((Activity)context).startActivity(intent);
            }
        });
        //Gan su kien cho nut xoa
        holder.iv_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                dao= new LOPDAO(context);
                if(dao.DeleteSP(data.get(position).getMaSP())){
                    Toast.makeText(context,"Xóa Thành Công",Toast.LENGTH_LONG).show();
                    data.clear();
                    // Cho cập nhật lại danh sách
                    data.addAll(dao.readAllSP());
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context,"Xóa không thành công",Toast.LENGTH_LONG).show();
                }

            }
        });
        //hiển thị dữ liệu
        SANPHAM sanpham= data.get(position);
        holder.textView_MaSP.setText(data.get(position).getMaSP());
        holder.textView_TenSP.setText(data.get(position).getTenSP());
        holder.textView_GiaCa.setText(String.format("%.0f VND",data.get(position).getDonGia()));
        //chuyen byte[] -> bitmap
        byte[] HinhAnh=  sanpham.getHinhAnh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(HinhAnh,0,HinhAnh.length);
        holder.img_HinhAnh.setImageBitmap(bitmap);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        ConvertView.startAnimation(anim);
        return ConvertView;
    }
    //Tạo lơp ViewHolder;
    class ViewHolder{
        ImageView iv_edit,iv_delete, img_HinhAnh;
        TextView textView_MaSP,textView_TenSP,textView_GiaCa;
    }
    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length()==0)
        {
            data.addAll(listSP);
        }
        else
        {
            for (SANPHAM sp : listSP)
            {
                if(sp.getMaSP().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    data.add(sp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
