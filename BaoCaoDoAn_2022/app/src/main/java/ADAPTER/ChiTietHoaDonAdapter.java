package ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.baocaodoan_2022.UpdateActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import DAO.LOPDAO;
import MODEL.CHITIETHOADON;
import MODEL.HOADON;

public class ChiTietHoaDonAdapter extends BaseAdapter {
    Context context;
    List<CHITIETHOADON> data;
    ArrayList<CHITIETHOADON> listCTHD;
    LOPDAO dao;

    public ChiTietHoaDonAdapter(Context context, ArrayList<CHITIETHOADON> data) {
        this.context = context;
        this.data = data;
        this.listCTHD = new ArrayList<CHITIETHOADON>();
        this.listCTHD.addAll(data);
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChiTietHoaDonAdapter.ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.chitiethoadon_item, null);
            holder.tvMaHD = view.findViewById(R.id.tv_CTHDMaHD);
            holder.tvMaSP = view.findViewById(R.id.tv_CTHDMaSP);
            holder.tvSoLuong = view.findViewById(R.id.tv_CTHDSoLuong);
            holder.tvDonGia = view.findViewById(R.id.tv_CTHDDonGia);
            holder.tvThanhTien = view.findViewById(R.id.tv_CTHDThanhTien);
            view.setTag(holder);
        }
        else
        {
            holder = (ChiTietHoaDonAdapter.ViewHolder) view.getTag();
        }

        holder.tvMaHD.setText(data.get(i).getMaHD());
        holder.tvMaSP.setText(data.get(i).getMaSP());
        holder.tvSoLuong.setText(String.format("%d",data.get(i).getSoLuong()));
        holder.tvDonGia.setText(String.format("%.0f VND",data.get(i).getDonGia()));
        holder.tvThanhTien.setText(String.format("%.0f VND",data.get(i).getThanhTien()));
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(anim);
        return view;
    }
    class ViewHolder
    {
        TextView tvMaHD, tvMaSP, tvSoLuong, tvDonGia, tvThanhTien;
    }

}
