package com.example.ikefluxa.stufflogger;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    UsersListView usersListView;
    MainTopBarView mainTopBarView;

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
        // Need the line below in every activity
        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        // Define all the things in the xml code
        mainTopBarView = findViewById(R.id.mainTopBarView);
        usersListView = findViewById(R.id.usersListView);

        // Do other things to the stuff in the xml code
        mainTopBarView.draw();
        usersListView.draw();

        // Ask for permissions
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
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

    public void newUserClick(View target) {
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
