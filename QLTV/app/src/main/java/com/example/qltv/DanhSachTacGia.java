package com.example.qltv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DanhSachTacGia extends AppCompatActivity {
    int vitri;
    TacGiaAdapter tgAdapter;
    ListView lv;
    ArrayList<TacGia> tgArrayList;
    DBHelper dbHelper;
    int vitri1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tac_gia);

        dbHelper = new DBHelper(this);
        tgArrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv);
        ArrayList<TacGia> arraylist = new ArrayList<>();
        arraylist = dbHelper.AllTacGia();
        for(int i=0;i<arraylist.size(); i++){
            tgArrayList.add(new TacGia(arraylist.get(i).getId(),arraylist.get(i).getAuthor()));
        }
        tgAdapter = new TacGiaAdapter(this,R.layout.dong_tac_gia,tgArrayList);
        lv.setAdapter(tgAdapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri = i;
                XacNhanXoa();
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri1 = i;
                dialogCapNhat(vitri1);
            }
        });
    }
    public void XacNhanXoa(){
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setTitle("Xoá");
        alBuilder.setMessage("Bạn có muốn xoá tác giả này không?");
        alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String s = tgArrayList.get(vitri).getId();
                dbHelper.Delete(s);
                tgArrayList.remove(vitri);
                tgAdapter.notifyDataSetChanged();

            }
        });
        alBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alBuilder.show();
    }

    public void dialogCapNhat(final int vitri1){
        Dialog dialog = new Dialog(DanhSachTacGia.this);
        dialog.setContentView(R.layout.dialog_custom_capnhat);
        final EditText idTG = (EditText)dialog.findViewById(R.id.edt_IdAuthor_capnhat);
        final EditText author = (EditText)dialog.findViewById(R.id.edt_Author_capnhat);
        final Button xoaTrang = (Button) dialog.findViewById(R.id.btn_Clear_capnhat);
        final Button capNhat = (Button)dialog.findViewById(R.id.btn_Update_capnhat);
        xoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTG.setText("");
                author.setText("");
            }
        });
        capNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    TacGia tg = new TacGia(idTG.getText().toString(),author.getText().toString());
                    String idht = tgArrayList.get(vitri1).getId();
                    if(dbHelper.UpdateTacGia(tg,idht)){
                        Toast.makeText(DanhSachTacGia.this,"Cap nhat thanh cong",Toast.LENGTH_SHORT).show();
                        tgArrayList.set(vitri1,tg);
                        tgAdapter.notifyDataSetChanged();
                        dbHelper.UpdateTacGia(tg,idht);
                    }else {
                        Toast.makeText(DanhSachTacGia.this,"Cap khong thanh cong, trung ma tac gia",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        dialog.show();
    }


}
