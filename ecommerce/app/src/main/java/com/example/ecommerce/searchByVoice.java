package com.example.ecommerce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.speech.tts.TextToSpeech;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Locale;

public class searchByVoice extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT =1000 ;
    TextView txtv;
    ImageButton Voicebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_voice);

        txtv = findViewById(R.id.voicetxtv);
        Voicebtn=findViewById(R.id.voicebtn);
        Voicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }

    private void speak()
    {


        try {

            Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something");

            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,   Uri.parse("https://market.android.com/details?id=APP_PACKAGE_NAME"));
            startActivity(browserIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT:{if(resultCode==RESULT_OK && null!=data )
            {
                //get text arry from voice intent
                ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //set to text
                txtv.setText(result.get(0));
            }
            break;
            }
        }
    }
}