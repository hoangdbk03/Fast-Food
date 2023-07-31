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
import android.widget.Toast;

import com.example.fastfood.adapter.LoaiSanPhamAdapter;
import com.example.fastfood.dao.QLCLSanPhamDAO;
import com.example.fastfood.model.LoaiSanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLCLSanPhamActivity extends AppCompatActivity {
    ImageView btnBackqlclsp;
    QLCLSanPhamDAO dao;
    RecyclerView recyclerLoaiSP;
    EditText edtMaSP,edtTenSP, edtSuaMaSP, edtSuaTenSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlclsan_pham);
        btnBackqlclsp = findViewById(R.id.btnBackqlclsp);
        recyclerLoaiSP = findViewById(R.id.recyclerQLCLSP);

        dao = new QLCLSanPhamDAO(this);
        loadData();

        btnBackqlclsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        FloatingActionButton floatAdd = findViewById(R.id.floatAdd);

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
        View view = inflater.inflate(R.layout.dialog_them_clsp, null);
        edtMaSP = view.findViewById(R.id.edtMaSP);
        edtTenSP = view.findViewById(R.id.edtTenSP);
        edtSuaMaSP = view.findViewById(R.id.edtSuaMaSP);
        edtSuaTenSP = view.findViewById(R.id.edtSuaTenSP);

        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String maloai = edtMaSP.getText().toString();
                String tenloai = edtTenSP.getText().toString();

                if (dao.themLoaiSP(maloai, tenloai) ){
                    loadData();
                    Toast.makeText(QLCLSanPhamActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(QLCLSanPhamActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerLoaiSP.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiSanPham> list = dao.getDSLoaiSanPham();
        LoaiSanPhamAdapter adapter = new LoaiSanPhamAdapter(this, list, dao);

        recyclerLoaiSP.setAdapter(adapter);
    }
}