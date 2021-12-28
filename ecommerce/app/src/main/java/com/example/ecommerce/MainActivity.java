package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText repass;
    Button signup;
    Button signin;

    boolean flag;
    TextView displayDate;
    private static final String TAG= "MainActivity";
    private DatePickerDialog.OnDateSetListener DateListener;



    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences selected_item = getSharedPreferences("PREF", 0);
        SharedPreferences.Editor editor = selected_item.edit();
        editor.putBoolean("is Search",false);
        editor.commit();
        getSupportActionBar().hide();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repass);
        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);

        flag=false;


        displayDate=(TextView) findViewById(R.id.textViewDate);

        DB = new DBHelper(this);


        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.YEAR);
                int DayOfMonth=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog= new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,DateListener,year,Month,DayOfMonth);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

            }
        });

        DateListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                Log.d(TAG,"onDateSet: mm/dd/yyy: " + month + "/"+dayOfMonth+"/"+year);

                String date= month + "/"+dayOfMonth+"/"+year;
                displayDate.setText(date);

                flag=true;


            }
        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String rep = repass.getText().toString();

                String date=displayDate.getText().toString();


                if(user.equals("")||pass.equals("")||rep.equals("")||flag==false)
                    Toast.makeText(MainActivity.this, "There is an empty field.", Toast.LENGTH_LONG).show();
                else
                {
                    if(pass.equals(rep)){
                        Boolean check = DB.checkuser(user);
                        if(check==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registeration Succeeded", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomePage.class);
                                startActivity(intent);



                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Sorry, User already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords do not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });




    }


}