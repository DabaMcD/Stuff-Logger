package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        name = findViewById(R.id.name);
        String[] nicknameHints = new String[] {
                "e.g. Bobby Joe",
                "e.g. Zigzag Dude",
                "e.g. Ricky",
                "Nickname goes here",
                "e.g. Sandy Sanders",
                "e.g. Carl",
                "Nick's my nickname, DUH",
                "Nickname, sweetie pie!",
                "___________ <-- nickname",
                "Nick's name, please"
        };
        name.setHint(nicknameHints[(int) (Math.random() * nicknameHints.length)]);
    }

    public void newUserClick(View target) {
        Constants.users.add(new User(name.getText().toString()));
        Constants.currentUserIndex = Constants.users.size() - 1;
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
}
