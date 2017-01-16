package com.github.florent37.camerafragment.internal.manager.listener;

import java.io.File;

import com.github.florent37.camerafragment.internal.utils.Size;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;

/*
 * Created by memfis on 8/14/16.
 */
public interface CameraVideoListener {
    void onVideoRecordStarted(Size videoSize);

    void onVideoRecordStopped(File videoFile, CameraFragmentResultListener callback);

    void onVideoRecordError();
}
