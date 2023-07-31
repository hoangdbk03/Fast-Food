package com.example.fastfood.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fastfood.database.DbHelper;

public class NguoiDungDAO {

    DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("LOGIN", MODE_PRIVATE);
    }

    public boolean checkLogin(String mand, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE mand = ? AND matkhau = ?", new String[]{mand, matkhau});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mand", cursor.getString(0));
            editor.putString("tennd", cursor.getString(2));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }

    public int capNhatMatKhau(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE mand = ? AND matkhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);

            long check = sqLiteDatabase.update("NGUOIDUNG", contentValues, "mand = ?", new String[]{username});
            if (check == -1)
                return -1;
            return 1;
        }
        return 0;
    }

}
