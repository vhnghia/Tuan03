package com.example.qltv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Sach> sachArrayList;

    public SachAdapter(Context context, int layout, ArrayList<Sach> sachArrayList) {
        this.context = context;
        this.layout = layout;
        this.sachArrayList = sachArrayList;
    }

    @Override
    public int getCount() {
        return sachArrayList.size();
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
        view = inflater.inflate(layout,null);
        TextView stt = (TextView)view.findViewById(R.id.txt_stt_dsXML);
        TextView TenSach = (TextView)view.findViewById(R.id.txt_TenSach_dsXML);
        TextView nxb = (TextView)view.findViewById(R.id.txt_NXB_dsXML);
        Sach s = new Sach();
        s = sachArrayList.get(i);
        stt.setText(i+1+"");
        TenSach.setText(s.getTenSach());
        nxb.setText(s.getNXB());

        return view;
    }
}
