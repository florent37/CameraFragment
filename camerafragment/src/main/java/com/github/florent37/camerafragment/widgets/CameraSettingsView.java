package com.github.florent37.camerafragment.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.github.florent37.camerafragment.R;

/*
 * Created by memfis on 8/23/16.
 */
public class CameraSettingsView extends ImageButton {

    public CameraSettingsView(Context context) {
        this(context, null);
    }

    public CameraSettingsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraSettingsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CameraSettingsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.circle_frame_background_dark);
        setImageResource(R.drawable.ic_settings_white_24dp);
        setScaleType(ScaleType.CENTER);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (Build.VERSION.SDK_INT > 10) {
            if (enabled) {
                setAlpha(1f);
            } else {
                setAlpha(0.5f);
            }
        }
    }
}
