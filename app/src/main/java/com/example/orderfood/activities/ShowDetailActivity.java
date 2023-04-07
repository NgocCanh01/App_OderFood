package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.orderfood.R;
import com.example.orderfood.databinding.ActivityShowDetailBinding;
import com.example.orderfood.models.MealDetail;
import com.example.orderfood.viewModel.ShowDetailViewModel;

public class ShowDetailActivity extends AppCompatActivity {
    ShowDetailViewModel viewModel;
    ActivityShowDetailBinding binding;     //STEP 9
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_detail);//STEP 9
        getData();
    }

    private void getData() {
        int id = getIntent().getIntExtra("id",0);
        viewModel = new ViewModelProvider(this).get(ShowDetailViewModel.class);
        viewModel.mealDetailModelMutableLiveData(id).observe(this,mealDetailModel -> {
            if(mealDetailModel.isSuccess()){
                MealDetail mealDetail = mealDetailModel.getResult().get(0);
                Log.d("TAG", mealDetailModel.getResult().get(0).getMeal());
                binding.tvNameFood.setText(mealDetail.getMeal());
                binding.tvPrice.setText("$ "+mealDetail.getPrice());
                binding.tvDescription.setText(mealDetail.getInstructions());
                Glide.with(this).load(mealDetail.getStrmealthumb()).into(binding.imgNameFood);
            }
        });
    }
}