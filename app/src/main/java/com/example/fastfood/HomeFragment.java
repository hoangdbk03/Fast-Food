package com.example.fastfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fastfood.adapter.PhotoAdapter;
import com.example.fastfood.adapter.PopularFoodAdapter;
import com.example.fastfood.adapter.RecommendFoodAdapter;
import com.example.fastfood.model.Popular;
import com.example.fastfood.model.Recommend;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;
    private CircleIndicator circleIndicator;
    RecyclerView popularRecycler, recommendRecycler;
    PopularFoodAdapter popularFoodAdapter;
    RecommendFoodAdapter recomendFoodAdapter;
    ConstraintLayout btnQLHoaDon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        popularRecycler = view.findViewById(R.id.popular_recyclerview);
        recommendRecycler = view.findViewById(R.id.recommend_recyclerview);
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(this, mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlideImages();

        List<Popular> popularFoodList = new ArrayList<>();

        popularFoodList.add(new Popular("Gà rán", R.drawable.chicken));
        popularFoodList.add(new Popular("Piza", R.drawable.pizza));
        popularFoodList.add(new Popular("Mì", R.drawable.noodles));
        popularFoodList.add(new Popular("Hamburger", R.drawable.hamburger));
        popularFoodList.add(new Popular("Hot dog", R.drawable.hot_dog));
        popularFoodList.add(new Popular("Kimbap", R.drawable.kimbap));
        popularFoodList.add(new Popular("Bánh mì", R.drawable.bread));

        setPopularRecycler(popularFoodList);

        List<Recommend> recommendFoodList = new ArrayList<>();

        recommendFoodList.add(new Recommend("Gà rán", R.drawable.garankfc,"Gà rán KFC", 36000));
        recommendFoodList.add(new Recommend("Pizza", R.drawable.pizzahaisan, "Pizza hải sản", 70000));
        recommendFoodList.add(new Recommend("Mì", R.drawable.micayhaisan, "Mì cay hải sản", 20000));
        recommendFoodList.add(new Recommend("Pizza", R.drawable.pizzasotcachua, "Pizza sốt cà chua", 50000));
        recommendFoodList.add(new Recommend("Gà rán", R.drawable.garansotdacbiet, "Gà rán sốt đặc biệt", 40000));
        recommendFoodList.add(new Recommend("Mì", R.drawable.micayxucxich, "Mì cay xúc xích", 15000));
        recommendFoodList.add(new Recommend("Mì", R.drawable.mixaothitbo, "Mì xào thịt bò", 17000));

        setRecommend(recommendFoodList);

        return view;
    }

    private void setPopularRecycler(List<Popular> popularFoodList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setRecommend(List<Recommend> recommendFoodList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recommendRecycler.setLayoutManager(layoutManager);
        recomendFoodAdapter = new RecommendFoodAdapter(this, recommendFoodList);
        recommendRecycler.setAdapter(recomendFoodAdapter);
    }


    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.banner4));
        list.add(new Photo(R.drawable.banner1));
        list.add(new Photo(R.drawable.banner3));
        list.add(new Photo(R.drawable.banner7));

        return list;
    }

    private void autoSlideImages(){

        if (mListPhoto == null || mListPhoto.isEmpty() || viewPager == null){
            return;
        }

        if (mTimer == null){
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if (currentItem < totalItem){
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },3000, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

}
