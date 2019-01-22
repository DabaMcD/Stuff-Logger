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
    private ClearLogButtonView clearLogButtonView;
    private LogTopBarView logTopBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Screen.correctDims(getResources());

        drawLog = findViewById(R.id.drawLog);
        drawLog.draw();

        newLoglineButton = findViewById(R.id.newLoglineButton);
        newLoglineButton.draw();

        logTopBarView = findViewById(R.id.logTopBarView);
        logTopBarView.draw();

        clearLogButtonView = findViewById(R.id.clearLogButtonView);
        clearLogButtonView.draw();

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
                        newLoglineButton.actionDown(event.getX(), event.getY());
                        clearLogButtonView.actionDown(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newLoglineButton.actionMove(event.getX(), event.getY());
                        clearLogButtonView.actionMove(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        newLoglineButton.actionUp(event.getX(), event.getY(), getApplicationContext());
                        clearLogButtonView.actionUp(event.getX(), event.getY(), getApplicationContext(), drawLog);
                        break;
                }
                return true;
            }
        });
    }
}
