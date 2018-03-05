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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        createUser = findViewById(R.id.createUser);
        name = findViewById(R.id.name);

        String[] nicknames = new String[] {"e.g. Bobby Joe", "e.g. Zigzag Dude", "e.g. Ricky", "Nickname goes here", "e.g. Sandy Sanders", "e.g. Carl", "Nick's my nickname, DUH", "Nickname, sweetie pie!", "Bob's a nickname? Then Nick's a bobname!", "Nick's name, please", "!Nikcnaem ,pleaes"};
        name.setHint(nicknames[(int) (Math.random() * nicknames.length)]);
    }

    public void newUserClick(View target) {
        Constants.users[Constants.users.length] = new User(name.getText().toString());
        Intent myIntent = new Intent(this, LogActivity.class);
        startActivity(myIntent);
    }
}
