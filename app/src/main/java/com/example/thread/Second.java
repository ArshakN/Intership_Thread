package com.example.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Second extends AppCompatActivity {
    private static final int CLICK = 999;
    private Button btn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = findViewById(R.id.id_second_button);

        Handler.Callback hc = new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                if (msg.what==CLICK)
                    Toast.makeText(Second.this,"Button has been clicked",Toast.LENGTH_SHORT).show();
                return false;
            }
        };

        handler = new Handler(hc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(CLICK);
                    }
                }).start();
            }
        });
    }
}
