package com.example.home3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class productsact extends AppCompatActivity {
    GridView gridView;
    SearchView searchView;
    CustomAdapter customAdapter;
    String[] fruitNames = {"Tamarind", "wheat", "turmeric", "watermelon", "oraginc product", "onion","garlic","flower","cotton","farmland","fruits","vegetables","livestock","livestock prod.","seeds","nursery"};
    int[] fruitImages = {R.drawable.termarind, R.drawable.wheat, R.drawable.turmeric, R.drawable.watermelon, R.drawable.organicproduct, R.drawable.onion, R.drawable.garlic, R.drawable.flowerr, R.drawable.cotton, R.drawable.farmlanf, R.drawable.fruits, R.drawable.vegetables, R.drawable.livestock, R.drawable.livestockpro, R.drawable.seeds, R.drawable.nursery};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productsact);
        searchView=findViewById(R.id.searchview);
        gridView = findViewById(R.id.gridview);
        arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,fruitNames);

         customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),grid_item.class);
                intent.putExtra("nameOfProduct", arrayAdapter.getItem(i));
                intent.putExtra("name",fruitNames[i]);
                intent.putExtra("image",fruitImages[i]);
                startActivity(intent);

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productsact.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productsact.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return fruitImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.wheatimg);

            name.setText(fruitNames[i]);
            image.setImageResource(fruitImages[i]);
            return view1;

        }
    }

}