package ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baocaodoan_2022.R;

import java.util.ArrayList;

import MODEL.CHUCNANG;

public class ChucNangTKAdapter extends ArrayAdapter<CHUCNANG> {
    Context context;
    int layoutht;
    ArrayList<CHUCNANG> chucNangTKS;
    public ChucNangTKAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CHUCNANG> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutht=resource;
        this.chucNangTKS=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(layoutht,null);

        ImageView icon = convertView.findViewById(R.id.icontk);
        TextView labelten = convertView.findViewById(R.id.labeltencn);

        CHUCNANG danght = chucNangTKS.get(position);

        icon.setImageResource(danght.getIcon());

        labelten.setText(danght.getTenchucnang());

        return convertView;
    }
}
