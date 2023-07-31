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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fastfood.adapter.KhachHangAdapter;
import com.example.fastfood.dao.KhachHangDAO;
import com.example.fastfood.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLNguoiDungActivity extends AppCompatActivity {

    ImageView btnBackqlnd;
    KhachHangDAO khachHangDAO;
    RecyclerView recyclerKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlnguoi_dung);

        btnBackqlnd = findViewById(R.id.btnBackqlnd);
        FloatingActionButton floatAdd = findViewById(R.id.floatAdd);
        recyclerKH = findViewById(R.id.recyclerQLND);

        khachHangDAO = new KhachHangDAO(this);

        loadData();

        btnBackqlnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
        View view = inflater.inflate(R.layout.dialog_them_khachhang, null);
        builder.setView(view);

        EditText edtMaKH = view.findViewById(R.id.edtMaKH);
        EditText edtTenKH = view.findViewById(R.id.edtTenKH);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String makh = edtMaKH.getText().toString();
                String tenkh = edtTenKH.getText().toString();
                boolean check = khachHangDAO.themKhachHang(makh, tenkh);
                if (check){
                    loadData();
                    Toast.makeText(QLNguoiDungActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(QLNguoiDungActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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

    private void loadData(){

        ArrayList<KhachHang> list = khachHangDAO.getDSKhachHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerKH.setLayoutManager(linearLayoutManager);
        KhachHangAdapter adapter = new KhachHangAdapter(this, list, khachHangDAO);
        recyclerKH.setAdapter(adapter);
    }
}