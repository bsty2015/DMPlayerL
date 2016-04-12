package com.lvandroid.bsty.dmplayer.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lvandroid.bsty.dmplayer.R;
import com.lvandroid.bsty.dmplayer.phonemidea.DMPlayerUtility;
import com.lvandroid.bsty.dmplayer.uicomponent.Slider;

/**
 * Created by bsty on 4/12/16.
 */
public class DMPlayerBaseActivity extends AppCompatActivity implements View.OnClickListener, Slider.OnValueChangedListener, com.lvandroid.bsty.dmplayer.manager.NotificationManager.NotificationCenterDelegate {
    private static final String TAG = "ActivityDMPlayerBase";
    private Context context;
    private SharedPreferences sharedPreferences;
    private int theme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //set your theme first
        context = DMPlayerBaseActivity.this;
        theme();

        //set your Layout view
        super.onCreate(savedInstanceState);
        setContentView(R.layout);
    }

    //Catch theme changed from settings
    private void theme() {
        sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        theme = sharedPreferences.getInt("THEME", 0);
        DMPlayerUtility.settingTheme(context, theme);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void didReceivedNotification(int id, Object... args) {

    }

    @Override
    public void newSongLoaded(Object... args) {

    }

    @Override
    public void onValueChanged(int value) {

    }
}
