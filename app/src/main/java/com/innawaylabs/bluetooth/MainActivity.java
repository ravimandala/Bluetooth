package com.innawaylabs.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
        } else if (mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is enabled", Toast.LENGTH_SHORT).show();
        } else {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                if (mBluetoothAdapter.enable()) {
                    Toast.makeText(this, "Enabled Bluetooth successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to enable Bluetooth.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Intent intentBTEnabled = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                int REQUEST_ENABLE_BT = 101;
                startActivityForResult(intentBTEnabled, REQUEST_ENABLE_BT);
            }
        }
    }
}
