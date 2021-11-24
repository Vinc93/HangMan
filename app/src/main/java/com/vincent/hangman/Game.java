package com.vincent.hangman;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {


    static final Random random = new Random();
    static ArrayList<String> list = new ArrayList<String>();
    static ArrayList<String> wGuessed = new ArrayList<String>();
    static ArrayList<String> uWord = new ArrayList<String>();
    static ArrayList<String> winners = new ArrayList<String>();
    static int tries = 9;
    static int counter = 0;
    static String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        list.add("Manchester");
        list.add("Pasta");
        list.add("Dator");
        list.add("Skrivbord");
        list.add("Restaurang");
        list.add("Blizzard");
        list.add("Mystify");
        list.add("Nightclub");
        list.add("Glyph");
        list.add("Unknown");
        list.add("Unworthy");
        list.add("Yxa");
        list.add("Magi");
        list.add("Öl");
        list.add("Tåg");
        list.add("Såg");
        list.add("Låg");
        list.add("Vraka");
        list.add("Etylendiamintetraättiksyradinatriumsalt");
        list.add("Liverpool");
        list.add("Barcelona");
        list.add("United");
        list.add("Pc");
        list.add("Applikation");
        list.add("Pizza");
        list.add("Kebab");
        list.add("Mjöl");
        list.add("Gymma");




        int randomInt = random.nextInt(list.size());
        ImageView img = (ImageView) findViewById(R.id.mainPic);
        EditText text = (EditText) findViewById(R.id.guessWord);
        TextView guess = (TextView) findViewById(R.id.guess);
        TextView wordsTried = (TextView) findViewById(R.id.wordsGuessed);
        TextView word = (TextView) findViewById(R.id.hiddenWord);
        TextView triesLeft = (TextView) findViewById(R.id.guessLeft);
        Init(randomInt, word, triesLeft, img);

        // checkRandom(randomInt, text,word);

        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameCheck(text, randomInt, wordsTried, triesLeft, word);

                //uWord.set(1,"b ");
                loser(randomInt);
                winner(randomInt);
                triesChange(img);
                System.out.println(counter);
                System.out.println(tries);
                counter = 0;
                text.getText().clear();
            }
        });


        aB();

    }

    public void aB() {// Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle("Lycka till! =) ");
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.MainMenu) {
            reset();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return true;
        }
        if (id == R.id.Info) {
            reset();
            startActivity(new Intent(getApplicationContext(), About.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public static void reset() {

        Game.result = "";
        Game.tries = 9;
        Game.uWord.clear();
        Game.wGuessed.clear();


    }

    public void Init(int randomInt, TextView word, TextView triesLeft, ImageView img) {


        triesLeft.setText(" Du har " + tries + " försök kvar!");


        String ranWord = list.get(randomInt);
        System.out.println(ranWord);


        img.setImageResource(R.drawable.zero);


        for (int i = 0; i < ranWord.length(); i++) {

            uWord.add("- ");
            System.out.println(uWord.get(i));
        }

        String name = "";
        for (int i = 0; i < uWord.size(); i++) {

            name = name + uWord.get(i);


            word.setText(name);


        }


    }

    public void addWord(EditText text, TextView wordsTried) {

        String count = "";
        String c = text.getText().toString().toUpperCase();

        wGuessed.add(c);

        for (int i = 0; i < wGuessed.size(); i++) {

            if (emptyCheck(text) == true) {
                count = count + "/" + wGuessed.get(i);

                wordsTried.setText("Använda ord: " + count);
            }
        }


    }

    public boolean checkWord(EditText text) {


        for (int i = 0; i < wGuessed.size(); i++) {
            if (text.getText().toString().toLowerCase().equals(wGuessed.get(i).toLowerCase())) {
                Toast.makeText(Game.this, "Du har redan använt detta ord!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (text.getText().toString().toUpperCase().equals(wGuessed.get(i).toUpperCase())) {

                System.out.println("Error");

                return false;

            } else System.out.println("No Errror");
        }

        return true;
    }


    /*public boolean checkWUsed(EditText text) {
        try {


            for (int i = 0; i < wGuessed.size(); i++) {
                if (text.getText().toString().toUpperCase().charAt(0) == (wGuessed.get(i).toString().toUpperCase().charAt(0))) {
                    System.out.println(wGuessed.get(i));
                    System.out.println("False");
                    return false;


                } else System.out.println("true");
                return true;


            }


        } catch (Exception e) {
            System.out.println("meh");
        }


        System.out.println("true");
        return true;


    }*/

    public boolean emptyCheck(EditText text) {
        if (TextUtils.isEmpty(text.getText().toString())
        ) {
            Toast.makeText(Game.this, "Inga tomma inmatningar snälla! xD", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }

    public boolean sizeTextCheck(EditText text){

        if(text.getText().toString().length()>1){
            Toast.makeText(Game.this, "Inte mer än ett ord", Toast.LENGTH_SHORT).show();
            return false;
        }else return true;

    }



    public void triesChange(ImageView img) {
        if (tries == 8) {
            img.setImageResource(R.drawable.one);
        }
        if (tries == 7) {
            img.setImageResource(R.drawable.two);
        }
        if (tries == 6) {
            img.setImageResource(R.drawable.three);
        }
        if (tries == 5) {
            img.setImageResource(R.drawable.four);
        }
        if (tries == 4) {
            img.setImageResource(R.drawable.five);
        }
        if (tries == 3) {
            img.setImageResource(R.drawable.six);
        }
        if (tries == 2) {
            img.setImageResource(R.drawable.seven);
        }
        if (tries == 1) {
            img.setImageResource(R.drawable.eigth);
        }
        if (tries == 0) {
            img.setImageResource(R.drawable.nine);
        }


    }

    public void winner(int randomInt) {
        System.out.println(result);
        System.out.println(list.get(randomInt));
        if (list.get(randomInt).toUpperCase().equals(result)) {
            Intent intent = new Intent();
            intent.setClass(this, Result.class);
            intent.putExtra("word", list.get(randomInt));
            intent.putExtra("WON/Loss", "won");

            startActivity(intent);
            System.out.println("U won!");
            Toast.makeText(Game.this, "U did it", Toast.LENGTH_SHORT).show();

        }


    }

    public void loser(int randomInt) {

        if (tries == 0) {
            Intent intent = new Intent();
            intent.setClass(this, Result.class);
            intent.putExtra("word", list.get(randomInt));
            intent.putExtra("triesLeft", tries);
            intent.putExtra("WON/Loss", "loss");

            startActivity(intent);
            Toast.makeText(Game.this, "U lost! =(", Toast.LENGTH_SHORT).show();
            System.out.println("Loser :(");
            //System.exit(1);

        }

    }

    public void GameCheck(EditText text, int randomInt, TextView wordsTried, TextView triesLeft, TextView word) {
        if(sizeTextCheck(text)==true){
        emptyCheck(text);
        try {
            if (checkWord(text) == false) {
                System.out.println("error");
            } else {
                for (int i = 0; i < uWord.size(); i++) {
                    System.out.println(uWord.get(i));
                    char ez = list.get(randomInt).toString().toUpperCase().charAt(i);
                    try {
                        char pls = text.getText().toString().toUpperCase().charAt(0);


                        if (pls == ez) {
                            System.out.println("YESPLS");

                            uWord.set(i, text.getText().toString().toUpperCase());
                            counter++;
                            System.out.println(counter);
                        } else if (pls != ez) {
                            System.out.println("fail!");

                        }


                    } catch (Exception e) {
                        System.out.println("Dont leave me blanked!");
                    }
                }

                if (counter == 0 && emptyCheck(text) == true) {
                    tries--;
                    triesLeft.setText(" Du har " + tries + " försök kvar!");
                }

                addWord(text, wordsTried);

            }
        } catch (Exception e) {
            System.out.println("No items yet");
        }

        String name = "";
        for (int i = 0; i < uWord.size(); i++) {

            name = name + uWord.get(i);

            result = name;
            word.setText(name);
            System.out.println(result);


        }


    }}

}
