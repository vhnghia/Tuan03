package com.example.qltv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_ThemTacGia, btn_XemDanhSach, btn_QuanLySach;
    private DBHelper dbHelper, dbHelper1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ThemTacGia = (Button)findViewById(R.id.btn_Add);
        btn_XemDanhSach = (Button)findViewById(R.id.btn_XemDanhSach);
        btn_QuanLySach = (Button)findViewById(R.id.btn_QuanLySach);
        dbHelper = new DBHelper(this);

        btn_ThemTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
        btn_XemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DanhSachTacGia.class);
                startActivity(intent);
            }
        });
        btn_QuanLySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,QuanLySach.class);
                startActivity(intent1);
            }
        });
    }
    public void  DialogAdd(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_custom);
        final EditText id = (EditText)dialog.findViewById(R.id.edt_IdAuthor);
        final EditText tenTG = (EditText)dialog.findViewById(R.id.edt_Author);
        final Button clear = (Button)dialog.findViewById(R.id.btn_Clear);
        final Button add = (Button)dialog.findViewById(R.id.btn_Save);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TacGia s = new TacGia();
                s.setId(id.getText().toString());
                s.setAuthor(tenTG.getText().toString());
                if(dbHelper.InsertOne(s)){
                    Toast.makeText(getApplicationContext(),"Luu thanh cong",Toast.LENGTH_SHORT).show();
                    System.out.println("so dong: "+dbHelper.soDong());
                }else {
                    Toast.makeText(getApplicationContext()  ,"Luu khong thanh cong",Toast.LENGTH_SHORT).show();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText("");
                tenTG.setText("");
            }
        });

        dialog.show();
    }
}
