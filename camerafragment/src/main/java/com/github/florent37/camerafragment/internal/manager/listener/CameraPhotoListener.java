package com.github.florent37.camerafragment.internal.manager.listener;

import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;

import java.io.File;

/*
 * Created by memfis on 8/14/16.
 */
public interface CameraPhotoListener {
    void onPhotoTaken(byte[] bytes, File photoFile, CameraFragmentResultListener callback);

    void onPhotoTakeError();
}
