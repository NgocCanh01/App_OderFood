package com.example.orderfood.viewModel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orderfood.models.MealsModel;
import com.example.orderfood.repository.MealRepository;

public class CategoryViewModel extends ViewModel {
    private MealRepository mealRepository;

    public CategoryViewModel(){
        mealRepository = new MealRepository();
    }
    public MutableLiveData<MealsModel> mealsModelMutableLiveData(int idcate){
        return mealRepository.getMeals(idcate);
    }
}
