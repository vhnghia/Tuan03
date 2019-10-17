package com.example.qltv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuanLySach extends AppCompatActivity {
    private Button btn_nxb, btn_themSach;
    private EditText edt_TacGia_QLS, edt_TenSach_QLS, edt_NgayXB_QLS;
    private Spinner sp;
    private ListView lv;
    private DBHelper dbHelper;
    private List<TacGia> dstg = new ArrayList<>();
    private ArrayList<String> ar;
    private ArrayList<Sach> adSach;
    private SachAdapter sachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);
        btn_nxb = (Button)findViewById(R.id.btn_NXB_QLSXML);
        btn_themSach = (Button)findViewById(R.id.btn_Add_QLSXML);
        edt_TacGia_QLS = (EditText)findViewById(R.id.edt_TacGia_QLSXML);
        edt_TenSach_QLS = (EditText)findViewById(R.id.edt_TenSach_QLSXML);
        edt_NgayXB_QLS = (EditText)findViewById(R.id.edt_NXB_QLSXML);
        sp = (Spinner)findViewById(R.id.sp_XML);
        lv = (ListView)findViewById(R.id.lv_QLSXML);
        dbHelper = new DBHelper(this);
        dstg = dbHelper.AllTacGia();
        ar = new ArrayList<>();
        for(int i=0;i<dstg.size(); i++)
            ar.add(dstg.get(i).toString());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,ar);
        sp.setAdapter(arrayAdapter);
        adSach = dbHelper.AllSach();
        sachAdapter = new SachAdapter(this,R.layout.dong_sach,adSach);
        lv.setAdapter(sachAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edt_TacGia_QLS.setText(ar.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_themSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach s = new Sach(edt_TenSach_QLS.getText().toString(),edt_NgayXB_QLS.getText().toString());
                adSach.add(s);
                dbHelper.InsertOneSach(s);
                sachAdapter.notifyDataSetChanged();
            }
        });
        btn_nxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThietLapNgay();
            }
        });
    }

    public void ThietLapNgay(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_NgayXB_QLS.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }


}
