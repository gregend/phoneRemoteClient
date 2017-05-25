package com.example.gregend.phoneremote;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class TestActivty extends AppCompatActivity {

    TextView joystickAngle;
    TextView joystickStrenght;
    TextView joystickX;
    TextView joystickY;
    double x;
    double y;
    String message;

    private TCPClient mTcpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joystick_layout);

        joystickAngle = (TextView) findViewById(R.id.joystick_angle);
        joystickStrenght = (TextView) findViewById(R.id.joystick_strength);
        joystickX = (TextView) findViewById(R.id.joystickX);
        joystickY = (TextView) findViewById(R.id.joystickY);

        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);
//        new connectTask().execute("");

        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {

            @Override
            public void onMove(int angle, int strength) {
                joystickAngle.setText("angle: " + String.valueOf(angle));
                joystickStrenght.setText("strength: " + String.valueOf(strength));
                if(angle != 0 && strength != 0){
                    x = (Math.cos(angle)*strength);
                    y = (Math.sin(angle)*strength);
                    joystickX.setText(String.format("x: %.2f", x));
                    joystickY.setText(String.format("y: %.2f", y));
                    message = String.format("%.2f&%.2f", x, y);
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage(message);
                    }

                }

            }
        });
    }

//    public class connectTask extends AsyncTask<String,String,TCPClient> {
//
//        @Override
//        protected TCPClient doInBackground(String... message) {
//
//            //we create a TCPClient object and
//            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
//                @Override
//                //here the messageReceived method is implemented
//                public void messageReceived(String message) {
//                    //this method calls the onProgressUpdate
//                    publishProgress(message);
//                }
//            });
//            mTcpClient.run();
//
//            return null;
//        }
//
//
//    }
}
