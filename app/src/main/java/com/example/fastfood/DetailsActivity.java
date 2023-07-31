package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fastfood.model.Recommend;

public class DetailsActivity extends AppCompatActivity {
    TextView txtTenMon, txtGiaDetail, txtCount, txtDatNgay;
    ImageView backDetails, imgMinus, imgPlus, imgFood;
    private Recommend object;
    int countNumber = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }
}