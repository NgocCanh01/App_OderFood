package com.example.orderfood.retrofit;

import com.example.orderfood.models.CategoryModel;
import com.example.orderfood.models.MealsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderFoodApi {
    @GET("category.php")//gọi tới file api có sẵn
    Call<CategoryModel> getCategory();

    //đẩy data lên
    @POST("meal.php")
    //giải ra: application/x-www-form-urlencoded (HEADER)
    @FormUrlEncoded
    //có giá trị đẩy lên
    Call<MealsModel> getMeals(
            @Field("idcate") int idcate//có trường để đẩy lên

            );
}
