package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordRecovery extends AppCompatActivity {

    EditText username;
    Button reset;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        username =(EditText) findViewById(R.id.usernamereset);
        reset=(Button) findViewById(R.id.button3);
        DB= new DBHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                Boolean checkUser= DB.checkuser(user);
                if(checkUser==true)
                {
                    Intent intent = new Intent(getApplicationContext(), NewPassword.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PasswordRecovery.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}