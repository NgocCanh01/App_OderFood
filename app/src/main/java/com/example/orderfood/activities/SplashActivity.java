package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.orderfood.R;
import com.example.orderfood.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);
        binding.tvGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();//chuyển màn hình xong hủy luôn
            }
        });
    }
}