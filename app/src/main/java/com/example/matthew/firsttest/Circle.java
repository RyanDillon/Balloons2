package com.example.matthew.firsttest;

/**
 * Created by Matthew on 2/9/2017.
 */
import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle {
    Paint p;
    float x;
    float y;
    float radius = 30;
    float xSpeed = 8;
    float ySpeed = 8;
    float screenWidth;
    float screenHeight;

    public Circle(Paint paint, float xSpot, float ySpot, float width, float height){
        p = paint;
        x = xSpot;
        y = ySpot;
        screenHeight = height;
        screenWidth = width;
    }

    public void draw(Canvas c){
        c.drawCircle(x, y, radius, p);
    }

    public void update(){

        if(x >= screenWidth) {
            xSpeed = xSpeed * -1;
        }else if(x <= 0){
            xSpeed = xSpeed * -1;
        }

        if(y >= screenHeight){
            ySpeed = ySpeed * -1;
        }else if(y <= 0){
            ySpeed = ySpeed * -1;
        }

        x = x + xSpeed;
        y = y + ySpeed;
    }
}

