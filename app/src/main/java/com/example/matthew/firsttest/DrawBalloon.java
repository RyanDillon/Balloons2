package com.example.matthew.firsttest;

/**
 * Created by Matthew on 2/9/2017.
 */
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.Color;
import android.widget.Button;

import java.util.Random;

public class DrawBalloon extends View {
    Random xpos = new Random();
    Random sp = new Random();
    Random co = new Random();
    int numobjects=5;
    int score =0;
    int lives=5;
    int passes = 0;
    float rad = 0;
    int screenx=0;
    int screeny = 0;
    int startx = 0;
    int starty = 900;
    int color1 = co.nextInt(numobjects-2) + 1;
    int startx2 = 0;
    int starty2 = 900;
    int speed = sp.nextInt(5) + 20;
    int xspeed = sp.nextInt(20) + -10;
    int speed2 = sp.nextInt(5) + 20;
    int xspeed2 = sp.nextInt(20) + -10;
    int color2 = co.nextInt(numobjects-1) + 1;
    int startx3 = 0;
    int starty3 = 900;
    int speed3 = sp.nextInt(5) + 20;
    int xspeed3 = sp.nextInt(20) + -10;
    int color3 = co.nextInt(numobjects) + 1;
    int startx4 = 0;
    int starty4 = 900;
    int speed4 = sp.nextInt(5) + 20;
    int xspeed4 = sp.nextInt(20) + -10;
    int color4 = co.nextInt(numobjects) + 1;
    int prevx1 = 0;
    int prevy1 = 0;
    boolean balloon1=false;
    int prevx2 = 0;
    int prevy2 = 0;
    boolean balloon2=false;
    int prevx3 = 0;
    int prevy3 = 0;
    boolean balloon3=false;
    int prevx4 = 0;
    int prevy4 = 0;
    int prevcol2=0;
    int prevcol3=0;
    int prevcol4=0;
    boolean balloon4=false;
    int Numballoons =0;
    boolean touch1 = false;
    boolean touch2 = false;

    public DrawBalloon(Context context){
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        int x = canvas.getWidth() - 20;
        int y = canvas.getHeight();
        screenx = canvas.getWidth();
        screeny = y;
        Resources res = getResources();
//        Bitmap bckgrnd = BitmapFactory.decodeResource(res, R.drawable.backgroundimage4);
//        canvas.drawBitmap(bckgrnd, null, new Rect(0, 0, x+50, y), null);
        rad = screenx / 12;
        if (passes == 0) {
            startx = xpos.nextInt(screenx - 100) + 50;
            startx2 = xpos.nextInt(screenx - 100) + 50;
            startx3 = xpos.nextInt(screenx - 100) + 50;
            startx4 = xpos.nextInt(screenx - 100) + 50;
        }
        passes++;

        Random rand = new Random();

        super.onDraw(canvas);

        Paint p2 = new Paint();
        p2.setColor(Color.BLACK);
        p2.setStyle(Paint.Style.STROKE);
        p2.setTextSize(34);
        p2.setTextAlign(Paint.Align.CENTER);

        if(balloon1){
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pop);
            canvas.drawBitmap(bitmap, null, new Rect(prevx1 - (int)rad, prevy1 - (int)rad, prevx1 + (int)rad, prevy1 + (int)rad), null);
            balloon1=false;
        }
        if(balloon2){
            if(prevcol2==4){
                canvas.drawText("+2", prevx2, prevy2, p2);
            }
            else {
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pop);
                canvas.drawBitmap(bitmap, null, new Rect(prevx2 - (int) rad, prevy2 - (int) rad, prevx2 + (int) rad, prevy2 + (int) rad), null);
            }
            balloon2=false;
        }
        if(balloon3){
            if(prevcol3==4){
                canvas.drawText("+2", prevx3, prevy3, p2);
            }
            else if(prevcol3==5){
                canvas.drawText("-10", prevx3, prevy3, p2);
            }
            else {
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pop);
                canvas.drawBitmap(bitmap, null, new Rect(prevx3 - (int) rad, prevy3 - (int) rad, prevx3 + (int) rad, prevy3 + (int) rad), null);
            }
            balloon3=false;
        }
        if(balloon4){
            if(prevcol4==4){
                canvas.drawText("+2", prevx4, prevy4, p2);
            }
            else if(prevcol4==5){
                canvas.drawText("-1", prevx4, prevy4, p2);
            }
            else {
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pop);
                canvas.drawBitmap(bitmap, null, new Rect(prevx4 - (int) rad, prevy4 - (int) rad, prevx4 + (int) rad, prevy4 + (int) rad), null);
            }
            balloon4=false;
        }
        if(lives>=0) {
            canvas.drawText("lives: " + lives, 58, 28, p2);
            canvas.drawText("score: " + score, screenx - 100, 28, p2);
        }
        xspeed = sp.nextInt((int)rad/2) + -((int)rad/4);
        xspeed2 = sp.nextInt((int)rad/2) + -((int)rad/4);
        if(color3!=5) {
            xspeed3 = sp.nextInt((int) rad / 2) + -((int) rad / 4);
        }
        if(color4!=5) {
            xspeed4 = sp.nextInt((int) rad / 2) + -((int) rad / 4);
        }
        if(Numballoons == 10 && !touch1){
            canvas.drawText("Level 2: More Balloons\nTouch anywhere.", screenx/2, screeny/2, p2);
            startx = xpos.nextInt(screenx - 100) + 50;
            starty=screeny+70;
        }
        if(Numballoons == 30 && !touch2){
            canvas.drawText("Level 3: New Objects\nTouch anywhere.", screenx/2, screeny/2, p2);
            startx = xpos.nextInt(screenx - 100) + 50;
            starty=screeny+100;
            startx2 = xpos.nextInt(screenx - 100) + 50;
            starty2=screeny+100;
            startx3 = xpos.nextInt(screenx - 100) + 50;
            starty3=screeny+100;
        }
