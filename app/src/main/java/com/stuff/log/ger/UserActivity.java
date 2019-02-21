package com.stuff.log.ger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Screen.correctDims(getResources());

        name = findViewById(R.id.name);
        UserTopBarView userTopBarView = findViewById(R.id.userTopBarView);
        userTopBarView.draw();

        String[] nicknames = new String[] {
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
        name.setHint(nicknames[(int) (Math.random() * nicknames.length)]);
    }
    public void newUserClick(View v) {
        Globals.users.add(0, new User(name.getText().toString()));

        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
}
