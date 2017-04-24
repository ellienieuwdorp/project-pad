package com.pad.sss04.pianocontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectedActivity extends AppCompatActivity {

    private Button buttonSend;

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
    }
    @Override
    public void onBackPressed(){

    }
}
