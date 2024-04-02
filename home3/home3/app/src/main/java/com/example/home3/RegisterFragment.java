package com.example.home3;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home3.databinding.FragmentDashfragBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RegisterFragment extends Fragment {
  FragmentDashfragBinding binding;
    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}