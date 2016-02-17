package com.tc.systeminfo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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
        tv.append("\nGSF_ID = " + getGSFId(getApplicationContext()));

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

    private static String getGSFId(Context context)
    {
        Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        String ID_KEY = "android_id";
        String params[] = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (!c.moveToFirst() || c.getColumnCount() < 2)
            return null;
        try
        {
            return Long.toHexString(Long.parseLong(c.getString(1)));
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
