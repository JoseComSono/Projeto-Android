package com.example.projetoandroid.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepCounter implements SensorEventListener {

    private final SensorManager sm;
    private final Sensor stepSensor;
    private int steps = 0;
    private final OnStepListener listener;

    public interface OnStepListener { void onStep(int totalSteps); }

    public StepCounter(Context ctx, OnStepListener l) {
        sm = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        this.listener = l;
    }

    public void start() { steps = 0; sm.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL); }
    public void stop() { sm.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent e) {
        if (e.values[0] == 1.0f) {
            steps++;
            listener.onStep(steps);
        }
    }
    @Override public void onAccuracyChanged(Sensor s, int i) {}
}