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

        Screen.correctDims(getResources());

        subjectName = findViewById(R.id.subjectName);
        subjectName.setTextColor(Color.DKGRAY);

        NewLoglineTopBarView newLoglineTopBarView = findViewById(R.id.newLoglineTopBarView);
        newLoglineTopBarView.draw();
    }
    public void back(View view) {
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
    public void addLogline(View view) {
        Constants.users.get(0).logs.get(Constants.users.get(0).logs.size() - 1).logLines.add(new LogLine(new MyTime(), new Subject(subjectName.getText().toString())));
        Intent myIntent = new Intent(this, LogActivity.class);
        Files.reSave(this);
        startActivity(myIntent);
    }
}
