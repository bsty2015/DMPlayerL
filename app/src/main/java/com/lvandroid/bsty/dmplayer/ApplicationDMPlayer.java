package com.lvandroid.bsty.dmplayer;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;

import com.lvandroid.bsty.dmplayer.dbhandler.DMPlayerDBHelper;
import com.lvandroid.bsty.dmplayer.models.SongDetail;

import java.util.ArrayList;

/**
 * Created by bsty on 4/11/16.
 */
public class ApplicationDMPlayer extends Application {
    public ArrayList<SongDetail> songList = new ArrayList<>();
    public static Context applicationContext = null;
    public static volatile Handler applicationHandler = null;
    public static Point displaySize = new Point();
    public static float density = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());

        /**
         * Data base initialize
         */
        initializeDB();

        /**
         * Display Density Calculation so that Applcation not problem with all resolution.
         */
        checkDisplaySize();
        density = applicationContext.getResources().getDisplayMetrics().density;

        /**
         * Imageloader initialize
         */
        initImageLoader(applicationContext);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * Initialize Image Loader.
     *
     * @param context
     */
    public static void initImageLoader(Context context) {

    }

    public static int dp(float value) {
        return (int) Math.ceil(density * value);
    }

    public static void checkDisplaySize() {

    }

    private void initializeDB() {

    }

    private void closeDB() {

    }

    public DMPlayerDBHelper DB_HELPER;
}
