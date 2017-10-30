package com.dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Devesh Chaturvedi on 030-30-10-2017.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ((Button) findViewById(R.id.light_theme_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.dark_theme_btn)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.light_theme_btn:
                Utility.setTheme(getApplicationContext(), 1);
                Toast t = Toast.makeText(SettingsActivity.this, "Theme Changed", Toast.LENGTH_SHORT);
                t.show();
                recreateActivity();
                break;
            case R.id.dark_theme_btn:
                Utility.setTheme(getApplicationContext(), 2);
                t = Toast.makeText(SettingsActivity.this, "Theme Changed", Toast.LENGTH_SHORT);
                t.show();
                recreateActivity();
                break;
        }
    }

    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        recreate();
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
