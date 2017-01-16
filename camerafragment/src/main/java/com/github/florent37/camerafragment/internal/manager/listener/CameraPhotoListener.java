package com.github.florent37.camerafragment.internal.manager.listener;

import java.io.File;

/**
 * Created by memfis on 8/14/16.
 */
public interface CameraPhotoListener {
    void onPhotoTaken(byte[] bytes, File photoFile);

    void onPhotoTakeError();
}
