package com.example.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLastScore,textViewRight,textViewViewHint;
    private EditText guessInput;
    private Button buttonConfirm;
//Create objects to store previous activity information(intent)
    boolean twoDigits,threeDigits,fourDigits;
// Create Random object from the Random class
    Random r = new Random();
    int random;


    int remainingRight = 10;

//Create Array List of guess to display in dialog

    ArrayList <Integer> guessList = new ArrayList<>();
    int userAttempts = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//Finding components by its respective ID's

        textViewLastScore = findViewById(R.id.textViewLastScore);
        textViewRight = findViewById(R.id.textViewRight);
        textViewViewHint = findViewById(R.id.textViewHint);

        guessInput = findViewById(R.id.editTextGuess);
        buttonConfirm = findViewById(R.id.buttonConfirm);

//Declare container values with keys created in previous intent

        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);

        if(twoDigits){
            random = r.nextInt(90)+10;
        }
        if(threeDigits){
            random = r.nextInt(900)+100;
        }
        if(fourDigits){
            random = r.nextInt(9000)+1000;
        }

        buttonConfirm.setOnClickListener(view -> {
            String guess = guessInput.getText().toString();

            if(guess.equals("")){
                Toast.makeText(GameActivity.this,"Please enter a guess value",Toast.LENGTH_LONG).show();
            }else{
                textViewLastScore.setVisibility(View.VISIBLE);
                textViewRight.setVisibility(View.VISIBLE);
                textViewViewHint.setVisibility(View.VISIBLE);
//On each user submit click with value in text controller attempts increase
                userAttempts++;
//Each incorrect guess remaining decreases with one
                remainingRight--;

                int userGuess = Integer.parseInt(guess);
// Add user guess to array
                guessList.add(userGuess);

                textViewLastScore.setText("Your Last Guess is: "+guess);
                textViewRight.setText("Your remaining rights are: "+remainingRight);

                if(random == userGuess){

                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("Number Guessing Game");
                    builder.setCancelable(false);
                    builder.setMessage("Congrats. The Robots Guess was: "+ random +
                            " " +
                            "\n Attempts it took you: "+userAttempts + " " +"attempts."
                            +"\n Your guesses were: "+ guessList + "\n Do you want to play again?");
//Process to either return to home screen or exit the application

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(GameActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);

                        }
                    });

                    builder.create().show();
                }

                if(random < userGuess){
                    textViewViewHint.setText("Decrease your guess");
                }

                if(random > userGuess){
                    textViewViewHint.setText("Increase your guess");
                }

                if(remainingRight == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("Number Guessing Game");
                    builder.setCancelable(false);
                    builder.setMessage("Sorry your Guess Attempts are depleted" +
                            "\nAttempts it took you: "+userAttempts + " " +"attempts."
                            +"\nYour guesses were: "+ guessList + "\n Do you want to play again?");
//Process to either return to home screen or exit the application

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(GameActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);

                        }
                    });

                    builder.create().show();

                }

                guessInput.setText("");
            }
        });
    }

}