package com.example.rohaanscafeapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class DeleteUser extends AppCompatActivity {
    EditText email,password;
    Button delbutton;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        delbutton= findViewById(R.id.deleteuserbutton);
        email = findViewById(R.id.deleteemail);
        password = findViewById(R.id.deletepassword);
        auth= FirebaseAuth.getInstance();




        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String emailaddress = email.getText().toString();
              String deletepassword = password.getText().toString();


              if(!emailaddress.equalsIgnoreCase("") && !deletepassword.equalsIgnoreCase("")){
                  AlertDialog.Builder alert = new AlertDialog.Builder(DeleteUser.this);
                  alert.setTitle("Are you sure ?");
                  alert.setMessage("You won't be able to sign in again");
                  alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference reference  =FirebaseDatabase.getInstance().getReference().child("User Details");
                        reference.child(auth.getCurrentUser().getUid()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    Log.d(TAG,"Realtime Database deleted!");
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                          auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if(task.isSuccessful()){
                                      Toast.makeText(getApplicationContext(),"Deleted account sucessfully",Toast.LENGTH_SHORT).show();
                                      Intent intent = new Intent(DeleteUser.this,MainActivity.class);
                                      startActivity(intent);
                                  }
                                  else{
                                      Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });
                      }
                  });
                  alert.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          dialog.dismiss();
                      }
                  });alert.show();
                }
              else{
                  Toast.makeText(getApplicationContext(),"Please enter the email or password first",Toast.LENGTH_SHORT).show();

              }
            }
        });



    }
}