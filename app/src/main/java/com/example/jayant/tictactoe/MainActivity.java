package com.example.jayant.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button a1,a2,a3,b1,b2,b3,c1,c2,c3,btNewGame;
    Button[] bArray;
    boolean turn = true;
    int turn_count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a1 = (Button)findViewById(R.id.a1);
        a2 = (Button)findViewById(R.id.a2);
        a3 = (Button)findViewById(R.id.a3);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        c1 = (Button)findViewById(R.id.c1);
        c2 = (Button)findViewById(R.id.c2);
        c3 = (Button)findViewById(R.id.c3);
        btNewGame = (Button)findViewById(R.id.btNewGame);

        bArray = new Button[]{a1,a2,a3,b1,b2,b3,c1,c2,c3};

        for(Button b : bArray){

            b.setOnClickListener(this);
        }

        btNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                turn = true;
                turn_count=0;
                enableDisbaleAllButtons(true);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        buttonClicked(b);

    }

    public void buttonClicked(Button b) {

        if(turn){
            b.setText("X");
        }else{
            b.setText("0");
        }

        turn_count++;
        b.setBackgroundColor(Color.LTGRAY);
        b.setClickable(false);
        turn = !turn;
        checkForWinner();

    }

    private void checkForWinner() {
         boolean winner = false;

         if(a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable())
             winner = true;
         else if(b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable())
             winner = true;
         else if(c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable())
             winner = true;
         else if(a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable())
             winner = true;
         else if(a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable())
             winner = true;
         else if(a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable())
             winner = true;
         else if(a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable())
             winner = true;
         else if(a3.getText() == b2.getText() && b2.getText() == c1.getText() && !b2.isClickable())
             winner = true;


         if(winner){

             if(!turn) {
                 toast("X is winner");
             }else{
                 toast("0 is winner");
             }

             enableDisbaleAllButtons(false);
         }else if(turn_count == 9){
             toast("Its a Draw");

         }



    }

    private void enableDisbaleAllButtons(boolean enable) {

        for(Button b : bArray){
            b.setClickable(enable);

            if(enable){
                b.setBackgroundColor(Color.parseColor("#33b5e5"));
                b.setText("");
            }
            else{
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
