package com.qilong.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{


    private Button btnSave,btnGetAllData;
    private EditText editName, editPunchSpeed, editPunchPower, editKickSpeed,editKickPower;
    private TextView txtGetData;

    private String allKickBoxers;
    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        editName = findViewById(R.id.editName);
        editPunchSpeed = findViewById(R.id.editPunchSpeed);
        editPunchPower = findViewById(R.id.editPunchPower);
        editKickSpeed = findViewById(R.id.editKickSpeed);
        editKickPower = findViewById(R.id.editKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);


        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("gpwPcAPCWY", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e == null){
                            txtGetData.setText(object.get("name") + ""
                                    + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

                //queryAll.whereGreaterThan("punchPower",100);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e == null){
                            if(objects.size() >0){

                                for(ParseObject kickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUp.this,allKickBoxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                            }else{
                                FancyToast.makeText(SignUp.this,"ERROR!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }

                    }
                });
            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,SignUpLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",editName.getText().toString());
        kickBoxer.put("punchSpeed",editPunchSpeed.getText().toString());
        kickBoxer.put("punchPower",editPunchPower.getText().toString());
        kickBoxer.put("kickSpeed",editKickSpeed.getText().toString());
        kickBoxer.put("kickPower",editKickPower.getText().toString());
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Toast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to server", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


}
