package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class LogActivity extends AppCompatActivity {
    private NewLoglineButtonView newLoglineButton;
    private Boolean clicking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        DrawLog drawLog = findViewById(R.id.drawLog);

        drawLog.draw();

        newLoglineButton = findViewById(R.id.newLoglineButton);

        newLoglineButton.draw(false);

        clicking = false;

        // Set up click listener
        findViewById(R.id.LayoutBackground).setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
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
}
