package com.example.home3;

import static com.example.home3.R.id.addbtn1;
import static com.example.home3.R.id.bottom_navigation;
import static com.example.home3.R.id.home;
import static com.example.home3.R.id.person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.home3.databinding.ActivityMain2Binding;
import com.example.home3.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
ActivityMain2Binding binding;

//private static final int FGGG = R.id.cart;
    @SuppressLint("ConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replceFragment(new homefrag());
        binding.bottomNavigation.setBackground(null);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replceFragment(new homefrag());
            } else if (item.getItemId() == R.id.cart) {
                replceFragment(new cartfrag());
            }  else if (item.getItemId() == R.id.notification) {
                replceFragment(new notificationfrag());
            } else if (item.getItemId() == R.id.person) {
                replceFragment(new personfrag());
            } else {
                return false;
            }
            return false;
        });
        binding.addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replceFragment(new addfrag());
            }
        });
    }




    private void replceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}