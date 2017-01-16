package com.github.florent37.camerafragment.internal.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Flash {
    public static final int FLASH_ON = 0;
    public static final int FLASH_OFF = 1;
    public static final int FLASH_AUTO = 2;

    @IntDef({FLASH_ON, FLASH_OFF, FLASH_AUTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FlashMode {
    }
}
