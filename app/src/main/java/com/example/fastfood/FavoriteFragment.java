package com.example.fastfood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.adapter.FavoriteAdapter;
import com.example.fastfood.dao.FavoriteDAO;
import com.example.fastfood.model.SanPham;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        RecyclerView recyclerFavorite = view.findViewById(R.id.recyclerFavorite);

        FavoriteDAO favoriteDAO = new FavoriteDAO(getContext());
        ArrayList<SanPham> list = favoriteDAO.getFavorite();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerFavorite.setLayoutManager(linearLayoutManager);
        FavoriteAdapter adapter = new FavoriteAdapter(getContext(), list);
        recyclerFavorite.setAdapter(adapter);

        return view;
    }
}
