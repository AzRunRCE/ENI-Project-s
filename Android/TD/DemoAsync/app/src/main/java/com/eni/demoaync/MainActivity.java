package com.eni.demoaync;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonSansThread, buttonAvecThread, buttonAsyncTask, buttonHandler;
    private MonHandler handler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSansThread = findViewById(R.id.buttonSansThread);
        buttonAvecThread = findViewById(R.id.buttonAvecThread);
        buttonAsyncTask = findViewById(R.id.buttonAsyncTask);
        buttonHandler = findViewById(R.id.buttonAvecHandler);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);
        handler = new MonHandler();

        buttonSansThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0;i<=10;i++){
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        buttonAvecThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0;i<=10;i++){
                            progressBar.setProgress(i);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
        buttonHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message msgGo = new Message();
                        msgGo.what = 1;
                        handler.sendMessage(msgGo);

                        for (int i = 0;i<=10;i++) {
                            Message msgEnCours = new Message();
                            msgEnCours.what = 2;
                            msgEnCours.arg1 = i;
                            handler.sendMessage(msgEnCours);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        Message msgEnd = new Message();
                        msgEnd.what = 3;
                        handler.sendMessage(msgEnd);

                    }
                }).start();
            }
        });

        buttonAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<Void,Integer,Void> asyncTask = new AsyncTask<Void,Integer,Void>(){
                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        progressBar.setProgress(values[0]);
                        super.onProgressUpdate(values);
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        for (int i = 0;i<=10;i++) {
                            publishProgress(i);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                };
                asyncTask.execute();
            }
        });
    }
    class MonHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1: buttonHandler.setEnabled(false); break;
                case 2: progressBar.setProgress(msg.arg1); break;
                case 3: buttonHandler.setEnabled(true); break;
            }
        }
    }

}
