package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewLoglineActivity extends AppCompatActivity {
    private EditText subjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_logline);

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        subjectName = findViewById(R.id.subjectName);
        subjectName.setTextColor(Color.DKGRAY);
    }

    public void back(View view) {
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }

    public void addLogline(View view) {
        Constants.users.get(Constants.currentUserIndex).logLines.add(new LogLine(new MyTime(Constants.getHour(), Constants.getMinute()), new Subject(subjectName.getText().toString())));
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
}
