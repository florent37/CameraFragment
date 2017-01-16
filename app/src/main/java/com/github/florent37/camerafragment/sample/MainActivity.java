package com.github.florent37.camerafragment.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.PreviewActivity;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentControlsListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentVideoRecordTextListener;
import com.github.florent37.camerafragment.widgets.CameraSettingsView;
import com.github.florent37.camerafragment.widgets.CameraSwitchView;
import com.github.florent37.camerafragment.widgets.FlashSwitchView;
import com.github.florent37.camerafragment.widgets.MediaActionSwitchView;
import com.github.florent37.camerafragment.widgets.RecordButton;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSIONS = 931;
    private static final int REQUEST_PREVIEW_CODE = 1001;

    public static final String FRAGMENT_TAG = "camera";

    @Bind(R.id.settings_view) CameraSettingsView settingsView;
    @Bind(R.id.flash_switch_view) FlashSwitchView flashSwitchView;
    @Bind(R.id.front_back_camera_switcher) CameraSwitchView cameraSwitchView;
    @Bind(R.id.record_button) RecordButton recordButton;
    @Bind(R.id.photo_video_camera_switcher) MediaActionSwitchView mediaActionSwitchView;

    @Bind(R.id.record_duration_text) TextView recordDurationText;
    @Bind(R.id.record_size_mb_text) TextView recordSizeText;

    @Bind(R.id.cameraLayout) View cameraLayout;
    @Bind(R.id.addCameraButton) View addCameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);

        recordButton.setVisibility(View.GONE);
        settingsView.setVisibility(View.GONE);
        flashSwitchView.setVisibility(View.GONE);
        cameraSwitchView.setVisibility(View.GONE);
        mediaActionSwitchView.setVisibility(View.GONE);

        setupViewListeners();
    }

    @OnClick(R.id.take_photo)
    public void takePhotoClicked(){
        final CameraFragment cameraFragment = getCameraFragment();
        if (cameraFragment != null) {
            cameraFragment.takePhotoOrCaptureVideo();
        }
    }

    private void setupViewListeners() {
        flashSwitchView.setFlashSwitchListener(new FlashSwitchView.FlashModeSwitchListener() {
            @Override
            public void toggleFlashMode() {
                final CameraFragment cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.toggleFlashMode();
                }
            }
        });

        cameraSwitchView.setOnCameraTypeChangeListener(new CameraSwitchView.OnCameraTypeChangeListener() {
            @Override
            public void switchCameraType() {
                final CameraFragment cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.switchCameraType();
                }
            }
        });

        recordButton.setRecordButtonListener(new RecordButton.RecordButtonListener() {
            @Override
            public void onRecordButtonClicked() {
                final CameraFragment cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.takePhotoOrCaptureVideo();
                }
            }
        });

        mediaActionSwitchView.setOnMediaActionStateChangeListener(new MediaActionSwitchView.OnMediaActionStateChangeListener() {
            @Override
            public void switchAction() {
                final CameraFragment cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.switchAction();
                }
            }
        });

        settingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CameraFragment cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.openSettingDialog();
                }
            }
        });
    }

    @OnClick(R.id.addCameraButton)
    public void onAddCameraClicked(){
        if (Build.VERSION.SDK_INT > 15) {
            final String[] permissions = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};

            final List<String> permissionsToRequest = new ArrayList<>();
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission);
                }
            }
            if (!permissionsToRequest.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[permissionsToRequest.size()]), REQUEST_CAMERA_PERMISSIONS);
            } else addCamera();
        } else {
            addCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length != 0) {
            addCamera();
        }
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    public void addCamera() {
        addCameraButton.setVisibility(View.GONE);
        cameraLayout.setVisibility(View.VISIBLE);

        final CameraFragment cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, cameraFragment, FRAGMENT_TAG)
                .commit();

        if (cameraFragment != null) {

            cameraFragment.setResultListener(new CameraFragmentResultListener() {
                @Override
                public void onVideoRecorded(String filePath) {
                    Intent intent = PreviewActivity.newIntentVideo(MainActivity.this, filePath);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }

                @Override
                public void onPhotoTaken(byte[] bytes, String filePath) {
                    Intent intent = PreviewActivity.newIntentPhoto(MainActivity.this, filePath);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            });

            cameraFragment.setStateListener(new CameraFragmentStateListener() {

                @Override
                public void onCurrentCameraBack() {
                    cameraSwitchView.displayBackCamera();
                }

                @Override
                public void onCurrentCameraFront() {
                    cameraSwitchView.displayFrontCamera();
                }

                @Override
                public void onFlashAuto() {
                    flashSwitchView.displayFlashAuto();
                }

                @Override
                public void onFlashOn() {
                    flashSwitchView.displayFlashOn();
                }

                @Override
                public void onFlashOff() {
                    flashSwitchView.displayFlashOff();
                }

                @Override
                public void onCameraSetupForPhoto() {
                    mediaActionSwitchView.displayActionWillSwitchVideo();

                    recordButton.displayPhotoState();
                    flashSwitchView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCameraSetupForVideo() {
                    mediaActionSwitchView.displayActionWillSwitchPhoto();

                    recordButton.displayVideoRecordStateReady();
                    flashSwitchView.setVisibility(View.GONE);
                }

                @Override
                public void shouldRotateControls(int degrees) {
                    ViewCompat.setRotation(cameraSwitchView, degrees);
                    ViewCompat.setRotation(mediaActionSwitchView, degrees);
                    ViewCompat.setRotation(flashSwitchView, degrees);
                    ViewCompat.setRotation(recordDurationText, degrees);
                    ViewCompat.setRotation(recordSizeText, degrees);
                }

                @Override
                public void onRecordStateVideoReadyForRecord() {
                    recordButton.displayVideoRecordStateReady();
                }

                @Override
                public void onRecordStateVideoInProgress() {
                    recordButton.displayVideoRecordStateInProgress();
                }

                @Override
                public void onRecordStatePhoto() {
                    recordButton.displayPhotoState();
                }

                @Override
                public void onStopVideoRecord() {
                    recordSizeText.setVisibility(View.GONE);
                    //cameraSwitchView.setVisibility(View.VISIBLE);
                    settingsView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onStartVideoRecord(File outputFile) {
                }
            });

            cameraFragment.setControlsListener(new CameraFragmentControlsListener() {
                @Override
                public void lockControls() {
                    cameraSwitchView.setEnabled(false);
                    recordButton.setEnabled(false);
                    settingsView.setEnabled(false);
                    flashSwitchView.setEnabled(false);
                }

                @Override
                public void unLockControls() {
                    cameraSwitchView.setEnabled(true);
                    recordButton.setEnabled(true);
                    settingsView.setEnabled(true);
                    flashSwitchView.setEnabled(true);
                }

                @Override
                public void allowCameraSwitching(boolean allow) {
                    //cameraSwitchView.setVisibility(allow ? View.VISIBLE : View.GONE);
                }

                @Override
                public void allowRecord(boolean allow) {
                    recordButton.setEnabled(allow);
                }

                @Override
                public void setMediaActionSwitchVisible(boolean visible) {
                    //mediaActionSwitchView.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
                }
            });

            cameraFragment.setTextListener(new CameraFragmentVideoRecordTextListener() {
                @Override
                public void setRecordSizeText(long size, String text) {
                    recordSizeText.setText(text);
                }

                @Override
                public void setRecordSizeTextVisible(boolean visible) {
                    recordSizeText.setVisibility(visible ? View.VISIBLE : View.GONE);
                }

                @Override
                public void setRecordDurationText(String text) {
                    recordDurationText.setText(text);
                }

                @Override
                public void setRecordDurationTextVisible(boolean visible) {
                    recordDurationText.setVisibility(visible ? View.VISIBLE : View.GONE);
                }
            });


        }

        cameraSwitchView.displayBackCamera();
        flashSwitchView.displayFlashAuto();
        recordButton.displayPhotoState();
        mediaActionSwitchView.displayActionWillSwitchVideo();
    }

    private CameraFragment getCameraFragment() {
        return (CameraFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }
}
