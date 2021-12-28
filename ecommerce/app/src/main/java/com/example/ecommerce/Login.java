package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnlogin;
    DBHelper DB;
    public CheckBox rememberme;

    public Button forgot;
    public SharedPreferences mprefs;
    public static final String prefName="prefsfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mprefs = getSharedPreferences(prefName,MODE_PRIVATE);
        getSupportActionBar().hide();
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);
        rememberme=findViewById(R.id.rememberMe);
        forgot = (Button) findViewById(R.id.button2);

        SharedPreferences selected_item = getSharedPreferences("PREF", 0);
        SharedPreferences.Editor editor = selected_item.edit();
        editor.putBoolean("is Search",false);
        editor.commit();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(rememberme.isChecked())
                {
                    Boolean boolisChecked= rememberme.isChecked();
                    SharedPreferences.Editor editor= mprefs.edit();
                    editor.putString("prefName",username.getText().toString());
                    editor.putString("prefpass",password.getText().toString());
                    editor.putBoolean("prefcheck",boolisChecked);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"settings are saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    mprefs.edit().clear().apply();
                }

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkpass = DB.checkuserpassword(user, pass);
                    if (checkpass == false) {
                        Toast.makeText(Login.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Sign in Succeeded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                        username.getText().clear();
                        password.getText().clear();


                    }
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordRecovery.class);
                startActivity(intent);
            }
        });
        getPrefrencsData();
    }
    private void getPrefrencsData()
    {
        SharedPreferences sp= getSharedPreferences(prefName,MODE_PRIVATE);
        if(sp.contains("prefName"))
        {
            String u=sp.getString("prefName","not found");
            username.setText(u.toString());
        }
        if(sp.contains("prefpass"))
        {
            String p= sp.getString("prefpass","not found");
            password.setText((p.toString()));
        }
        if(sp.contains("prefcheck"))
        {
            Boolean b=sp.getBoolean("prefcheck",false);
            rememberme.setChecked(b);
        }
    }

}
