package com.example.orderfood.models;

public class Cart {
    private MealDetail mealDetail;
    private int amount;

    public MealDetail getMealDetail() {
        return mealDetail;
    }

    public void setMealDetail(MealDetail mealDetail) {
        this.mealDetail = mealDetail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
