package com.example.rohaanscafeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rohaanscafeapplication.Adapters.RecipeAdapter;
import com.example.rohaanscafeapplication.Models.RecipeModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        auth= FirebaseAuth.getInstance();


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.SETTINGS:
                Toast.makeText(this, "Settings closed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.LOGOUT:
                auth.signOut();
                Intent intent = new Intent(ItemsActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.DELETEACCOUNT:
                startActivity(new Intent(ItemsActivity.this,DeleteUser.class));
                break;

            case R.id.UPDATEEMAIL:
                startActivity(new Intent(ItemsActivity.this,UpdateEmail.class));
                break;
        }



        return true;
    }


}