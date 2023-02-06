package com.example.rohaanscafeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateEmail extends AppCompatActivity {
EditText editText1,editText2;
Button btn;
FirebaseAuth auth;
FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_email);
        auth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.upDateButton);
        editText1 = findViewById(R.id.updemail1);
        editText2=findViewById(R.id.updateEmail);
        firebaseUser = auth.getCurrentUser();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= editText1.getText().toString();
                String email1 = editText2.getText().toString();
                if(!email.equalsIgnoreCase("")&& !email1.equalsIgnoreCase("")){
                    firebaseUser.updateEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                HashMap hashMap = new HashMap();
                                hashMap.put("Email Address",email1);
                                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("User Details").child(firebaseUser.getUid()).updateChildren(hashMap);
                                auth.signOut();
                                startActivity(new Intent(UpdateEmail.this,MainActivity.class));
                                Toast.makeText(UpdateEmail.this, "Your email is updated! Please Login Again", Toast.LENGTH_SHORT).show();
                                 finish();
                            }
                            else{
                                Toast.makeText(UpdateEmail.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
            }
        });




    }


}




