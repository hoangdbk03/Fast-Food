package com.example.fastfood.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fastfood.database.DbHelper;
import com.example.fastfood.model.SanPham;

import java.util.ArrayList;

public class FavoriteDAO {
    DbHelper dbHelper;
    public FavoriteDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<SanPham> getFavorite(){
        String SQL = "SELECT ct.tensp, COUNT(ct.tensp) FROM HDCT ct, SANPHAM sp WHERE ct.tensp = sp.tensp " +
                "GROUP BY ct.tensp ORDER by COUNT(ct.tensp) DESC LIMIT 10";
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getString(0), cursor.getInt(1)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}

