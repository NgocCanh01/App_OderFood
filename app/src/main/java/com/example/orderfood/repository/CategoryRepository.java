package com.example.orderfood.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.orderfood.models.Category;
import com.example.orderfood.models.CategoryModel;
import com.example.orderfood.retrofit.OrderFoodApi;
import com.example.orderfood.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private OrderFoodApi orderFoodApi;

    public CategoryRepository() {
        orderFoodApi = RetrofitInstance.getRetrofit().create(OrderFoodApi.class);
    }
    public MutableLiveData<CategoryModel> getCategory(){
        MutableLiveData<CategoryModel> data = new MutableLiveData<>();
        //Nếu thành công
        orderFoodApi.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.d("logg",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
