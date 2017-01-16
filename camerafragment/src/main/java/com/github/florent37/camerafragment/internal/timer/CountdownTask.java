package com.github.florent37.camerafragment.internal.timer;

import android.graphics.Color;

import java.util.concurrent.TimeUnit;

import com.github.florent37.camerafragment.internal.utils.DateTimeUtils;

/*
 * Created by florentchampigny on 13/01/2017.
 */

public class CountdownTask extends TimerTaskBase implements Runnable {

    private int maxDurationMilliseconds = 0;

    public CountdownTask(Callback callback, int maxDurationMilliseconds) {
        super(callback);
        this.maxDurationMilliseconds = maxDurationMilliseconds;
    }

    @Override
    public void run() {

        recordingTimeSeconds--;

        int millis = (int) recordingTimeSeconds * 1000;

        if (callback != null) {
            callback.setText(
                    String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(millis),
                            TimeUnit.MILLISECONDS.toSeconds(millis) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                    ));
        }

        if (alive && recordingTimeSeconds > 0) handler.postDelayed(this, DateTimeUtils.SECOND);
    }

    @Override
    public void stop() {
        if (callback != null) {
            callback.setTextVisible(false);
        }
        alive = false;
    }

    @Override
    public void start() {
        alive = true;
        recordingTimeSeconds = maxDurationMilliseconds / 1000;
        if (callback != null) {
            callback.setText(
                    String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(maxDurationMilliseconds),
                            TimeUnit.MILLISECONDS.toSeconds(maxDurationMilliseconds) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(maxDurationMilliseconds))
                    ));
            callback.setTextVisible(true);
        }
        handler.postDelayed(this, DateTimeUtils.SECOND);
    }
}
