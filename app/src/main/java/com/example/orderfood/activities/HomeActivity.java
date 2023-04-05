package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.orderfood.R;
import com.example.orderfood.adapters.CategoryAdapter;
import com.example.orderfood.adapters.PopularAdapter;
import com.example.orderfood.databinding.ActivityHomeBinding;
import com.example.orderfood.models.Category;
import com.example.orderfood.models.Meals;
import com.example.orderfood.viewModel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    HomeViewModel homeViewModel;
    ActivityHomeBinding binding;
    //STEP 1:Màn hình Splash
    //STEP 6: HIỆN DATA LÊN RECYCLEVIEW POPULAR: tạo item_popular,sửa activityHome(RecycleView)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        initView();
        initData();


    }

    private void initView() {
        binding.rcCategories.setHasFixedSize(true);
        //Vuot theo chieu ngang
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //setlayout manager cho recycle view
        binding.rcCategories.setLayoutManager(layoutManager);
        //STEP 6:
        binding.rcPopular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this,3);
        binding.rcPopular.setLayoutManager(layoutManager1);
    }

    private void initData() {
        //trước khi gọi thì tạo mới
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.categoryRepositoryMutableLiveData().observe(this, categoryModel -> {
            if (categoryModel.isSuccess()) {
//                Log.d("logg", categoryModel.getResult().get(0).getCategory());
                CategoryAdapter adapter = new CategoryAdapter(categoryModel.getResult());
                binding.rcCategories.setAdapter(adapter);
            }
        });

        homeViewModel.mealsModelMutableLiveData(1).observe(this,mealsModel -> {
//            for(Meals meals:mealsModel.getResult()){
//                Log.d("logg", meals.getStrMeal());
//            }
            //STEP 6:
            if(mealsModel.isSuccess()){
                PopularAdapter adapter = new PopularAdapter(mealsModel.getResult());
                binding.rcPopular.setAdapter(adapter);
            }
        });
    }
}