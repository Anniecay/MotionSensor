package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {


    private SensorManager sensorManager;
    RecyclerViewerAdapter adapter;
    RecyclerView recyclerView;
    List<Sensor> sensorList;
    Button gyroBtn;
    Button lightBtn;
    Button aBtn;
    Button gameBtn;
    Button loco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);


        recyclerView = findViewById(R.id.recylerViewer);
        adapter = new RecyclerViewerAdapter(sensorList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        gyroBtn = findViewById(R.id.gyro);
        gyroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GyroSensor.class);
                startActivity(intent);
            }
        });

        lightBtn = findViewById(R.id.lightSensor);
        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lightsensor.class);
                startActivity(intent);
            }
        });
        aBtn=findViewById(R.id.accelemeter);
        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccelerometerSensor.class);
                startActivity(intent);
            }
        });
        gameBtn = findViewById(R.id.game);

        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SensorGameBall.class);
                startActivity(intent);
            }
        });
        loco = findViewById(R.id.local);
        loco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GPS.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
