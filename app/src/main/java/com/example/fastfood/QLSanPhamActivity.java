package com.example.fastfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fastfood.adapter.SanPhamAdapter;
import com.example.fastfood.dao.QLCLSanPhamDAO;
import com.example.fastfood.dao.SanPhamDAO;
import com.example.fastfood.model.LoaiSanPham;
import com.example.fastfood.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class QLSanPhamActivity extends AppCompatActivity {

    ImageView btnBackqlsp;
    SanPhamDAO sanPhamDAO;
    RecyclerView recyclerSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsan_pham);

        btnBackqlsp = findViewById(R.id.btnBackqlsp);
        FloatingActionButton floatAdd = findViewById(R.id.floatAdd);
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        btnBackqlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerSP = findViewById(R.id.recyclerSP);

        sanPhamDAO = new SanPhamDAO(this);
        loadData();

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_sanpham, null);
        builder.setView(view);

        EditText edtMaSP = view.findViewById(R.id.edtThemMaSP);
        EditText edtTenSP = view.findViewById(R.id.edtThemTenSP);
        EditText edtGia = view.findViewById(R.id.edtThemGiaSP);
        Spinner spnLoaiSP = view.findViewById(R.id.spnLoaiSP);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                getDSLoaiSP(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSP.setAdapter(simpleAdapter);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String masp = edtMaSP.getText().toString();
                String tensp = edtTenSP.getText().toString();
                int tien = Integer.parseInt(edtGia.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiSP.getSelectedItem();
                String maloai = (String) hs.get("maloai");

                boolean check = sanPhamDAO.themSanPham(masp, tensp, tien, maloai);
                if (check){
                    loadData();
                    Toast.makeText(QLSanPhamActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(QLSanPhamActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
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

    private ArrayList<HashMap<String, Object>> getDSLoaiSP(){
        QLCLSanPhamDAO qlclSanPhamDAO = new QLCLSanPhamDAO(this);
        ArrayList<LoaiSanPham> list = qlclSanPhamDAO.getDSLoaiSanPham();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSanPham loaiSanPham : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loaiSanPham.getId());
            hs.put("tenloai", loaiSanPham.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }

    private void loadData(){
        ArrayList<SanPham> list = sanPhamDAO.getDSSanPham();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerSP.setLayoutManager(linearLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(this, list, getDSLoaiSP(), sanPhamDAO);
        recyclerSP.setAdapter(adapter);
    }
}