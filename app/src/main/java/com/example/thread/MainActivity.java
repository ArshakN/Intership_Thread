package com.example.thread;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    Thread thread;
    private Button btn_start;
    private Button btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        handleMsg();
        onClick();
    }

    public void findView() {
        btn_start = findViewById(R.id.button_start);
        btn_stop = findViewById(R.id.button_stop);
    }

    public void handleMsg() {
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 2) {
                    btn_start.setText("25%");
                } else if (msg.what == 5) {
                    btn_start.setText("50%");
                } else if (msg.what == 7) {
                    btn_start.setText("75%");
                } else if (msg.what == 10) {
                    btn_start.setText("100%");
                } else if (msg.what == 999) {
                    Toast.makeText(MainActivity.this, "Thread is Stoped", Toast.LENGTH_SHORT).show();
                    btn_start.setText("Start");
                }
            }
        };
    }

    public void startActivityDelayed(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Second.class);
                startActivity(intent);
            }
        }, 11000);
    }

    public void onClick() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread(new Runnable() {
                    public void run() {
                        Log.d("AAA", "thread is work");
                        for (int i = 1; i <= 10; i++) {
                            handler.sendEmptyMessage(i);
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                handler.sendEmptyMessage(999);
                                break;
                            }
                        }
                    }
                });
                thread.start();
                startActivityDelayed();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
    }

}
