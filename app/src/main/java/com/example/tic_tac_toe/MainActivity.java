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

    int scoreX = 0,scoreO = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tag_no = Integer.parseInt(img.getTag().toString());

        if (!gameActive) return; // Do nothing if the game is not active

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

        // Check for winner
        for (int[] winCondition : win_arr) {
            if (state[winCondition[0]] == state[winCondition[1]] &&
                    state[winCondition[1]] == state[winCondition[2]] &&
                    state[winCondition[0]] != 2) {
                String winner;
                gameActive = false;
                if (state[winCondition[0]] == 0) {
                    winner = "X won the game!";
                    scoreX++;
                } else {
                    winner = "O won the game!";
                    scoreO++;
                }
                TextView player_turn = findViewById(R.id.player_turn);
                player_turn.setText(winner);

                // Update scores
                TextView scoreTextView = findViewById(R.id.player_score);
                scoreTextView.setText("Score: X - " + scoreX + " | O - " + scoreO);
                return;
            }
        }

        // Check for a tie
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

    // Game Reset
    public void resetGame(View view) {
        gameActive = true;
        active = 0;
        for (int i = 0; i < state.length; i++) state[i] = 2;

        // Clear the board
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        // Reset turn display
        TextView player_turn = findViewById(R.id.player_turn);
        player_turn.setText("X's turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize score
        TextView scoreTextView = findViewById(R.id.player_score);
        scoreTextView.setText("Score: X - 0 | O - 0");
    }
}
