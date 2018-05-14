package com.example.ikefluxa.stufflogger;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    UsersListView usersListView;
    MainTopBarView mainTopBarView;
    Boolean clickingOnAddUser;
    MainTopBarShadowView mainTopBarShadowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Constants.goToLogActivityFromMainActivity) {
            goToLogActivity();
            Constants.goToLogActivityFromMainActivity = false;
        }

        // Need the line below in the startup activity
        defineConstantsStuff();
        // Need the line below in every other activity
        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        // Define all the things in the xml code
        mainTopBarShadowView = findViewById(R.id.mainTopBarShadowView);
        mainTopBarView = findViewById(R.id.mainTopBarView);
        usersListView = findViewById(R.id.usersListView);

        // Do other things to the stuff in the xml code
        mainTopBarShadowView.draw(mainTopBarView);
        mainTopBarView.draw(false);
        usersListView.draw(-1); // -1 if no index

        // Ask for permissions
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

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

        super.onBackPressed();
    }

    public void setTopBarTouchListener() {
        findViewById(R.id.mainTopBarView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        usersListView.draw(usersListView.actionDown(event.getX(), event.getY()));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        usersListView.draw(usersListView.actionMove(event.getX(), event.getY()));
                        break;
                    case MotionEvent.ACTION_UP:
                        int clickedIndex = usersListView.actionUp(event.getX(), event.getY());
                        if(clickedIndex != -1) {
                            usersListClick(clickedIndex);
                            usersListView.draw(clickedIndex);
                        }
                        break;
                }
                return true;
            }
        });
    }

    public void usersListClick(int userIndex) {
        Constants.moveUserToFrontIndex = userIndex;

        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
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

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);
    }

    public void newUserClick() {
        Intent myIntent = new Intent(this, UserActivity.class);
        startActivity(myIntent);
    }

    public void goToLogActivity() {
        Intent myIntent = new Intent(this, LogActivity.class);
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
