package com.example.home3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home3.databinding.FragmentHomefragBinding;
import com.google.android.material.button.MaterialButton;


public class homefrag extends Fragment {
FragmentHomefragBinding binding;

MaterialButton button;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHomefragBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
       binding.productcard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
        return binding.getRoot();
    }
}
