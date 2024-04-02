package com.example.home3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.home3.databinding.FragmentHomefragBinding;
import com.google.android.material.button.MaterialButton;


public class homefrag extends Fragment {
    FragmentHomefragBinding binding;
    public CardView card1, card2;

    MaterialButton button;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomefragBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment

        binding.productcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), productsact.class);
                startActivity(intent);
            }

        });

        binding.servicecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(getContext(), servicesact.class);
                startActivity(intent1);
            }
        });
return binding.getRoot();
    }
}










