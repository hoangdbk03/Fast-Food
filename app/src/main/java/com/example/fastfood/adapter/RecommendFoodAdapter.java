package com.example.fastfood.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.DetailsActivity;
import com.example.fastfood.HomeFragment;
import com.example.fastfood.R;
import com.example.fastfood.model.Recommend;

import java.util.List;

public class RecommendFoodAdapter extends RecyclerView.Adapter<RecommendFoodAdapter.RecommendFoodViewHolder>{

    HomeFragment context;
    List<Recommend> recommendFoodList;

    public RecommendFoodAdapter(HomeFragment context, List<Recommend> recommendFoodList) {
        this.context = context;
        this.recommendFoodList = recommendFoodList;
    }

    @NonNull
    @Override
    public RecommendFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false);
        return new RecommendFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendFoodViewHolder holder, int position) {
        holder.imageRecom.setImageResource(recommendFoodList.get(position).getImageUrl());
        holder.txtPrice.setText(String.valueOf(recommendFoodList.get(position).getGia()));
        holder.txtName.setText(recommendFoodList.get(position).getName());
        holder.txtLoai.setText(recommendFoodList.get(position).getLoai());

        holder.imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendFoodList.size();
    }

    public class RecommendFoodViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtPrice, txtLoai;
        private ImageView imageRecom, imgOrder;

        public RecommendFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLoai = itemView.findViewById(R.id.txtLoai);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imageRecom = itemView.findViewById(R.id.imageRecom);
            imgOrder = itemView.findViewById(R.id.imgOrder);
        }
    }
}
