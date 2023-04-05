package com.example.orderfood.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.orderfood.models.MealsModel;
import com.example.orderfood.retrofit.OrderFoodApi;
import com.example.orderfood.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {
    private OrderFoodApi api;

    public MealRepository() {
        api = RetrofitInstance.getRetrofit().create(OrderFoodApi.class);
    }

    public MutableLiveData<MealsModel> getMeals(int idcate){
        MutableLiveData<MealsModel> data = new MutableLiveData<>();
        api.getMeals(idcate).enqueue(new Callback<MealsModel>() {
            @Override
            public void onResponse(Call<MealsModel> call, Response<MealsModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MealsModel> call, Throwable t) {
                Log.d("logg", t.getMessage());
                data.setValue(null);
            }
        });


        return data;
    }
}
