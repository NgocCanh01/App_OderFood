package com.example.orderfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.orderfood.R;
import com.example.orderfood.Ultils.Ultils;
import com.example.orderfood.adapters.CartAdapter;
import com.example.orderfood.databinding.ActivityCartBinding;
import com.example.orderfood.listener.ChangeNumListener;
import com.example.orderfood.models.Cart;

import java.util.List;

import io.paperdb.Paper;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart);
        Paper.init(this);
        initView();
        initData();
        totalPrice();
    }

    private void initData() {
        List<Cart> carts = Paper.book().read("cart");
        Ultils.cartList = carts;
        CartAdapter adapter = new CartAdapter(this, Ultils.cartList, new ChangeNumListener() {
            @Override
            public void change() {
                totalPrice();
            }
        });
        binding.recyclecart.setAdapter(adapter);
    }

    private void totalPrice() {
        int item = 0;
        for(int i=0; i<Ultils.cartList.size();i++){
            item = item + Ultils.cartList.get(i).getAmount();
        }
        binding.txtitem.setText(String.valueOf(item));
        Double price = 0.0;
        for(int i = 0; i<Ultils.cartList.size();i++){
            price = price+(Ultils.cartList.get(i).getAmount()*Ultils.cartList.get(i).getMealDetail().getPrice());
        }
        binding.txtprice.setText("$"+String.valueOf(price));
    }

    private void initView() {
        binding.recyclecart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclecart.setLayoutManager(layoutManager);
    }
}