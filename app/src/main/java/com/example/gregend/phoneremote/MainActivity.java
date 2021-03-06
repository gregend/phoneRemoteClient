package com.example.gregend.phoneremote;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity
{
    private ListView mList;
//    private ArrayList<String> arrayList;
//    private MyCustomAdapter mAdapter;
    private TCPClient mTcpClient;
    private LinearLayout layout;
    private Activity activity = this;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

//        arrayList = new ArrayList<String>();

        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText connectEditText = (EditText) findViewById(R.id.connect_edit_text);
        Button connectButton = (Button) findViewById(R.id.connect_button);
        Button send = (Button)findViewById(R.id.send_button);
        Button doubleClickButton = (Button) findViewById(R.id.db_click_button);
        doubleClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("dbclick");
                }
            }
        });

        Button rightClickButton = (Button) findViewById(R.id.right_click_button);
        rightClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("rclick");
                }
            }
        });

        Button leftClickButton = (Button) findViewById(R.id.left_click_button);
        leftClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("lclick");
                }
            }
        });

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("left");
                }
            }
        });

        Button forwardButton = (Button) findViewById(R.id.forward_button);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("right");
                }
            }
        });



        //relate the listView from java to the one created in xml
//        mList = (ListView)findViewById(R.id.list);
//        mAdapter = new MyCustomAdapter(this, arrayList);
//        mList.setAdapter(mAdapter);
        layout = (LinearLayout) findViewById(R.id.layout);


        // connect to the server
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAddr = connectEditText.getText().toString();
                if(!ipAddr.matches("")){

                    connectTask cT = new connectTask();
                    cT.ipAddr = ipAddr;
                    cT.execute();
                }
            }
        });


//        layout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
////                int x = (int) view.getX();
////                int y = (int) view.getY();
////
////                String message = x + "&" + y;
////                editText.setText(message);
////                //add the text in the arrayList
////                arrayList.add("c: " + message);
////
////                //sends the message to the server
////                if (mTcpClient != null) {
////                    mTcpClient.sendMessage(message);
////                }
////
////                //refresh the list
////                mAdapter.notifyDataSetChanged();
//
//
//                return false;
//            }
//        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(activity, TestActivty.class));
//
                String message = editText.getText().toString();

                //add the text in the arrayList
//                arrayList.add("c: " + message);

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }

                //refresh the list
//                mAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

    }

    public class connectTask extends AsyncTask<String,String,TCPClient> {


        String ipAddr = "";



        @Override
        protected TCPClient doInBackground(String... message) {

            //we create a TCPClient object and
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
//                    publishProgress(message);
                }
            });
            mTcpClient.run(ipAddr);

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            //in the arrayList we add the messaged received from server
//            arrayList.add(values[0]);
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
//            mAdapter.notifyDataSetChanged();
        }

    }
}