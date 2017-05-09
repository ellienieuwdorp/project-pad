package com.pad.sss04.pianocontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConnectedActivity extends AppCompatActivity {

    private Button buttonSend;
    private BroadcastReceiver mBroadcastReceiver;
    private Button buttonCollections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

        init();
    }

    private void init() {
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConnectedActivity.this, BluetoothClientService.class);
                i.putExtra("message", "test message");
                startService(i);
            }
        });

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String ServiceMessage = extras.getString("ServiceMessage");
                    if (ServiceMessage != null) {
                        switch (ServiceMessage) {
                            case "DISCONNECTED":
                                Toast.makeText(ConnectedActivity.this, "Connection to the toy has been lost.", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(ConnectedActivity.this, MainActivity.class);
                                startActivity(i);
                                break;
                        }
                    }
                }
            }
        };


        // button initialize
        buttonCollections = (Button) findViewById(R.id.buttonCollections);
        buttonCollections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConnectedActivity.this, CollectionsActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mBroadcastReceiver),
                new IntentFilter(BluetoothClientService.BLUETOOTH_RESULT)
        );
    }

    @Override
    public void onBackPressed(){

    }
}
