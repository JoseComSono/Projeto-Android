package com.example.projetoandroid.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {

    private static final float THRESHOLD = 12f;
    private final SensorManager sm;
    private final Sensor sensor;
    private long lastTime;
    private final Runnable onShake;

    public ShakeDetector(Context ctx, Runnable onShake) {
        sm = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.onShake = onShake;
    }

    public void start() { sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI); }
    public void stop() { sm.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent e) {
        long now = System.currentTimeMillis();
        if (now - lastTime < 500) return;
        float x = e.values[0], y = e.values[1], z = e.values[2];
        double acc = Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;
        if (acc > THRESHOLD) {
            lastTime = now;
            onShake.run();
        }
    }
    @Override public void onAccuracyChanged(Sensor s, int i) {}
}