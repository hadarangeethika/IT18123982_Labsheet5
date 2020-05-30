package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String BROADCAST_ACTION = "";
    TextView textView;
    Button button;
    Receiver localListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv1);
        button = findViewById(R.id.button);

    }

    protected void onStart(){
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }

    protected void onStop(){
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if(view.getId()==R.id.button){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver{
        public void onReceive(Context context, Intent intent){
            String currentText = textView.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived" + message;
            textView.setText(currentText);
        }
    }

}
