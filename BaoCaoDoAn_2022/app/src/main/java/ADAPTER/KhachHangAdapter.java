package ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocaodoan_2022.R;
import com.example.baocaodoan_2022.UpdateActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import DAO.LOPDAO;
import MODEL.KHACHHANG;

public class KhachHangAdapter extends BaseAdapter {
    Context context;
    List<KHACHHANG> data;
    ArrayList<KHACHHANG> listKH;
    LOPDAO dao;

    public KhachHangAdapter(Context context, ArrayList<KHACHHANG> data) {
        this.context = context;
        this.data = data;
        this.listKH = new ArrayList<KHACHHANG>();
        this.listKH.addAll(data);
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
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.khachhang_item, null);
            holder.tvMaSP = view.findViewById(R.id.tv_MaKH);
            holder.tvTenSP = view.findViewById(R.id.tv_TenKH);
            holder.tvSDT = view.findViewById(R.id.tv_SDT);
            holder.tvEmail = view.findViewById(R.id.tv_Email);
            holder.img_edit = view.findViewById(R.id.img_Edit);
            holder.img_delete = view.findViewById(R.id.img_Delete);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        // Gan su kien nut sua
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("khachhang", data.get(i));
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
                        if(dao.Delete(data.get(i).getMaKH()))
                        {
                            Toast.makeText(context, "Xoá thành công!", Toast.LENGTH_LONG).show();
                            data.clear();
                            data.addAll(dao.readAll());
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
        holder.tvMaSP.setText(data.get(i).getMaKH());
        holder.tvTenSP.setText(data.get(i).getTenKH());
        holder.tvSDT.setText(data.get(i).getSDT());
        holder.tvEmail.setText(data.get(i).getEmail());
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(anim);
        return view;
    }
    class ViewHolder
    {
        ImageView img_edit, img_delete;
        TextView tvTenSP, tvMaSP, tvSDT, tvEmail;
    }
    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length()==0)
        {
            data.addAll(listKH);
        }
        else
        {
            for (KHACHHANG kh : listKH)
            {
                if(kh.getSDT().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    data.add(kh);
                }
            }
        }
        notifyDataSetChanged();
    }
}
