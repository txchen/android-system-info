package com.tc.systeminfo;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textViewInfo);
        tv.setText("SystemInfo:\n");

        // android device id
        tv.append("\nANDROID_ID = " + Settings.Secure.getString(this.getBaseContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));

        // GSF id
        tv.append("\nGSF_ID = " + getGSFId(getApplicationContext()));

        // IMEI
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tv.append("\nIMEI = " + telephonyManager.getDeviceId());

        // Sim Subscriber ID
        tv.append(String.format("\nSIM_SUBSCRIBER_ID = %s", telephonyManager.getSubscriberId()));

        // Sim Card Serial
        tv.append(String.format("\nSIM_SERIAL = %s", telephonyManager.getSimSerialNumber()));

        // Local IP address V4
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        tv.append(String.format("\nLOCAL_IP_ADDRESS = %s", ip));

        // Wi-Fi MAC
        WifiInfo wifiInf = wm.getConnectionInfo();
        String wifiMAC = wifiInf.getMacAddress();
        tv.append(String.format("\nWIFI_MAC = %s", wifiMAC));

        // Bluetooth MAC
        tv.append(String.format("\nBT_MAC = %s", BluetoothAdapter.getDefaultAdapter().getAddress()));

        // Hardware Serial
        tv.append(String.format("\nHARDWARE_SERIAL = %s", android.os.Build.SERIAL));

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

    private static String getGSFId(Context context) {
        Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        String ID_KEY = "android_id";
        String params[] = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (!c.moveToFirst() || c.getColumnCount() < 2)
            return null;
        try {
            return Long.toHexString(Long.parseLong(c.getString(1)));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
