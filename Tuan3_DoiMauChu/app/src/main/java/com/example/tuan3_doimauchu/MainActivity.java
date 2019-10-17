package com.example.tuan3_doimauchu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnShowContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowContext = (Button)findViewById(R.id.btnShowContext);
        registerForContextMenu(btnShowContext);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemRed:
                btnShowContext.setTextColor(getResources().getColor(R.color.clrred));
                break;
            case R.id.itemGreen:
                btnShowContext.setTextColor(getResources().getColor(R.color.clrgreen));
                break;
            case R.id.itemBule:
                btnShowContext.setTextColor(getResources().getColor(R.color.clrblue));
                break;
                default: btnShowContext.setTextColor(getResources().getColor(R.color.clrred));
                break;
        }

        return super.onContextItemSelected(item);
    }
}
