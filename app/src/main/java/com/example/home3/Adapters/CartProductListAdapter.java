package com.example.home3.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home3.ProductDataModel;
import com.example.home3.cartfrag;
import com.example.home3.databinding.CartCardViewBinding;

import java.util.List;

public class CartProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private cartfrag context;
    private List<ProductDataModel> productList_;
    int totalPrice=0;

    public CartProductListAdapter(cartfrag context , List<ProductDataModel> productList_) {
        this.productList_ = productList_;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartCardViewBinding binding = CartCardViewBinding.inflate(inflater, parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CartViewHolder viewholder = (CartViewHolder) holder;
        viewholder.binding_.productName.setText(getItem(position).getName());
        viewholder.binding_.location.setText(getItem(position).getLocation());
        viewholder.binding_.price.setText(getItem(position).getPrice());
        viewholder.binding_.timeperiod.setText(getItem(position).getPeriod());


    }

    @Override
    public int getItemCount() {
        return productList_.size();
    }

    public ProductDataModel getItem(int position) {
        return productList_.get(position);
    }

    public void setProductList(List<ProductDataModel> productList) {
        productList_ = productList;
        notifyDataSetChanged();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private CartCardViewBinding binding_;

        public CartViewHolder(@NonNull CartCardViewBinding binding) {
            super(binding.getRoot());
            binding_ = binding;

            binding_.removeitembutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.removeCartItem(getItem(getAdapterPosition()).getName());
                }
            });
        }
    }
}
