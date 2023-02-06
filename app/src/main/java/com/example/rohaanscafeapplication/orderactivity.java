package com.example.rohaanscafeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class orderactivity extends AppCompatActivity {
TextView txt,updatetxt, deleteacc;
ImageView image;
Button btn, logoutoutbutton;
int pic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderactivity);
        txt = findViewById(R.id.ordertext);
        image = findViewById(R.id.orderImage);
        btn = findViewById(R.id.orderbutton);
        updatetxt = findViewById(R.id.updateText);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Log Out");
        progressDialog.setMessage("Please wait");
        logoutoutbutton= findViewById(R.id.logout);
        deleteacc = findViewById(R.id.deleteaccount);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(orderactivity.this, " Your order has been placed, It will reach to you after 30-45 mints, Thank you",Toast.LENGTH_SHORT).show();
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();


        String fooditem = getIntent().getStringExtra("Foodname");
        pic = getIntent().getIntExtra("foodpic",0); // 0 will show transaparent or nothing if image is not sent (default value)
        image.setImageResource(pic);

        DatabaseReference databaseReference = database.getReference().child("Itemdetails").child(fooditem).child("price");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object value = snapshot.getValue();
                txt.setText("Food Item: "+fooditem+ "\nPrice of this item is: "+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        updatetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(orderactivity.this,UpdateEmail.class);
                startActivity(intent);

            }
        });

        logoutoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signOut();
                        startActivity(new Intent(orderactivity.this, MainActivity.class));
                        progressDialog.dismiss();
                    }
                },1000);

            }
        });

        deleteacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent = new Intent(orderactivity.this,DeleteUser.class);
                        startActivity(intent);
                    }
                },1000);

            }
        });





    }
}