package com.example.orderfood.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.orderfood.models.MealDetailModel;
import com.example.orderfood.retrofit.OrderFoodApi;
import com.example.orderfood.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailRepository {
    private OrderFoodApi appApi;

    public MealDetailRepository() {
        appApi = RetrofitInstance.getRetrofit().create(OrderFoodApi.class);
    }
    public MutableLiveData<MealDetailModel> getMealDetail(int id){
        MutableLiveData<MealDetailModel> data = new MutableLiveData<>();
        appApi.getMealDetail(id).enqueue(new Callback<MealDetailModel>() {
            @Override
            public void onResponse(Call<MealDetailModel> call, Response<MealDetailModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MealDetailModel> call, Throwable t) {
                data.setValue(null);
                Log.d("TAG", t.getMessage());
            }
        });
        return data;
    }
}
