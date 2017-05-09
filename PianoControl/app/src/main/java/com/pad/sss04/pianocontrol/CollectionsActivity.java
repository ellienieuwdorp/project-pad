package com.pad.sss04.pianocontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CollectionsActivity extends AppCompatActivity {
    // Decleration button in the activity
    private Button btnFarts;
    private Button btnPiano;
    private Button btnCow;
    private Button btnBurps;
    private Button btnSheep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections_activity);

        btnFarts = (Button) findViewById(R.id.btnFarts);
        btnFarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CollectionsActivity.this, BluetoothClientService.class);
                i.putExtra("message", "collection: Farts");
                startService(i);
            }
        });

        btnPiano = (Button) findViewById(R.id.btnPiano);
        btnPiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CollectionsActivity.this, BluetoothClientService.class);
                i.putExtra("message", "collection: Piano");
                startService(i);
            }
        });

        btnCow = (Button) findViewById(R.id.btnCow);
        btnCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CollectionsActivity.this, BluetoothClientService.class);
                i.putExtra("message", "collection: Cow");
                startService(i);
            }
        });

        btnBurps = (Button) findViewById(R.id.btnBurps);
        btnBurps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CollectionsActivity.this, BluetoothClientService.class);
                i.putExtra("message", "collection: Burps");
                startService(i);
            }
        });

        btnSheep = (Button) findViewById(R.id.btnSheep);
        btnSheep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CollectionsActivity.this, BluetoothClientService.class);
                i.putExtra("message", "collection: Sheep");
                startService(i);
            }
        });



    }
}
