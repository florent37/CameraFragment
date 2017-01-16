package com.github.florent37.camerafragment.internal.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Record {

    public static final int TAKE_PHOTO_STATE = 0;
    public static final int READY_FOR_RECORD_STATE = 1;
    public static final int RECORD_IN_PROGRESS_STATE = 2;

    @IntDef({TAKE_PHOTO_STATE, READY_FOR_RECORD_STATE, RECORD_IN_PROGRESS_STATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RecordState {
    }

}
