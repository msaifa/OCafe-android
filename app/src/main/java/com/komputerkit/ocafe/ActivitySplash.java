package com.komputerkit.ocafe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setSplash();
    }

    private void setSplash() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(ActivitySplash.this, ActivityLogin.class);
                startActivity(i);

                finish();
            }
        }, 2000);
    }
}
