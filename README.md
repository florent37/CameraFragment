# CameraFragment

[![png](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/sample.png)](https://github.com/florent37/CameraFragment)

```java
//you can configure the fragment by the configuration builder
CameraFragment cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());

getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, cameraFragment, FRAGMENT_TAG)
                .commit();
```

## Actions

You can directly take a photo / video with
```java
cameraFragment.takePhotoOrCaptureVideo(callback);
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/take_photo.gif)](https://github.com/florent37/CameraFragment)

Flash can be enable / disabled ( `AUTO` / `OFF` / `ON` ) with

```java
cameraFragment.toggleFlashMode();
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/switch_flash.gif)](https://github.com/florent37/CameraFragment)

Camera Type can be modified ( `BACK` / `FRONT` ) with

```java
cameraFragment.switchCameraType();
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/switch_camera.gif)](https://github.com/florent37/CameraFragment)

Camera action ( `PHOTO` / `VIDEO` ) can be modified with

```java
cameraFragment.switchActionPhotoVideo();
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/switch_action.gif)](https://github.com/florent37/CameraFragment)

And you can change the captured photo / video size with

```java
cameraFragment.openSettingDialog();
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/settings.gif)](https://github.com/florent37/CameraFragment)

# Listeners

## Result

Get back the result of the camera record / photo in the `CameraFragmentResultListener`

```java
cameraFragment.setResultListener(new CameraFragmentResultListener() {
       @Override
       public void onVideoRecorded(byte[] bytes, String filePath) {
                //called when the video record is finished and saved

                startActivityForResult(PreviewActivity.newIntentVideo(MainActivity.this, filePath));
       }

       @Override
       public void onPhotoTaken(byte[] bytes, String filePath) {
                //called when the photo is taken and saved

                startActivity(PreviewActivity.newIntentPhoto(MainActivity.this, filePath));
       }
});
```

## Camera Listener

```java
cameraFragment.setStateListener(new CameraFragmentStateListener() {

    //when the current displayed camera is the back
    void onCurrentCameraBack();
    //when the current displayed camera is the front
    void onCurrentCameraFront();

    //when the flash is at mode auto
    void onFlashAuto();
    //when the flash is at on
    void onFlashOn();
    //when the flash is off
    void onFlashOff();

    //if the camera is ready to take a photo
    void onCameraSetupForPhoto();
    //if the camera is ready to take a video
    void onCameraSetupForVideo();

    //when the camera state is "ready to record a video"
    void onRecordStateVideoReadyForRecord();
    //when the camera state is "recording a video"
    void onRecordStateVideoInProgress();
    //when the camera state is "ready to take a photo"
    void onRecordStatePhoto();

    //after the rotation of the screen / camera
    void shouldRotateControls(int degrees);

    void onStartVideoRecord(File outputFile);
    void onStopVideoRecord();
});
```

## Text

CameraFragment can ping you with the current record duration with `CameraFragmentTextListener`

# Widgets

CameraFragment comes with some default views

`RecordButton`, `MediaActionSwitchView`, `FlashSwitchView`, `CameraSwitchView`, `CameraSettingsView`

[![png](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/buttons.png)](https://github.com/florent37/CameraFragment)
