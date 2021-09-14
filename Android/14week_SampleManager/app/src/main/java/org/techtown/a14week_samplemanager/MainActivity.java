package org.techtown.a14week_samplemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    SensorManager manager;
    List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSensorList();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerFirstSensor();
            }
        });
    }
    public void registerFirstSensor(){
        manager.registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        String output = "Sensor Timestamp : " + event.timestamp + "\n\n";
                        for(int index = 0; index < event.values.length; ++index){
                            output += ("Sensor Value #" + (index + 1) + ":" + event.values[index] + "\n");
                        }
                        println(output);
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {

                    }
                },
                sensors.get(0),
                SensorManager.SENSOR_DELAY_UI);
    }

    public  void getSensorList(){
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = manager.getSensorList(Sensor.TYPE_ALL);

        int index = 0;
        for(Sensor sensor : sensors){
            println("#" + index + " : " + sensor.getName());
        }
    }


    public void getServiceList(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();

        for(int i = 0; i< processInfoList.size(); i++){
            ActivityManager.RunningAppProcessInfo info = processInfoList.get(i);
            println("#" + i + "->" + info.pid + ", " + info.processName);
        }
    }

    public void getCurrentActivity (){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = manager.getRunningTasks(1);

        ActivityManager.RunningTaskInfo info = taskList.get(0);
        println("Running Task->" + info.topActivity.toString());
    }

    public void getAppList(){
        PackageManager manager = getPackageManager();
        List<ApplicationInfo> appInfoList = manager.getInstalledApplications(PackageManager.GET_META_DATA);

        for(int i = 0; i < appInfoList.size(); i++){
            ApplicationInfo info = appInfoList.get(i);
            println("#" + i + "-> " + info.loadLabel(manager).toString()+ ", " + info.packageName);
        }
    }

    public void findActivity(){
        PackageManager manager = getPackageManager();

        Intent intent = new Intent(this, MainActivity.class);
        List<ResolveInfo> activityInfoList = manager.queryIntentActivities(intent, 0);

        for(int i = 0; i < activityInfoList.size(); i++){
            ResolveInfo info = activityInfoList.get(i);
            println("#" + i + "->" + info.activityInfo.applicationInfo.packageName);
        }
    }

    public void setAlarm(){
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 60000, pendingIntent);
    }
    public void println(String data){ textView.append(data + "\n");}
}