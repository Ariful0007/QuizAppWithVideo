package com.ariful.quizappwithvideo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText phone_number_text;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Button generate_btn, generate_btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        phone_number_text = findViewById(R.id.phone_number_text);
        generate_btn = findViewById(R.id.generate_btn);
        generate_btn1 = findViewById(R.id.generate_btn1);
        generate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phone_number_text.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(phone + "@gmail.com", "123456")
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Register done", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        generate_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterACtivity.class));
            }
        });
    }
}