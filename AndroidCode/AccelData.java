package com.example.markus.softwareprojektprototyp;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;


public class AccelData implements SensorEventListener {
    double XAccel = 0.0;
    double YAccel = 0.0;
    double ZAccel = 0.0;

    public AccelData(){

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            XAccel = event.values[0];
            YAccel = event.values[1];
            ZAccel = event.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public String getValuesAsString(){
        String output = this.XAccel + " " + this.YAccel +" "+ this.ZAccel;
        return output;

    }




}
