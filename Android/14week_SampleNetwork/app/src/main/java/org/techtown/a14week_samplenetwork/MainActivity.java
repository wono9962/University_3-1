package org.techtown.a14week_samplenetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnectivity();
            }
        });

        wifiReceiver = new WiFiReceiver();
    }

    public void checkConnectivity(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetwork();
        if(info != null){
            if(info.getType() == ConnectivityManager.TYPE_WIFI){
                println("WiFi로 설정됨");
            } else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                println("일반망으로 설정됨");
            }

            println("연결 여부: " + info.isConnected());
        }else {
            println("데이터 통신 불가");
        }
    }

    public void println (String data){
        textView.append(data + "\n");
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(wifiReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver, filter);
    }

    class WiFiReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
                if(state == WifiManager.WIFI_STATE_ENABLED){
                    println("WiFi enabled");
                }else if(state == WifiManager.WIFI_STATE_DISABLED)
            }
        }
    }
}