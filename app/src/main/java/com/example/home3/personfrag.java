package com.example.home3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home3.databinding.FragmentPersonfragBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personfrag extends Fragment {
    TextView pname, pemail, ppassword, phone, padd, ppostal, userName;
    Button editbtn, logoutbtn;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    FragmentPersonfragBinding fragmentPersonfragBinding;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        fragmentPersonfragBinding = FragmentPersonfragBinding.inflate(inflater, container, false);
        fragmentPersonfragBinding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        pname = fragmentPersonfragBinding.profName;
        pemail = fragmentPersonfragBinding.profEmail;
        userName = fragmentPersonfragBinding.profPassword;
        phone = fragmentPersonfragBinding.mobnoname;


      user= FirebaseAuth.getInstance().getCurrentUser();
        Bundle args = getArguments();
        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();
            String phone = user.getPhoneNumber();
            String uname = user.getDisplayName();

//            String name = args.getString("name");
//            String email = args.getString("email");
//            String uphone = args.getString("phone");
//            String username = args.getString("userName");


            pname.setText(name);
            pemail.setText(email);

//            userName.setText(username);
//            phone.setText(uphone);

        }


        fragmentPersonfragBinding.logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        // Set click listener for edit button
//        fragmentPersonfragBinding.editbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editprofile(view);
//            }
//        });

       // showUserData();

        return fragmentPersonfragBinding.getRoot();
    }
}


//    public void showUserData() {
//
//        if (ProfileHolder.currentUser != null) {
//            // Set user information to TextViews
//            pname.setText(ProfileHolder.currentUser.getName());
//            pemail.setText(ProfileHolder.currentUser.getEmail());
//            ppassword.setText(ProfileHolder.currentUser.getPassword());
//            phone.setText(ProfileHolder.currentUser.getPhone());
//        } else {
//            Toast.makeText(getContext(), "User profile not found", Toast.LENGTH_SHORT).show();
//        }
//        Intent intent;
//        intent = new Intent(getContext(), MainActivity.class);
//        String uname = intent.getStringExtra(String.valueOf(pname));
//        String username = intent.getStringExtra(String.valueOf(userName));
//        String uphone = intent.getStringExtra(String.valueOf(phone));
//        String uemail = intent.getStringExtra(String.valueOf(pemail));


////        pname.setText(uname);
////        userName.setText(username);
////        phone.setText(uphone);
////        pemail.setText(uemail);
////        ppassword.setText();
//        pname.setText(user.getDisplayName());
//        pemail.setText(user.getEmail());
//        phone.setText(user.getPhoneNumber());
//        userName.setText(user.getDisplayName());

//
//    }
//        private void editprofile(View view) {
        // Implement edit profile functionality here
//        // You can access and modify the profile data using ProfileHolder.currentUser
//    }
//}

//
//
//
//


        // Check if currentUser is not null
//

    //}


//}
//}