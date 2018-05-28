package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LogActivity extends AppCompatActivity {
    DrawLog drawLog;
    NewLoglineButtonView newLoglineButton;
    Boolean clicking = false;
    LogTopBarView logTopBarView;
    ClearLogButtonView clearLogButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 0;
        getWindow().setAttributes(params);

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        drawLog = findViewById(R.id.drawLog);

        drawLog.draw();

        newLoglineButton = findViewById(R.id.newLoglineButton);

        newLoglineButton.draw(false);

        logTopBarView = findViewById(R.id.logTopBarView);

        logTopBarView.draw();

        clearLogButtonView = findViewById(R.id.clearLogButtonView);

        clearLogButtonView.draw(false);

        // Stuff for previous activity
        if(Constants.moveUserToFrontIndex != -1) {
            // Move the user clicked to the front of the users list,
            // effectively putting it as the most recent.
            Constants.users.add(0, Constants.users.get(Constants.moveUserToFrontIndex));
            Constants.users.remove(Constants.moveUserToFrontIndex + 1);
            Constants.moveUserToFrontIndex = -1;
        }

        // Set up click listener
        setTouchListeners();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }

    public void setTouchListeners() {
        findViewById(R.id.LayoutBackground).setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Add logline button
                        if(Constants.getDist(event.getX(), event.getY(), newLoglineButton.x, newLoglineButton.y) <= newLoglineButton.rad) {
                            newLoglineButton.draw(true);
                            clicking = true;
                        }

                        // Clear log button
                        if(Constants.getDist(event.getX(), event.getY(), clearLogButtonView.x, clearLogButtonView.y) <= clearLogButtonView.rad) {
                            clearLogButtonView.draw(true);
                            clicking = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Add logline button
                        if(Constants.getDist(event.getX(), event.getY(), newLoglineButton.x, newLoglineButton.y) > newLoglineButton.rad && clicking) {
                            clicking = false;
                            newLoglineButton.draw(false);
                        }

                        // Clear log button
                        if(Constants.getDist(event.getX(), event.getY(), clearLogButtonView.x, clearLogButtonView.y) > clearLogButtonView.rad && clearLogButtonView.hovering) {
                            clearLogButtonView.hovering = false;
                            clearLogButtonView.draw(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        // Add logline button
                        if(clicking) {
                            if (Constants.getDist(event.getX(), event.getY(), newLoglineButton.x, newLoglineButton.y) <= newLoglineButton.rad) {
                                newLogline();
                            } else {
                                newLoglineButton.draw(false);
                            }
                        }

                        // Clear log button
                        if(clearLogButtonView.hovering) {
                            if (Constants.getDist(event.getX(), event.getY(), clearLogButtonView.x, clearLogButtonView.y) <= newLoglineButton.rad) {
                                Constants.users.get(0).newLog();
                                drawLog.draw();
                            }
                            clearLogButtonView.draw(false);
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
}
