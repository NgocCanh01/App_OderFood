package com.example.orderfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfood.databinding.ItemMealBinding;
import com.example.orderfood.models.Meals;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder>{
private List<Meals> mealsList;

    public MealAdapter(List<Meals> mealsList) {
        this.mealsList = mealsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMealBinding binding = ItemMealBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(mealsList.get(position));
        Glide.with(holder.itemView).load(mealsList.get(position).getStrMealThumb()).into(holder.binding.imgCircle);
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemMealBinding binding;

        public MyViewHolder(ItemMealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void setBinding(Meals meals){
            binding.setMealitem(meals);
            binding.executePendingBindings();
        }
    }
}
