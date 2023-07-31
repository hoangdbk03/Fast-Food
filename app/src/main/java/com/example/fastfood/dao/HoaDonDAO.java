package com.example.fastfood.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fastfood.database.DbHelper;
import com.example.fastfood.model.HoaDon;

import java.util.ArrayList;

public class HoaDonDAO {
    DbHelper dbHelper;
    public HoaDonDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<HoaDon> getDsHoaDon(){
        String SQL = "SELECT ct.mahdct, ct.makh, kh.tenkh, ct.tensp, ct.giatien, ct.ngaymua, ct.trangthai " +
                "FROM HDCT ct, KHACHHANG kh WHERE ct.makh = kh.makh ORDER BY ct.mahdct DESC";
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMahdct(cursor.getInt(0));
                hoaDon.setMakh(cursor.getString(1));
                hoaDon.setTenkh(cursor.getString(2));
                hoaDon.setTensp(cursor.getString(3));
                hoaDon.setGiatien(cursor.getInt(4));
                hoaDon.setNgay(cursor.getString(5));
                hoaDon.setThanhtoan(cursor.getInt(6));
                list.add(hoaDon);
            }while (cursor.moveToNext());
        }

        return list;
    }
    
    public boolean thayDoiTrangThai(int mahdct){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai", 1);
        long check = sqLiteDatabase.update("HDCT", contentValues, "mahdct = ?", new String[]{String.valueOf(mahdct)});
        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean themHoaDon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //mahdct integer primary key autoincrement, makh text references KHACHHANG(makh), ngaymua text, " +
        //                " tensp text, giatien float, " +
        //                " trangthai integer
        contentValues.put("makh", hoaDon.getMakh());
        contentValues.put("ngaymua", hoaDon.getNgay());
        contentValues.put("tensp", hoaDon.getTensp());
        contentValues.put("giatien", hoaDon.getGiatien());
        contentValues.put("trangthai", hoaDon.getGiatien());

        long check = sqLiteDatabase.insert("HDCT", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
}
