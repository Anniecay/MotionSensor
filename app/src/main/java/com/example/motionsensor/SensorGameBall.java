package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SensorGameBall extends AppCompatActivity implements SensorEventListener {
    private ShapeDrawable ball;
    private static int x, y;
    int displayWidth, displayHeight;
    BallAnimation ballAnimation = null;
    SensorManager sensorManager;
    boolean isOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ballAnimation = new BallAnimation(this);
        isOver =false;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayWidth = displayMetrics.widthPixels-200;
        displayHeight = displayMetrics.heightPixels;
        x = displayWidth / 2;
        y = displayHeight / 2;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor accelemeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        try {
            if (accelemeter != null) {
                sensorManager.registerListener(this, accelemeter, SensorManager.SENSOR_DELAY_FASTEST);
            }
        } catch (Exception e) {
            Log.e("onCreate: ", e.toString());
        }

        setContentView(ballAnimation);

    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            x -= (int) event.values[0];
            y += (int) event.values[1];

            if (x > displayWidth) {
                x = displayWidth;
                finishGame();
            } else if (x < 0) {
                x = 0;
                finishGame();
            }
            if (y > displayHeight - 200) {
                y = displayHeight - 200;
                finishGame();
            } else if (y < 0) {
                y = 0;
                finishGame();
            }

        }
    }

    public void finishGame() {
        isOver = true;
        onPause();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public class BallAnimation extends androidx.appcompat.widget.AppCompatImageView {


        static final int ballWidth = 200;
        static final int ballHeight = 200;
        Paint paint = new Paint();

        public BallAnimation(Context context) {
            super(context);
            ball = new ShapeDrawable(new OvalShape());
            ball.getPaint().setColor(Color.parseColor("#74C0EF"));


        }

        @Override
        protected void onDraw(Canvas canvas) {


            ball.setBounds(x, y, x + ballWidth, y + ballHeight);
            ball.draw(canvas);

            if (isOver) {
                paint.setColor(Color.RED);
                paint.setTextSize(120);
                canvas.drawText("GAME OVER", 200, 150, paint);
            }
            invalidate();


        }
    }
}
