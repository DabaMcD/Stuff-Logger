package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LogActivity extends AppCompatActivity {
    DrawLog drawLog;
    NewLoglineButtonView newLoglineButton;
    Boolean clicking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        drawLog = findViewById(R.id.drawLog);

        drawLog.draw();

        newLoglineButton = findViewById(R.id.newLoglineButton);

        newLoglineButton.draw(false);

        clicking = false;

        // Set up click listener
        findViewById(R.id.LayoutBackground).setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(Constants.getDist(event.getX(), event.getY(), newLoglineButton.x, newLoglineButton.y) <= newLoglineButton.rad) {
                            newLoglineButton.draw(true);
                            clicking = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(Constants.getDist(event.getX(), event.getY(), newLoglineButton.x, newLoglineButton.y) > newLoglineButton.rad) {
                            clicking = false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(Constants.getDist(event.getX(), event.getY(), newLoglineButton.x, newLoglineButton.y) <= newLoglineButton.rad && clicking) {
                            newLogline();
                        } else {
                            newLoglineButton.draw(false);
                        }
                        break;
                }
                return true;
            }
        });
    }

    public void newLogline() {
        Intent myIntent = new Intent(this, NewLoglineActivity.class);
        startActivity(myIntent);

    }

    private void saveFile(String filename, String text) {
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFile(String file) {
        String text = "";
        FileInputStream fis;
        try {
            fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading!!", Toast.LENGTH_LONG).show();
        }
        return text;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
