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
import MODEL.HOADON;
import MODEL.KHACHHANG;

public class HoaDonAdapter extends BaseAdapter {
    Context context;
    List<HOADON> data;
    ArrayList<HOADON> listHD;
    LOPDAO dao;

    public HoaDonAdapter(Context context, ArrayList<HOADON> data) {
        this.context = context;
        this.data = data;
        this.listHD = new ArrayList<HOADON>();
        this.listHD.addAll(data);
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
        HoaDonAdapter.ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.hoadon_item, null);
            holder.tvMaHD = view.findViewById(R.id.tv_MaHD);
            holder.tvNgayLap = view.findViewById(R.id.tv_NgayLap);
            holder.tvThanhTien = view.findViewById(R.id.tv_ThanhTien);
            holder.img_edit = view.findViewById(R.id.img_EditHD);
            holder.img_delete = view.findViewById(R.id.img_DeleteHD);

            view.setTag(holder);
        }
        else
        {
            holder = (HoaDonAdapter.ViewHolder) view.getTag();
        }
        // Gan su kien nut sua
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("hoadon", data.get(i));
                ((Activity)context).startActivity(intent);
            }
        });
        // Gan su kien xoa
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new LOPDAO(context);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn xóa?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao.Delete(data.get(i).getMaHD()))
                        {
                            Toast.makeText(context, "Xoá thành công!", Toast.LENGTH_LONG).show();
                            data.clear();
                            data.addAll(dao.readAllHD());
                            notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(context, "Xoá không thành công!", Toast.LENGTH_LONG).show();
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
        });
        holder.tvMaHD.setText(data.get(i).getMaHD());
        holder.tvNgayLap.setText(data.get(i).getNgayLap());
        holder.tvThanhTien.setText(String.format("%.0f VND",data.get(i).getThanhTien()));
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(anim);
        return view;
    }
    class ViewHolder
    {
        ImageView img_edit, img_delete;
        TextView tvMaHD, tvNgayLap, tvThanhTien;
    }
    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length()==0)
        {
            data.addAll(listHD);
        }
        else
        {
            for (HOADON hd : listHD)
            {
                if(hd.getMaHD().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    data.add(hd);
                }
            }
        }
        notifyDataSetChanged();
    }
}
