package com.example.fastfood.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fastfood.database.DbHelper;
import com.example.fastfood.model.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {

    DbHelper dbHelper;

    public SanPhamDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<SanPham> getDSSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sp.masp, sp.tensp, sp.maloai, sp.giatien, ls.tenloai FROM SANPHAM sp, LOAISANPHAM ls WHERE sp.maloai = ls.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4)));
//                SanPham sanPham = new SanPham();
//                sanPham.setMasp(cursor.getString(0));
//                sanPham.setTensp(cursor.getString(1));
//                sanPham.setMaloai(cursor.getString(2));
//                sanPham.setGiatien(cursor.getInt(3));
//                sanPham.setTenloai(cursor.getString(4));
//                list.add(sanPham);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSanPham(String masp, String tensp, int gia, String maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masp", masp);
        contentValues.put("tensp", tensp);
        contentValues.put("giatien", gia);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinSP(String masp, String tensp, int giatien, String maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masp", masp);
        contentValues.put("tensp", tensp);
        contentValues.put("giatien", giatien);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.update("SANPHAM", contentValues, "masp = ?", new String[]{masp});
        if (check == -1)
            return false;
        return true;
    }

    public int xoaSanPham(String tensp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HDCT WHERE tensp = ?", new String[]{tensp});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("SANPHAM", "tensp = ?", new String[]{tensp});
        if (check == -1)
            return 0;
        return 1;
    }

}

