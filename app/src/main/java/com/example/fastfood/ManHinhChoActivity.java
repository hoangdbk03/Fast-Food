package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ManHinhChoActivity extends AppCompatActivity {

    private  static int SPLASH_SCREEN = 5000;
    ImageView imageLogo;
    Animation topAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_man_hinh_cho);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        imageLogo = findViewById(R.id.imageLogo);

        imageLogo.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChoActivity.this, LoginActivity.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(imageLogo, "logo_image");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ManHinhChoActivity.this, pairs);
                startActivity(intent,options.toBundle());
            }
        },SPLASH_SCREEN);
    }

}