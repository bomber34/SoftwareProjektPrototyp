package com.example.markus.softwareprojektprototyp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DataDisplay {

    Handler handler = new Handler();
    TextView textView;
    Button startServer;

    Boolean serverStarted = false;


    public SensorManager mSensorManager;
    public Sensor mSensor;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        startServer = (Button) findViewById(R.id.startServer);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

    }

    public void connect(View view){
        serverStarted = true;
        if(serverStarted == true) {
            startServer.setText("Server läuft");
            startServer.setClickable(false);
        }
        MyServer server = new MyServer();
        server.setEventListener(this);
        server.startListening(handler);
    }

    public void Display(String message){
        textView.setText(message);
    }



}
