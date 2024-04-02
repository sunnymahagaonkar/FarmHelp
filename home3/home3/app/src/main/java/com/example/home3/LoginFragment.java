package com.example.home3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.home3.databinding.FragmentDashfragBinding;


public class LoginFragment extends Fragment {
    FragmentDashfragBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDashfragBinding.inflate(inflater,container, false);

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

binding.registerbtn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        RegisterFragment registerFragment= RegisterFragment.newInstance();
        FragmentTransaction transaction= getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.screen4,registerFragment);
        transaction.commit();
        binding.screen3.setVisibility(View.GONE);
        binding.screen4.setVisibility(View.VISIBLE);



    }
});


        // Inflate the layout for this fragment
        return binding.getRoot();



    }

}