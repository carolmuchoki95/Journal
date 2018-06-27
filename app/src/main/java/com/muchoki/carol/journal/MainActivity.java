package com.muchoki.carol.journal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btn_login, btn_signup;
    private ProgressBar pb_loading;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_login = (Button) findViewById(R.id.btn_login);
        pb_loading=(ProgressBar) findViewById(R.id.progressBar);



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etUsername.getText().toString();
                String password= etPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!",
                            Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password!",
                            Toast.LENGTH_LONG).show();
                }


                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:"
                               +task.isSuccessful(), Toast.LENGTH_SHORT).show();

                               if (!task.isSuccessful()){
                                   Toast.makeText(MainActivity.this, "Authentication failed."+
                                          task.getException(), Toast.LENGTH_SHORT).show();
                               } else {
                                   startActivity(new Intent(MainActivity.this, JournalEntries.class ));
                                   finish();
                               }

                            }
                        });


            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),JournalEntries.class));
                finish();
            }
        });

    }

}




