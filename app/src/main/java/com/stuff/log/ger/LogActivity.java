package com.stuff.log.ger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class LogActivity extends AppCompatActivity {
    private LogLogLinesListView logLogLinesListView;
    private LogNewLogLineButtonView logNewLogLineButtonView;
    private LogClearLogButtonView logClearLogButtonView;
    private LogTopBarView logTopBarView;
    private LogActivityDividerView logActivityDividerView;
    private LogToDoListView logToDoListView;

    private ScrollView logLinesScrollView;

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

        // Get all instances out of xml file
        logActivityDividerView = findViewById(R.id.logActivityDividerView);
        logLogLinesListView = findViewById(R.id.logLogLinesListView);
        logNewLogLineButtonView = findViewById(R.id.logNewLogLineButtonView);
        logClearLogButtonView = findViewById(R.id.logClearLogButtonView);
        logToDoListView = findViewById(R.id.logToDoListView);
        logTopBarView = findViewById(R.id.logTopBarView);
        logLinesScrollView = findViewById(R.id.logLinesScrollView);

        logActivityDividerView.init();

        logLogLinesListView.init((Screen.width - logActivityDividerView.lineThk) / 2f, logTopBarView.getTopBarHeight());
        logLogLinesListView.draw();
        logNewLogLineButtonView.draw();
        logClearLogButtonView.draw();

        logToDoListView.init((Screen.width - logActivityDividerView.lineThk) / 2f, logTopBarView.getTopBarHeight());
        logToDoListView.draw();

        logActivityDividerView.draw();

        logTopBarView.draw();

        // Set up click listener
        setTouchListeners();

        logLinesScrollView.post(new Runnable() {
            public void run() {
                logLinesScrollView.scrollTo(0, logLinesScrollView.getBottom());
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
    @Override
    protected void onResume() {
        logNewLogLineButtonView.init(this);
        super.onResume();
    }
    private void setTouchListeners() {
        findViewById(R.id.LayoutBackground).setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        logNewLogLineButtonView.actionDown(event.getX(), event.getY());
                        logClearLogButtonView.actionDown(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        logNewLogLineButtonView.actionMove(event.getX(), event.getY());
                        logClearLogButtonView.actionMove(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        logNewLogLineButtonView.actionUp(event.getX(), event.getY());
                        logClearLogButtonView.actionUp(event.getX(), event.getY(), logLogLinesListView);
                        break;
                }
                return true;
            }
        });
    }
}
