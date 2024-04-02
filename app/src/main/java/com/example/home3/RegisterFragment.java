package com.example.home3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home3.databinding.FragmentDashfragBinding;
import com.example.home3.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterFragment extends Fragment {

    FragmentRegisterBinding fragmentRegisterBinding;
    String name, email, password, phone;
    FirebaseDatabase db;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


//    EditText Rname, Remail, Rpassword, Rphone;
//    Button registerbtn2;
//    TextView loginbtn2;
//    FirebaseAuth firebaseAuth;
//    ProgressBar progressBar;


    //    public RegisterFragment() {
//        // Required empty public constructor
//    }
//
    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(getLayoutInflater());
        fragmentRegisterBinding.getRoot();
        firebaseAuth=FirebaseAuth.getInstance();

        fragmentRegisterBinding.registerbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = fragmentRegisterBinding.name.getText().toString();
                email = fragmentRegisterBinding.email.getText().toString();
                password = fragmentRegisterBinding.password.getText().toString();
                phone = fragmentRegisterBinding.phone.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    storeCredentials();
                    Users users = new Users(name, email, password, phone);
                    db = FirebaseDatabase.getInstance();
                    databaseReference = db.getReference("Users");
                    databaseReference.child(name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            fragmentRegisterBinding.name.setText("");
                            fragmentRegisterBinding.email.setText("");
                            fragmentRegisterBinding.password.setText("");
                            fragmentRegisterBinding.phone.setText("");

                        }

                    });

                }
            }
        });

        return fragmentRegisterBinding.getRoot();
    }

    private void storeCredentials() {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Bundle args = new Bundle();
                    args.putString("name", name);
                    args.putString("email", email);
                    args.putString("phone", phone);

                    personfrag personFragment = new personfrag();
                    personFragment.setArguments(args);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.personfragcont, personFragment);
                    fragmentTransaction.commit();
                    Toast.makeText(getContext(), "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                } else {
                    Toast.makeText(getContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
}



// Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_register, container, false);
//        Rname = view.findViewById(R.id.name);
//        Remail = view.findViewById(R.id.email);
//        Rpassword = view.findViewById(R.id.password);
//        Rphone = view.findViewById(R.id.phone);
//        registerbtn2 = view.findViewById(R.id.registerbtn2);
//        loginbtn2 = view.findViewById(R.id.createtext);
//         firebaseAuth = FirebaseAuth.getInstance();
//        progressBar = view.findViewById(R.id.progressBar);

//        if (firebaseAuth.getCurrentUser() != null) {
//           // startActivity(new Intent(getContext(), MainActivity2.class));
//        }

//
//
//                    }
//                });

//            loginbtn2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getContext(), LoginFragment.class));
//                }
//            });


//            }
//
//            //private FragmentManager getSupportFragmentManager() {
//            //return null;
//            //}
//
//        });
//
//        return view;
//    }
//
//
//}

//  @SuppressLint("WrongViewCast")
// @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       Rname= Rname.findViewById(R.id.name);
//        Remail= Remail.findViewById(R.id.email);
//        Rpassword= Rpassword.findViewById(R.id.password);
//        Rphone= Rphone.findViewById(R.id.phone);
//        registerbtn2= registerbtn2.findViewById(R.id.registerbtn2);
//        loginbtn=loginbtn.findViewById(R.id.createtext);
//        firebaseAuth= FirebaseAuth.getInstance();
//        progressBar=progressBar.findViewById(R.id.progressBar);


// }