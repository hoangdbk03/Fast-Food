package com.example.fastfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fastfood.adapter.HoaDonAdapter;
import com.example.fastfood.dao.HoaDonDAO;
import com.example.fastfood.dao.KhachHangDAO;
import com.example.fastfood.dao.SanPhamDAO;
import com.example.fastfood.model.HoaDon;
import com.example.fastfood.model.KhachHang;
import com.example.fastfood.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class QLHoaDonActivity extends AppCompatActivity {

    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> list;
    RecyclerView recyclerView;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlhoa_don);

        recyclerView = findViewById(R.id.recyclerQLHoaDon);
        FloatingActionButton floatAdd = findViewById(R.id.floatAdd);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //data

        //adapter
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_hoadon, null);
        Spinner spnKhachHang = view.findViewById(R.id.spnMaKH);
        Spinner spnSanPham = view.findViewById(R.id.spnTenSP);
        getDataKhachHang(spnKhachHang);
        getDataSanPham(spnSanPham);
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                HashMap<String, Object> hsKH = (HashMap<String, Object>) spnKhachHang.getSelectedItem();
                String makh = (String) hsKH.get("makh");

                HashMap<String, Object> hsSP = (HashMap<String, Object>) spnSanPham.getSelectedItem();
                String tensp = (String) hsSP.get("tensp");

                int tien = (int) hsSP.get("giatien");

                themHoaDon(makh, tensp, tien);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(){
        hoaDonDAO = new HoaDonDAO(this);
        list = hoaDonDAO.getDsHoaDon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HoaDonAdapter adapter = new HoaDonAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private void getDataKhachHang(Spinner spnKhachHang){
        KhachHangDAO khachHangDAO = new KhachHangDAO(this);
        ArrayList<KhachHang> list = khachHangDAO.getDSKhachHang();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (KhachHang kh : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("makh", kh.getMakh());
            hs.put("tenkh", kh.getTenkh());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenkh"},
                new int[]{android.R.id.text1});
        spnKhachHang.setAdapter(simpleAdapter);
    }

    private void getDataSanPham(Spinner spnSanPham){
        SanPhamDAO sanPhamDAO = new SanPhamDAO(this);
        ArrayList<SanPham> list = sanPhamDAO.getDSSanPham();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (SanPham sp : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masp", sp.getMasp());
            hs.put("tensp", sp.getTensp());
            hs.put("giatien", sp.getGiatien());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensp"},
                new int[]{android.R.id.text1});
        spnSanPham.setAdapter(simpleAdapter);
    }

    private void themHoaDon(String makh, String tensp, int tien){

        Date currenTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currenTime);

        HoaDon hoaDon = new HoaDon(makh, tensp, tien, ngay, 0);
        boolean kiemtra = hoaDonDAO.themHoaDon(hoaDon);
        if (kiemtra){
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}