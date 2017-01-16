package com.github.florent37.camerafragment.internal.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Camera {

    public static final int CAMERA_TYPE_FRONT = 0;
    public static final int CAMERA_TYPE_REAR = 1;

    @IntDef({CAMERA_TYPE_FRONT, CAMERA_TYPE_REAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CameraType {
    }
}
