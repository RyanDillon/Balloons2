package com.example.matthew.firsttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class GameAct extends AppCompatActivity {
    DrawBalloon v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v=new DrawBalloon(this);
        setContentView(v);
    }
}
