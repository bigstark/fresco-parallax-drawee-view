package com.bigstark.fresco.parallax;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by bigstark on 2017. 8. 18..
 */

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
