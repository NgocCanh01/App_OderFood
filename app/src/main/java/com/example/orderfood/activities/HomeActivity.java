package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.orderfood.R;
import com.example.orderfood.adapters.CategoryAdapter;
import com.example.orderfood.adapters.PopularAdapter;
import com.example.orderfood.databinding.ActivityHomeBinding;
import com.example.orderfood.listener.CategoryListener;
import com.example.orderfood.listener.EventClickListener;
import com.example.orderfood.models.Category;
import com.example.orderfood.models.Meals;
import com.example.orderfood.viewModel.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements CategoryListener, EventClickListener {
    HomeViewModel homeViewModel;
    ActivityHomeBinding binding;
    //STEP 1:Màn hình Splash
    //STEP 6: HIỆN DATA LÊN RECYCLEVIEW POPULAR: tạo item_popular,sửa activityHome(RecycleView)
    //STEP 7: EVENT CLICK CATEGORY SCREEN:tạo CategoryActivity,package(listener), sửa CategoryAdapter,HomeActivity,tao background_meal, MealAdapter,CategoryViewModel
    //search: imager cicler lib android
    //STEP 8: GET DATA CHO MÀN SHOW DETAIL:tạo EventClickListener,MealDetail va Model; MealDetailRepository, ShowDetailViewModel
    // sửa: MealAdapter,CategoryActivity,OderFoodApi
    //STEP 9: GIAO DIỆN DETAIL_ACTIVITY:xml and ActivityShowDetail
    //STEP 10: EVENT BUTTON ADD CART: tạo Cart in models: Ultils,add library paper, chỉnh sửa giao diện add
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

        binding.btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(cart);
            }
        });
    }

    private void initData() {
        //trước khi gọi thì tạo mới
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.categoryRepositoryMutableLiveData().observe(this, categoryModel -> {
            if (categoryModel.isSuccess()) {
//                Log.d("logg", categoryModel.getResult().get(0).getCategory());
                CategoryAdapter adapter = new CategoryAdapter(categoryModel.getResult(),this);
                binding.rcCategories.setAdapter(adapter);
            }
        });

        homeViewModel.mealsModelMutableLiveData(1).observe(this,mealsModel -> {
//            for(Meals meals:mealsModel.getResult()){
//                Log.d("logg", meals.getStrMeal());
//            }
            //STEP 6:
            if(mealsModel.isSuccess()){
                PopularAdapter adapter = new PopularAdapter(mealsModel.getResult(),this);
                binding.rcPopular.setAdapter(adapter);
            }
        });
    }
    //STEP 7
    @Override
    public void onClickCategory(Category category) {
        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
        intent.putExtra("idcate",category.getId());
        intent.putExtra("namecate",category.getCategory());
        startActivity(intent);
    }
    //STEP 8:
    @Override
    public void onClickPopular(Meals meals) {
        Intent intent = new Intent(getApplicationContext(),ShowDetailActivity.class);
        intent.putExtra("id",meals.getIdMeal());
        startActivity(intent);
    }
}