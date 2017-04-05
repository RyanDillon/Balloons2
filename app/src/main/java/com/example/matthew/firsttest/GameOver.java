package com.example.matthew.firsttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Bundle b = getIntent().getExtras();
        int value = -1;
        if(b != null)
            value = b.getInt("score");
        TextView tv = (TextView) findViewById(R.id.textView8);
        tv.setText("Score: "+value);
        Button button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGameActivity();
            }
        });
        Button button2 = (Button) findViewById(R.id.button12);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFirstActivity();
            }
        });

    }
    private void goToFirstActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
    private void goToGameActivity() {

        Intent intent = new Intent(this, GameAct.class);

        startActivity(intent);
    }
}
