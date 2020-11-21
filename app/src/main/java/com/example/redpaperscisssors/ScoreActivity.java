package com.example.redpaperscisssors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView playerScore = (TextView) findViewById(R.id.playerScore);
        TextView aiScore = (TextView) findViewById(R.id.aiScore);
        playerScore.setText(String.valueOf(MainActivity.playerWins));
        aiScore.setText(String.valueOf(MainActivity.aiWins));
        changeView();
    }

    private void changeView()
    {
        Button button = (Button) findViewById(R.id.game);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}