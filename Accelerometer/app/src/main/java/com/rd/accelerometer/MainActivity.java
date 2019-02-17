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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rd.accelerometer.client.Connector;
import com.rd.accelerometer.websocket.to.GameMessage;
import com.rd.accelerometer.websocket.type.MessageType;

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

    private String deviceID;

    private Connector connector;
    private boolean connected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        deviceID = Settings.Secure.getString(this.getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

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
            if(nick.getText().toString().length() == 0) {
                Toast.makeText(this, "Za krótki nick", Toast.LENGTH_SHORT);
                return;
            }

            String addr = address.getText().toString();

            if(addr.length() == 0) {
                Toast.makeText(this, "Nieprawidłowy adres", Toast.LENGTH_SHORT);
                return;
            }

            try {
                connector.run(addr);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(!connector.isOpen()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(!connector.isOpen()) {
                Toast.makeText(this, "Problem z połaczeniem", Toast.LENGTH_SHORT);
                return;
            }

            Toast.makeText(this, "Dodawanie gracza do gry...", Toast.LENGTH_SHORT);

            sendJoinRequest();

            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!connector.isOpen()) {
                Toast.makeText(this, "Nie udało się dołączyć do gry", Toast.LENGTH_SHORT);
                return;
            }

            connected = true;
            connect.setText("Disconnect");
        } else {
            try {
                connector.close();
            } catch (Exception e) {
                e.printStackTrace();
                //return;
            }
            connected = false;
            connect.setText("Connect");
        }


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
            if(!update()) {
                return;
            }

            displayCurrentSensorValue();
            speedValue = displayCurrentSpeed();
            xSensorValue = event.values[0];

            if(connector.isOpen()) {
                sendSpeed(speedValue);
            }
            //vibrate(speedValue);
        }

    }

    private void sendJoinRequest() {
        GameMessage gameMessage = new GameMessage();
        gameMessage.setMessageType(MessageType.JOIN_TO_GAME);
        gameMessage.setContent(nick.getText().toString());
        gameMessage.setId(deviceID);
        connector.send(gameMessage);
    }

    private void sendSpeed(long speed) {
        if(!connector.isOpen()) {
            connected = false;
            connect.setText("Connect");
            return;
        }

        GameMessage gameMessage = new GameMessage();
        gameMessage.setMessageType(MessageType.SPEED);
        gameMessage.setContent(speed + "");
        gameMessage.setId(deviceID);
        connector.send(gameMessage);
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

    private long lastUpdate = 0;

    private boolean update() {
        if((System.currentTimeMillis() - 100) > lastUpdate) {
            lastUpdate = System.currentTimeMillis();
            return true;
        }

        return false;
    }

}
