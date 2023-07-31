package com.example.fastfood.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fastfood.database.DbHelper;
import com.example.fastfood.model.LoaiSanPham;

import java.util.ArrayList;

public class QLCLSanPhamDAO {
    DbHelper dbHelper;
    public QLCLSanPhamDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiSanPham> getDSLoaiSanPham(){
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISANPHAM", null);
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSanPham(cursor.getString(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        return list;
    }
    public boolean themLoaiSP(String maloai, String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai", maloai);
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.insert("LOAISANPHAM", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public int xoaLoaiSanPham(String id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM WHERE masp = ?", new String[]{(id)});
        if (cursor.getCount() != 0 ){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISANPHAM", "maloai = ?", new String[]{(id)});
        if (check == -1)
            return 0;
        return 1;
    }

    public boolean suaLoaiSanPham(String maloai, String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai", maloai);
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.update("LOAISANPHAM", contentValues, "maloai = ?", new String[]{maloai});
        if (check == -1)
            return false;
        return true;
    }
}
