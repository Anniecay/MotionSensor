package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class GyroSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private  Sensor gyroSensor;
    TextView values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro_sensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        values = findViewById(R.id.gyroText);

        if(gyroSensor!=null){
            sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            values.setText("Gyro not found!");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor =event.sensor;
        if(sensor.getType()== Sensor.TYPE_GYROSCOPE){

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            values.setText("X: " + Float.toString(x) + "\nY: " + Float.toString(y) + "\nZ: " + Float.toString(z));

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
