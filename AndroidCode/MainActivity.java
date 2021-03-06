package com.example.markus.softwareprojektprototyp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sp_common.DataSink;
import sp_common.DataSource;
import sp_common.SensorData;
import sp_common.SensorType;

public class MainActivity extends AppCompatActivity implements DataDisplay {

    Handler handler = new Handler();
    TextView textView;
    Button startServer;
    Button stopServer;

    Boolean serverStarted = false;


    public SensorManager mSensorManager;
    public Sensor mSensor;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.textView);
        startServer = (Button) findViewById(R.id.startServer);
        stopServer = (Button) findViewById(R.id.stopServer);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        AccelData listener = new AccelData();
        mSensorManager.registerListener(listener,mSensor,0);
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

    public void stopServer(View view){
        MyServer.isRunning = false;
        startServer.setText("Start Server");
        startServer.setClickable(true);
    }


}
