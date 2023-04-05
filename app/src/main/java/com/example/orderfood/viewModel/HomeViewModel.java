package com.example.orderfood.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orderfood.models.CategoryModel;
import com.example.orderfood.models.MealsModel;
import com.example.orderfood.repository.CategoryRepository;
import com.example.orderfood.repository.MealRepository;

public class HomeViewModel extends ViewModel {
    //Gọi tới Category Repository theo MVVM
    private CategoryRepository categoryRepository;
    private MealRepository mealRepository;

    public HomeViewModel() {
        categoryRepository = new CategoryRepository();//khởi tạo
        //gọi tới orderfoodapi bên CategoryRepository
        mealRepository = new MealRepository();
    }

    public MutableLiveData<CategoryModel> categoryRepositoryMutableLiveData(){
        return categoryRepository.getCategory();
    }
    //View gọi tới ViewModel -> lại gọi tới CategoryRepository

    //cần có id
    public MutableLiveData<MealsModel> mealsModelMutableLiveData(int idcate){
        return mealRepository.getMeals(idcate);
    }
    //=> Chạy qua MealsRepository
}
