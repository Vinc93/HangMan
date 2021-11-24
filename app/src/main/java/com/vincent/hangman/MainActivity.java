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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();


        aB();


        TextView aboutBtn = (TextView) findViewById(R.id.About);
        TextView playBtn = (TextView)findViewById(R.id.Play);




        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,About.class);
                startActivity(intent);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Game.class);
                startActivity(intent);
            }
        });



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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.InfosMain) {
            Game.reset();
            startActivity(new Intent(getApplicationContext(), About.class));
            return true;
        }
        if (id == R.id.PlayMain) {
            Game.reset();
            startActivity(new Intent(getApplicationContext(), Game.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}