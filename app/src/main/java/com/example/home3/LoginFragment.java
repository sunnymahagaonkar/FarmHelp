package com.example.home3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.home3.databinding.FragmentDashfragBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginFragment extends Fragment {
    EditText Remail, Rpassword;
    Button loginbtn, registerbtn1;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
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
        //View view=inflater.inflate(R.layout.fragment_dashfrag,container, false);
        binding = FragmentDashfragBinding.inflate(inflater, container, false);
        binding = FragmentDashfragBinding.inflate(getLayoutInflater());
        Remail = binding.lemail;
        Rpassword = binding.lpassword;
        loginbtn = binding.lloginbtn;
        registerbtn1 = binding.lregisterbtn1;
        progressBar = binding.progressBar2;

        Remail = Remail.findViewById(R.id.lemail);
        Rpassword = Rpassword.findViewById((R.id.lpassword));
        loginbtn = loginbtn.findViewById(R.id.lloginbtn);
        registerbtn1 = registerbtn1.findViewById(R.id.lregisterbtn1);
        progressBar = progressBar.findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();


        binding.lloginbtn.setOnClickListener(new View.OnClickListener() {
            //            @Override
            public void onClick(View v) {
                String email = Remail.getText().toString().trim();
                String password = Rpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Remail.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Rpassword.setError("password is required");
                    return;
                }
                if (password.length() < 6) {
                    Rpassword.setError("password must be atleast contain 6 letter");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            ProfileHolder.currentUser = snapshot.getValue(Users.class);
                                            Intent intent = new Intent(getContext(), personfrag.class);
                                            startActivity(new Intent(getContext(), MainActivity2.class));
                                            Toast.makeText(getContext(), "User logged in succesfully", Toast.LENGTH_SHORT).show();
                                            // finish();

                                        }



                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                        } else {
                            Toast.makeText(getContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });


////
//                Intent intent=new Intent(getContext(),MainActivity2.class);
//                startActivity(intent);
            }
        });

        binding.lregisterbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegisterFragment registerFragment = RegisterFragment.newInstance();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.screen4, registerFragment);
                transaction.commit();
                binding.screen3.setVisibility(View.GONE);
                binding.screen4.setVisibility(View.VISIBLE);


            }
        });


        // Inflate the layout for this fragment
        //return binding.getRoot();
        return binding.getRoot();


    }
}