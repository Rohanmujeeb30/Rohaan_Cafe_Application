package com.example.rohaanscafeapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rohaanscafeapplication.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
ActivityRegistrationBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseFirestore firestore;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait");
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firestore=FirebaseFirestore.getInstance();

        binding.btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!binding.regname.getText().toString().equalsIgnoreCase("")&& !binding.regemail.getText().toString().equalsIgnoreCase("")
                        &&!binding.regpassword.getText().toString().equalsIgnoreCase("")
                        && !binding.regphone.getText().toString().equalsIgnoreCase("")){
                        progressDialog.show();

                    auth.createUserWithEmailAndPassword(binding.regemail.getText().toString(),
                            binding.regpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String name = binding.regname.getText().toString();
                                String email = binding.regemail.getText().toString();
                                String password = binding.regpassword.getText().toString();
                                String phonenumber = binding.regphone.getText().toString();
                                HashMap user = new HashMap();
                                user.put("Name",name);
                                user.put("Email Address",email);
                                user.put("Password",password);
                                user.put("Phone Number",phonenumber);
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("User Details").child(id).setValue(user);
                                firestore.collection("User Details").document(id).set(user);
                                Intent intent = new Intent(RegistrationActivity.this, ItemsActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                finish();
                                Toast.makeText(getApplicationContext(),"Successfully Created your account",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(RegistrationActivity.this,"Please enter the value first",Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.regtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegistrationActivity.this, Loginactivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                        finish();
                    }
                },1000);
            }
        });

        if(auth.getCurrentUser()!= null)
        {
            Intent intent = new Intent(RegistrationActivity.this, ItemsActivity.class);
            startActivity(intent);
            Toast.makeText(RegistrationActivity.this, "You're already logged in",Toast.LENGTH_SHORT).show();
            finish();
        }


    }
}