package com.example.fastfood.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fastfood.database.DbHelper;
import com.example.fastfood.model.KhachHang;

import java.util.ArrayList;

public class KhachHangDAO {

    DbHelper dbHelper;

    public KhachHangDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<KhachHang> getDSKhachHang(){
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG", null);
        if (cursor.getCount() !=0 ){
            cursor.moveToFirst();
            do {
                KhachHang khachHang = new KhachHang();
                khachHang.setMakh(cursor.getString(0));
                khachHang.setTenkh(cursor.getString(1));
                list.add(khachHang);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themKhachHang(String makh, String tenkh){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("makh", makh);
        contentValues.put("tenkh", tenkh);
        long check = sqLiteDatabase.insert("KHACHHANG", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinKH(String makh, String tenkh){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("makh", makh);
        contentValues.put("tenkh", tenkh);
        long check = sqLiteDatabase.update("KHACHHANG", contentValues, "makh = ?", new String[]{makh});
        if (check == -1)
            return false;
        return true;
    }

    //int 1: xóa thành công //0: thất bại //-1 chưa thanh toán hóa đơn
    public int xoaThongTinKH(String makh){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HDCT WHERE makh = ?", new String[]{makh});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("KHACHHANG", "makh = ?", new String[]{makh});
        if (check == -1)
            return 0;
        return 1;
    }
}
