package com.github.florent37.camerafragment.internal.utils;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by memfis on 12/1/16.
 */

public class Size {

    private int width;
    private int height;

    public Size() {
        width = 0;
        height = 0;
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Size(android.util.Size size) {
        this.width = size.getWidth();
        this.height = size.getHeight();
    }

    @SuppressWarnings("deprecation")
    public Size(Camera.Size size) {
        this.width = size.width;
        this.height = size.height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static List<Size> fromList2(List<android.util.Size> sizes) {
        if (sizes == null) return null;
        List<Size> result = new ArrayList<>(sizes.size());

        for (android.util.Size size : sizes) {
            result.add(new Size(size));
        }

        return result;
    }

    @SuppressWarnings("deprecation")
    public static List<Size> fromList(List<Camera.Size> sizes) {
        if (sizes == null) return null;
        List<Size> result = new ArrayList<>(sizes.size());

        for (Camera.Size size : sizes) {
            result.add(new Size(size));
        }

        return result;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size[] fromArray2(android.util.Size[] sizes) {
        if (sizes == null) return null;
        Size[] result = new Size[sizes.length];

        for (int i = 0; i < sizes.length; ++i) {
            result[i] = new Size(sizes[i]);
        }

        return result;
    }

    @SuppressWarnings("deprecation")
    public static Size[] fromArray(Camera.Size[] sizes) {
        if (sizes == null) return null;
        Size[] result = new Size[sizes.length];

        for (int i = 0; i < sizes.length; ++i) {
            result[i] = new Size(sizes[i]);
        }

        return result;
    }
}
