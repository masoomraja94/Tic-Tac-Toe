package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView player1Text;
    private TextView player2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Text = findViewById(R.id.text_view_p1);
        player2Text = findViewById(R.id.text_view_p2);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String Button_ID = "button_"+i+j;
                int Res_ID = getResources().getIdentifier(Button_ID,"id",getPackageName());
                buttons[i][j] = findViewById(Res_ID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button button_Reset = findViewById(R.id.button_reset);
        button_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!(((Button) v).getText().toString().equals("")))
            return;

        if(player1Turn)
        {
            ((Button) v).setText("X");
        }
        else
        {
            ((Button) v).setText("O");
        }

        roundCount++;

        if(CheckForWin())
        {
            if(player1Turn)
            {
                player1Wins();
            }
            else
            {
                player2Wins();
            }
        }
        else
            if(roundCount==9)
            {
                draw();
            }
            else
            {
                player1Turn = !player1Turn;
            }
    }

    private boolean CheckForWin()
    {
        String[][] field = new String[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
                return(true);
        }
        for(int j=0;j<3;j++)
        {
            if(field[0][j].equals(field[1][j]) && field[0][j].equals(field[2][j]) && !field[0][j].equals(""))
                return(true);
        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
            return(true);
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
            return(true);

        return(false);
    }

    private void player1Wins()
    {
        player1Points++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins()
    {
        player2Points++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw()
    {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText()
    {
        player1Text.setText("Player 1 : "+player1Points);
        player2Text.setText("Player 2 : "+player2Points);
    }

    private void resetBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;
    }

    private void resetGame()
    {
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }
}
