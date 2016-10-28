package com.example.markus.softwareprojektprototyp;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.io.Serializable;
;
import sp_common.SensorData;
import sp_common.SensorType;


public class AccelData implements SensorEventListener, Serializable {


    static SensorData mSensorData;

    public AccelData(){

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            mSensorData = new SensorData(SensorType.Accelerometer, event.values, event.timestamp, event.accuracy);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
