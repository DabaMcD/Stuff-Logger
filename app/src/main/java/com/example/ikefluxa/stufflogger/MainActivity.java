package com.example.ikefluxa.stufflogger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Need the line below in the startup activity
        defineConstantsStuff();
        // Need the line below in every activity
        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        // Define all the things in the xml code
        Button newUser = findViewById(R.id.newUser);
        newUser.setText(R.string.add_user);

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
}
