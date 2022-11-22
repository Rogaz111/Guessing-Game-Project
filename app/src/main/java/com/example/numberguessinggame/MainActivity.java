package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioTwo,radioThree,radioFour;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buttonStart = findViewById(R.id.buttonStart);
        radioTwo = findViewById(R.id.radioTwo);
        radioThree = findViewById(R.id.radioThree);
        radioFour = findViewById(R.id.radioFour);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,GameActivity.class);

                if(!radioTwo.isChecked() && !radioTwo.isChecked() && !radioTwo.isChecked()){
                    Snackbar.make(view,"Please select a number of digits",Snackbar.LENGTH_LONG).show();
                }else{

                    if(radioTwo.isChecked()){
                        intent.putExtra("two",true);
                    }
                    if(radioThree.isChecked()){
                        intent.putExtra("three",true);
                    }
                    if(radioFour.isChecked()){
                        intent.putExtra("four",true);
                    }

                    startActivity(intent);
                }
            }
        });
    }
}