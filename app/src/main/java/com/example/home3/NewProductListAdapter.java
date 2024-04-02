package com.example.home3;

import static androidx.core.content.ContextCompat.startActivity;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home3.databinding.ProductCardViewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ProductDataModel> productList_;
    FirebaseAuth auth;

    private ProductDataModel productDataModel;

    public NewProductListAdapter(List<ProductDataModel> productList) {
        productList_ = productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProductCardViewBinding binding = ProductCardViewBinding.inflate(inflater);
        return new NewProductListAdapter.ProductListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductListViewHolder viewHolder = (ProductListViewHolder) holder;
        viewHolder.binding_.productName.setText(getItem(position).name);
        viewHolder.binding_.location.setText(getItem(position).location);
        viewHolder.binding_.price.setText((getItem(position).price));

        viewHolder.binding_.timeperiod.setText(getItem(position).period);

    }

    public ProductDataModel getItem(int position) {
        return productList_.get(position);
    }

    @Override
    public int getItemCount() {
        return productList_.size();
    }

    public void setProductList(List<ProductDataModel> productList) {
        productList_ = productList;
        notifyDataSetChanged();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder {
        public ProductCardViewBinding binding_;


        TextView quantity;
        int totalquantity = 1;

        public ProductListViewHolder(@NonNull ProductCardViewBinding itemView) {
            super(itemView.getRoot());
            binding_ = itemView;
            quantity = itemView.getRoot().findViewById(R.id.quantity);

            binding_.addcart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (totalquantity < 10) {
                        totalquantity++;
                        Log.d("product1info", "addcart clicked");

                        quantity.setText(String.valueOf(totalquantity));
                    }
                }
            });

            binding_.removecart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (totalquantity > 1) {
                        totalquantity--;
                        quantity.setText(String.valueOf(totalquantity));
                    }
                }
            });
            binding_.addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addedtocart(getAdapterPosition());

                }
            });




        }
    }

    private void addedtocart(int position) {

        //ProductDataModel productDataModel= new ProductDataModel();
            // Create a new HashMap to store the cart item data
            HashMap<String, String> cartItem = new HashMap<>();
            cartItem.put("productName", productList_.get(position).getName());
            cartItem.put("productPrice", productList_.get(position).getPrice());
        cartItem.put("productLocation", productList_.get(position).getLocation());
        cartItem.put("productPeriod", productList_.get(position).getPeriod());

        //artItem.put("productEmail",productDataModel.getLocation());

            // Get the current user's UID
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference isUserAddedProduct = FirebaseDatabase.getInstance().getReference("AddToCart").child(uid);
        DatabaseReference cartRef;
        if (isUserAddedProduct != null) {
           cartRef = isUserAddedProduct.child(cartItem.get("productName"));
        } else {
           cartRef = FirebaseDatabase.getInstance().getReference("AddToCart").child(uid);
           cartRef.child("productName");
        }


            // Add the cart item to the database using the generated key
            cartRef.setValue(cartItem)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Cart item added successfully
                                //Toast.makeText(getContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
                            } else {
                                // Failed to add cart item
                                //Toast.makeText(getContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


// Create a new child node with the generated key



// Set the value of the new child node


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference addToCartRef = database.getReference("AddToCart");
//
//        DatabaseReference currentUserRef = addToCartRef.child(auth.getCurrentUser().getUid());
//
//        Map<String, Object> cartMap = new HashMap<>();
//// Populate cartMap with the data you want to add to the database
//
//        currentUserRef.setValue(cartMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getContext(),"Added To Cart", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Toast.makeText(cartfrag.this, "Failed to add to cart: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


//        String saveCurrentDate,saveCurrentTime;
//        Calendar calendar=Calendar.getInstance();
//        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd,yyyy");
//        saveCurrentDate =currentDate.format(calendar.getTime());
//
//        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currentTime.format(calendar.getTime());
//
//        cartMap.put("currentDate", saveCurrentDate);
//        cartMap.put("currentTime", saveCurrentTime);
//        FirebaseAuth firebaseAuth=  FirebaseAuth.getInstance()   ;
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference addcart = database.getReference().child(nameOfProduct).child("subproducts");



    }





