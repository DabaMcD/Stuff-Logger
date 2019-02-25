package com.stuff.log.ger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {
    private MainTopBarShadowView mainTopBarShadowView;
    private UsersListView usersListView;
    private MainTopBarView mainTopBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doFileStuff();

        // We need the line below in the startup activity. Shouldn't need it anywhere else
        Screen.setDims(getWindowManager(), getResources());

        // We need the line below in every other activity
        Screen.correctDims(getResources());

        // Define all the things in the xml code
        mainTopBarShadowView = findViewById(R.id.mainTopBarShadowView);
        mainTopBarView = findViewById(R.id.mainTopBarView);
        usersListView = findViewById(R.id.usersListView);

        // Draw the stuff in the xml code
        usersListView.init(this);
        usersListView.draw();
        mainTopBarShadowView.draw();
        mainTopBarView.draw();

        // Touch listeners
        setTopBarTouchListener();
        setUserListTouchListener();

        usersListView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                usersListView.setTouchingUserIndex(-1);
                usersListView.draw();
            }
        });
    }
    private void doFileStuff() {
        if(Globals.users.size() > 0) {
            Files.reSave(this);
        } else {
            Files.retrieve(this);

            // If there was something in the saved files
            if(Globals.users.size() > 0) {
                // Restart activity
                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }
    @Override
    protected void onResume() { // Should be called when coming off of ConfirmUserDelete activity
        usersListView.setTrashClickingIndex(-1);
        usersListView.draw();
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        Files.reSave(this);

        // Open the apps page
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // Touch listeners
    private void setTopBarTouchListener() {
        findViewById(R.id.mainTopBarView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mainTopBarView.actionDown(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mainTopBarView.actionMove(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        mainTopBarView.actionUp(event.getX(), event.getY());
                        break;
                }
                mainTopBarView.draw();
                return true;
            }
        });
    }
    private void setUserListTouchListener() {
        findViewById(R.id.usersListView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        usersListView.actionDown(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        usersListView.actionMove(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        usersListView.actionUp(event.getX(), event.getY());
                        break;
                }
                return true;
            }
        });
    }
}
