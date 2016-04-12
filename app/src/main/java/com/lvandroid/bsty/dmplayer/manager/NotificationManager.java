package com.lvandroid.bsty.dmplayer.manager;

/**
 * Created by bsty on 4/12/16.
 */
public class NotificationManager {
    public interface NotificationCenterDelegate {
        void didReceivedNotification(int id, Object... args);

        void newSongLoaded(Object... args);
    }
}
