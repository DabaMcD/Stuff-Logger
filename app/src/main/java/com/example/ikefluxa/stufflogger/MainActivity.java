package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {
    private UsersListView usersListView;
    private MainTopBarView mainTopBarView;
    private Boolean clickingOnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Constants.users.size() > 0) {
            Constants.saveUserFiles(this);
        } else {
            Constants.retrieveUserFiles(this);

            // If there was something in the saved files
            if(Constants.users.size() > 0) {
                // Restart activity
                startActivity(new Intent(this, MainActivity.class));
            }
        }

        // Need the line below in the startup activity
        defineConstantsStuff();
        // Need the line below in every other activity
        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        // Define all the things in the xml code
        MainTopBarShadowView mainTopBarShadowView = findViewById(R.id.mainTopBarShadowView);
        mainTopBarView = findViewById(R.id.mainTopBarView);
        usersListView = findViewById(R.id.usersListView);

        // Draw the stuff in the xml code
        mainTopBarShadowView.draw();
        mainTopBarView.draw(false);
        usersListView.draw(-1); // -1 if no index

        clickingOnAddUser = false;

        // Touch listeners
        setTopBarTouchListener();
        setUserListTouchListener();

        usersListView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                usersListView.draw(-1);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        Constants.saveUserFiles(this);
    }
    public void setTopBarTouchListener() {
        findViewById(R.id.mainTopBarView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(Constants.getDist(event.getX(), event.getY(), mainTopBarView.x, mainTopBarView.y) <= mainTopBarView.rad) {
                            clickingOnAddUser = true;
                            mainTopBarView.draw(true);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(Constants.getDist(event.getX(), event.getY(), mainTopBarView.x, mainTopBarView.y) > mainTopBarView.rad) {
                            clickingOnAddUser = false;
                            mainTopBarView.draw(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(Constants.getDist(event.getX(), event.getY(), mainTopBarView.x, mainTopBarView.y) <= mainTopBarView.rad && clickingOnAddUser) {
                            newUserClick();
                        }
                        mainTopBarView.draw(false);
                        clickingOnAddUser = false;
                        break;
                }
                return true;
            }
        });
    }
    public void setUserListTouchListener() {
        findViewById(R.id.usersListView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        usersListView.draw(usersListView.actionDown(event.getX(), event.getY()));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        usersListView.draw(usersListView.actionMove(event.getX(), event.getY()));
                        break;
                    case MotionEvent.ACTION_UP:
                        int clickedIndex = usersListView.actionUpUserButton(event.getX(), event.getY());
                        if(clickedIndex != -1) {
                            usersListClick(clickedIndex);
                        }
                        usersListView.draw(clickedIndex);

                        int trashClickedIndex = usersListView.actionUpTrashButton(event.getX(), event.getY());
                        if(trashClickedIndex != -1) {
                            trashUsersListClick(trashClickedIndex);
                        }
                        break;
                }
                return true;
            }
        });
    }
    public void usersListClick(int userIndex) {
        Constants.moveUserToFrontIndex(userIndex);

        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
    public void trashUsersListClick(int clickIndex) {
        ConfirmUserDeleteActivity.userIndex = clickIndex;
        startActivity(new Intent(this, ConfirmUserDeleteActivity.class));
    }
    public void defineConstantsStuff() {
        // Status bar height
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        Constants.STATUS_BAR_HEIGHT = result;

        // Screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int wd = displayMetrics.widthPixels;
        int ht = displayMetrics.heightPixels;
        Constants.ORIG_LONGER_SCREEN_DIM = Math.max(ht, wd);
        Constants.ORIG_SHORTER_SCREEN_DIM = Math.min(ht, wd);
    }
    public void newUserClick() {
        Intent myIntent = new Intent(this, UserActivity.class);
        startActivity(myIntent);
    }
}
