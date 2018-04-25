# CameraFragment

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CameraFragment-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5089)
[![CircleCI](https://circleci.com/gh/florent37/CameraFragment/tree/master.svg?style=svg)](https://circleci.com/gh/florent37/CameraFragment/tree/master)

A simple easy-to-integrate Camera Fragment for Android

CameraFragment preview directly the camera view, and provides a easy API to capture or manage the device

You can setup your own layout and control the camera using CameraFragment

<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

# CameraKit

This library works, but on some devices... errors happens, I don't have time to maintain compatibility with all device
A community group was created, they created CameraKit, don't hesitate to try it !

https://github.com/CameraKit/camerakit-android

# Setup

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
cameraFragment.takePhotoOrCaptureVideo(callback, directoryPath, fileName);
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/take_photo.gif)](https://github.com/florent37/CameraFragment)

Flash can be enable / disabled ( `AUTO` / `OFF` / `ON` ) with

```java
cameraFragment.toggleFlashMode();
```

[![gif](https://raw.githubusercontent.com/florent37/CameraFragment/master/media/switch_flash.gif)](https://github.com/florent37/CameraFragment)

Camera Type can be modified ( `BACK` / `FRONT` ) with

```java
cameraFragment.switchCameraTypeFrontBack();
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

# Download

<a href='https://ko-fi.com/A160LCC' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

In your module [![Download](https://api.bintray.com/packages/florent37/maven/CameraFragment/images/download.svg)](https://bintray.com/florent37/maven/CameraFragment/_latestVersion)
```groovy
compile 'com.github.florent37:camerafragment:1.0.10'
```

# Community
 
Forked from [https://github.com/memfis19/Annca](https://github.com/memfis19/Annca)

This library works, but on some devices... errors happens, I don't have time to maintain compatibility with all device
A community group was created, they created CameraKit, don't hesitate to try it !

https://github.com/CameraKit/camerakit-android

# Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

Blog : [http://www.tutos-android-france.com/](http://www.www.tutos-android-france.com/)


<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/florentchampigny">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>

# License

    Copyright 2017 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
