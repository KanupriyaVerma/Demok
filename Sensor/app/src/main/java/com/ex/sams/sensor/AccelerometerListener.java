package com.ex.sams.sensor;

/**
 * Created by Sam's on 5/10/2018.
 */

public interface AccelerometerListener {

    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);

}