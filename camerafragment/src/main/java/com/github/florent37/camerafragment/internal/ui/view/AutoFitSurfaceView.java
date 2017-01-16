package com.github.florent37.camerafragment.internal.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*
 * Created by memfis on 7/6/16.
 */
@SuppressWarnings("deprecation")
@SuppressLint("ViewConstructor")
public class AutoFitSurfaceView extends SurfaceView {

    private final static String TAG = "AutoFitSurfaceView";

    private final SurfaceHolder surfaceHolder;

    private int ratioWidth;
    private int ratioHeight;

    public AutoFitSurfaceView(@NonNull Context context, SurfaceHolder.Callback callback) {
        super(context);

        this.surfaceHolder = getHolder();

        this.surfaceHolder.addCallback(callback);
        this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    /*
     * Sets the aspect ratio for this view. The size of the view will be measured based on the ratio
     * calculated fromList the parameters. Note that the actual sizes of parameters don't matter, that
     * is, calling setAspectRatio(2, 3) and setAspectRatio(4, 6) make the same result.
     *
     * @param width  Relative horizontal size
     * @param height Relative vertical size
     */
    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        ratioWidth = width;
        ratioHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        if (0 == ratioWidth || 0 == ratioHeight) {
            setMeasuredDimension(width, height);
        } else {
            if (width < height * (ratioWidth / (float) ratioHeight)) {
                setMeasuredDimension(width, (int) (width * (ratioWidth / (float) ratioHeight)));
            } else {
                setMeasuredDimension((int) (height * (ratioWidth / (float) ratioHeight)), height);
            }
        }
    }
}
