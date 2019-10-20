package com.example.qltv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    DBHelper(Context context){
        super(context,"book_list", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TacGia(id text primary key , "+"author text)");
        db.execSQL("create table Sach(tenSach text,"+" nxb text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS TacGia");
        db.execSQL("Drop table if exists Sach");
        onCreate(db);
    }

    public ArrayList<TacGia> AllTacGia(){
        ArrayList<TacGia> bookArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TacGia",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            bookArrayList.add(new TacGia(cursor.getString(0),cursor.getString(1)));
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return bookArrayList;
    }

    public ArrayList<Sach> AllSach(){
        ArrayList<Sach> bookArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Sach",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            bookArrayList.add(new Sach(cursor.getString(0),cursor.getString(1)));
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return bookArrayList;
    }

    public void InsertOneSach(Sach s){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",s.getTenSach());
        contentValues.put("nxb",s.getNXB());
        db.insert("Sach",null,contentValues);
    }

    public boolean InsertOne(TacGia tg){
        ArrayList<TacGia> dstg = AllTacGia();
        for(TacGia tacgia: dstg)
            if(tacgia.getId().equalsIgnoreCase(tg.getId()))
                return false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",tg.getId());
        contentValues.put("author",tg.getAuthor());
        db.insert("TacGia",null,contentValues);
        return true;
    }

    public TacGia getBook(String id1){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TacGia where id="+id1,null);
        cursor.moveToFirst();
        TacGia tg = new TacGia(cursor.getString(0),cursor.getString(1));
        cursor.close();
        db.close();
        return tg;
    }

    public int soDong(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TacGia",null);
        int i = cursor.getCount();
        cursor.close();
        db.close();
        return  i;
    }

    public void Delete(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TacGia WHERE id="+"'"+id+"'");
    }

    public boolean KiemTraTrungMa(String id){
        ArrayList<TacGia> bookArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TacGia",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            bookArrayList.add(new TacGia(cursor.getString(0),cursor.getString(1)));
            cursor.moveToNext();
        }
        for(int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getId().equalsIgnoreCase(id))
            {
                System.out.println(bookArrayList.get(i).getId());
                return false;
            }
        }
        return  true;
    }

    public Boolean UpdateTacGia(TacGia tg,String idht){
        SQLiteDatabase db = getWritableDatabase();
        if(KiemTraTrungMa(tg.getId())==false)
            return false;
        db.execSQL("Update TacGia SET id ='"+tg.getId()+"', author='"+tg.getAuthor()+"' WHERE id='"+idht+"'");
        return  true;
    }

}
