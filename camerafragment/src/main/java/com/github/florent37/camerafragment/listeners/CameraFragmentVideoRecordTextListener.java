package com.github.florent37.camerafragment.listeners;

/*
 * Created by florentchampigny on 13/01/2017.
 */

public interface CameraFragmentVideoRecordTextListener {
    void setRecordSizeText(long size, String text);
    void setRecordSizeTextVisible(boolean visible);

    void setRecordDurationText(String text);
    void setRecordDurationTextVisible(boolean visible);
}
