package com.connect3g.aswin.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0: yellow , 1: red ,
    int activeColor = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean activeYellow = true;
    boolean activeRed = false;
    int[][] winningPostions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void selectArea(View view)
    {
        ImageView counter = (ImageView) view;
        String tag = counter.getTag().toString();

        if(activeYellow && gameActive && gameState[Integer.parseInt(tag)]==2)
        {
            counter.setTranslationY(-1500);
            activeYellow = false;
            activeRed = true;

            gameState[Integer.parseInt(tag)] = 0;

            counter.setImageResource(R.drawable.yellow);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(200);
        }

        else if(activeRed && gameActive && gameState[Integer.parseInt(tag)]==2)
        {
            counter.setTranslationY(-1500);
            activeRed = false;
            activeYellow = true;

            gameState[Integer.parseInt(tag)] = 1;

            counter.setImageResource(R.drawable.red);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(200);
        }

        String winner = "Winner is ";
        boolean checkTie = true;

        for(int[] winningPosition : winningPostions)
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[1]]!=2)
            {
                if(activeYellow)
                {
                    winner+="Red";
                    gameActive = false;
                    checkTie = false;
                    break;
                }

                else
                {
                    winner+="Yellow";
                    gameActive = false;
                    checkTie = false;
                    break;
                }
            }
            else
            {
                // Loop for checking whether all the boxes have been selected or not
                for(int i=0;i<gameState.length;i++)
                {
                    if(gameState[i]==2)
                    {
                        checkTie = false;
                        gameActive = true;
                        break;
                    }
                    else
                    {
                        checkTie = true;
                        gameActive = false;
                    }
                }
            }
        }


        //if someone wins or it becomes a tie
        if(!gameActive)
        {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setVisibility(View.VISIBLE);
            if(checkTie)
                textView.setText("Its a Tie");
            else
                textView.setText(winner);

            Button button = (Button) findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
        }

    }

    public void resetBoxes(View view)
    {
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridView);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activeYellow = true;

        gameActive = true;

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);

        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);

        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

    }
}
