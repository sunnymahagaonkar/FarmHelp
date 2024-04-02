package com.example.home3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.home3.Adapters.ProductListAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class grid_item2 extends AppCompatActivity
{
    RecyclerView recview;
    ProductListAdapter productListAdapter;
    private List<ProductDataModel> productList_ = new ArrayList<>();
    private NewProductListAdapter newProductListAdapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        Intent intent = getIntent();
        String nameOfProduct = (String) intent.getExtras().get("nameOfProduct");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("services").child(nameOfProduct).child("subproducts");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ProductDataModel> dataList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = (String) snapshot.child("name").getValue();
                    String location = (String) snapshot.child("location").getValue();
                    String price = (String) snapshot.child("price").getValue();

                    String period = (String) snapshot.child("period").getValue();


                    ProductDataModel data = new ProductDataModel(name,price,location,period);

                    dataList.add(data);
                }
                productList_ = dataList;
                newProductListAdapter_.setProductList(productList_);
                // Use the dataList as needed
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error fetching data", databaseError.toException());
            }
        });

        recview=(RecyclerView)findViewById(R.id.recyclerView);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ProductDataModel> options =
                new FirebaseRecyclerOptions.Builder<ProductDataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("services"), ProductDataModel.class)
                        .build();

        newProductListAdapter_=new NewProductListAdapter(productList_);
        recview.setAdapter(newProductListAdapter_);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}