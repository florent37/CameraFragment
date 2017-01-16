package com.github.florent37.camerafragment.internal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;

/*
 * Created by memfis on 7/18/16.
 */
public final class ImageLoader {

    private Context context;
    private String url;

    private ImageLoader(Context context) {
        this.context = context;
    }

    public static class Builder {

        private ImageLoader imageLoader;

        public Builder(@NonNull Context context) {
            imageLoader = new ImageLoader(context);
        }

        public Builder load(String url) {
            imageLoader.url = url;
            return this;
        }

        public ImageLoader build() {
            return imageLoader;
        }
    }

    public void into(final ImageView target) {
        ViewTreeObserver viewTreeObserver = target.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                target.getViewTreeObserver().removeOnPreDrawListener(this);

                new ImageLoaderThread(target, url).start();

                return true;
            }
        });
    }

    private class ImageLoaderThread extends Thread {

        private ImageView target;
        private String url;
        private Handler mainHandler = new Handler(Looper.getMainLooper());

        private ImageLoaderThread(ImageView target, String url) {
            this.target = target;
            this.url = url;
        }

        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();

            int imageViewHeight;
            int imageViewWidth;

            if (Build.VERSION.SDK_INT < 13) {
                imageViewHeight = display.getHeight();
                imageViewWidth = display.getWidth();
            } else {
                Point size = new Point();
                display.getSize(size);
                imageViewHeight = size.y;
                imageViewWidth = size.x;
            }

//            int imageViewHeight = target.getMeasuredHeight();
//            int imageViewWidth = target.getMeasuredWidth();

            Bitmap decodedBitmap = decodeSampledBitmapFromResource(url, imageViewWidth, imageViewHeight);
            final Bitmap resultBitmap = rotateBitmap(decodedBitmap, getExifOrientation());

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    target.setImageBitmap(resultBitmap);
                }
            });
        }

        private Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_NORMAL:
                    return bitmap;
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.setScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_TRANSPOSE:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_TRANSVERSE:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(-90);
                    break;
                default:
                    return bitmap;
            }

            try {
                Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return bmRotated;
            } catch (OutOfMemoryError ignore) {
                return null;
            }
        }

        private int getExifOrientation() {
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(url);
            } catch (IOException ignore) {
            }
            return exif == null ? ExifInterface.ORIENTATION_UNDEFINED :
                    exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        }

        private Bitmap decodeSampledBitmapFromResource(String url,
                                                       int requestedWidth, int requestedHeight) {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(url, options);

            options.inSampleSize = calculateInSampleSize(options, requestedWidth, requestedHeight);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(url, options);
        }

        private int calculateInSampleSize(BitmapFactory.Options options,
                                          int requestedWidth, int requestedHeight) {

            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > requestedHeight || width > requestedWidth) {

                final int halfHeight = height / inSampleSize;
                final int halfWidth = width / inSampleSize;

                while ((halfHeight / inSampleSize) > requestedHeight
                        && (halfWidth / inSampleSize) > requestedWidth) {
                    inSampleSize *= 2;
                }
            }
            return inSampleSize;
        }
    }

}

