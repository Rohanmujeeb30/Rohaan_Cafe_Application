package com.example.rohaanscafeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rohaanscafeapplication.Adapters.RecipeAdapter;
import com.example.rohaanscafeapplication.Models.RecipeModel;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

      Data();
    }

    private void Data() {
        recyclerView = findViewById(R.id.recylerView);

        ArrayList<RecipeModel> list=new ArrayList<>();

        list.add(new RecipeModel(R.drawable.food1,"Chicken Tikka"));
        list.add(new RecipeModel(R.drawable.malaitikka, "Chicken Malai Tikka"));
        list.add(new RecipeModel(R.drawable.biharitikka, "Chicken Bihari Tikka"));
        list.add(new RecipeModel(R.drawable.food1,"Chicken Tikka"));
        list.add(new RecipeModel(R.drawable.malaitikka, "Chicken Malai Tikka"));
        list.add(new RecipeModel(R.drawable.biharitikka, "Chicken Bihari Tikka"));
        list.add(new RecipeModel(R.drawable.food1,"Chicken Tikka"));
        list.add(new RecipeModel(R.drawable.malaitikka, "Chicken Malai Tikka"));
        list.add(new RecipeModel(R.drawable.biharitikka, "Chicken Bihari Tikka"));
        list.add(new RecipeModel(R.drawable.food1,"Chicken Tikka"));
        list.add(new RecipeModel(R.drawable.malaitikka, "Chicken Malai Tikka"));
        list.add(new RecipeModel(R.drawable.biharitikka, "Chicken Bihari Tikka"));
        list.add(new RecipeModel(R.drawable.food1,"Chicken Tikka"));
        list.add(new RecipeModel(R.drawable.malaitikka, "Chicken Malai Tikka"));
        list.add(new RecipeModel(R.drawable.biharitikka, "Chicken Bihari Tikka"));






        RecipeAdapter adapter = new RecipeAdapter(list,this);
        recyclerView.setAdapter(adapter);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

}