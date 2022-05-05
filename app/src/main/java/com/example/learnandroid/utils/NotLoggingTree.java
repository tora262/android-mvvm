package com.example.learnandroid.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import timber.log.Timber;

/**
 * @author hieutt (tora262)
 */
public class NotLoggingTree extends Timber.Tree {
    @Override
    protected void log(int priority, @Nullable String tag, @NonNull String message, @Nullable Throwable t) {

    }
}
