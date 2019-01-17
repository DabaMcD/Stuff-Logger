package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class LogActivity extends AppCompatActivity {
    private DrawLog drawLog;
    private NewLoglineButtonView newLoglineButton;
    private Boolean clicking = false;
    private ClearLogButtonView clearLogButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Screen.correctDims(getResources());

        drawLog = findViewById(R.id.drawLog);
        drawLog.draw();

        newLoglineButton = findViewById(R.id.newLoglineButton);
        newLoglineButton.draw(false);

        LogTopBarView logTopBarView;
        logTopBarView = findViewById(R.id.logTopBarView);
        logTopBarView.draw();

        clearLogButtonView = findViewById(R.id.clearLogButtonView);
        clearLogButtonView.draw(false);

        // Set up click listener
        setTouchListeners();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
    private void setTouchListeners() {
        findViewById(R.id.LayoutBackground).setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
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
    private void newLogline() {
        Intent myIntent = new Intent(this, NewLoglineActivity.class);
        startActivity(myIntent);
    }
}
