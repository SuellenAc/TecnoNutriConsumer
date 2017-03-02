package com.example.suellencolangelo.tecnonutriconsumer.base;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.example.suellencolangelo.tecnonutriconsumer.R;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public class DefaultApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        //Font padrao do app
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
