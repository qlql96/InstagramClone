package com.qilong.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp, edtUserNameLogin, edtPasswordSignUp,edtPasswordLogin;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            Toast.makeText(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null && e == null){
                            Toast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in successfully", Toast.LENGTH_SHORT).show();


                        }else{
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });
    }
}
