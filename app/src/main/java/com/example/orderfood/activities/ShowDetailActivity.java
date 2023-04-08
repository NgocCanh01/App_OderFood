package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.orderfood.R;
import com.example.orderfood.Ultils.Ultils;
import com.example.orderfood.databinding.ActivityShowDetailBinding;
import com.example.orderfood.models.Cart;
import com.example.orderfood.models.MealDetail;
import com.example.orderfood.viewModel.ShowDetailViewModel;

import java.util.List;

import io.paperdb.Paper;

public class ShowDetailActivity extends AppCompatActivity {
    ShowDetailViewModel viewModel;
    ActivityShowDetailBinding binding;     //STEP 9
    int amount =1 ;
    MealDetail mealDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_detail);//STEP 9
        Paper.init(this);
        int id = getIntent().getIntExtra("id",0);
        getData(id);
        eventClick();
        showToData(id);
    }

    private void showToData(int id) {
        if(Paper.book().read("cart")!=null){
            List<Cart> list = Paper.book().read("cart");
            Ultils.cartList = list;
        }


        if(Ultils.cartList.size()>0){
            for(int i = 0; i<Ultils.cartList.size();i++) {
                if (Ultils.cartList.get(i).getMealDetail().getId() == id ){
                    binding.tvAmount.setText(Ultils.cartList.get(i).getAmount()+"");

                }
            }

        }else {
            binding.tvAmount.setText(amount+"");
        }
    }

    private void eventClick() {
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = Integer.parseInt(binding.tvAmount.getText().toString())+1;
                binding.tvAmount.setText(String.valueOf(amount));
            }
        });
        binding.imgSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(binding.tvAmount.getText().toString())>1){
                    amount = Integer.parseInt(binding.tvAmount.getText().toString())-1;
                    binding.tvAmount.setText(String.valueOf(amount));
                }

            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(amount);
            }
        });

    }

    private void addToCart(int amount) {
        boolean checkExit = false;
        int n = 0;
        if(Ultils.cartList.size()>0){
            for(int i = 0; i<Ultils.cartList.size();i++){
                if(Ultils.cartList.get(i).getMealDetail().getId()==mealDetail.getId()){
                    checkExit = true;
                    n = i;
                    break;
                }
            }
        }
        if(checkExit ){
            Ultils.cartList.get(n).setAmount(amount);
        }else {
            Cart cart = new Cart();
            cart.setMealDetail(mealDetail);
            cart.setAmount(amount);
            Ultils.cartList.add(cart);
        }
        Toast.makeText(getApplicationContext(),"Adder to your cart",Toast.LENGTH_LONG).show();
        Paper.book().write("cart",Ultils.cartList);
    }

    private void getData(int id) {
        viewModel = new ViewModelProvider(this).get(ShowDetailViewModel.class);
        viewModel.mealDetailModelMutableLiveData(id).observe(this,mealDetailModel -> {
            if(mealDetailModel.isSuccess()){
                mealDetail = mealDetailModel.getResult().get(0);
                Log.d("TAG", mealDetailModel.getResult().get(0).getMeal());
                binding.tvNameFood.setText(mealDetail.getMeal());
                binding.tvPrice.setText("$ "+mealDetail.getPrice());
                binding.tvDescription.setText(mealDetail.getInstructions());
                Glide.with(this).load(mealDetail.getStrmealthumb()).into(binding.imgNameFood);
            }
        });
    }
}