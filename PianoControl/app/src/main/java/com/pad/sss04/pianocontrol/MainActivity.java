package com.pad.sss04.pianocontrol;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    // Shared preferences save/load functionality
    private static final String MY_PREFERENCES = "My_Preferences";
    private static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferenceEditor;
    private static String prefMACAddress = null;
    private static String prefMACkey = "prefMAC";

    // Debugging
    private static final String TAG = "MainActivity";
    private static final boolean D = true;

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    // Layout Views
    private Button mConnectButton;

    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (D) Log.e(TAG, "+++ ON CREATE +++");

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, getString(R.string.bluetooth_unavailable), Toast.LENGTH_LONG).show();
            finish();
        }

        // Tries the connection and connect with the remembered device when found
        tryConnection();

        // Create the connect button with the connection functionality
        mConnectButton = (Button) findViewById(R.id.buttonConnect);
        mConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serverIntent = new Intent(MainActivity.this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
            }
        });

    }

    private void tryConnection() {
        // Get the sharedPreferences and set the MAC address when it exists
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        prefMACAddress = sharedPreferences.getString(prefMACkey, null);

        // Connect with the remembered device when it exists
        if(prefMACAddress != null) {
            setupConnection();
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(prefMACAddress);
            connectDevice(device.getAddress());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (D) Log.e(TAG, "++ ON START ++");

        // If BT is not on, request that it be enabled.
        // setupConnection() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the connection
        } else {
            setupConnection();
        }
    }

    private void setupConnection() {
        Log.d(TAG, "setupConnection()");

        // Initialize the BluetoothClientService to perform bluetooth connections
        Intent i = new Intent(MainActivity.this, BluetoothClientService.class);
        startService(i);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    String address = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    connectDevice(address);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set the connection
                    setupConnection();
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, getString(R.string.bt_not_enabled_leaving), Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void connectDevice(String address) {
        // Set the preferences MAC address to the current fetched address and apply it
        preferenceEditor = sharedPreferences.edit();
        prefMACAddress = address;
        preferenceEditor.putString(prefMACkey, prefMACAddress);
        preferenceEditor.apply();

        // Attempt to connect to the device
        Intent i = new Intent(MainActivity.this, BluetoothClientService.class);
        i.putExtra("address", address);
        startService(i);
    }

}

