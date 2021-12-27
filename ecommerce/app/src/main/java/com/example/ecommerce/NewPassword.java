package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewPassword extends AppCompatActivity {

    TextView username;
    EditText pass,repass;
    Button resetbtn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        DB=new DBHelper(this);
        username=(TextView) findViewById(R.id.usrnm);
        pass= (EditText) findViewById(R.id.pw);
        repass=(EditText) findViewById(R.id.repass);

        resetbtn=(Button) findViewById(R.id.button4);
        //Intent intent = new Intent();
        username.setText(getIntent().getStringExtra("username"));
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String password=pass.getText().toString();
                String repassword=repass.getText().toString();
                if(password.equals(repassword)) {


                    Boolean checkPassUpdate = DB.updatePassword(user, password);
                    if (checkPassUpdate == true) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        Toast.makeText(NewPassword.this, "Password updated successfullu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewPassword.this, "Password update failed", Toast.LENGTH_SHORT).show();
                    }
                }

                else
                {
                    Toast.makeText(NewPassword.this,"Password doesnt match",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}