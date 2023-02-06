package com.example.rohaanscafeapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rohaanscafeapplication.databinding.ActivityLoginactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Loginactivity extends AppCompatActivity {
ActivityLoginactivityBinding binding;
FirebaseAuth auth;
ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait");




        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!binding.loginemail.getText().toString().equalsIgnoreCase("")&&
                !binding.loginpassword.getText().toString().equalsIgnoreCase(""))
                {
                    progressDialog.show();
                    auth.signInWithEmailAndPassword(binding.loginemail.getText().toString(),binding.loginpassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful())
                                 {

                                     if(binding.checkBox.isChecked()) {
                                         //Saving the data into Local Database;
                                         String email = binding.loginemail.getText().toString();
                                         String password = binding.loginpassword.getText().toString();

                                         SharedPreferences sharedPreferences = getSharedPreferences("Local Database",MODE_PRIVATE);
                                         SharedPreferences.Editor editor = sharedPreferences.edit();
                                         editor.putString("Email Address", email);
                                         editor.putString("Password",password);
                                         editor.apply();
                                     }
                                     DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                     HashMap user = new HashMap();
                                     user.put("Password",binding.loginpassword.getText().toString());
                                     databaseReference.child("User Details").child(auth.getCurrentUser().getUid()).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                         @Override
                                         public void onComplete(@NonNull Task task) {
                                             if(task.isSuccessful()){
                                                 Log.d(TAG,"OK! Works fine!");
                                             }
                                             else{
                                                 Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     });



                                     progressDialog.dismiss();
                                     Intent intent = new Intent(Loginactivity.this, ItemsActivity.class);
                                     startActivity(intent);
                                     Toast.makeText(Loginactivity.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                                     finish();


                                 }

                                 else
                                 {
                                     progressDialog.dismiss();

                                     Toast.makeText(Loginactivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                 }

                                }
                            });
                }
                else
                {
                    Toast.makeText(Loginactivity.this,"Please enter email or password",Toast.LENGTH_SHORT).show();
                }
            }

        });
        //Display the save data from Shared Preferences;
        SharedPreferences getShared = getSharedPreferences("Local Database",MODE_PRIVATE);
        String value = getShared.getString("Email Address","");
        String value1 = getShared.getString("Password","");
        binding.loginemail.setText(value);
        binding.loginpassword.setText(value1);





//        if (auth.getCurrentUser()!=null){
//
//            Intent intent = new Intent(Loginactivity.this, ItemsActivity.class);
//            startActivity(intent);
//
//        }

        binding.logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Loginactivity.this, RegistrationActivity.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }
                },1000);
            }
        });

        if(auth.getCurrentUser()!= null)
        {
            Intent intent = new Intent(Loginactivity.this, ItemsActivity.class);
            Toast.makeText(Loginactivity.this,"You're already logged in",Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }

        binding.forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent = new Intent(Loginactivity.this, ForgetPassword.class);
                        startActivity(intent);
                    }
                },1000);
            }
        });






    }

}