package com.lvandroid.bsty.dmplayer.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.lvandroid.bsty.dmplayer.ApplicationDMPlayer;
import com.lvandroid.bsty.dmplayer.models.SongDetail;

/**
 * Created by bsty on 4/12/16.
 */
public class FavoritePlayTableHelper {
    public static final String TABLENAME = "ResentPlay";

    public static final String ID = "_id";
    public static final String ALBUM_ID = "album_id";
    public static final String ARTIST = "artist";
    public static final String TITLE = "title";
    public static final String DISPLAY_NAME = "display_name";
    public static final String DURATION = "duration";
    public static final String PATH = "path";
    public static final String AUDIOPROGRESS = "audioProgress";
    public static final String AUDIOPROGRESSEC = "audioProgressSec";
    public static final String LastPlayTime = "lastPlayTime";
    public static final String ISFAVORITE = "isfavorite";

    private static DMPlayerDBHelper dbHelper = null;
    private static FavoritePlayTableHelper mInstance;
    private SQLiteDatabase sampleDB;

    public static synchronized FavoritePlayTableHelper getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FavoritePlayTableHelper(context);
        }
        return mInstance;
    }

    public Context context;

    public FavoritePlayTableHelper(Context context_) {
        this.context = context_;
        if (dbHelper == null) {
            dbHelper = ((ApplicationDMPlayer) context.getApplicationContext()).DB_HELPER;
        }
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }

    public void inserSong(SongDetail songDetail, int isFav) {
        try {
            sampleDB = dbHelper.getDB();
            sampleDB.beginTransaction();

            String sql = "Insert or Replace into " + TABLENAME + " values(?,?,?,?,?,?,?,?,?,?,?)";
            SQLiteStatement insert = sampleDB.compileStatement(sql);

            try {
                if (songDetail != null) {
                    insert.clearBindings();
                    insert.bindLong(1, songDetail.getId());
                    insert.bindLong(2, songDetail.getAlbum_id());
                    insert.bindString(3, songDetail.getArtist());
                    insert.bindString(4, songDetail.getTitle());
                    insert.bindString(5, songDetail.getDisplay_name());
                    insert.bindString(6, songDetail.getDuration());
                    insert.bindString(7, songDetail.getPath());
                    insert.bindString(8, songDetail.audioProgress + "");
                    insert.bindString(9, songDetail.audioProgressSec + "");
                    insert.bindString(10, System.currentTimeMillis() + "");
                    insert.bindLong(11, isFav);

                    insert.execute();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            sampleDB.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("XML:",e.toString());
        }finally {
            sampleDB.endTransaction();
        }
    }

    public Cursor getFavoriteSongList() {
        Cursor mCursor = null;

        try {
            String sqlQuery = "Select * from " + TABLENAME + " where " + ISFAVORITE + "=1";
            sampleDB = dbHelper.getDB();
            mCursor = sampleDB.rawQuery(sqlQuery, null);
        } catch (Exception e) {
            closeCursor(mCursor);
            e.printStackTrace();
        }
        return mCursor;
    }

    public boolean getIsFavorite(SongDetail songDetail) {
        Cursor mCursor = null;
        try {
            String sqlQuery = "select * from " + TABLENAME + " where " + ID + "=" + songDetail.getId() + " and " + ISFAVORITE + "=1";
            sampleDB = dbHelper.getDB();
            mCursor = sampleDB.rawQuery(sqlQuery, null);
            if (mCursor != null && mCursor.getCount() >= 1) {
                closeCursor(mCursor);
                return true;
            }
        } catch (Exception e) {
            closeCursor(mCursor);
            e.printStackTrace();
        }
        return false;
    }
}
