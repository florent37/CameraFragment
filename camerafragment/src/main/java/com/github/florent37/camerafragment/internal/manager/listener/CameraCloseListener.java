package com.github.florent37.camerafragment.internal.manager.listener;

/*
 * Created by memfis on 8/14/16.
 */
public interface CameraCloseListener<CameraId> {
    void onCameraClosed(CameraId closedCameraId);
}
