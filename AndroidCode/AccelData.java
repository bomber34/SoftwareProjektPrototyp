package com.example.markus.softwareprojektprototyp;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.io.Serializable;
import java.util.Date;
;
import sp_common.SensorData;
import sp_common.SensorType;


public class AccelData implements SensorEventListener, Serializable {

    static float AccelX;
    static float AccelY;
    static float AccelZ;
    static long timestampInMillis;

    //static SensorData mSensorData;

    public AccelData(){

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {

            AccelX = event.values[0];
            AccelY = event.values[1];
            AccelZ = event.values[2];

            timestampInMillis = new Date().getTime(); //+(event.timestamp - System.nanoTime()) / 1000000L;




           // mSensorData = new SensorData(SensorType.Accelerometer, event.values, event.timestamp, event.accuracy);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public String toString(){
        String string = AccelX + " " + AccelY + " " + AccelZ + " " +  timestampInMillis;
        return string;
    }

}
