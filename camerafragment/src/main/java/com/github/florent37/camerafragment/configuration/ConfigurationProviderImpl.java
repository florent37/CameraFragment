package com.github.florent37.camerafragment.configuration;

/*
 * Created by florentchampigny on 12/01/2017.
 */

public class ConfigurationProviderImpl implements ConfigurationProvider {

    protected long videoFileSize = -1;
    protected int videoDuration = -1;
    protected int minimumVideoDuration = -1;

    @Configuration.MediaAction
    protected int mediaAction = Configuration.MEDIA_ACTION_UNSPECIFIED;

    @Configuration.MediaQuality
    protected int mediaQuality = Configuration.MEDIA_QUALITY_MEDIUM;

    @Configuration.MediaQuality
    protected int passedMediaQuality = Configuration.MEDIA_QUALITY_MEDIUM;

    @Configuration.FlashMode
    protected int flashMode = Configuration.FLASH_MODE_AUTO;

    @Configuration.CameraFace
    protected int cameraFace = Configuration.CAMERA_FACE_REAR;

    @Configuration.SensorPosition
    protected int sensorPosition = Configuration.SENSOR_POSITION_UNSPECIFIED;

    @Configuration.DeviceDefaultOrientation
    protected int deviceDefaultOrientation;

    private int degrees = -1;

    @Override
    public int getMediaAction() {
        return mediaAction;
    }

    @Override
    public int getMediaQuality() {
        return mediaQuality;
    }

    @Override
    public int getVideoDuration() {
        return videoDuration;
    }

    @Override
    public long getVideoFileSize() {
        return videoFileSize;
    }

    @Override
    public int getMinimumVideoDuration() {
        return minimumVideoDuration / 1000;
    }

    @Override
    public final int getSensorPosition() {
        return sensorPosition;
    }

    @Override
    public final int getDegrees() {
        return degrees;
    }

    @Override
    public int getFlashMode() {
        return flashMode;
    }

    @Override
    public void setMediaQuality(int mediaQuality) {
        this.mediaQuality = mediaQuality;
    }

    @Override
    public void setPassedMediaQuality(int mediaQuality) {
        this.passedMediaQuality = mediaQuality;
    }

    @Override
    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    @Override
    public void setVideoFileSize(long videoFileSize) {
        this.videoFileSize = videoFileSize;
    }

    @Override
    public void setMinimumVideoDuration(int minimumVideoDuration) {
        this.minimumVideoDuration = minimumVideoDuration;
    }

    @Override
    public void setFlashMode(int flashMode) {
        this.flashMode = flashMode;
    }

    public void setMediaAction(@Configuration.MediaAction int mediaAction) {
        this.mediaAction = mediaAction;
    }

    public int getPassedMediaQuality() {
        return passedMediaQuality;
    }

    @Configuration.CameraFace
    public int getCameraFace() {
        return cameraFace;
    }

    public void setCameraFace(@Configuration.CameraFace int cameraFace) {
        this.cameraFace = cameraFace;
    }

    @Override
    public void setupWithAnnaConfiguration(Configuration configuration) {
        if (configuration != null) {

            final int mediaAction = configuration.getMediaAction();
            if (mediaAction != -1) {
                switch (mediaAction) {
                    case Configuration.MEDIA_ACTION_PHOTO:
                        setMediaAction(Configuration.MEDIA_ACTION_PHOTO);
                        break;
                    case Configuration.MEDIA_ACTION_VIDEO:
                        setMediaAction(Configuration.MEDIA_ACTION_VIDEO);
                        break;
                    default:
                        setMediaAction(Configuration.MEDIA_ACTION_UNSPECIFIED);
                        break;
                }
            }

            final int mediaQuality = configuration.getMediaQuality();
            if (mediaQuality != -1) {
                switch (mediaQuality) {
                    case Configuration.MEDIA_QUALITY_AUTO:
                        setMediaQuality(Configuration.MEDIA_QUALITY_AUTO);
                        break;
                    case Configuration.MEDIA_QUALITY_HIGHEST:
                        setMediaQuality(Configuration.MEDIA_QUALITY_HIGHEST);
                        break;
                    case Configuration.MEDIA_QUALITY_HIGH:
                        setMediaQuality(Configuration.MEDIA_QUALITY_HIGH);
                        break;
                    case Configuration.MEDIA_QUALITY_MEDIUM:
                        setMediaQuality(Configuration.MEDIA_QUALITY_MEDIUM);
                        break;
                    case Configuration.MEDIA_QUALITY_LOW:
                        setMediaQuality(Configuration.MEDIA_QUALITY_LOW);
                        break;
                    case Configuration.MEDIA_QUALITY_LOWEST:
                        setMediaQuality(Configuration.MEDIA_QUALITY_LOWEST);
                        break;
                    default:
                        setMediaQuality(Configuration.MEDIA_QUALITY_MEDIUM);
                        break;
                }
                setPassedMediaQuality(getMediaQuality());
            }

            final int videoDuration = configuration.getVideoDuration();
            if (videoDuration != -1) {
                setVideoDuration(videoDuration);
            }

            final int cameraFace = configuration.getCameraFace();
            if (cameraFace != -1) {
                setCameraFace(cameraFace);
            }

            final long videoFileSize = configuration.getVideoFileSize();
            if (videoFileSize != -1) {
                setVideoFileSize(videoFileSize);
            }

            final int minimumVideoDuration = configuration.getMinimumVideoDuration();
            if (minimumVideoDuration != -1) {
                setMinimumVideoDuration(minimumVideoDuration);
            }

            final int flashMode = configuration.getFlashMode();
            if (flashMode != -1)
                switch (flashMode) {
                    case Configuration.FLASH_MODE_AUTO:
                        setFlashMode(Configuration.FLASH_MODE_AUTO);
                        break;
                    case Configuration.FLASH_MODE_ON:
                        setFlashMode(Configuration.FLASH_MODE_ON);
                        break;
                    case Configuration.FLASH_MODE_OFF:
                        setFlashMode(Configuration.FLASH_MODE_OFF);
                        break;
                    default:
                        setFlashMode(Configuration.FLASH_MODE_AUTO);
                        break;
                }
        }
    }

    public void setSensorPosition(int sensorPosition) {
        this.sensorPosition = sensorPosition;
    }

    public int getDeviceDefaultOrientation() {
        return deviceDefaultOrientation;
    }

    public void setDeviceDefaultOrientation(int deviceDefaultOrientation) {
        this.deviceDefaultOrientation = deviceDefaultOrientation;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
