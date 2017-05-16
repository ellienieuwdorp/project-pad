package com.pad.sss04.pianocontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CollectionsActivity extends AppCompatActivity {
    // Declaration button in the activity
    private Button btnFarts;
    private Button btnPiano;
    private Button btnCow;
    private Button btnBurps;
    private Button btnSheep;

    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

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

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String ServiceMessage = extras.getString("ServiceMessage");
                    if (ServiceMessage != null) {
                        switch (ServiceMessage) {
                            case "DISCONNECTED":
                                Toast.makeText(CollectionsActivity.this, "Connection to the toy has been lost.", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(CollectionsActivity.this, MainActivity.class);
                                startActivity(i);
                                break;
                        }
                    }
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_disconnect:
                // TO-DO Close connection and go back to MainActivity
                return true;
            case R.id.action_forget:
                // TO-DO Remove MAC address from sharedPrefs and close the connection and go back to MainActivity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void onBackPressed() {

    }
}
