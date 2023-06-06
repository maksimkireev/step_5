package com.example.step_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.SensorEvent;

import android.os.Handler;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int Count = -1;
    private double magLast = 0;

    private TextView Steps, inf;

    private Button tonull, layout2;

    int progress = 0;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Steps = findViewById(R.id.Steps);
        tonull = findViewById(R.id.tonull);
        layout2 = findViewById(R.id.layout2);
        progressBar = findViewById(R.id.progressBar);

        inf = findViewById(R.id.inf);

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent != null) {
                    double dx = sensorEvent.values[0];
                    double dy = sensorEvent.values[1];
                    double dz = sensorEvent.values[2];

                    double mag = Math.sqrt(dx*dx + dy*dy + dz*dz);

                    if(mag-magLast > 4) {
                        Count++;
                        progressBar.setProgress(Count);
                        Steps.setText(Integer.toString(Math.max(Count, 0)));
                    }
                    magLast = mag;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(stepDetector,sensor,sensorManager.SENSOR_DELAY_NORMAL);


        tonull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Count=0;
                progressBar.setProgress(0);
                Steps.setText(Integer.toString(Math.max(Count, 0)));
            }
        });

        try {
            inf.setText(getIntent().getStringExtra("edKey"));
        } finally {
            final Object o = null;
        }

    }

    private void setDistance(String dis){
        progressBar.setMax(Integer.parseInt(dis));
    }

    public void layout2(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}