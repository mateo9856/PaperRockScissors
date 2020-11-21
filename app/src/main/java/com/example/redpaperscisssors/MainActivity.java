package com.example.redpaperscisssors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Drawable SetActiveColor(View v) {
        ShapeDrawable shape = new ShapeDrawable();
        shape.getPaint().setColor(Color.GREEN);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(8);
        return shape;
    }

    private Drawable AIChoiceColor(View v) {
        ShapeDrawable shape = new ShapeDrawable();
        shape.getPaint().setColor(Color.RED);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(8);
        return shape;
    }

    public static Boolean isClick = true;
    Map<Integer, ImageView> activeThing = new LinkedHashMap<>();
    public static int playerWins = 0;
    public static int aiWins = 0;
    String activeString = "";
    String aiString = "";
    Drawable deleteDraw = new ColorDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn = (Button) findViewById(R.id.button);

        //zmiana contentu
        Button renderScores = (Button) findViewById(R.id.staticstics);
        renderScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
                Log.i("isWork", "work");
            }
        });


        this.addAttributesOrSetFalse();
        for (final Map.Entry<Integer, ImageView> value : activeThing.entrySet()) {
            value.getValue().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//po kliknieciu wypierdala apke sprobowac rozwiazac w onClick
                    //activeString = v.getResources().getResourceName(v.getId());
                    v.setForeground(SetActiveColor(v));
                    switch (value.getKey()) {
                        case 0:
                            activeString = "paper";
                            changeBorder(1, 2);
                            break;
                        case 1:
                            activeString = "rock";
                            changeBorder(0, 2);
                            break;
                        case 2:
                            activeString = "scissors";
                            changeBorder(0, 1);
                            break;
                        default:
                            break;
                    }

                    btn.setVisibility(View.VISIBLE);
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mechanika po kliknieciu kto wygrał
                if (isClick) {
                    int randomNum = (int) Math.floor(Math.random() * 3);
                    if (randomNum == 0) {
                        aiString = "paper";
                    } else if (randomNum == 1) {
                        aiString = "rock";
                    } else {
                        aiString = "scissors";
                    }
                    isClick = false;
                    activeThing.get(randomNum).setForeground(AIChoiceColor(v));
                    checkWhoWin();
                    btn.setText("PLAY AGAIN!");
                } else {
                    PlayAgain();
                    btn.setText("PLAY!");
                    TextView resetGame = (TextView) findViewById(R.id.whoWin);
                    resetGame.setVisibility(View.INVISIBLE);
                    isClick = true;
                }


            }
        });

    }

    private void addAttributesOrSetFalse() {
        activeThing.put(0, (ImageView) findViewById(R.id.paper));
        activeThing.put(1, (ImageView) findViewById(R.id.rock));
        activeThing.put(2, (ImageView) findViewById(R.id.scissors));
    }

    private void changeBorder(int num1, int num2) {
        activeThing.get(num1).setForeground(null);
        activeThing.get(num2).setForeground(null);
    }

    private void checkWhoWin() {
        TextView winnerText = (TextView) findViewById(R.id.whoWin);
        if (activeString == "paper" && aiString == "rock" || activeString == "rock" && aiString == "scissors" || activeString == "scissors" && aiString == "paper") {
            winnerText.setText("You Win!");
            ++playerWins;
        } else if (aiString == "paper" && activeString == "rock" || aiString == "rock" && activeString == "scissors" || aiString == "scissors" && activeString == "paper") {
            winnerText.setText("You LOSE!");
            ++aiWins;
        } else if (activeString == aiString) {
            winnerText.setText("DRAW");
        }
        winnerText.setVisibility(View.VISIBLE);
    }

    private void PlayAgain() {
        for(Map.Entry<Integer, ImageView> value : activeThing.entrySet()) {
            value.getValue().setForeground(null);
        }
    }


}