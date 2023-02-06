package com.example.rohaanscafeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rohaanscafeapplication.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
ActivityForgetPasswordBinding binding;
FirebaseAuth auth;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sending you an email");
        progressDialog.setMessage("Please wait");
        binding.resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if(!binding.resetemail.getText().toString().equalsIgnoreCase("")){

                    auth.sendPasswordResetEmail(binding.resetemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(ForgetPassword.this,"Email has been sent",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetPassword.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(ForgetPassword.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(ForgetPassword.this,"Please enter the email first",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}