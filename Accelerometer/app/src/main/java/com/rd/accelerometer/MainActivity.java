package com.rd.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rd.accelerometer.client.Connector;

public class MainActivity extends Activity implements SensorEventListener {

    private static final int VIBRATION_TIME = 2000;
    private static final int SPEED_ALERT = 170;
    private static final int MAX_SPEED = 200;
    private static final int MAX_REVERSE_SPEED = -200;

    public Vibrator v;
    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;

    private float xSensorValue = 0;
    private int speedValue = 0;
    private TextView rotationX, currentSpeed;
    private EditText address, nick;
    Button connect;

    private Connector connector;
    private boolean connected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        //initialize rotation rotationVectorSensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager != null && sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null) {
            rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        //initialize vibration
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        connector = new Connector();

    }

    public void initializeViews() {
        //to remove
        address = findViewById(R.id.address);
        nick = findViewById(R.id.nick);

        rotationX = findViewById(R.id.rotationX);
        currentSpeed = findViewById(R.id.currentSpeed);
        connect = findViewById(R.id.connect);

        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connect();
            }
        });
    }

    public void connect() {
        if (!connected) {
            String addr = address.getText().toString();
            try {
                connector.run(addr);
                Thread.sleep(1000);
            } catch (Exception e) {
                return;
            }
            connected = true;
            connect.setText("Disconnect");
        } else {
            try {
                connector.close();
            } catch (Exception e) {
                return;
            }
            connected = false;
            connect.setText("Connect");
        }
    }

    private String getDeviceID() {
        return Settings.Secure.getString(this.getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    //onResume() register the sensor for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the sensor for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, rotationVectorSensor);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR && connected) {
            displayCurrentSensorValue();
            speedValue = displayCurrentSpeed();
            xSensorValue = event.values[0];
            vibrate(speedValue);
        }

    }

    public void displayCurrentSensorValue() {
        rotationX.setText(String.format("%s",xSensorValue));
    }

    public int displayCurrentSpeed() {
        float speedValue = ((-xSensorValue + 0.3f) * 1000);
        if (speedValue > MAX_SPEED) speedValue = MAX_SPEED;
        if (speedValue < MAX_REVERSE_SPEED) speedValue = MAX_REVERSE_SPEED;
        String speedString = Float.toString(speedValue);
        speedString = speedString.substring(0, speedString.indexOf("."));
        currentSpeed.setText(speedString);
        return Integer.valueOf(speedString);
    }

    // if the change in the accelerometer value is big enough, then vibrateAcc!
    // our threshold is MaxValue/2
    public void vibrate(int speed) {
        if (Math.abs(speed) > SPEED_ALERT) {
            v.vibrate(VIBRATION_TIME);
        }
    }

}
