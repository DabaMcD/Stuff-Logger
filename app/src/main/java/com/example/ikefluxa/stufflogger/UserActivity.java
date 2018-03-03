package com.example.ikefluxa.stufflogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        String[] nicknames = new String[] {"e.g. Bobby Joe", "e.g. Zigzag Dude", "e.g. Ricky", "Nickname goes here", "e.g. Sandy Sanders", "e.g. Carl", "Nick's my nickname, DUH"};
        name.setHint(nicknames[(int) (Math.random() * nicknames.length)]);

        name.setWidth(Constants.SCREEN_WIDTH / 2);
        name.setX(Constants.SCREEN_WIDTH / 4);
        name.setHeight(Constants.SCREEN_HEIGHT / 10);
        name.setY(Constants.SCREEN_HEIGHT / 4);

        createUser.setWidth(Constants.SCREEN_WIDTH / 4);
        createUser.setX((float) ((Constants.SCREEN_WIDTH / 4) * 1.5));
        createUser.setHeight(Constants.SCREEN_HEIGHT / 7);
        createUser.setY((Constants.SCREEN_HEIGHT / 4) * 3);
    }
}
