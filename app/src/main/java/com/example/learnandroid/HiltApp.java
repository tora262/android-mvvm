package com.example.learnandroid;

import android.app.Application;

import com.example.learnandroid.utils.NotLoggingTree;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

/**
 * @author hieutt (tora262)
 */
@HiltAndroidApp
public class HiltApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!BuildConfig.DEBUG) {
            Timber.plant(new NotLoggingTree());
        } else {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
