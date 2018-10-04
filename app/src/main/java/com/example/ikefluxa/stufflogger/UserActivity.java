package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    Button createUser;
    EditText name;
    UserTopBarView userTopBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Constants.correctScreenDims(this.getResources().getConfiguration().orientation);

        createUser = findViewById(R.id.createUser);
        name = findViewById(R.id.name);
        userTopBarView = findViewById(R.id.userTopBarView);
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

    public void newUserClick(View target) {
        Constants.users.add(0, new User(name.getText().toString()));

        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
}
