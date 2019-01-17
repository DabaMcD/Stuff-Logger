package com.example.ikefluxa.stufflogger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ConfirmUserDeleteActivity extends AppCompatActivity {
    static int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_user_delete);

        Screen.correctDims(getResources());

        getWindow().setLayout((int) (Screen.width * 0.75), Screen.height + Screen.statusBarHeight - (Screen.width / 4));

        // Dim window
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(layoutParams);

        TextView text = findViewById(R.id.text);

        // Set text
        User user = Constants.users.get(userIndex);
        SpannableString spannableString = new SpannableString("Are you sure you want to delete " + user.name + "'s user from this app?");
        BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(user.color);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.DKGRAY);
        spannableString.setSpan(backgroundSpan, 32, 32 + user.name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(foregroundSpan, 32, 32 + user.name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(spannableString);
    }
    public void cancel(View v) {
        onBackPressed();
    }
    public void delete(View v) {
        Constants.deletedUser = Constants.users.get(userIndex);
        Constants.users.remove(userIndex);
        if(Constants.users.size() == 0) {
            deleteFile("User0.txt");
        }
        startActivity(new Intent(this, MainActivity.class));
    }
}
