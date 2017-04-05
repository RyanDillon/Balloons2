package com.example.matthew.firsttest;

/**
 * Created by Matthew on 2/9/2017.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sean on 12/9/2015.
 */
public class CanvasView extends View {


    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    String TAG = "CanvasView";
    boolean initialDraw = true;

    ArrayList<Circle> myCircles = new ArrayList<Circle>();
    int[] colors = {Color.BLACK, Color.MAGENTA};
    int numColors = 2;
    Random rando = new Random();
    Paint circlePaint = new Paint();
    Paint textPaint = new Paint();

    //text view on Canvas screen
    TextView numCircles;


    public CanvasView(Context c, AttributeSet attrs) {
        //this is like OnCreate()
        super(c, attrs);
        context = c;

        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(16f);

        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.ROUND);
        circlePaint.setStrokeWidth(16f);

        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLUE);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeJoin(Paint.Join.ROUND);
        textPaint.setStrokeWidth(4f);
        textPaint.setTextSize(50f);

        Log.d(TAG, "Width is: " + getWidth());

        numCircles = (TextView) findViewById(R.id.textView);
        // numCircles = c.s


    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (initialDraw == true) {
            //get screen sizes
            width = getWidth();
            height = getHeight();
            initialDraw = false;
        } else {
            // draw the mPath with the mPaint on the canvas when onDraw
            canvas.drawPath(mPath, mPaint);

            for (int i = 0; i < myCircles.size(); i++) {
                myCircles.get(i).draw(canvas);
            }

            //draw the text for the number of circles
            drawNumberOfCircles(canvas);

            //update
            update();

        }
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;

        addACircle(x, y);

    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        myCircles.clear();
        invalidate();
    }

    public void clearDrawing() {
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);

    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }

    public void update() {
        //call update things

        for (int i = 0; i < myCircles.size(); i++) {
            myCircles.get(i).update();
        }
        invalidate();
    }

    public int changePaintColor() {
        int rand = rando.nextInt(2);//gives you a random number between 0 and 2 excluding 2
        Log.d(TAG, "random number is: " + rand);
        circlePaint.setColor(colors[rand]);
        return rand;
    }

    public void addACircle(float x, float y) {

        changePaintColor();
        Paint pp = new Paint();
        pp.setAntiAlias(true);
        pp.setColor(colors[changePaintColor()]);
        pp.setStyle(Paint.Style.STROKE);
        pp.setStrokeJoin(Paint.Join.ROUND);
        pp.setStrokeWidth(16f);

        myCircles.add(new Circle(pp, x, y, width, height));
        Log.d(TAG, "myCircles size is now: " + myCircles.size());


        //numCircles.setText(myCircles.size());

    }

    public int getNumberOfCircles() {
        return myCircles.size();
    }

    public void drawNumberOfCircles(Canvas c) {

        c.drawText("Circles: " + myCircles.size(), 50f, 50f, textPaint);

    }
}
