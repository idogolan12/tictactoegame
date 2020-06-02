package com.example.tictactoegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int roundcount;
    private int activitycount = 1;
    private Button[][]  buttons = new Button[3][3];
    private boolean player1turn = true;
    private int player1points;
    private int player2points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1 = findViewById(R.id.tv_p1);
        textViewPlayer2 = findViewById(R.id.tv_p2);
        textViewPlayer1.setBackgroundColor(Color.rgb(0,255,0));
        for (int i = 0; i< 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setTextColor(Color.rgb(0,0,0));
                buttons[i][j].setOnClickListener(this);

            }
        }

    }
    public void reset(View view) {
        resetgame();
    }
    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if (player1turn){
            ((Button) v).setText("X");
        }
        else {
            ((Button) v).setText("O");
        }
        roundcount++;
        activitystatus();
        if (CheckForWins()){
            if (player1turn){
                player1wins();
            }
            else {
                player2wins();
            }
        }
        else if (roundcount == 9){
            draw();
        }
        else {
            player1turn = !player1turn;
        }
    }


    public boolean CheckForWins(){
        String[][] field = new String[3][3];
        for (int i = 0; i < 3 ;i++){
            for (int j = 0 ; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;

            }
        }
        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;

            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
    public void player1wins(){
        player1points++;
        Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
        UpdatePointsTable();
        resetBoard();
    }



    public void player2wins(){
        player2points++;
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
        UpdatePointsTable();
        resetBoard();
    }
    public void draw(){
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();

    }
    public void UpdatePointsTable(){
        textViewPlayer1.setText("Player 1: " + player1points);
        textViewPlayer2.setText("Player 1: " + player2points);
    }
    private void resetBoard() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        roundcount = 0;
        activitycount = 1;
        activitynum();
        player1turn = true;
    }
    public void resetgame(){
        resetBoard();
        player1points = 0;
        player2points = 0;
        UpdatePointsTable();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putInt("roundcount",roundcount);
        outState.putBoolean("player1turn",player1turn);
        outState.putInt("activitycount",activitycount);

        activitynum();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        roundcount = savedInstanceState.getInt("player1turn");
        player1turn = savedInstanceState.getBoolean("player1turn");
        activitycount =savedInstanceState.getInt("activitycount");

        activitynum();
    }
    public void activitystatus(){
        activitycount++;
        if (activitycount%2 == 0){
            textViewPlayer1.setBackgroundColor(Color.rgb(128,128,128));
            textViewPlayer2.setBackgroundColor(Color.rgb(0,255,0));
        }
        else {
            textViewPlayer1.setBackgroundColor(Color.rgb(0,255,0));
            textViewPlayer2.setBackgroundColor(Color.rgb(128,128,128));
        }
    }
    public void activitynum(){
        if (activitycount%2 == 0){
            textViewPlayer1.setBackgroundColor(Color.rgb(128,128,128));
            textViewPlayer2.setBackgroundColor(Color.rgb(0,255,0));
        }
        else {
            textViewPlayer1.setBackgroundColor(Color.rgb(0,255,0));
            textViewPlayer2.setBackgroundColor(Color.rgb(128,128,128));
        }
    }


}