//        canvas.drawBitmap(bitmap, startx, starty, null);
        if(touch1 || Numballoons !=10) {
            starty = starty - speed;
            startx = startx + xspeed;
        }
        if (startx <= 5) {
            startx = 6;
        }
        if (startx >= canvas.getWidth()){
            startx = canvas.getWidth()-10;
        }
        if(color1==1) {
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.balloonred);
            canvas.drawBitmap(bitmap, null, new Rect(startx - (int)rad, starty - (int)rad, startx + (int)rad, starty + (int)rad), null);
        }
        else if(color1==2) {
            Bitmap bitmap2 = BitmapFactory.decodeResource(res, R.drawable.balloonblue);
            canvas.drawBitmap(bitmap2, null, new Rect(startx - (int)rad, starty - (int)rad, startx + (int)rad, starty + (int)rad), null);
        }
        else if(color1==3) {
            Bitmap bitmap3 = BitmapFactory.decodeResource(res, R.drawable.balloongreen);
            canvas.drawBitmap(bitmap3, null, new Rect(startx - (int)rad, starty - (int)rad, startx + (int)rad, starty + (int)rad), null);
        }
        //canvas.drawCircle(startx, starty, rad, p);
        if(Numballoons>10) {
            if(touch2 || Numballoons !=30) {
                starty2 = starty2 - speed2;
                startx2 = startx2 + xspeed2;
            }
            if (startx2 <= 5) {
                startx2 = 6;
            }
            if (startx2 >= canvas.getWidth()){
                startx2 = canvas.getWidth()-10;
            }
            if(color2==1) {
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.balloonred);
                canvas.drawBitmap(bitmap, null, new Rect(startx2 - (int)rad, starty2 - (int)rad, startx2 + (int)rad, starty2 + (int)rad), null);
            }
            else if(color2==2) {
                Bitmap bitmap2 = BitmapFactory.decodeResource(res, R.drawable.balloonblue);
                canvas.drawBitmap(bitmap2, null, new Rect(startx2 - (int)rad, starty2 - (int)rad, startx2 + (int)rad, starty2 + (int)rad), null);
            }
            else if(color2==3) {
                Bitmap bitmap3 = BitmapFactory.decodeResource(res, R.drawable.balloongreen);
                canvas.drawBitmap(bitmap3, null, new Rect(startx2 - (int)rad, starty2 - (int)rad, startx2 + (int)rad, starty2 + (int)rad), null);
            }
            else if(color2==4) {
                Bitmap bitmap4 = BitmapFactory.decodeResource(res, R.drawable.goldballoon);
                canvas.drawBitmap(bitmap4, null, new Rect(startx2 - (int)rad, starty2 - (int)rad, startx2 + (int)rad, starty2 + (int)rad), null);
            }
            //canvas.drawCircle(startx2, starty2, rad, p);
        }
        if(Numballoons>30) {
            starty3 = starty3 -speed3;
            if(color3==5 && startx3<=5){
                xspeed = 10;
            }
            if(color3==5 && startx3 >= canvas.getWidth()){
                xspeed = -10;
            }
            startx3 = startx3+xspeed3;
            if (startx3 <= 5) {
                startx3 = 6;
            }
            if (startx3 >= canvas.getWidth()){
                startx3 = canvas.getWidth()-10;
            }
            if(color3==1) {
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.balloonred);
                canvas.drawBitmap(bitmap, null, new Rect(startx3 - (int)rad, starty3 - (int)rad, startx3 + (int)rad, starty3 + (int)rad), null);
            }
            else if(color3==2) {
                Bitmap bitmap2 = BitmapFactory.decodeResource(res, R.drawable.balloonblue);
                canvas.drawBitmap(bitmap2, null, new Rect(startx3 - (int)rad, starty3 - (int)rad, startx3 + (int)rad, starty3 + (int)rad), null);
            }
            else if(color3==3) {
                Bitmap bitmap3 = BitmapFactory.decodeResource(res, R.drawable.balloongreen);
                canvas.drawBitmap(bitmap3, null, new Rect(startx3 - (int)rad, starty3 - (int)rad, startx3 + (int)rad, starty3 + (int)rad), null);
            }
            else if(color3==4) {
                Bitmap bitmap4 = BitmapFactory.decodeResource(res, R.drawable.goldballoon);
                canvas.drawBitmap(bitmap4, null, new Rect(startx3 - (int)rad, starty3 - (int)rad, startx3 + (int)rad, starty3 + (int)rad), null);
            }
            else if(color3==5) {
                Bitmap bitmap5 = BitmapFactory.decodeResource(res, R.drawable.bird1);
                canvas.drawBitmap(bitmap5, null, new Rect(startx3 - (int)rad, starty3 - (int)rad, startx3 + (int)rad, starty3 + (int)rad), null);
            }
         //   canvas.drawCircle(startx3, starty3, rad, p);
        }
        if(Numballoons>50) {
            starty4 = starty4 -speed4;
            if(color3==5 && startx3<=5){
                xspeed = 10;
            }
            if(color3==5 && startx3 >= canvas.getWidth()){
                xspeed = -10;
            }
            startx4 = startx4+xspeed4;
            if (startx4 <= 5) {
                startx4 = 6;
            }
            if (startx4 >= canvas.getWidth()){
                startx4 = canvas.getWidth()-10;
            }
            if(color4==1) {
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.balloonred);
                canvas.drawBitmap(bitmap, null, new Rect(startx4 - (int)rad, starty4 - (int)rad, startx4 + (int)rad, starty4 + (int)rad), null);
            }
            else if(color4==2) {
                Bitmap bitmap2 = BitmapFactory.decodeResource(res, R.drawable.balloonblue);
                canvas.drawBitmap(bitmap2, null, new Rect(startx4 - (int)rad, starty4 - (int)rad, startx4 + (int)rad, starty4 + (int)rad), null);
            }
            else if(color4==3) {
                Bitmap bitmap3 = BitmapFactory.decodeResource(res, R.drawable.balloongreen);
                canvas.drawBitmap(bitmap3, null, new Rect(startx4 - (int)rad, starty4 - (int)rad, startx4 + (int)rad, starty4 + (int)rad), null);
            }
            else if(color4==4) {
                Bitmap bitmap4 = BitmapFactory.decodeResource(res, R.drawable.goldballoon);
                canvas.drawBitmap(bitmap4, null, new Rect(startx4 - (int)rad, starty4 - (int)rad, startx4 + (int)rad, starty4 + (int)rad), null);
            }
            else if(color4==5) {
                Bitmap bitmap5 = BitmapFactory.decodeResource(res, R.drawable.bird1);
                canvas.drawBitmap(bitmap5, null, new Rect(startx4 - (int)rad, starty4 - (int)rad, startx4 + (int)rad, starty4 + (int)rad), null);
            }
          //  canvas.drawCircle(startx4, starty4, rad, p);
        }

        if(starty<-10) {
            starty = screeny + 40;
            startx = xpos.nextInt(screenx - 100) + 50;
            speed = sp.nextInt(15) + (int)(rad*.6);
            color1 = co.nextInt(numobjects-2) + 1;
            lives--;
            Numballoons++;
            if (lives<= 0){
                Intent i = new Intent(this.getContext(),GameOver.class);
                Bundle b = new Bundle();
                b.putInt("score", score);
                i.putExtras(b);
                this.getContext().startActivity(i);
            }
            invalidate();
        }
        if(starty2<-10){
            starty2=screeny+40;
            startx2 = xpos.nextInt(screenx - 100) + 50;
            speed2 = sp.nextInt(15) + (int)(rad*.7);
            color2 = co.nextInt(numobjects-1) + 1;
            lives--;
            Numballoons++;
            if (lives<= 0){
                Intent i = new Intent(this.getContext(),GameOver.class);
                Bundle b = new Bundle();
                b.putInt("score", score);
                i.putExtras(b);
                this.getContext().startActivity(i);
            }
            invalidate();
        }
        if(starty3<-10){
            starty3=screeny+40;
            startx3 = xpos.nextInt(screenx - 100) + 50;
            speed3 = sp.nextInt(15) + (int)(rad*.8);
            if(color3!=5) {
                lives--;
            }
            color3 = co.nextInt(numobjects) + 1;
            Numballoons++;
            if (lives<= 0){
                Intent i = new Intent(this.getContext(),GameOver.class);
                Bundle b = new Bundle();
                b.putInt("score", score);
                i.putExtras(b);
                this.getContext().startActivity(i);
            }
            invalidate();
        }
        if(starty4<-10) {
            starty4 = screeny+40;
            startx4 = xpos.nextInt(screenx - 100) + 50;
            speed4 = sp.nextInt(15) + (int)(rad*.9);
            if(color4!=5) {
                lives--;
            }
            color4 = co.nextInt(numobjects) + 1;
            Numballoons++;
            if (lives<= 0){
                Intent i = new Intent(this.getContext(),GameOver.class);
                Bundle b = new Bundle();
                b.putInt("score", score);
                i.putExtras(b);
                this.getContext().startActivity(i);
            }
            invalidate();
        }

        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float tx = event.getX();
        float ty = event.getY();
        if(Numballoons ==10){
            touch1=true;
        }
        if(Numballoons ==30){
            touch2=true;
        }
        if(tx < startx+rad && tx > startx-rad && ty < starty+rad && ty > starty-rad){
            prevx1=startx;
            prevy1=starty;
            balloon1=true;
            starty=screeny+40;
            startx = xpos.nextInt(screenx - 100) + 50;
            speed = sp.nextInt(15) + (int)(rad*.6);
            color1 = co.nextInt(numobjects-2) + 1;
            score++;
            Numballoons++;
            invalidate();
        }
        if(tx < startx2+rad && tx > startx2-rad && ty < starty2+rad && ty > starty2-rad){
            prevx2=startx2;
            prevy2=starty2;
            prevcol2=color2;
            balloon2=true;
            starty2=screeny+40;
            startx2 = xpos.nextInt(screenx - 100) + 50;
            speed2 = sp.nextInt(15) + (int)(rad*.7);
            if(color2==4){
                score+=2;
            }
            else {
                score++;
            }
            color2 = co.nextInt(numobjects-1) + 1;
            Numballoons++;
            invalidate();
        }
        if(tx < startx3+rad && tx > startx3-rad && ty < starty3+rad && ty > starty3-rad){
            prevx3=startx3;
            prevy3=starty3;
            prevcol3=color3;
            balloon3=true;
            starty3=screeny+40;
            startx3 = xpos.nextInt(screenx - 100) + 50;
            speed3 = sp.nextInt(15) + (int)(rad*.8);
            if(color3==4){
                score+=2;
            }
            else if(color3==5) {
                score=score-10;
            }
            else{
                score++;
            }
            color3 = co.nextInt(numobjects) + 1;
            Numballoons++;
            invalidate();
        }
        if(tx < startx4+rad && tx > startx4-rad && ty < starty4+rad && ty > starty4-rad){
            prevx4=startx4;
            prevy4=starty4;
            prevcol4=color4;
            balloon4=true;
            starty4=screeny+40;
            startx4 = xpos.nextInt(screenx - 100) + 50;
            speed4 = sp.nextInt(15) + (int)(rad*.9);
            if(color4==4){
                score+=2;
            }
            else if(color4==5) {
                score=score-10;
            }
            else{
                score++;
            }
            color4 = co.nextInt(numobjects) + 1;
            Numballoons++;
            invalidate();
        }

        return super.onTouchEvent(event);
    }


}
