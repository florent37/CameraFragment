package com.github.florent37.camerafragment.internal.ui.model;

import android.media.CamcorderProfile;

import java.util.concurrent.TimeUnit;

import com.github.florent37.camerafragment.configuration.Configuration;

/*
 * Created by memfis on 12/1/16.
 */

public class VideoQualityOption implements CharSequence {

    private String title;

    @Configuration.MediaQuality
    private int mediaQuality;

    public VideoQualityOption(@Configuration.MediaQuality int mediaQuality, CamcorderProfile camcorderProfile, double videoDuration) {
        this.mediaQuality = mediaQuality;

        long minutes = TimeUnit.SECONDS.toMinutes((long) videoDuration);
        long seconds = ((long) videoDuration) - minutes * 60;

        if (mediaQuality == Configuration.MEDIA_QUALITY_AUTO) {
            title = "Auto " + ", (" + (minutes > 10 ? minutes : ("0" + minutes)) + ":" + (seconds > 10 ? seconds : ("0" + seconds)) + " min)";
        } else {
            title = String.valueOf(camcorderProfile.videoFrameWidth)
                    + " x " + String.valueOf(camcorderProfile.videoFrameHeight)
                    + (videoDuration <= 0 ? "" : ", (" + (minutes > 10 ? minutes : ("0" + minutes)) + ":" + (seconds > 10 ? seconds : ("0" + seconds)) + " min)");
        }
    }

    @Configuration.MediaQuality
    public int getMediaQuality() {
        return mediaQuality;
    }

    @Override
    public int length() {
        return title.length();
    }

    @Override
    public char charAt(int index) {
        return title.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return title.subSequence(start, end);
    }

    @Override
    public String toString() {
        return title;
    }
}