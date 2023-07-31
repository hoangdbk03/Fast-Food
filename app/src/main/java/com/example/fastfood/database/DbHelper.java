package com.example.fastfood.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    
    public DbHelper(Context context){
        super(context, "FASTFOOD", null, 1);
    }
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(mand text primary key, " +
                "matkhau text, " +
                "tennd text)";
        sqLiteDatabase.execSQL(dbNguoiDung);

        String dbKhachHang = "CREATE TABLE KHACHHANG(makh text primary key , tenkh text)";
        sqLiteDatabase.execSQL(dbKhachHang);

        String dbSanPham = "CREATE TABLE SANPHAM(masp text primary key, " +
                "tensp text, " +
                "maLoai text references LOAISANPHAM(maloai), " +
                "giaTien integer)";
        sqLiteDatabase.execSQL(dbSanPham);

        String dbLoaiSanPham = "CREATE TABLE LOAISANPHAM(maloai text primary key, " +
                "tenloai text)";
        sqLiteDatabase.execSQL(dbLoaiSanPham);

        String dbHoaDon = "CREATE TABLE HOADON(mahd integer primary key autoincrement, mand text, masp text)";
        sqLiteDatabase.execSQL(dbHoaDon);

        String dbHoaDonChiTiet = "CREATE TABLE HDCT(mahdct integer primary key autoincrement, makh text references KHACHHANG(makh), ngaymua text, " +
                " tensp text references SANPHAM(tensp), giatien float, " +
                " trangthai integer)";
        sqLiteDatabase.execSQL(dbHoaDonChiTiet);

        //data

        sqLiteDatabase.execSQL("INSERT INTO NGUOIDUNG VALUES ('admin1', '123', 'Nguyễn Thanh Hoàng'), ('admin2', '123', 'Nguyễn Duy Đạt')");

        sqLiteDatabase.execSQL("INSERT INTO KHACHHANG VALUES ('mk', 'Mỹ Khánh'), ('ns', 'Nguyên Sáng')");

        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES ('MT', 'Mì Tôm'), ('GR', 'Gà rán'), " +
                "('HD', 'Hot Dog'), ('PZ', 'Pizza'), ('BM', 'Bánh mì'), ('KB', 'KimBap'), ('HB', 'Hamburger')");

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES (1, 'Mì tôm cay hải sản', 'MT', 20000), " +
                "(2, 'Mì tôm cay xúc xích', 'MT',  15000), (3, 'Mì tôm xào thịt bò', 'MT', 17000), " +
                "(4, 'Gà rán KFC', 'GR', 36000), (5, 'Gà rán sốt đặc biệt', 'GR', 40000), " +
                "(6, 'Piza hải sản', 'PZ', 70000), " +
                "(7, 'Piza sốt cà chua', 'PZ', 50000), (8, 'Bánh mì chả', 'BM', 15000), (9, 'Bánh mì ốp la', 'BM', 17000), " +
                "(10, 'Bánh mì que', 'BM', 12000), (11, 'Bánh mì gà', 'BM', 12.000), (12, 'Bánh mì thịt nướng', 'BM', 25000), " +
                "(13, 'Kimbap truyền thống', 'KB', 15000), (14, 'Kimbap cá ngừ', 'KB', 20000), " +
                "(15, 'Kimbap thịt bò', 'KB', 22000), (16, 'Hamburger thịt xông khói', 'HB', 15000), " +
                "(17, 'Hamburger bò phô mai', 'HB', 16000), (18, 'Hamburger bò nướng', 'HB', 20000), (19, 'Hot Dog', 'HD', 30000)");

        sqLiteDatabase.execSQL("INSERT INTO HOADON VALUES (1, 'admin1', 'GR'), (2, 'admin1', 'MT')");

        //1: đã thanh toán / 0: chưa thanh toán
        sqLiteDatabase.execSQL("INSERT INTO HDCT VALUES (1, 'mk', '20/11/2022', 'Gà rán KFC', 36000, 1), (2, 'ns', '25/11/2022', 'Mì tôm xào thịt bò', 15000, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS HOADON");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS HDCT");
            onCreate(sqLiteDatabase);
        }
    }
}
