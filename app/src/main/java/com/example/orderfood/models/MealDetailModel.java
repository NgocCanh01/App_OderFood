package com.example.orderfood.models;

import java.util.List;

public class MealDetailModel {
    private boolean success;
    private String message;
    private List<MealDetail> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MealDetail> getResult() {
        return result;
    }

    public void setResult(List<MealDetail> result) {
        this.result = result;
    }
}
