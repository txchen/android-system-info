package com.tc.systeminfo;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.textViewInfo);
        tv.setText("SystemInfo:\n");

        // android device id
        tv.append("\nANDROID_ID = " + Settings.Secure.getString(this.getBaseContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        
        // GSF id

        // IMEI

        // Sim Subscriber ID

        // Sim Card Serial

        // Local IP addresses

        // Wi-Fi MAC

        // Bluetooth MAC

        // Hardware Serial

        // Device Build Fingerprints

        // Manufacture
        // Model
        // Device

        // User Agent * 3

        // CPU_ABI

        // Version Codename
        // Version Incremental
        // Version Sdk

        // Display Density
        // Display Density Dpi
        // Display Density xDpi
        // Display Density yDpi
        // Display widthPixels
        // Display heightPixels
    }
}
