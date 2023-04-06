package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.orderfood.R;
import com.example.orderfood.adapters.MealAdapter;
import com.example.orderfood.databinding.ActivityCategoryBinding;
import com.example.orderfood.viewModel.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    CategoryViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_category);
        initView();
        initData();
    }

    private void initData() {
        int idcate = getIntent().getIntExtra("idcate",1);
        String namecate = getIntent().getStringExtra("namecate");
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        viewModel.mealsModelMutableLiveData(idcate).observe(this,mealsModel -> {
            if(mealsModel.isSuccess()){
                MealAdapter adapter = new MealAdapter(mealsModel.getResult());
                binding.rcCategory.setAdapter(adapter);
                binding.tvName.setText(namecate + ": "+ mealsModel.getResult().size());
            }
        });
    }

    private void initView() {
        binding.rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        binding.rcCategory.setLayoutManager(layoutManager);
    }
}