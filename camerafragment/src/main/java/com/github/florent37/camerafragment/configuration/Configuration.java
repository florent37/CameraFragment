package com.github.florent37.camerafragment.configuration;

import android.support.annotation.IntDef;
import android.support.annotation.IntRange;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * Created by memfis on 7/6/16.
 * Updated by Florent37
 */
public final class Configuration implements Serializable {

    public static final int MEDIA_QUALITY_AUTO = 10;
    public static final int MEDIA_QUALITY_LOWEST = 15;
    public static final int MEDIA_QUALITY_LOW = 11;
    public static final int MEDIA_QUALITY_MEDIUM = 12;
    public static final int MEDIA_QUALITY_HIGH = 13;
    public static final int MEDIA_QUALITY_HIGHEST = 14;

    public static final int MEDIA_ACTION_VIDEO = 100;
    public static final int MEDIA_ACTION_PHOTO = 101;
    public static final int MEDIA_ACTION_UNSPECIFIED = 102;

    public static final int CAMERA_FACE_FRONT = 0x6;
    public static final int CAMERA_FACE_REAR = 0x7;

    public static final int SENSOR_POSITION_UP = 90;
    public static final int SENSOR_POSITION_UP_SIDE_DOWN = 270;
    public static final int SENSOR_POSITION_LEFT = 0;
    public static final int SENSOR_POSITION_RIGHT = 180;
    public static final int SENSOR_POSITION_UNSPECIFIED = -1;

    public static final int DISPLAY_ROTATION_0 = 0;
    public static final int DISPLAY_ROTATION_90 = 90;
    public static final int DISPLAY_ROTATION_180 = 180;
    public static final int DISPLAY_ROTATION_270 = 270;

    public static final int ORIENTATION_PORTRAIT = 0x111;
    public static final int ORIENTATION_LANDSCAPE = 0x222;

    public static final int FLASH_MODE_ON = 1;
    public static final int FLASH_MODE_OFF = 2;
    public static final int FLASH_MODE_AUTO = 3;

    @IntDef({MEDIA_QUALITY_AUTO, MEDIA_QUALITY_LOWEST, MEDIA_QUALITY_LOW, MEDIA_QUALITY_MEDIUM, MEDIA_QUALITY_HIGH, MEDIA_QUALITY_HIGHEST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQuality {
    }

    @IntDef({MEDIA_ACTION_VIDEO, MEDIA_ACTION_PHOTO, MEDIA_ACTION_UNSPECIFIED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaAction {
    }

    @IntDef({FLASH_MODE_ON, FLASH_MODE_OFF, FLASH_MODE_AUTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FlashMode {
    }

    @IntDef({CAMERA_FACE_FRONT, CAMERA_FACE_REAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CameraFace {
    }

    @IntDef({SENSOR_POSITION_UP, SENSOR_POSITION_UP_SIDE_DOWN, SENSOR_POSITION_LEFT, SENSOR_POSITION_RIGHT, SENSOR_POSITION_UNSPECIFIED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SensorPosition {
    }

    @IntDef({DISPLAY_ROTATION_0, DISPLAY_ROTATION_90, DISPLAY_ROTATION_180, DISPLAY_ROTATION_270})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayRotation {
    }

    @IntDef({ORIENTATION_PORTRAIT, ORIENTATION_LANDSCAPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DeviceDefaultOrientation {
    }

    @MediaAction
    private int mediaAction = -1;

    @MediaQuality
    private int mediaQuality = -1;

    @CameraFace
    private int cameraFace = -1;

    private int videoDuration = -1;

    private long videoFileSize = -1;

    private int minimumVideoDuration = -1;

    @FlashMode
    private int flashMode = FLASH_MODE_AUTO;

    public static class Builder {

        private Configuration configuration;

        public Builder() {
            configuration = new Configuration();
        }

        public Builder setMediaAction(@MediaAction int mediaAction) {
            configuration.mediaAction = mediaAction;
            return this;
        }

        public Builder setMediaQuality(@MediaQuality int mediaQuality) {
            configuration.mediaQuality = mediaQuality;
            return this;
        }

        /**
         * @param videoDurationInMilliseconds - video duration in milliseconds
         * @return
         */
        public Builder setVideoDuration(@IntRange(from = 1000, to = Integer.MAX_VALUE) int videoDurationInMilliseconds) {
            configuration.videoDuration = videoDurationInMilliseconds;
            return this;
        }

        /**
         * @param minimumVideoDurationInMilliseconds - minimum video duration in milliseconds, used only in video mode
         *                                           for auto quality.
         * @return
         */
        public Builder setMinimumVideoDuration(@IntRange(from = 1000, to = Integer.MAX_VALUE) int minimumVideoDurationInMilliseconds) {
            configuration.minimumVideoDuration = minimumVideoDurationInMilliseconds;
            return this;
        }

        /**
         * @param videoSizeInBytes - file size in bytes
         * @return
         */
        public Builder setVideoFileSize(@IntRange(from = 1048576, to = Long.MAX_VALUE) long videoSizeInBytes) {
            configuration.videoFileSize = videoSizeInBytes;
            return this;
        }

        public Builder setFlashMode(@FlashMode int flashMode) {
            configuration.flashMode = flashMode;
            return this;
        }

        public Builder setCamera(@CameraFace int camera){
            configuration.cameraFace = camera;
            return this;
        }

        public Configuration build() throws IllegalArgumentException {
            if (configuration.mediaQuality == MEDIA_QUALITY_AUTO && configuration.minimumVideoDuration < 0) {
                throw new IllegalStateException("Please provide minimum video duration in milliseconds to use auto quality.");
            }

            return configuration;
        }

    }

    @MediaAction
    public int getMediaAction() {
        return mediaAction;
    }

    public int getMediaQuality() {
        return mediaQuality;
    }

    @CameraFace
    public int getCameraFace() {
        return cameraFace;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public long getVideoFileSize() {
        return videoFileSize;
    }

    public int getMinimumVideoDuration() {
        return minimumVideoDuration;
    }

    @FlashMode
    public int getFlashMode() {
        return flashMode;
    }
}
