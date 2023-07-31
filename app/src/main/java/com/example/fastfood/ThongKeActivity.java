package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fastfood.dao.ThongKeDAO;

import java.util.Calendar;

public class ThongKeActivity extends AppCompatActivity {
    ImageView imgViewBottom;
    String URL = "https://b-f13-zpc.zdn.vn/101637166631889834/d78ef69e85d85d8604c9.jpg";
    EditText edtStar, edtEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        imgViewBottom = findViewById(R.id.imgViewBottom);
        Glide.with(this).load(URL).into(imgViewBottom);

        edtStar = findViewById(R.id.edtStart);
        edtEnd = findViewById(R.id.edtEnd);
        Button btnThongKe = findViewById(R.id.btnThongKe);
        ImageView btnBack = findViewById(R.id.btnBackThongKe);
        TextView txtKetQua = findViewById(R.id.txtKetQua);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Calendar calendar = Calendar.getInstance();

        edtStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongKeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month < 10){
                                    thang = "0" + (month + 1);
                                }else {
                                    thang = String.valueOf(month + 1);
                                }
                                edtStar.setText(year + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongKeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month < 10){
                                    thang = "0" + (month + 1);
                                }else {
                                    thang = String.valueOf(month + 1);
                                }
                                edtEnd.setText(year + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKeDAO = new ThongKeDAO(ThongKeActivity.this);
                String ngaybatdau = edtStar.getText().toString();
                String ngayketthuc = edtEnd.getText().toString();
                int doanhthu = thongKeDAO.getDoanhThu(ngaybatdau, ngayketthuc);
                txtKetQua.setText(doanhthu + " VNĐ");
            }
        });
    }
}