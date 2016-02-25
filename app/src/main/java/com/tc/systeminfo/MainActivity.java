package com.tc.systeminfo;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textViewInfo);
        tv.setMovementMethod(new ScrollingMovementMethod());
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
        tv.append(String.format("\nBUILD_FINGERPRINTS = %s", Build.FINGERPRINT));
        // Manufacture
        tv.append(String.format("\nBUILD_MANUFACTURE = %s", Build.MANUFACTURER));
        // Model
        tv.append(String.format("\nBUILD_MODEL = %s", Build.MODEL));
        // ID
        tv.append(String.format("\nBUILD_ID = %s", Build.ID));
        // Device
        tv.append(String.format("\nBUILD_DEVICE = %s", Build.DEVICE));

        // User Agent * 3
        Context ctx = getApplicationContext();
        WebSettings ws = new WebView(ctx).getSettings();
        tv.append(String.format("\nUA_HTTP_AGENT = %s", System.getProperty("http.agent")));
        tv.append(String.format("\nUA_DEFAULT_USER_AGENT = %s", ws.getDefaultUserAgent(ctx)));
        tv.append(String.format("\nUA_USER_AGENT_STRING = %s", ws.getUserAgentString()));

        // CPU_ABI
        tv.append(String.format("\nOS_ARCH = %s", System.getProperty("os.arch")));

        // Version Codename
        tv.append(String.format("\nVERSION_CODENAME = %s", Build.VERSION.CODENAME));
        // Version Incremental
        tv.append(String.format("\nVERSION_INCREMENTAL = %s", Build.VERSION.INCREMENTAL));
        // Version Sdk
        tv.append(String.format("\nVERSION_SDK = %s", Build.VERSION.SDK));

        // Display Density
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        tv.append(String.format("\nDISPLAY_DENSITY = %.2f", metrics.density));
        // Display Density Dpi
        tv.append(String.format("\nDISPLAY_DENSITY_DPI = %d", metrics.densityDpi));
        // Display Density xDpi
        tv.append(String.format("\nDISPLAY_X_DPI = %.3f", metrics.xdpi));
        // Display Density yDpi
        tv.append(String.format("\nDISPLAY_Y_DPI = %.3f", metrics.ydpi));
        // Display widthPixels
        tv.append(String.format("\nDISPLAY_WIDTH_PIXELS = %d", metrics.widthPixels));
        // Display heightPixels
        tv.append(String.format("\nDISPLAY_HEIGHT_PIXELS = %d", metrics.heightPixels));
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
