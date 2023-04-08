package com.example.orderfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfood.Utils.Ultils;
import com.example.orderfood.databinding.ItemCartBinding;
import com.example.orderfood.listener.ChangeNumListener;
import com.example.orderfood.models.Cart;

import java.util.List;

import io.paperdb.Paper;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private List<Cart> cartList;
    private ChangeNumListener changeNumListener;

    public CartAdapter(Context context, List<Cart> cartList, ChangeNumListener changeNumListener) {
        this.context = context;
        this.cartList = cartList;
        this.changeNumListener = changeNumListener;
    }

    public CartAdapter(List<Cart> cartList, ChangeNumListener changeNumListener) {
        this.cartList = cartList;
        this.changeNumListener = changeNumListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding cartBinding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(cartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.binding.tvName.setText(cart.getMealDetail().getMeal());
        Glide.with(context).load(cart.getMealDetail().getStrmealthumb()).into(holder.binding.imgCart);
        holder.binding.tvPriceCart.setText(cart.getMealDetail().getPrice() + "");
        holder.binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(holder.getAdapterPosition());
                notifyDataSetChanged();
                changeNumListener.change();
            }
        });
        holder.binding.imgSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subToCart(holder.getAdapterPosition());
                notifyDataSetChanged();
                changeNumListener.change();
            }
        });
        holder.binding.tvAmount.setText(cart.getAmount()+"");
        holder.binding.tvPrice2.setText("$" + String.valueOf(cart.getAmount() * cart.getMealDetail().getPrice()));
    }

    private void subToCart(int adapterPosition) {
        if (Ultils.cartList.get(adapterPosition).getAmount() == 1) {
            Ultils.cartList.remove(adapterPosition);
        } else {
            Ultils.cartList.get(adapterPosition).setAmount(Ultils.cartList.get(adapterPosition).getAmount() - 1);

        }
        Paper.book().write("cart",Ultils.cartList);

    }

    private void addToCart(int adapterPosition) {
        Ultils.cartList.get(adapterPosition).setAmount(Ultils.cartList.get(adapterPosition).getAmount() + 1);
        Paper.book().write("cart",Ultils.cartList);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding binding;

        public MyViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
