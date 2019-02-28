package com.stuff.log.ger;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewLoglineActivity extends AppCompatActivity {
    private boolean clickedBefore;
    private EditText subjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_logline);

        Screen.correctDims(getResources());

        clickedBefore = false;

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
        if(subjectName.getText().toString().length() > 0 && !clickedBefore) {
            clickedBefore = true;
            Globals.users.get(0).logs.get(Globals.users.get(0).logs.size() - 1).logLines.add(new LogLine(new MyTime(), new Subject(subjectName.getText().toString())));
            Intent myIntent = new Intent(this, LogActivity.class);
            Files.reSave(this);
            startActivity(myIntent);
        }
    }
}
