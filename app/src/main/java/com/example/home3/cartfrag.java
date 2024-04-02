package com.example.home3;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.home3.Adapters.CartProductListAdapter;
import com.example.home3.R;
import com.example.home3.databinding.FragmentCartfragBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cartfrag extends Fragment implements PaymentResultListener {

    Button placeorder;

    private List<ProductDataModel> productList_ = new ArrayList<>();
    private CartProductListAdapter cartProductListAdapter_;
    private FragmentCartfragBinding binding_;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Checkout.preload(getContext());

        // Inflate the layout for this fragment
        binding_ = FragmentCartfragBinding.inflate(inflater, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = database.getReference("AddToCart").child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int totalPrice = 0;
                List<ProductDataModel> dataList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = (String) snapshot.child("productName").getValue();
                    String location = (String) snapshot.child("productLocation").getValue();
                    String priceString = (String) snapshot.child("productPrice").getValue();
                    String period = (String) snapshot.child("productPeriod").getValue();

                    Pattern pattern = Pattern.compile("(\\d+)");
                    Matcher matcher = pattern.matcher(priceString);
                    if (matcher.find()) {
                        // Parse the extracted integer as an int
                        int value = Integer.parseInt(matcher.group(1));
                        totalPrice += value;
                    }

                    ProductDataModel data = new ProductDataModel(name, priceString, location, period);
                    dataList.add(data);
                }
                productList_ = dataList;
                cartProductListAdapter_.setProductList(productList_);

                // Set the total price TextView
                binding_.totalprice.setText(String.valueOf(totalPrice));
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error fetching data", databaseError.toException());
            }
        });

        binding_.cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cartProductListAdapter_ = new CartProductListAdapter(this, productList_);
        binding_.cartRecyclerView.setAdapter(cartProductListAdapter_);



        binding_.placeorderbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePayment();
            }
        });
        return binding_.getRoot();
    }


    public void removeCartItem(String productName) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = database.getReference("AddToCart").child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int totalPrice = 0;
                List<ProductDataModel> dataList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = (String) snapshot.child("productName").getValue();
                    String location = (String) snapshot.child("productLocation").getValue();
                    String priceString = (String) snapshot.child("productPrice").getValue();
                    String period = (String) snapshot.child("productPeriod").getValue();

                    if (name.equals(productName)) {
                        snapshot.getRef().removeValue(); // Remove the item
                        continue;
                    }
                    Pattern pattern = Pattern.compile("(\\d+)");
                    Matcher matcher = pattern.matcher(priceString);
                    if (matcher.find()) {
                        // Parse the extracted integer as an int
                        int value = Integer.parseInt(matcher.group(1));
                        totalPrice += value;
                    }

                    ProductDataModel data = new ProductDataModel(name, priceString, location, period);
                    dataList.add(data);
                }
                productList_ = dataList;
                cartProductListAdapter_.setProductList(productList_);

                // Set the total price TextView
                binding_.totalprice.setText(String.valueOf(totalPrice));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error fetching data", databaseError.toException());
            }
        });

    }

    private void makePayment() {
        Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_test_tlIPAOzKDu4Npp");
            checkout.setImage(R.drawable.logo2);


            final Activity activity = getActivity();

            try {
                JSONObject options = new JSONObject();

                String totalPrice = binding_.totalprice.getText().toString();
                options.put("name", "Merchant Name");
                options.put("description", "Reference No. #123456");
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
               // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                options.put("amount", totalPrice+"00" );//pass amount in currency subunits
                options.put("prefill.email", "gaurav.kumar@example.com");
                options.put("prefill.contact","9579099597");
                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                checkout.open(activity, options);

            } catch(Exception e) {
                Log.e(TAG, "Error in starting Razorpay Checkout", e);
            }
        }

    @Override
    public void onPaymentSuccess(String s) {
        Log.e(TAG, "Successfully payment is done "+s);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.e(TAG, "payment is faild" +s);

    }
}




