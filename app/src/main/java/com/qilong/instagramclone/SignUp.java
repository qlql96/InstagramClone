package com.qilong.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void helloWorldTapped(View view){
        ParseObject boxer = new ParseObject("Boxer");
        boxer.put("punch_speed", 200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if(e == null){
                    FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true);
                    Toast.makeText(SignUp.this,"boxer object is saved",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
