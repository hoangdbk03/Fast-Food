package com.example.fastfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.DetailsActivity;
import com.example.fastfood.HomeFragment;
import com.example.fastfood.R;
import com.example.fastfood.model.Popular;
import com.example.fastfood.model.Recommend;

import java.util.List;

public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder>{

    HomeFragment context;
    List<Popular> popularFoodList;

    public PopularFoodAdapter(HomeFragment context, List<Popular> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }


    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodViewHolder holder, int position) {
        holder.food_image.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.name.setText(popularFoodList.get(position).getName());

        int picUrl = 0;
        switch (position){
            case 0:{
                picUrl = R.drawable.chicken;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 1:{
                picUrl = R.drawable.pizza;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
                break;
            }
            case 2:{
                picUrl = R.drawable.noodles;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 3:{
                picUrl = R.drawable.hamburger;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 4:{
                picUrl = R.drawable.hot_dog;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 5:{
                picUrl = R.drawable.kimbap;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
                break;
            }
            case 6:{
                picUrl = R.drawable.bread;
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
        }
        int drawableRe = holder.itemView.getContext().getResources().getIdentifier(String.valueOf(picUrl), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableRe)
                .into(holder.food_image);


    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }

    public class PopularFoodViewHolder extends RecyclerView.ViewHolder{

        private ImageView food_image;
        private TextView name;
        private ConstraintLayout mainLayout;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.food_image);
            name = itemView.findViewById(R.id.name);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
