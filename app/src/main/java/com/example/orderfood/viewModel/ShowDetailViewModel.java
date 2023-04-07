package com.example.orderfood.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orderfood.models.MealDetailModel;
import com.example.orderfood.repository.MealDetailRepository;

public class ShowDetailViewModel extends ViewModel {
    private MealDetailRepository mealDetailRepository;

    public ShowDetailViewModel(){
        mealDetailRepository = new MealDetailRepository();
    }
    public MutableLiveData<MealDetailModel> mealDetailModelMutableLiveData(int id){
        return mealDetailRepository.getMealDetail(id);
    }

}
