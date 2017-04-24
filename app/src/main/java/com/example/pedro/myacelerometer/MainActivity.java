package com.example.pedro.myacelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    TextView xCoor;
    TextView yCoor;
    TextView zCoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xCoor=(TextView) findViewById(R.id.xcoor);
        yCoor=(TextView) findViewById(R.id.ycoor);
        zCoor=(TextView) findViewById(R.id.zcoor);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        //Adiciona o listener
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Verifica o tipo de sensor
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            xCoor.setText("X: "+event.values[0]);
            yCoor.setText("Y: "+event.values[1]);
            zCoor.setText("Z: "+event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Necessario implementar mesmo estando vazio
    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onStop(){
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}
