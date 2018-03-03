package com.example.ikefluxa.stufflogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    EditText name;
    Button createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Define all the things in the xml code
        name = findViewById(R.id.name);
        createUser = findViewById(R.id.createUser);
    }
}
