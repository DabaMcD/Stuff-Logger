package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewLoglineActivity extends AppCompatActivity {
    Button addLogline;
    Button back;
    TextView title;
    EditText subjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_logline);

        addLogline = findViewById(R.id.addLogline);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        subjectName = findViewById(R.id.subjectName);
        User user = Constants.users.get(Constants.currentUserIndex);
        subjectName.setTextColor(user.color);
    }

    public void back(View view) {
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }

    public void addLogline(View view) {
        Constants.users.get(Constants.currentUserIndex).logLines.add(new LogLine(new MyTime(Constants.getHour(), Constants.getMinute(), Constants.getSecond()), new Subject(subjectName.getText().toString())));
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
}
