package com.vincent.hangman;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        aB();

        TextView result = (TextView) findViewById(R.id.results);

        TextView finalword = (TextView) findViewById(R.id.theWord);
        TextView triesLeft = (TextView) findViewById(R.id.trieskvar);
        TextView bkToMenuBtn = (TextView) findViewById(R.id.bkToMain);


        finalword.setText("Ordet var: " + getIntent().getStringExtra("word"));
        triesLeft.setText("Antal försök kvar: " + Game.tries);
        realResult(result);

        bkToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.uWord.clear();
                Game.wGuessed.clear();
                Game.tries = 9;
                Game.result = "";
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }

    public void realResult(TextView result) {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (getIntent().getStringExtra("WON/Loss").equals("won")) {
            result.setText("Grattis! du vann!");
            actionBar.setTitle("Gött mos");

        }
        if (getIntent().getStringExtra("WON/Loss").equals("loss")) {
            result.setText("Tyvärr, du förlorade!");
            actionBar.setTitle("Bara på det igen =) ");
        }


    }

    public void aB() {// Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#009688"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.MenuResult) {
            Game.reset();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return true;
        }
        if (id == R.id.InfoResult) {
            Game.reset();
            startActivity(new Intent(getApplicationContext(), About.class));
            return true;
        }
        if (id == R.id.PlayResult) {
            Game.reset();
            startActivity(new Intent(getApplicationContext(), Game.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


}