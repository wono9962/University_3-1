package org.techtown.a13week_samplepushsend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;
    static String regId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                send(input);
            }
        });

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void send(String input){
        JSONObject requestData = new JSONObject();

        try{
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);

            JSONArray idArray = new JSONArray();
            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        }catch(Exception e){
            e.printStackTrace();
        }

        sendData(requestData, new SendResponseListener(){

        })
    }

    public void println(String data){
        textView.append(data + "\n");
    }

// key =   AAAA-2PEW7o:APA91bFjIBw7ow896MEnnZCQ1OUfruA2eIsRkZkiq9eWpRx-Aru3IE0zLVdJ-P0WZQxR2wLmQgnWMuSJHH1rwAA7zT9qHrUDJNOFjf58igej5gN28Yce0smpPQii2z8GueOWUS5Xs1Ka
}