package com.stuff.log.ger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class LogActivity extends AppCompatActivity {
    private LogView logView;
    private NewLoglineButtonView newLoglineButton;
    private ClearLogButtonView clearLogButtonView;
    private LogTopBarView logTopBarView;
    private LogActivityDividerView logActivityDividerView;
    private LogToDoView logToDoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // Cleaning up after previous activity (if MainActivity)
        if(Globals.logActivityFromMainActivityUserIndex > 0) {
            Globals.moveUserToFrontIndex(Globals.logActivityFromMainActivityUserIndex);
            Globals.logActivityFromMainActivityUserIndex = -1;
        }

        Screen.correctDims(getResources());

        // todo: switch out references to TopBar.standardHeight for an actual value from the logActivity's top bar instance

        logActivityDividerView = findViewById(R.id.logActivityDividerView);
        logActivityDividerView.init();

        // todo: rename some of the files and classes below
        logView = findViewById(R.id.logView);
        logView.init((Screen.width - logActivityDividerView.lineThk) / 2f);
        logView.draw();
        newLoglineButton = findViewById(R.id.newLoglineButton);
        newLoglineButton.draw();
        clearLogButtonView = findViewById(R.id.clearLogButtonView);
        clearLogButtonView.draw();

        logToDoView = findViewById(R.id.logToDoView);
        logToDoView.init((Screen.width - logActivityDividerView.lineThk) / 2f);
        logToDoView.draw();

        logActivityDividerView.draw();

        logTopBarView = findViewById(R.id.logTopBarView);
        logTopBarView.draw();

        // Set up click listener
        setTouchListeners();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
    @Override
    protected void onResume() {
        newLoglineButton.init(this);
        super.onResume();
    }
    private void setTouchListeners() {
        findViewById(R.id.LayoutBackground).setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        newLoglineButton.actionDown(event.getX(), event.getY());
                        clearLogButtonView.actionDown(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newLoglineButton.actionMove(event.getX(), event.getY());
                        clearLogButtonView.actionMove(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        newLoglineButton.actionUp(event.getX(), event.getY());
                        clearLogButtonView.actionUp(event.getX(), event.getY(), logView);
                        break;
                }
                return true;
            }
        });
    }
}
