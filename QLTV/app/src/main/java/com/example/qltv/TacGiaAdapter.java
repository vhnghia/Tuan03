package com.example.qltv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TacGiaAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<TacGia> dss;

    public TacGiaAdapter(Context context, int layout, ArrayList<TacGia> dss) {
        this.context = context;
        this.layout = layout;
        this.dss = dss;
    }

    @Override
    public int getCount() {
        return dss.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(layout,null);
        TextView txtSTT = (TextView)view.findViewById(R.id.stt);
        TextView txtId = (TextView)view.findViewById(R.id.txtId);
        TextView txtTenTG = (TextView)view.findViewById(R.id.txtTenTG);

        TacGia tg = dss.get(i);

        txtSTT.setText(i+1 +"");
        txtId.setText(tg.getId());
        txtTenTG.setText(tg.getAuthor());
        return view;
    }
}
