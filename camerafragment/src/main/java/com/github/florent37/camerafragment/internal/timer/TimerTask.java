package com.github.florent37.camerafragment.internal.timer;

import com.github.florent37.camerafragment.internal.utils.DateTimeUtils;

/*
 * Created by florentchampigny on 13/01/2017.
 */

public class TimerTask extends TimerTaskBase implements Runnable {

    public TimerTask(TimerTaskBase.Callback callback) {
        super(callback);
    }

    @Override
    public void run() {
        recordingTimeSeconds++;

        if (recordingTimeSeconds == 60) {
            recordingTimeSeconds = 0;
            recordingTimeMinutes++;
        }
        if(callback != null) {
            callback.setText(
                    String.format("%02d:%02d", recordingTimeMinutes, recordingTimeSeconds));
        }
        if (alive) handler.postDelayed(this, DateTimeUtils.SECOND);
    }

    public void start() {
        alive = true;
        recordingTimeMinutes = 0;
        recordingTimeSeconds = 0;
        if(callback != null) {
            callback.setText(
                    String.format("%02d:%02d", recordingTimeMinutes, recordingTimeSeconds));
            callback.setTextVisible(true);
        }
        handler.postDelayed(this, DateTimeUtils.SECOND);
    }

    public void stop() {
        if(callback != null){
            callback.setTextVisible(false);
        }
        alive = false;
    }
}
