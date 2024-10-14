package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 --> X, 1 --> O, 2 --> Empty
    int active = 0;
    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] win_arr = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                       {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                       {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tag_no = Integer.parseInt(img.getTag().toString());

        if(!gameActive) game_Reset(view);
        if (state[tag_no] == 2 && gameActive) {
            state[tag_no] = active;
            img.setTranslationY(-1000f);

            if (active == 0) {
                img.setImageResource(R.drawable.cross);
                active = 1;
                TextView player_turn = findViewById(R.id.player_turn);
                player_turn.setText("O's turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                active = 0;
                TextView player_turn = findViewById(R.id.player_turn);
                player_turn.setText("X's turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int it[]:win_arr) {
            if(state[it[0]]==state[it[1]] && state[it[1]]==state[it[2]] && state[it[0]]!=2) {
                String winner;
                gameActive=false;
                if (state[it[0]] == 0) winner = "X won the game";
                else winner = "O won the game";
                TextView player_turn = findViewById(R.id.player_turn);
                player_turn.setText(winner);
                return;
            }
        }

        boolean tie = true;
        for (int i : state) {
            if (i == 2) {
                tie = false;
                break;
            }
        }

        if (tie) {
            gameActive = false;
            TextView player_turn = findViewById(R.id.player_turn);
            player_turn.setText("It's a tie!");
        }
    }
    public void game_Reset(View view) {
        gameActive=true;
        active=0;
        for (int i = 0; i < state.length; i++)   state[i] = 2;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView player_turn = findViewById(R.id.player_turn);
        player_turn.setText("X's turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
